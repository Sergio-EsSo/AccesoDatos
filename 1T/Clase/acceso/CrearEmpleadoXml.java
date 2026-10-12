import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class CrearEmpleadoXml {

    private static final int LONG_APELLIDO = 10;

    public static void main(String[] args) {

        File fichero = UtilidadesFicheros.devolverFichero();
        if (fichero == null) {
            System.out.println("No se ha seleccionado ningún fichero.");
            return;
        }

        try (RandomAccessFile file = new RandomAccessFile(fichero, "r")) {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document doc = implementation.createDocument(null, "Empleados", null);
            doc.setXmlVersion("1.0");

            char[] apellido = new char[LONG_APELLIDO];

            // La lectura avanza el puntero; no hace falta seek ni "posicion"
            while (file.getFilePointer() < file.length()) {
                int id = file.readInt();
                for (int i = 0; i < apellido.length; i++) {
                    apellido[i] = file.readChar();
                }
                String apellidos = new String(apellido);
                int dep = file.readInt();
                double salario = file.readDouble();

                if (id > 0) {
                    Element raiz = doc.createElement("Empleado");
                    doc.getDocumentElement().appendChild(raiz);
                    crearElemento("id", String.valueOf(id), raiz, doc);
                    crearElemento("apellido", apellidos.trim(), raiz, doc);
                    crearElemento("departamento", String.valueOf(dep), raiz, doc);
                    crearElemento("salario", String.valueOf(salario), raiz, doc);
                }
            }

            Source source = new DOMSource(doc);
            Result result = new StreamResult(new File("Empleados.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);

            System.out.println("Empleados.xml creado correctamente.");

        } catch (IOException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    static void crearElemento(String datoEmp, String valor, Element raiz, Document document) {
        Element elem = document.createElement(datoEmp);
        elem.appendChild(document.createTextNode(valor));
        raiz.appendChild(elem);
    }
}
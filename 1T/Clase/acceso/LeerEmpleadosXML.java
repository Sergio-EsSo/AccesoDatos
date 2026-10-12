import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class LeerEmpleadosXML {

    public static void main(String[] args) {
 
        File fichero = new File("Empleados.xml");
        if (!fichero.exists()) {
            System.out.println("No se encuentra el fichero: " + fichero.getAbsolutePath());
            return;
        }
 
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fichero);
            doc.getDocumentElement().normalize();
 
            System.out.println("Elemento raiz: " + doc.getDocumentElement().getNodeName());
 
            NodeList empleados = doc.getElementsByTagName("Empleado");
            System.out.println("Número de empleados: " + empleados.getLength());
            System.out.println("----------------------------------------");
 
            for (int i = 0; i < empleados.getLength(); i++) {
                Node nodo = empleados.item(i);
 
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element empleado = (Element) nodo;
 
                    System.out.println("Empleado " + (i + 1));
                    System.out.println("  ID           : " + getValor("id", empleado));
                    System.out.println("  Apellido     : " + getValor("apellido", empleado));
                    System.out.println("  Departamento : " + getValor("departamento", empleado));
                    System.out.println("  Salario      : " + getValor("salario", empleado));
                    System.out.println("----------------------------------------");
                }
            }
 
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
 
    // Devuelve el texto de la primera etiqueta con ese nombre dentro del empleado
    private static String getValor(String etiqueta, Element elem) {
        NodeList lista = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node nodo = (Node) lista.item(0);
        return nodo.getNodeValue();
    }
}

//una vez para que este codigo funcione, hay que seleccionar Empleados.dat despues de ejecutar
//CrearEmpleadoXml.java -> se crea todo solo y despues se ejecuta este codigo y sale todo bien y bonito por pantallla
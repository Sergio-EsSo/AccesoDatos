import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;

public class LectorLibreria {
    public static void main(String[] args) {
        // Cambia al xml librearia erroneo para probar el control de errores
        String ficheroXML = "libreria.xml"; 

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();

            GestionLibreria handler = new GestionLibreria();
            xmlReader.setContentHandler(handler);
            xmlReader.setErrorHandler(handler); // Registro del ErrorHandler

            System.out.println("Procesando archivo: " + ficheroXML);
            xmlReader.parse(ficheroXML);

        } catch (Exception e) {
            System.err.println("\n[EXCEPCIÓN] El análisis XML se detuvo debido a un error de estructura.");
        }
    }
}
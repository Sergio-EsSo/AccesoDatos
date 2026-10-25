import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;

public class LectorAlumnos {
    public static void main(String[] args) {
        try {
            // instanciar parseadores SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            // obtener el lector xml
            XMLReader xmlReader = saxParser.getXMLReader();
            
            // asignar el manejador personalizado
            GestionAlumnos gestionAlumnos = new GestionAlumnos();
            xmlReader.setContentHandler(gestionAlumnos);
            
            // parsear el fichero XML
            xmlReader.parse("alumnos.xml");
            
        } 
        catch (Exception e) {
            System.err.println("Error durante el procesamiento del archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
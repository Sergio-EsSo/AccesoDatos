import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionLibreria extends DefaultHandler {
    private List<LibroSAX> libros = new ArrayList<>();
    private LibroSAX libroActual;
    private StringBuilder textoActual = new StringBuilder();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("=== INICIO: Leyendo el catálogo de la librería ===");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("=== FIN: Lectura completada ===\n");
        mostrarResultados();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        textoActual.setLength(0);
        
        if (qName.equalsIgnoreCase("libro")) {
            libroActual = new LibroSAX(); // Creamos instancia y reiniciamos lista de autores implícitamente
            libroActual.setIsbn(attributes.getValue("isbn"));
            libroActual.setIdioma(attributes.getValue("idioma"));
        } else if (qName.equalsIgnoreCase("precio")) {
            if (libroActual != null) {
                libroActual.setMoneda(attributes.getValue("moneda"));
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        textoActual.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String valor = textoActual.toString().trim();

        if (qName.equalsIgnoreCase("titulo")) {
            if (libroActual != null) libroActual.setTitulo(valor);
        } else if (qName.equalsIgnoreCase("autor")) {
            if (libroActual != null) libroActual.addAutor(valor);
        } else if (qName.equalsIgnoreCase("editorial")) {
            if (libroActual != null) libroActual.setEditorial(valor);
        } else if (qName.equalsIgnoreCase("anio")) {
            if (libroActual != null) libroActual.setAnio(Integer.parseInt(valor));
        } else if (qName.equalsIgnoreCase("precio")) {
            if (libroActual != null) libroActual.setPrecio(Double.parseDouble(valor));
        } else if (qName.equalsIgnoreCase("libro")) {
            if (libroActual != null) {
                libros.add(libroActual);
            }
        }
    }

    private void mostrarResultados() {
        // mostrar libros
        System.out.println("--- 1. LISTADO COMPLETO DE LIBROS ---");
        for (LibroSAX l : libros) {
            String autoresStr = String.join(", ", l.getAutores());
            System.out.println(String.format("%s (%d) - %s - %s - %.2f %s - [%s] - %s",
                    l.getTitulo(), l.getAnio(), autoresStr, l.getEditorial(),
                    l.getPrecio(), l.getMoneda(), l.getIsbn(), l.getIdioma()));
        }

        // mostrar libros dle 2010
        System.out.println("\n--- 2. LIBROS PUBLICADOS A PARTIR DE 2010 ---");
        for (LibroSAX l : libros) {
            if (l.getAnio() >= 2010) {
                String autoresStr = String.join(", ", l.getAutores());
                System.out.println(String.format("%s (%d) - %s", l.getTitulo(), l.getAnio(), autoresStr));
            }
        }

        // conteo de libros por idioams
        Map<String, Integer> conteoIdiomas = new HashMap<>();
        for (LibroSAX l : libros) {
            conteoIdiomas.put(l.getIdioma(), conteoIdiomas.getOrDefault(l.getIdioma(), 0) + 1);
        }
        System.out.println("\n--- 3. CONTEO DE LIBROS POR IDIOMA ---");
        for (Map.Entry<String, Integer> entry : conteoIdiomas.entrySet()) {
            System.out.println("Idioma '" + entry.getKey() + "': " + entry.getValue() + " libros");
        }

        // precio
        double sumaEuros = 0;
        int countEuros = 0;
        for (LibroSAX l : libros) {
            if ("EUR".equalsIgnoreCase(l.getMoneda())) {
                sumaEuros += l.getPrecio();
                countEuros++;
            }
        }
        System.out.println("\n--- 4. ESTADÍSTICAS DE PRECIOS EN EUROS (EUR) ---");
        System.out.println(String.format("Precio total: %.2f EUR", sumaEuros));
        if (countEuros > 0) {
            System.out.println(String.format("Precio medio: %.2f EUR", (sumaEuros / countEuros)));
        } else {
            System.out.println("No hay libros registrados en euros.");
        }

        // libros con muchos autores
        if (!libros.isEmpty()) {
            LibroSAX libroMasAutores = libros.get(0);
            for (LibroSAX l : libros) {
                if (l.getAutores().size() > libroMasAutores.getAutores().size()) {
                    libroMasAutores = l;
                }
            }
            System.out.println("\n--- 5. LIBRO CON MÁS AUTORES ---");
            System.out.println(String.format("Título: %s (Autores: %s) - Total autores: %d",
                    libroMasAutores.getTitulo(), String.join(", ", libroMasAutores.getAutores()), 
                    libroMasAutores.getAutores().size()));
        }
    }

    // --- CONTROL DE ERRORES (ErrorHandler) ---
    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.out.println("[ADVERTENCIA] Línea " + e.getLineNumber() + ", Columna " + e.getColumnNumber() + ": " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println("[ERROR] Línea " + e.getLineNumber() + ", Columna " + e.getColumnNumber() + ": " + e.getMessage());
        throw e;
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("[ERROR FATAL] Línea " + e.getLineNumber() + ", Columna " + e.getColumnNumber() + ": " + e.getMessage());
        throw e;
    }
}
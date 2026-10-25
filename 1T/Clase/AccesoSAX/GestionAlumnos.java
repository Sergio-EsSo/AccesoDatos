import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionAlumnos extends DefaultHandler {
    private final List<Alumno> alumnos = new ArrayList<>();
    private Alumno alumnoActual;
    private final StringBuilder textoActual = new StringBuilder();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("=== INICIO: Leyendo el documento XML de alumnos ===");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("=== FIN: Lectura del documento XML completada ===\n");
        mostrarEstadisticas();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        textoActual.setLength(0); // Limpiamos el buffer para el nuevo elemento
        
        if (qName.equalsIgnoreCase("alumno")) {
            alumnoActual = new Alumno();
            // Recogemos el atributo id
            alumnoActual.setId(attributes.getValue("id"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Acumulamos los caracteres en caso de que vengan fragmentados
        textoActual.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String valor = textoActual.toString().trim();

        if (qName.equalsIgnoreCase("nombre")) {
            if (alumnoActual != null) {
                alumnoActual.setNombre(valor);
            }
        } else if (qName.equalsIgnoreCase("apellidos")) {
            if (alumnoActual != null) {
                alumnoActual.setApellidos(valor);
            }
        } else if (qName.equalsIgnoreCase("ciclo")) {
            if (alumnoActual != null) {
                alumnoActual.setCiclo(valor);
            }
        } else if (qName.equalsIgnoreCase("nota")) {
            if (alumnoActual != null) {
                alumnoActual.setNota(Double.parseDouble(valor));
            }
        } else if (qName.equalsIgnoreCase("alumno")) {
            if (alumnoActual != null) {
                alumnos.add(alumnoActual);
                // Mostrar el listado apellidos nombre y nota
                System.out.println(String.format("[%s] %s, %s (%s) - Nota: %.2f",
                        alumnoActual.getId(),
                        alumnoActual.getApellidos(),
                        alumnoActual.getNombre(),
                        alumnoActual.getCiclo(),
                        alumnoActual.getNota()));
            }
        }
    }

    private void mostrarEstadisticas() {
        if (alumnos.isEmpty()) {
            System.out.println("No se han encontrado registros de alumnos.");
            return;
        }

        int totalAlumnos = alumnos.size();
        double sumaNotas = 0;
        int aprobados = 0;
        int suspensos = 0;

        Alumno mejorAlumno = alumnos.get(0);
        Alumno peorAlumno = alumnos.get(0);

        for (Alumno a : alumnos) {
            double nota = a.getNota();
            sumaNotas += nota;

            // Conteo de aprobados y suspensos (>= 5)
            if (nota >= 5.0) {
                aprobados++;
            } else {
                suspensos++;
            }

            // Nota más alta y más baja
            if (nota > mejorAlumno.getNota()) {
                mejorAlumno = a;
            }
            if (nota < peorAlumno.getNota()) {
                peorAlumno = a;
            }
        }

        double notaMedia = sumaNotas / totalAlumnos;

        System.out.println("--- ESTADÍSTICAS FINALES ---");
        System.out.println("3. Número total de alumnos leídos: " + totalAlumnos);
        System.out.println(String.format("4. Nota media de todos los alumnos: %.2f", notaMedia));
        System.out.println(String.format("5. Alumno con la nota más alta: %s %s (%.2f)", 
                mejorAlumno.getNombre(), mejorAlumno.getApellidos(), mejorAlumno.getNota()));
        System.out.println(String.format("   Alumno con la nota más baja: %s %s (%.2f)", 
                peorAlumno.getNombre(), peorAlumno.getApellidos(), peorAlumno.getNota()));
        System.out.println("6. Alumnos aprobados (>= 5): " + aprobados);
        System.out.println("   Alumnos suspensos (< 5): " + suspensos);
    }
}
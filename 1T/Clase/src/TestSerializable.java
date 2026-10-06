import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String nombre;
    private final int edad;
    private final double notaMedia;
    private final transient String contraseña;

    public Estudiante(String nombre, int edad, double notaMedia, String contraseña) {
        this.nombre = nombre;
        this.edad = edad;
        this.notaMedia = notaMedia;
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", notaMedia=" + notaMedia +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}

public class TestSerializable {
    public static void main(String[] args) {
        String archivo = "estudiante.ser";

        // onbjeto
        //Estudiante estudianteOg = new Estudiante("Marisa", 23, 6.7, "ad1234");
        //System.out.println("Antes: " + estudianteOg);
        
        // Lista (nuevo)
        List<Estudiante> lista = new ArrayList<>();
        lista.add(new Estudiante("Marisa", 23, 6.7, "ad1234"));
        lista.add(new Estudiante("Juan Guillermo", 31, 8.9, "AlumnosCEBEM"));
        lista.add(new Estudiante("Ricardo", 26, 5.6,"23407sdf3bk3128"));

        System.out.println("Lista antes de guardar/serializar:");
        for(Estudiante e : lista){
            System.out.println(e);
        }

        // guardar
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            //oos.writeObject(estudianteOg);
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Error al guardar el estudiante: " + e.getMessage());
        }

        // recupera
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            //Estudiante estudianteRecuperado = (Estudiante) ois.readObject();
            //System.out.println("Después: " + estudianteRecuperado);
            @SuppressWarnings("unchecked")
            List<Estudiante> listaRecuperada = (List<Estudiante>) ois.readObject();

            System.out.println("Lista después de guardar/serilaizr:");
            for(Estudiante e : listaRecuperada){
                System.out.println(e);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al recuperar el estudiante: " + e.getMessage());
        }
    }
}

// Pregunta: ¿Por qué la contraseña vale null después de deserializar?
// Repsuesta: La contraseña vale null porque el atributo está declarado con la palabra clave transient -> OOS ignora ese campo y al traer el archivo, todos los campos sin nada son rellenados con null en el constructor
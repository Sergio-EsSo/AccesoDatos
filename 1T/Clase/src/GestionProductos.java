
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class GestionProductos {

    //tmaños en bytes
    private static final int ID_SIZE = 4;
    private static final int NAME_LENGTH = 12;
    private static final int NAME_SIZE = NAME_LENGTH*2;
    private static final int EXIST_SIZE = 4;
    private static final int PRICE_SIZE = 8;
    private static final int REG_SIZE = ID_SIZE + NAME_SIZE + EXIST_SIZE + PRICE_SIZE; //40

    private static final String FICHERO = "productos.dat";

    public static void main(String[] args) {
        
        String[] nombre_productos = {"Manzana", "Plátano", "Naranja", "Melocotón", "Fresa"};
        int[] existencias = {50, 122, 33, 55, 201};
        double[] precios = {1.5, 0.9, 1.2, 2.1, 3.5};

        File f = new File(FICHERO);

        try (RandomAccessFile raf = new RandomAccessFile(f, "rw")) {
            raf.setLength(0); // si el fichero existe lo vacia

            for (int i = 0; i < nombre_productos.length; i++) {
                int id = i + 1;
                raf.writeInt(id);
                raf.writeChars(ajustarTexto(nombre_productos[i], NAME_LENGTH));
                raf.writeInt(existencias[i]);
                raf.writeDouble(precios[i]);
            }
            System.out.println("Fichero creado y datos cargados..");

        } catch (IOException e) {
            System.err.println("Error al inicializar el fichero: "+e.getMessage());
            return;
        }

        // leer y miostrar productos
        System.out.println("\n--- LISTADO INICIAL DE PRODUCTOS ---");
        mostrarTodosLosProductos(f);

        try (Scanner scanner = new Scanner(System.in);
             RandomAccessFile raf = new RandomAccessFile(f, "rw")) {

            long numRegistros = raf.length() / REG_SIZE;

            // consultar productos por id
            System.out.println("\n--- CONSULTA DE PRODUCTO ---");
            int idConsulta = leerIdValido(scanner, numRegistros);
            long posConsulta = calcularPosicion(idConsulta);
            
            raf.seek(posConsulta);
            Producto p = leerRegistroActual(raf);
            System.out.println("Producto encontrado: "+p);

            // actualizar existencias de cadad prodcto
            System.out.println("\n--- ACTUALIZAR EXISTENCIAS ---");
            int idExistencias = leerIdValido(scanner, numRegistros);
            System.out.print("Introduce la nueva cantidad de existencias: ");
            int nuevasExistencias = leerEnteroNoNegativo(scanner);

            // existencias -> id (4) + nombre (24) = 28 bytes
            long posExistencias = calcularPosicion(idExistencias) + ID_SIZE + NAME_SIZE;
            raf.seek(posExistencias);
            raf.writeInt(nuevasExistencias);
            System.out.println("Existencias actualizadas correctamente.");

            // actualizar precio de productos
            System.out.println("\n--- ACTUALIZAR PRECIO ---");
            int idPrecio = leerIdValido(scanner, numRegistros);
            System.out.print("Introduce el nuevo precio: ");
            double nuevoPrecio = leerDoubleNoNegativo(scanner);

            // precio: id (4) + nombre (24) + existencias (4) = 32 bytes
            long posPrecio = calcularPosicion(idPrecio) + ID_SIZE + NAME_SIZE + EXIST_SIZE;
            raf.seek(posPrecio);
            raf.writeDouble(nuevoPrecio);
            System.out.println("Precio actualizado correctamente.");

        } catch (IOException e) {
            System.err.println("Error durante las operaciones en el fichero: " + e.getMessage());
        }

        //mostrar de nuevo todos los productos para ver cambios
        System.out.println("\n--- LISTADO DE PRODUCTOS TRAS MODIFICACIONES ---");
        mostrarTodosLosProductos(f);
    }

    //lee y muestra por pantalla todos los registros del fichero en secuencia
    private static void mostrarTodosLosProductos(File archivo) {
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                Producto p = leerRegistroActual(raf);
                System.out.println(p);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    //lee un registro completo desde la posicion actual del puntero
    private static Producto leerRegistroActual(RandomAccessFile raf) throws IOException {
        int id = raf.readInt();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NAME_LENGTH; i++) {
            sb.append(raf.readChar());
        }
        String nombre = sb.toString().trim();
        
        int existencias = raf.readInt();
        double precio = raf.readDouble();

        return new Producto(id, nombre, existencias, precio);
    }

    //calcula la posicion (byte offset) del producto según la fórmula dada: posicion = (id − 1) * tamaño del registro
    private static long calcularPosicion(int id) {
        return (long) (id - 1) * REG_SIZE;
    }

    //ajusta una cadena a exactamente 'longitud' caracteres, recortando o añadiendo espacios
    private static String ajustarTexto(String texto, int longitud) {
        if (texto.length() > longitud) {
            return texto.substring(0, longitud);
        } else {
            while (texto.length() < longitud) {
                texto += " ";
            }
            return texto;
        }
    }

    //metodos de validacion y entrada
    private static int leerIdValido(Scanner sc, long maxRegistros) {
        int id;
        while (true) {
            System.out.print("Introduce el identificador (1 - " + maxRegistros + "): ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                if (id >= 1 && id <= maxRegistros) {
                    return id;
                }
            } else {
                sc.next(); // Limpiar entrada no numérica
            }
            System.out.println(" Identificador no válido. Debe estar entre 1 y " + maxRegistros + ".");
        }
    }

    private static int leerEnteroNoNegativo(Scanner sc) {
        int valor;
        while (true) {
            if (sc.hasNextInt()) {
                valor = sc.nextInt();
                if (valor >= 0) {
                    return valor;
                }
            } else {
                sc.next();
            }
            System.out.print(" Valor no válido. Introduce un número entero mayor o igual a 0: ");
        }
    }

    private static double leerDoubleNoNegativo(Scanner sc) {
        double valor;
        while (true) {
            if (sc.hasNextDouble()) {
                valor = sc.nextDouble();
                if (valor >= 0.0) {
                    return valor;
                }
            } else {
                sc.next();
            }
            System.out.print(" Valor no válido. Introduce un número mayor o igual a 0: ");
        }
    }

    //clase interna para facilitar el manejo de datos en memoria al mostrar por pantalla
    private static class Producto {
        private final int id;
        private final String nombre;
        private final int existencias;
        private final double precio;

        public Producto(int id, String nombre, int existencias, double precio) {
            this.id = id;
            this.nombre = nombre;
            this.existencias = existencias;
            this.precio = precio;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Nombre: " + nombre + " | Existencias: " + existencias + " | Precio: " + precio + " €";
        }
    }
}
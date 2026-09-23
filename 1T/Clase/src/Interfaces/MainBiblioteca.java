package Interfaces;

/*
1. Escribe una aplicación en la que se implementen dos métodos:

cuentaPrestados(): recibe por parámetro un array de objetos, y devuelve cuántos de ellos están prestados.
publicacionesAnterioresA(): recibe por parámetro un array de Publicaciones y un año, y devuelve cuántas publicaciones tienen fecha anterior al año recibido por parámetro.

En el método main(), crear un array de Publicaciones, con 2 libros y 2 revistas, prestar uno de los libros, mostrar por pantalla los datos almacenados en el array y mostrar por pantalla cuántas  hay prestadas y cuantas hay anteriores a 1990.
Escribe una aplicación en la que se implementen dos métodos:

cuentaPrestados(): recibe por parámetro un array de objetos, y devuelve cuántos de ellos están prestados.
publicacionesAnterioresA(): recibe por parámetro un array de Publicaciones y un año, y devuelve cuántas publicaciones tienen fecha anterior al año recibido por parámetro.

En el método main(), crear un array de Publicaciones, con 2 libros y 2 revistas, prestar uno de los libros, mostrar por pantalla los datos almacenados en el array y mostrar por pantalla cuántas  hay prestadas y cuantas hay anteriores a 1990.
*/

// Interfaz Prestable
interface Prestable {
    void prestar();
    void devolver();
    boolean estaPrestado();
}

// Clase base Publicacion
class Publicacion {
    private final String codigo;
    private final String titulo;
    private final int anoPublicacion;

    public Publicacion(String codigo, String titulo, int anoPublicacion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anoPublicacion = anoPublicacion;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Título: " + titulo + ", Año: " + anoPublicacion;
    }
}

// Clase Libro que implementa Prestable
class Libro extends Publicacion implements Prestable {
    private boolean prestado;

    public Libro(String codigo, String titulo, int anoPublicacion) {
        super(codigo, titulo, anoPublicacion);
        this.prestado = false;
    }

    @Override
    public void prestar() {
        this.prestado = true;
    }

    @Override
    public void devolver() {
        this.prestado = false;
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }

    @Override
    public String toString() {
        return super.toString() + " | Libro [Prestado: " + (prestado ? "Sí" : "No") + "]";
    }
}

// Clase Revista
class Revista extends Publicacion {
    private final int numero;

    public Revista(String codigo, String titulo, int anoPublicacion, int numero) {
        super(codigo, titulo, anoPublicacion);
        this.numero = numero;
    }

    @Override
    public String toString() {
        return super.toString() + " | Revista [Número: " + numero + "]";
    }
}

public class MainBiblioteca {
    public static int cuentaPrestados(Object[] objetos) {
        int contador = 0;
        for (Object obj : objetos) {
            if (obj instanceof Prestable && ((Prestable) obj).estaPrestado()) {
                contador++;
            }
        }
        return contador;
    }

    public static int publicacionesAnterioresA(Publicacion[] publicaciones, int ano) {
        int contador = 0;
        for (Publicacion p : publicaciones) {
            if (p.getAnoPublicacion() < ano) {
                contador++;
            }
        }
        return contador;
    }

    public static void main(String[] args) {
        Publicacion[] publicaciones = new Publicacion[4];
        publicaciones[0] = new Libro("L01", "Cien años de soledad", 1967);
        publicaciones[1] = new Libro("L02", "Don Quijote", 1605);
        publicaciones[2] = new Revista("R01", "National Geographic", 1988, 150);
        publicaciones[3] = new Revista("R02", "Scientific American", 2005, 42);

        // Prestar uno de los libros
        ((Libro) publicaciones[0]).prestar();

        System.out.println("--- PUBLICACIONES EN BIBLIOTECA ---");
        for (Publicacion p : publicaciones) {
            System.out.println(p);
        }

        System.out.println("\nPublicaciones prestadas: " + cuentaPrestados(publicaciones));
        System.out.println("Publicaciones anteriores a 1990: " + publicacionesAnterioresA(publicaciones, 1990));
    }
}
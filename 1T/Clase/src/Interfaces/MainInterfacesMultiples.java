package Interfaces;

/*
7. Crea las interfaces Imprimible y Resumible. 
La primera permitirá mostrar toda la información de un objeto y la segunda obtener un resumen. 
Crea una clase Factura que implemente ambas interfaces y una clase Informe que implemente solamente Imprimible. 
Comprueba las operaciones que pueden realizarse mediante variables de cada tipo de interfaz.
*/

interface Imprimible {
    void mostrarInformacionCompleta();
}

interface Resumible {
    String obtenerResumen();
}

class Factura implements Imprimible, Resumible {
    private final int numeroFactura;
    private final String cliente;
    private final double total;

    public Factura(int numeroFactura, String cliente, double total) {
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.total = total;
    }

    @Override
    public void mostrarInformacionCompleta() {
        System.out.println("[FACTURA DETALLADA] Nº: " + numeroFactura + " | Cliente: " + cliente + " | Total: " + total + "€");
    }

    @Override
    public String obtenerResumen() {
        return "Factura número: " + numeroFactura + " (" + total + "€)";
    }
}

class Informe implements Imprimible {
    private final String titulo;
    private final String autor;
    private final int numeroPaginas;

    public Informe(String titulo, String autor, int numeroPaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public void mostrarInformacionCompleta() {
        System.out.println("[INFORME TÉCNICO] Título: " + titulo + " | Autor: " + autor + " | Páginas: " + numeroPaginas);
    }
}

public class MainInterfacesMultiples {
    public static void main(String[] args) {
        Factura factura = new Factura(1001, "Empresa PACO", 1250.75);
        Informe informe = new Informe("Balance Anual", "Dep. Contabilidad", 45);

        System.out.println("--- USO CON VARIABLE Imprimible ---");
        Imprimible imp1 = factura;
        Imprimible imp2 = informe;
        imp1.mostrarInformacionCompleta();
        imp2.mostrarInformacionCompleta();

        System.out.println("\n--- USO CON VARIABLE Resumible ---");
        Resumible res1 = factura;
        System.out.println("Resumen: " + res1.obtenerResumen());
    }
}
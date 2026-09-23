package Interfaces;

/*
9 Crea una interfaz Identificable que permita obtener el identificador de un objeto. 
Crea una segunda interfaz llamada Gestionable que herede de Identificable y añada operaciones para activar y desactivar objetos. 
Implementa Gestionable en las clases Producto y Cliente. 
Utiliza una colección de objetos Gestionables para ejecutar sus operaciones mediante polimorfismo.
*/

import java.util.ArrayList;
import java.util.List;

interface Identificable {
    String getId();
}

interface Gestionable extends Identificable {
    void activar();
    void desactivar();
    boolean estaActivo();
}

class Producto implements Gestionable {
    private final String id;
    private final String nombre;
    private boolean activo;

    public Producto(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.activo = true;
    }

    @Override public String getId() { return id; }
    @Override public void activar() { this.activo = true; }
    @Override public void desactivar() { this.activo = false; }
    @Override public boolean estaActivo() { return activo; }

    @Override
    public String toString() {
        return "Producto [" + id + "] " + nombre + " - Estado: " + (activo ? "Activo" : "Inactivo");
    }
}

class Cliente implements Gestionable {
    private final String id;
    private final String nombre;
    private boolean activo;

    public Cliente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.activo = true;
    }

    @Override public String getId() { return id; }
    @Override public void activar() { this.activo = true; }
    @Override public void desactivar() { this.activo = false; }
    @Override public boolean estaActivo() { return activo; }

    @Override
    public String toString() {
        return "Cliente [" + id + "] " + nombre + " - Estado: " + (activo ? "Activo" : "Inactivo");
    }
}

public class MainGestionable {
    public static void main(String[] args) {
        List<Gestionable> elementos = new ArrayList<>();
        elementos.add(new Producto("PROD-01", "Portátil Gaming"));
        elementos.add(new Cliente("CLI-100", "Juan Pérez"));

        System.out.println("--- ESTADO INICIAL ---");
        elementos.forEach(System.out::println);

        // Operaciones polimórficas
        System.out.println("\n--- DESACTIVANDO ELEMENTOS ---");
        for (Gestionable g : elementos) {
            g.desactivar();
            System.out.println(g);
        }
    }
}
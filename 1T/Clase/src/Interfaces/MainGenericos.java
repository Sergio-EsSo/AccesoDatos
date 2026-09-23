package Interfaces;

/*
11. Crea una interfaz genérica llamada Gestor que permita añadir, buscar, eliminar y listar objetos almacenados en una colección durante la ejecución del programa. 
Crea las clases Producto y Cliente e implementa un gestor diferente para cada una. 
Crea una clase principal que permita gestionar ambos tipos de objetos mediante variables de tipo Gestor. 
Todos los datos existirán solamente mientras el programa esté en funcionamiento.
*/

import java.util.ArrayList;
import java.util.List;
import Interfaces.MainGestionable;

interface Gestor<T> {
    void agregar(T objeto);
    T buscar(String id);
    boolean eliminar(String id);
    List<T> listar();
}

class GestorProducto implements Gestor<Producto> {
    private final List<Producto> lista = new ArrayList<>();

    @Override
    public void agregar(Producto objeto) {
        lista.add(objeto);
    }

    @Override
    public Producto buscar(String id) {
        return lista.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean eliminar(String id) {
        return lista.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }

    @Override
    public List<Producto> listar() {
        return new ArrayList<>(lista);
    }
}

class GestorCliente implements Gestor<Cliente> {
    private List<Cliente> lista = new ArrayList<>();

    @Override
    public void agregar(Cliente objeto) {
        lista.add(objeto);
    }

    @Override
    public Cliente buscar(String id) {
        return lista.stream()
                .filter(c -> c.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean eliminar(String id) {
        return lista.removeIf(c -> c.getId().equalsIgnoreCase(id));
    }

    @Override
    public List<Cliente> listar() {
        return new ArrayList<>(lista);
    }
}

public class MainGenericos {
    public static void main(String[] args) {
        Gestor<Producto> gestorProductos = new GestorProducto();
        Gestor<Cliente> gestorClientes = new GestorCliente();

        // Operaciones con Productos
        gestorProductos.agregar(new Producto("P01", "Teclado Mecánico"));
        gestorProductos.agregar(new Producto("P02", "Ratón Optico"));

        // Operaciones con Clientes
        gestorClientes.agregar(new Cliente("C01", "Marisa Gomez"));

        System.out.println("--- LISTA DE PRODUCTOS ---");
        gestorProductos.listar().forEach(System.out::println);

        System.out.println("\n--- BÚSQUEDA ---");
        Producto buscado = gestorProductos.buscar("P01");
        System.out.println("Producto encontrado: " + buscado);

        System.out.println("\n--- ELIMINACIÓN ---");
        gestorProductos.eliminar("P02");
        gestorProductos.listar().forEach(System.out::println);
    }
}
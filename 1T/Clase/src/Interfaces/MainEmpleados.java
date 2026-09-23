package Interfaces;

/*
8 Crea una clase abstracta Empleado con los atributos nombre y salario base, un método para mostrar sus datos y un método abstracto para calcular su salario. 
Crea también una interfaz Bonificable. Implementa las clases EmpleadoFijo y 
Comercial, teniendo en cuenta que solamente el comercial podrá recibir una bonificación relacionada con sus ventas.
*/

abstract class Empleado {
    private final String nombre;
    private final double salarioBase;

    public Empleado(String nombre, double salarioBase) {
        this.nombre = nombre;
        this.salarioBase = salarioBase;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void mostrarDatos() {
        System.out.println("Empleado: " + nombre + " | Salario Base: " + salarioBase + "€");
    }

    public abstract double calcularSalario();
}

interface Bonificable {
    double calcularBonificacion();
}

class EmpleadoFijo extends Empleado {
    public EmpleadoFijo(String nombre, double salarioBase) {
        super(nombre, salarioBase);
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase();
    }
}

class Comercial extends Empleado implements Bonificable {
    private final double ventas;
    private final double porcentajeComision;

    public Comercial(String nombre, double salarioBase, double ventas, double porcentajeComision) {
        super(nombre, salarioBase);
        this.ventas = ventas;
        this.porcentajeComision = porcentajeComision;
    }

    @Override
    public double calcularBonificacion() {
        return ventas * porcentajeComision;
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + calcularBonificacion();
    }
}

public class MainEmpleados {
    public static void main(String[] args) {
        Empleado fijo = new EmpleadoFijo("Paco Fernandez", 1500.0);
        Comercial comercial = new Comercial("Marisa Gomez", 1600.0, 15000.0, 0.05);

        Empleado[] plantilla = new Empleado[]{fijo, comercial};

        for (Empleado em : plantilla) {
            em.mostrarDatos();
            if (em instanceof Bonificable bonificable) {
                System.out.println("  -> Bonificación por ventas: " + bonificable.calcularBonificacion() + "€");
            }
            System.out.println("  -> Salario Total: " + em.calcularSalario() + "€\n");
        }
    }
}
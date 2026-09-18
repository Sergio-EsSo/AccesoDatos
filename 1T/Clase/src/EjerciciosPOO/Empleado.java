//ejercicio 4

package EjerciciosPOO;

public class Empleado {
    private String nombre;
    private String dni;

    public Empleado() { //si se borra el constructor por defecto no pasa nada porque ya existe uno parametrizado
        this.nombre = "";
        this.dni = "";
    }

    public Empleado(String nombre, String dni) { //esto es un constructor parametrizado
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public static void main(String[] args) {
        Empleado e1 = new Empleado("Marisa", "72045678M");
        Empleado e2 = new Empleado("Eugenio", "60628320W");

        e1.setNombre("María Marisa");

        System.out.println(e1.getNombre() + ", con DNI " + e1.getDni());
        System.out.println(e2.getNombre() + ", con DNI " + e2.getDni());
    }
}
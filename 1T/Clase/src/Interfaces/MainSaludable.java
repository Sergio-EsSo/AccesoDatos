package Interfaces;

/*
2. Crea una interfaz llamada Saludable que declare un método saludar(). 
Implementa la interfaz en las clases Persona y Robot. Cada clase deberá mostrar un saludo diferente. 
Crea un programa principal que instancie ambas clases y ejecute sus métodos.
*/

interface Saludable {
    void saludar();
}

class Persona implements Saludable {
    private final String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void saludar() {
        System.out.println("Hola, encantado de conocerte. Mi nombre es " + nombre + ".");
    }
}

class Robot implements Saludable {
    private final String modelo;

    public Robot(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public void saludar() {
        System.out.println("BEEP BOOP! Saludos humano. Unidad de procesamiento " + modelo + " operativa.");
    }
}

public class MainSaludable {
    public static void main(String[] args) {
        Saludable p = new Persona("Laura");
        Saludable r = new Robot("RX-78");

        p.saludar();
        r.saludar();
    }
}
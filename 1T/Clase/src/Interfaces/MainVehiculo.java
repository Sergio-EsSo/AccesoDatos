package Interfaces;

/*
4. Crea una interfaz llamada ConfiguracionVehiculo que contenga constantes para establecer las velocidades mínima y máxima, así como métodos para acelerar y frenar. 
Implementa la interfaz en una clase Coche, controlando que su velocidad nunca supere los límites establecidos.
*/

interface ConfiguracionVehiculo {
    int VELOCIDAD_MINIMA = 0;
    int VELOCIDAD_MAXIMA = 120;

    void acelerar(int incremento);
    void frenar(int decremento);
}

class Coche implements ConfiguracionVehiculo {
    private int velocidadActual;

    public Coche() {
        this.velocidadActual = VELOCIDAD_MINIMA;
    }

    @Override
    public void acelerar(int incremento) {
        velocidadActual += incremento;
        if (velocidadActual > VELOCIDAD_MAXIMA) {
            velocidadActual = VELOCIDAD_MAXIMA;
            System.out.println("¡Límite máximo alcanzado! Velocidad ajustada a " + VELOCIDAD_MAXIMA + " km/h.");
        } else {
            System.out.println("Acelerando... Velocidad actual: " + velocidadActual + " km/h.");
        }
    }

    @Override
    public void frenar(int decremento) {
        velocidadActual -= decremento;
        if (velocidadActual < VELOCIDAD_MINIMA) {
            velocidadActual = VELOCIDAD_MINIMA;
            System.out.println("El coche está detenido. Velocidad ajustada a " + VELOCIDAD_MINIMA + " km/h.");
        } else {
            System.out.println("Frenando... Velocidad actual: " + velocidadActual + " km/h.");
        }
    }
}

public class MainVehiculo {
    public static void main(String[] args) {
        Coche coche = new Coche();
        coche.acelerar(80);
        coche.acelerar(50); // Intenta llegar a 130 km/h (se limita a 120)
        coche.frenar(100);
        coche.frenar(50);  // Intenta bajar de 0 km/h
    }
}
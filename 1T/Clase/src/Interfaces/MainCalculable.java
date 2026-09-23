package Interfaces;

/*
3. Crea una interfaz llamada Calculable que declare un método para calcular un área. 
Implementa la interfaz en las clases Círculo y Rectángulo. Añade los atributos y constructores necesarios. 
Crea varios objetos y muestra sus áreas.
*/

interface Calculable {
    double calcularArea();
}

class Circulo implements Calculable {
    private final double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }
}

class Rectangulo implements Calculable {
    private final double base;
    private final double altura;

    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }
}

public class MainCalculable {
    public static void main(String[] args) {
        Calculable[] figuras = new Calculable[] {
            new Circulo(5.0),
            new Rectangulo(4.0, 6.0),
            new Circulo(2.5)
        };

        for (Calculable f : figuras) {
            System.out.printf("Área de %s: %.2f%n", f.getClass().getSimpleName(), f.calcularArea());
        }
    }
}
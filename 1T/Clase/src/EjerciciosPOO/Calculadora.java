//ejercicio 5

package EjerciciosPOO;

public class Calculadora {

    public int sumar(int a, int b) { // Paco
        return a + b;
    }

    public int sumar(int a, int b, int c) { // Pedro
        return a + b + c;
    }

    public double sumar(double a, double b) { // Pepe
        return a + b;
    }

    /*public double sumar(int a, int b){
        return a + b;
    }*/

    // NO se puede añadir double sumar(int a, int b){} porque tiene la misma lista de parametros que Paco
    // == la firma está duplicada
    // da error de compilacion
    // a mayores, el compilador analiza las firmas por numero y tipo de parametros, así que no se permite otra sobrecarga
    // int sumar(int x, int y){} no funciona porque tiene el mismo numero (2) y tipo (int) de parametros que Paco 

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        System.out.println("sumar(2, 3): " + calc.sumar(2, 3)); // Llama a Paco       
        System.out.println("sumar(2, 3, 4): " + calc.sumar(2, 3, 4)); // Llama a Pedro
        System.out.println("sumar(2.5, 3.0): " + calc.sumar(2.5, 3.0)); // Llama a Pepe
    }
}
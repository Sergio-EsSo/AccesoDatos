import java.util.Scanner;
import java.util.InputMismatchException;

class ExcepcionIntervalo extends Exception{

    public ExcepcionIntervalo(String msg){
        super(msg);
    }    
}

public class Ej_excepciones {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Introduce el numerador (menor que 100): ");
            double nmd = Double.parseDouble(sc.nextLine());

            System.out.println("Introduce el denominador (mayor que -5): ");
            double dnm = Double.parseDouble(sc.nextLine());

            if(nmd>=100 || dnm <= -5){
                throw new ExcepcionIntervalo("El numerador debe ser menor que 100 y el denominador mayor que -5");
            }
            if (dnm == 0) {
                throw new ArithmeticException("No se puede dividir entre cero.");
            }

            double cociente = nmd / dnm;
            System.out.println("El cociente es: " + cociente);
            
        }
        catch (InputMismatchException e) {
            System.out.println("Error: Debes introducir un número válido (caracteres no numéricos no permitidos).");
        }
        catch (ExcepcionIntervalo e) {
            System.out.println("Error de intervalo: " + e.getMessage());
        }
        catch (ArithmeticException e) {
            System.out.println("Error aritmético: " + e.getMessage());
        }
        finally {
            sc.close();
        }
    }
}
//ejercicio 3

package EjerciciosPOO;

public class Rectangulo {
    private double alto;
    private double ancho;

    public Rectangulo() {
        this.alto = 1.0;
        this.ancho = 1.0;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        /*if (alto > 0) {
            this.alto = alto; //siempre positivo o mauyor a 0
        }*/
       if (alto >= 2){
            this.alto = alto; //nueva regla, se usa this.alto para el parametro que recibo y asi no se junta con el atributo de la clase 
       }
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        if (ancho > 0) {
            this.ancho = ancho; //seimpre positiov o mayor a 0
        }
    }

    public double area() {
        return alto*ancho;
    }

    public double perimetro() {
        return 2*(alto+ancho);
    }

    public static void main(String[] args) {
        Rectangulo r1 = new Rectangulo();
        
        r1.setAlto(3); //cambia el valor por defecto de 1 a 3
        r1.setAncho(6); //cambia el valor por defecto de 1 a 6
        r1.setAlto(-4); // no es valido porque es negfativo
        r1.setAncho(0);  // no es valido porque es 0

        System.out.println("Rectángulo 1:");
        System.out.println("· Alto: " + r1.getAlto() + "\n· Ancho: " + r1.getAncho());
        System.out.println("· Área: " + r1.area() + "\n· Perímetro: " + r1.perimetro());

        Rectangulo r2 = new Rectangulo(); //se queda con los valores por defecto

        System.out.println("Rectñangulo 2:");
        System.out.println("· Alto: " + r2.getAlto() + "\n· Ancho: " + r2.getAncho());
    }
}
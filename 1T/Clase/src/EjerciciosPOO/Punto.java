//Ejercicio 6

package EjerciciosPOO;

public class Punto {
    private int x, y;

    /*public void Punto(){ //esto es un metodo pero tiene nombre de constructor
        x = 0;
        y = 0;
    }

    public Punto(int x, int y){ // sin usar this. se asignan los valores locales de nuevo, inicializando a 0 por defecto
        x = x;
        y = y;
    }*/

    public Punto() {
        this(0, 0);
    }

    public Punto(int v) {
        this(v, v);
    }

    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void dibujar() {
        System.out.println("Punto en (" + x + ", " + y + ")");
    }

    public static void main(String[] args) {
        Punto p1 = new Punto();
        Punto p2 = new Punto(5);
        Punto p3 = new Punto(4, 6);

        p1.dibujar();
        p2.dibujar();
        p3.dibujar();
    }
}
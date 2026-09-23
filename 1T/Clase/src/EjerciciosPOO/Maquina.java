//ejericco 10

class Almacen {
    private int capacidad;
    private int cantidad;

    public Almacen(int capacidad) {
        this.capacidad = Math.max(1, capacidad);
        this.cantidad = 0;
    }

    public boolean meter() {
        if (cantidad < capacidad) {
            cantidad++;
            return true;
        }
        return false;
    }

    public boolean sacar() {
        if (cantidad > 0) {
            cantidad--;
            return true;
        }
        return false;
    }

    public void rellenar() {
        this.cantidad = this.capacidad;
    }

    public int getCantidad() { return cantidad; }
    public int getCapacidad() { return capacidad; }
}

public class Maquina {
    private Almacen[] almacenes;
    private int[] precios;

    public Maquina() {
        almacenes = new Almacen[]{
            new Almacen(3), // 0: Agua
            new Almacen(2), // 1: Naranja
            new Almacen(2)  // 2: Cola
        };
        precios = new int[]{60, 130, 100};
    }

    public void reponer(int codigo) {
        if (esCodigoValido(codigo)) {
            almacenes[codigo].rellenar();
        }
    }

    public boolean setPrecio(int codigo, int nuevoPrecio) {
        if (esCodigoValido(codigo) && nuevoPrecio > 0) {
            precios[codigo] = nuevoPrecio;
            return true;
        }
        return false;
    }

    public int getExistencias(int codigo) {
        if (esCodigoValido(codigo)) {
            return almacenes[codigo].getCantidad();
        }
        return -1;
    }

    public boolean comprar(int codigo, int pagoCentimos) {
        if (!esCodigoValido(codigo)) return false;
        if (pagoCentimos != precios[codigo]) return false;
        
        return almacenes[codigo].sacar();
    }

    private boolean esCodigoValido(int codigo) {
        return codigo >= 0 && codigo < almacenes.length;
    }

    public static void main(String[] args) {
        Maquina m1 = new Maquina();
        m1.reponer(0); // Rellena agua (3)

        System.out.println("--- Casos de prueba ---");
        System.out.println("1. Compra correcta: " + m1.comprar(0, 60)); // true
        System.out.println("2. Pago insuficiente: " + m1.comprar(0, 40)); // false
        System.out.println("3. Pago superior: " + m1.comprar(0, 100)); // false
        System.out.println("4. Código inválido: " + m1.comprar(5, 60)); // false

        // Vaciar inventario de agua
        m1.comprar(0, 60);
        m1.comprar(0, 60);
        System.out.println("5. Comprar sin existencias: " + m1.comprar(0, 60)); // false

        // Probar independencia entre dos máquinas
        Maquina m2 = new Maquina();
        m1.setPrecio(1, 200);
        System.out.println("Precio Naranja M1: " + m1.precios[1]); // 200
        System.out.println("Precio Naranja M2: " + m2.precios[1]); // 130
    }
}
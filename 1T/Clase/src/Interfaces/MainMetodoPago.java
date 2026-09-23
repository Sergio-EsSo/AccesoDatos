package Interfaces;

/*
5 Crea una interfaz llamada MetodoPago que permita realizar un pago y obtener una descripción del método utilizado. 
Implementa la interfaz en las clases PagoTarjeta, PagoPayPal y PagoEfectivo. 
Cada clase deberá realizar el pago de una forma diferente.
*/

interface MetodoPago {
    boolean pagar(double importe);
    String obtenerDescripcion();
}

class PagoTarjeta implements MetodoPago {
    private final String numeroTarjeta;

    public PagoTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    @Override
    public boolean pagar(double importe) {
        System.out.printf("Procesando pago de %.2f€ con tarjeta %s... ¡Exitoso!%n", importe, numeroTarjeta);
        return true;
    }

    @Override
    public String obtenerDescripcion() {
        return "Pago con Tarjeta de Crédito/Débito";
    }
}

class PagoPayPal implements MetodoPago {
    private String email;

    public PagoPayPal(String email) {
        this.email = email;
    }

    @Override
    public boolean pagar(double importe) {
        System.out.printf("Conectando a la cuenta PayPal %s para abonar %.2f€... ¡Exitoso!%n", email, importe);
        return true;
    }

    @Override
    public String obtenerDescripcion() {
        return "Pago mediante PayPal";
    }
}

class PagoEfectivo implements MetodoPago {
    @Override
    public boolean pagar(double importe) {
        System.out.printf("Cobro en efectivo recibido por valor de %.2f€... ¡Exitoso!%n", importe);
        return true;
    }

    @Override
    public String obtenerDescripcion() {
        return "Pago en Efectivo";
    }
}

public class MainMetodoPago {
    public static void main(String[] args) {
        MetodoPago pago = new PagoTarjeta("1234-5678-9012-3456");
        System.out.println(pago.obtenerDescripcion());
        pago.pagar(45.50);
    }
}
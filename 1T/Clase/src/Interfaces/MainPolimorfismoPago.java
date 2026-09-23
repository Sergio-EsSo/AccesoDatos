package Interfaces;

/*
6. Partiendo del ejercicio anterior, crea una colección que contenga diferentes métodos de pago. 
Recorre la colección y realiza un pago con cada uno utilizando polimorfismo. 
Cada método de pago deberá tener alguna condición que permita aceptar o rechazar la operación.
*/

import java.util.ArrayList;
import java.util.List;
import Interfaces.MainMetodoPago;

class PagoTarjetaValidado implements MetodoPago {
    private final String numeroTarjeta;
    private double saldoDisponible;

    public PagoTarjetaValidado(String numeroTarjeta, double saldoDisponible) {
        this.numeroTarjeta = numeroTarjeta;
        this.saldoDisponible = saldoDisponible;
    }

    @Override
    public boolean pagar(double importe) {
        if (importe <= saldoDisponible) {
            saldoDisponible -= importe;
            System.out.printf("[TARJETA %s] Pago de %.2f€ ACEPTADO. Saldo restante: %.2f€%n", numeroTarjeta, importe, saldoDisponible);
            return true;
        }
        System.out.printf("[TARJETA %s] Pago de %.2f€ RECHAZADO: Saldo insuficiente (%.2f€)%n", numeroTarjeta, importe, saldoDisponible);
        return false;
    }

    @Override
    public String obtenerDescripcion() {
        return "Tarjeta de Crédito";
    }
}

class PagoPayPalValidado implements MetodoPago {
    private final String email;
    private final boolean cuentaVerificada;

    public PagoPayPalValidado(String email, boolean cuentaVerificada) {
        this.email = email;
        this.cuentaVerificada = cuentaVerificada;
    }

    @Override
    public boolean pagar(double importe) {
        if (cuentaVerificada) {
            System.out.printf("[PAYPAL %s] Pago de %.2f€ ACEPTADO.%n", email, importe);
            return true;
        }
        System.out.printf("[PAYPAL %s] Pago de %.2f€ RECHAZADO: Cuenta no verificada.%n", email, importe);
        return false;
    }

    @Override
    public String obtenerDescripcion() {
        return "PayPal";
    }
}

class PagoEfectivoValidado implements MetodoPago {
    private final double importeMaximoPermitido = 1000.0; // Límite legal por operación

    @Override
    public boolean pagar(double importe) {
        if (importe <= importeMaximoPermitido) {
            System.out.printf("[EFECTIVO] Pago de %.2f€ ACEPTADO.%n", importe);
            return true;
        }
        System.out.printf("[EFECTIVO] Pago de %.2f€ RECHAZADO: Supera el límite de %.2f€ en efectivo.%n", importe, importeMaximoPermitido);
        return false;
    }

    @Override
    public String obtenerDescripcion() {
        return "Efectivo";
    }
}

public class MainPolimorfismoPago {
    public static void main(String[] args) {
        List<MetodoPago> metodos = new ArrayList<>();
        metodos.add(new PagoTarjetaValidado("1111-2222", 100.0));
        metodos.add(new PagoPayPalValidado("user@test.com", false));
        metodos.add(new PagoEfectivoValidado());

        double compra = 150.0;
        System.out.println("Intentando pagar una compra de " + compra + "€:\n");

        for (MetodoPago m : metodos) {
            System.out.println("Método: " + m.obtenerDescripcion());
            m.pagar(compra);
            System.out.println("----------------------------------------");
        }
    }
}
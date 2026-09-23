//ejericico 9

import java.util.Arrays;

public class Telefono {
    private String[] historial;
    private int ultimoIndice;
    private int contador;

    public Telefono(int capacidad) {
        int cap = Math.max(1, capacidad);
        this.historial = new String[cap];
        this.ultimoIndice = -1;
        this.contador = 0;
    }

    public void llamar(String numero) {
        ultimoIndice = (ultimoIndice + 1) % historial.length;
        historial[ultimoIndice] = numero;
        if (contador < historial.length) {
            contador++;
        }
    }

    public String ultimaLlamada() {
        return llamada(0);
    }

    public String llamada(int n) {
        if (n < 0 || n >= contador) {
            return null;
        }
        int idx = (ultimoIndice - n + historial.length) % historial.length;
        return historial[idx];
    }

    public static void main(String[] args) {
        Telefono t = new Telefono(3);
        String[] numeros = {"111", "222", "333", "444", "555"};

        for (String num : numeros) {
            t.llamar(num);
            System.out.println("Registrado: " + num + " | Array: " + Arrays.toString(t.historial) +
                               " | Índice último: " + t.ultimoIndice);
        }

        System.out.println("\nConsultas (n=0 es la más reciente):");
        for (int i = 0; i <= 3; i++) {
            System.out.println("Llamada n=" + i + ": " + t.llamada(i));
        }
    }
}

//Explica por qué el índice circular no basta para saber cuántas llamadas hay guardadas.
/*
El índice circular señala en que casilla se escribió la última llamada, pero cuando
el array esta parcialmente lleno o se acaba de inicializar,
nop ermite distinguir entre casillas con datos válidos y casillas vacías (null). 
Por eso se precisa llevar un contador independiente */
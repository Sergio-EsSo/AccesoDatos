package EjerciciosPOO.repaso.app;

import EjerciciosPOO.repaso.modelo.Mensaje; 

public class PruebaMensaje {
    
    public static void main(String[] args) {
        
        Mensaje msg1 = new Mensaje();
        EjerciciosPOO.repaso.modelo.Mensaje msg2 = new EjerciciosPOO.repaso.modelo.Mensaje(); 
        //^^^ version si no hubiera importado el paquete ;; hay que acceder manualmente y es un cristo
        System.out.println(msg1.getTexto());
        System.out.println(msg2.getTexto());
    }
}


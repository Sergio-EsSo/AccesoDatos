package Interfaces;

/*
10. Diseña un sistema de notificaciones mediante una interfaz ServicioNotificacion que permita validar un destinatario y enviar un mensaje. 
Implementa los servicios de correo electrónico, SMS y notificación de aplicación. Las operaciones únicamente mostrarán sus resultados por consola. 
Crea una clase GestorNotificaciones capaz de trabajar con cualquiera de los servicios sin conocer su clase concreta.
*/

interface ServicioNotificacion {
    boolean validarDestinatario(String destinatario);
    void enviarMensaje(String destinatario, String mensaje);
}

class ServicioEmail implements ServicioNotificacion {
    @Override
    public boolean validarDestinatario(String destinatario) {
        return destinatario != null && destinatario.contains("@");
    }

    @Override
    public void enviarMensaje(String destinatario, String mensaje) {
        System.out.println("[EMAIL] Enviado a <" + destinatario + ">: " + mensaje);
    }
}

class ServicioSMS implements ServicioNotificacion {
    @Override
    public boolean validarDestinatario(String destinatario) {
        return destinatario != null && destinatario.matches("\\+?[0-9]{9,12}");
    }

    @Override
    public void enviarMensaje(String destinatario, String mensaje) {
        System.out.println("[SMS] Enviado al número " + destinatario + ": " + mensaje);
    }
}

class ServicioAppPush implements ServicioNotificacion {
    @Override
    public boolean validarDestinatario(String destinatario) {
        return destinatario != null && destinatario.startsWith("USER_ID_");
    }

    @Override
    public void enviarMensaje(String destinatario, String mensaje) {
        System.out.println("[APP PUSH] Notificación enviada a " + destinatario + ": " + mensaje);
    }
}

class GestorNotificaciones {
    private final ServicioNotificacion servicio;

    public GestorNotificaciones(ServicioNotificacion servicio) {
        this.servicio = servicio;
    }

    public void notificar(String destinatario, String mensaje) {
        if (servicio.validarDestinatario(destinatario)) {
            servicio.enviarMensaje(destinatario, mensaje);
        } else {
            System.out.println("[ERROR] Destinatario no válido para el servicio actual: " + destinatario);
        }
    }
}

public class MainNotificaciones {
    public static void main(String[] args) {
        GestorNotificaciones gestorEmail = new GestorNotificaciones(new ServicioEmail());
        gestorEmail.notificar("usuario@email.com", "Tu pedido ha sido enviado.");

        GestorNotificaciones gestorSMS = new GestorNotificaciones(new ServicioSMS());
        gestorSMS.notificar("600123456", "Tu código de verificación es 4921.");

        GestorNotificaciones gestorPush = new GestorNotificaciones(new ServicioAppPush());
        gestorPush.notificar("USER_ID_8832", "¡Tienes una nueva oferta disponible!");
    }
}
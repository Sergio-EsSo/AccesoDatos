import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UtilidadesFicheros {

    public static File devolverFichero() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = fileChooser.showOpenDialog(null);

        return seleccion == JFileChooser.APPROVE_OPTION
                ? fileChooser.getSelectedFile()
                : null;
    }

    public static File devolverFicheroDAT() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(
                new FileNameExtensionFilter("Archivos DAT (*.dat)", "dat"));
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = fileChooser.showOpenDialog(null);

        return seleccion == JFileChooser.APPROVE_OPTION
                ? fileChooser.getSelectedFile()
                : null;
    }

    /** Sin nombre: simplemente pide un fichero existente. */
    public static File crearFichero() {
        return devolverFichero();
    }

    /** Pide una carpeta y crea dentro un fichero con el nombre dado. */
    public static File crearFichero(String nombre) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Selecciona la carpeta de destino");

        int seleccion = fileChooser.showDialog(null, "Crear aquí");
        if (seleccion != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        File fichero = new File(fileChooser.getSelectedFile(), nombre);
        if (!fichero.createNewFile()) {
            throw new IOException("El fichero ya existe: " + fichero);
        }
        return fichero;
    }
}
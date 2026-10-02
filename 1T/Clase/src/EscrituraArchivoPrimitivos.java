import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EscrituraArchivoPrimitivos {

    public static void main(String[] args) {
        
        File f = new File("datos.txt");

        //creación fichero
        try {
            boolean creado = f.createNewFile();

            if(creado){
                System.out.println("El archivo se ha creado.");
            }
            else{
                System.out.println("El archivo ya existe.");
            }
            
        } 
        catch (IOException e) {
            System.out.println("Error E/S al crear el archivo: "+e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        List<Integer> numeros = new ArrayList<>();

        //introducción de los enterios
        System.out.println("---");
        System.out.println("Introduce enteros uno a uno y pulsa ENTER para finalizar.");

        while(true){
            String entrada = sc.nextLine().trim();

            if(entrada.isEmpty()){
                break;
            }

            try {
                int num = Integer.parseInt(entrada);
                numeros.add(num);    
            } 
            catch (NumberFormatException e) {
                System.out.println("Entrada inválida: ");
            }
        }

        if(numeros.isEmpty()){
            System.out.println("No se introdujo ningún número,..");
            sc.close();
        }

        try(FileOutputStream fos = new FileOutputStream(f, false);
            DataOutputStream dos = new DataOutputStream(fos)){

                for(int i=0; i<numeros.size(); i+=2){

                    StringBuilder fila = new StringBuilder();
                    fila.append(numeros.get(i));

                    if(i+1<numeros.size()){
                        fila.append(" ").append(numeros.get(i+1));
                    }

                    fila.append("\n\n");

                    byte[] bytesFila = fila.toString().getBytes();
                    dos.write(bytesFila);
                }

                //No se como escribirlos en bytes

                System.out.println("\nDatos guardados! En: "+f);
            }
            catch (IOException e){
                System.err.println("Error al escribir en el fichero: "+e.getMessage());
            }

            sc.close();
    }
}
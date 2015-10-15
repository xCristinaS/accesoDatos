package ejercicio02;

import java.io.File;
import java.io.IOException;

/**
 * Created by xCristina_S on 29/09/2015.
 */
public class Ejercicio02 {
    public static void main(String[] args){
        File dir1 = new File("nuevoDir");
        File fich1 = new File("nuevoDir/fichero1");
        File fich2 = new File("nuevoDir/fichero2");
        File fich3 = new File("nuevoDir/fichero3");
        File dir2 = new File("nuevoDir/directorioInterno");
        File fich4 = new File("nuevoDir/directorioInterno/ficherito");
        dir1.mkdir();
        dir2.mkdir();
        try {
            fich1.createNewFile();
            fich2.createNewFile();
            fich3.createNewFile();
            fich4.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fich1.delete();
        fich2.renameTo(new File("nuevoDir/ficheroRenombrado"));
        System.out.printf("- directorio 1: %s\n- directorio 2: %s", dir1.getAbsolutePath(), dir2.getAbsolutePath());
        System.out.printf("\n\nContenido del directorio 1 (%s):\n", dir1.getName());
        for (int i = 0; i < dir1.list().length; i++)
            System.out.println(dir1.list()[i]);

        System.out.printf("\nContenido del directorio 2(%s):\n", dir2.getName());
        for (int i = 0; i < dir2.list().length; i++)
            System.out.println(dir2.list()[i]);
    }
}

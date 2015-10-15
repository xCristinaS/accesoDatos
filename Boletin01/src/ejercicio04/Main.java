package ejercicio04;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by xCristina_S on 05/10/2015.
 */
public class Main {
    public static void main(String[] args) {
        String ext;
        File dir;
        String[] ficheros;
        System.out.print("Indique el directorio donde desea buscar: ");
        dir = new File(Teclado.leerLinea());
        System.out.print("Indique la extensión de los ficheros que desea listar: ");
        ext = Teclado.leerPalabra();

        ficheros = dir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                boolean r = false;
                if (name.matches(".\\.*"+ext))
                    r = true;
                return r;
            }
        });
        if (ficheros.length > 0) {
            System.out.println("\nSe han encontrado los siguientes ficheros con la extensión \'" + ext +"\': ");
            for (int i = 0; i < ficheros.length; i++)
                System.out.println("- " + ficheros[i]);
        } else
            System.out.println("\nNo se ha encontrado ningún fichero con la extensión '" + ext +"\'. ");

        Teclado.close();
    }
}

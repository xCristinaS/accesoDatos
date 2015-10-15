package ejercicio01;

import java.io.File;

/**
 * Created by xCristina_S on 29/09/2015.
 */
public class Ejercicio01 {
    public static void main (String[] args){
        File buscar;
        String nombreF;
        System.out.print("Escriba el nombre del fichero a buscar: ");
        nombreF = Teclado.leerPalabra();

        buscar = new File(nombreF);
        if (buscar.exists())
            System.out.printf("\n- Nombre del fichero: %s\n- ¿Es un ejecutable?: %s\n- ¿Está oculto?: %s\n- Ruta relativa: %s\n- Ruta absoluta: %s\n"
                    +"- Tamaño: %d\n\n", buscar.getName(), buscar.canExecute()?"si":"no", buscar.isHidden()?"si":"no", buscar.getPath(),
                    buscar.getAbsolutePath(), buscar.length());
        else
            System.out.println("El fichero no existe o no se encuentra en la ruta introducida.");

        Teclado.close();
    }
}

package ejercicio03;

import java.io.File;

/**
 * Created by xCristina_S on 29/09/2015.
 */
public class Ejercicio03 {
	
    public static void main(String[] args){
        String ruta;
        File f;
        System.out.print("- Escriba el nombre del directorio que quiere listar: ");
        ruta = Teclado.leerLinea();
        f = new File(ruta);

        if (f.isDirectory()) {
            System.out.printf("\nEl directorio %s contiene: \n", ruta);
            listar(f, 0);
        } else
            System.out.println("El directorio introducido no existe.");

        Teclado.close();
    }
    private static void listar(File directorio, int cont) {
    	String tab = "";
    	for (int i = 0; i < cont; i++)
    		tab += "\t";
        for (int i = 0; i < directorio.list().length; i++) {
        	System.out.printf("%s- Nombre: %s | Tipo: %s\n", tab, directorio.listFiles()[i].getName(), directorio.listFiles()[i].isDirectory()?"directorio":"fichero");
            if (directorio.listFiles()[i].isDirectory()) 
                listar(directorio.listFiles()[i], cont+1);
        }
    }
}

package ejercicio07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio07 {

	public static void main(String[] args) {
		
		File fichero = new File("./fichero.txt"), encriptado = new File("./Encriptado.txt");
		FileReader lectorF, lectorEn;
		FileWriter wriEn, wriDes;
		String clave;
		int i, cont = 0, codLetra;
		
		System.out.print("- Introduzca la clave de encriptación: ");
		clave = Teclado.leerPalabra();
		try {
			wriEn = new FileWriter(encriptado);
			wriDes = new FileWriter("./Desencriptado.txt");
			try {
				 lectorF = new FileReader(fichero);
	                while ((i = lectorF.read()) != -1) {
	                    codLetra = i - clave.charAt(cont);
	                    wriEn.write(codLetra);
	                    cont++;
	                    if (cont >= clave.length()) 
	                    	cont = 0;
	                }
	                cont = 0; i = 0;
	                lectorF.close(); wriEn.close();
	                lectorEn = new FileReader(encriptado);
	                while ((codLetra = lectorEn.read()) != -1) {
	                    codLetra += clave.charAt(cont);
	                    wriDes.write(codLetra);
	                    cont++;
	                    if (cont >= clave.length()) 
	                    	cont = 0;
	                }
	                lectorEn.close(); wriDes.close();
			} catch (FileNotFoundException e) {}
		} catch (IOException e){}
	}
}

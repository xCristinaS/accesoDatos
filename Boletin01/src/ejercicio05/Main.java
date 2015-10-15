package ejercicio05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
		File fichero, destino;
		PrintWriter escritor;
		BufferedReader lector;
		String linea; 
		boolean bool = false;
		
		System.out.print("- Introduzca la ruta del fichero que quiere copiar: ");
		fichero = new File(Teclado.leerLinea());
		System.out.print("- Indique la ruta de destino: ");
		destino = new File(Teclado.leerLinea());
		
		try {
			try {
				lector = new BufferedReader(new FileReader(fichero));
				
				if (destino.isDirectory()){
					escritor = new PrintWriter(new File(destino.getAbsolutePath()+"\\"+fichero.getName().toString()));
					while ((linea = lector.readLine()) != null)
							escritor.println(linea);
					
					System.out.println("\n- Fichero copiado.");
					escritor.close();
				} else {
					if (destino.exists() && bool){
						escritor = new PrintWriter(destino);
						while ((linea = lector.readLine()) != null)
							escritor.println(linea);
						
						System.out.println("\n- Fichero copiado.");
						escritor.close();				
					} else if (!bool && destino.exists())
						throw new Exception();
				}
				lector.close();
			} catch (FileNotFoundException e){
				System.out.println("\n- El fichero introducido no se ha encontrado.");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

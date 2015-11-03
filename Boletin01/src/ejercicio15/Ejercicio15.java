package ejercicio15;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Ejercicio15 {

	public static void main(String[] args) {
		
		final String ASTERISCOS = "*****************************************************************************************************\n";
		final String INICIO = "\t\t\t\t\tAGENDA TELEFÓNICA\n";
		final String FIN = "\t\t\t\t\tFIN AGENDA TELEFÓNICA\n";
		Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("dd/MM/yyyy").create();
		LinkedList<Contacto> lista; BufferedWriter escritor;
		FileReader lector; Type tipo = new TypeToken<LinkedList<Contacto>>(){}.getType();
		
		try {
			lector = new FileReader("Ejercicio15.json");
			escritor = new BufferedWriter(new FileWriter("Ejercicio15.txt"));
			lista = gson.fromJson(lector, tipo);
			
			escritor.write(ASTERISCOS); escritor.newLine();
			escritor.write(INICIO); escritor.newLine();
			escritor.write(ASTERISCOS); escritor.newLine();
			for (Contacto c: lista){
				escritor.write(c.toString()); escritor.newLine();
				escritor.write(ASTERISCOS); escritor.newLine();
			}
			escritor.write(FIN); escritor.newLine();
			escritor.write(ASTERISCOS); escritor.newLine();
			escritor.close(); lector.close();
		} catch (FileNotFoundException e){
			
		} catch (IOException e){
			
		}
	}

}

package ejercicio16;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ejercicio09.Teclado;

public class Ejercicio16 {

	public static void main(String[] args) {
		// "nombreCont": "Pepe","telefono": "123","direccion": "rosales","codP": 11300,"fechaNacimiento": "10/10/2000","deboMoney": true,"cantDeb": 10.2
		
		String linea, json = "", nombre;
		BufferedReader lector;
		String buscar;
		Pattern patron;
		Matcher matcher;
		
		System.out.print("Indique nombre: ");
		nombre = Teclado.leerPalabra();
		
		buscar = String.format("(\"nombreCont\": \"%s\",\"telefono\": \".*\",\"direccion\": \".*\",\"codP\": \\d{5},\"fechaNacimiento\": \"\\d{2}/\\d{2}/\\d{4}\",\"deboMoney\": .+,\"cantDeb\": \\d+\\.\\d+)", nombre);
		patron = Pattern.compile(buscar); // Coge la cadena y la convierte en un patrón.
		
		try {
			lector = new BufferedReader(new FileReader("Ejercicio15.json"));
			
			while ((linea = lector.readLine()) != null)
				json += linea.trim(); // Quita los espacios. 
			
			matcher = patron.matcher(json); // Con esto indico dónde tiene que buscar usando el patrón. 
			
			while (matcher.find())
				System.out.println(matcher.group(1).replaceAll("\"", ""));
		} catch (FileNotFoundException e){
			
		} catch (IOException e){
			
		}

	}

}

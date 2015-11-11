package json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSON {
	
	public static LinkedList<String> buscarEnJSON(Map<String, String> mapa, String rutaJson){
		String  json = "", linea, valor = "", auxResult = ""; int i, j, k = 0, l = 0; String[] aux, campos, valores;
		BufferedReader lector;
		Pattern patron;
		Matcher matcher;
		LinkedList<String> objetosJson = new LinkedList<String>(), result = new LinkedList<String>();
		LinkedList<String[]> aux2 = new LinkedList<String[]>();
		boolean[] comprobaciones = new boolean[mapa.size()]; boolean guardar;
		
		patron = Pattern.compile("[\\||{\\[](.+?)[\\}||\\]]"); // Para sacar un objeto guardado en el Json.
		
		try {
			lector = new BufferedReader(new FileReader(rutaJson));
			while ((linea = lector.readLine()) != null)
				json += linea.trim();
			
			json = json.replaceAll("\t", "");
			json = json.replaceAll("\n", "");
			
			matcher = patron.matcher(json);
			while (matcher.find())
				objetosJson.add(matcher.group(1)); // Agrego a la lista todos los objetos encontrados. 
			
			objetosJson.set(0, objetosJson.getFirst().substring(1, objetosJson.get(0).length())); // Le quito al primer objeto de la lista la llave del inicio del Json.
			for (i = 0; i < objetosJson.size(); i++)
				objetosJson.set(i, objetosJson.get(i).replaceAll("\"","")); // Le quito todas las comillas a los objetos de la lista. 
			
			for (j = 0; j < objetosJson.size(); j++){
				guardar = true; Arrays.fill(comprobaciones, false); l = 0;
				aux = objetosJson.get(j).split(","); // Guardo en aux, la cadena correspondiente al primer objeto de la lista. 
				if (aux2.size() > 0)
					aux2.removeAll(aux2); 
				for (i = 0; i < aux.length; i++)
					aux2.add(aux[i].split(":")); // Agrego a una segunda lista el conjunto "atributo - valor" de la cadena aux. 
				
				campos = new String[aux2.size()]; 
				valores = new String[aux2.size()];
				i = 0;
				for (String[] conjunto: aux2){
					campos[i] = conjunto[0]; // Meto los atributos en un array de campos. 
					valores[i] = conjunto[1].trim(); // Meto los valores en otro. 
					i++;
				}
				for (String clave: mapa.keySet()){ // Recorro las claves del mapa. 
					valor = mapa.get(clave); // cojo el valor de esa clave. 
					k = 0;
					while(l < mapa.size()){ // este bucle lo necesito por si empieza a buscar de atras para delante. Para que realice la busqueda tantas veces como campos por los que buscar, independientemente de la posicion.
						for (i = 0; i < campos.length; i++)
							if (campos[i].equals(clave) && valor.equals(valores[i])){
								comprobaciones[k] = true;
								k++;
							}
						l++;
					}
				}
				for (i = 0; guardar && i < comprobaciones.length; i++)
					if (!comprobaciones[i])
						guardar = false;
				
				if (guardar){
					auxResult = "";
					for (i = 0; i < valores.length; i++)
						if (i == valores.length-1)
							auxResult += valores[i];
						else 
							auxResult += valores[i]+",";
					result.add(auxResult);
				}
			}
			lector.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}
}

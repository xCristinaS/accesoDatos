package consultJson;

import java.util.HashMap;

public class Prueba {
	
	public static void main(String[] args) {
		
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put("nombreCont", "Pepe");
		mapa.put("telefono", "1234");
		mapa.put("direccion", "rosales");
		mapa.put("codP", "11300");
		for (String s:ConsultJSON.buscarEnJSON(mapa, "Ejercicio15.json"))
			System.out.println(s);
	}
}

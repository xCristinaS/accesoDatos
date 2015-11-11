package json;

import java.util.HashMap;

public class Prueba {
	
	public static void main(String[] args) {
		
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put("nombreCont", "Pepe");
		mapa.put("telefono", "1234");
		mapa.put("direccion", "clavel");
		//mapa.put("codP", "113");
		for (String s:JSON.buscarEnJSON(mapa, "Ejercicio15.json"))
			System.out.println(s);
	}
}

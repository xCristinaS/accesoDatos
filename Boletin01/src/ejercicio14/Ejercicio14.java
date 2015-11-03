package ejercicio14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import ejercicio10.ListaContactos;
import ejercicio08.Contacto;

public class Ejercicio14 {

	public static void main(String[] args) {
		
		XStream xstream = new XStream();
		FileWriter escribirJson;
		ListaContactos lista; 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		xstream.alias("Contactos", ListaContactos.class);
		xstream.alias("Contacto", Contacto.class);
		xstream.addImplicitCollection(ListaContactos.class, "lista");
		
		try {
			escribirJson = new FileWriter("Ejercicio14.json");
			lista = (ListaContactos) xstream.fromXML(new FileInputStream("Contactos.xml"));
			escribirJson.write(gson.toJson(lista.getLista()));
			
			escribirJson.close(); 
		} catch (FileNotFoundException e){
			
		} catch (IOException e){
			
		}
	}

}

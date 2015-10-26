package ejercicio10;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.thoughtworks.xstream.XStream;

import ejercicio08.Contacto;

public class Ejercicio10 {

	public static void main(String[] args) {
		
		File fichero = new File("binarioSerial.dat");
		ListaContactos lista = new ListaContactos();
		FileInputStream fileIn;	ObjectInputStream lectorContacto;
		Contacto contacto; XStream xstream = new XStream();
		
		try {
			fileIn = new FileInputStream(fichero);
			lectorContacto = new ObjectInputStream(fileIn);
			
			try  {
				while (true){
					contacto = (Contacto) lectorContacto.readObject();
					lista.agregarContacto(contacto);
				}
			} catch (ClassNotFoundException e){
				
			} catch (EOFException e){
				
			} 
			lectorContacto.close();
			
			xstream.alias("Contactos", ListaContactos.class);
			xstream.alias("Contacto", Contacto.class);
			xstream.addImplicitCollection(ListaContactos.class, "lista");
			xstream.toXML(lista, new FileOutputStream("Contactos.xml"));
			
			fileIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			
		}
	}

}

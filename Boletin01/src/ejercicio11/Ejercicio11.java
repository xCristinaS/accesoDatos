package ejercicio11;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ListIterator;

import com.thoughtworks.xstream.XStream;

import ejercicio08.Contacto;
import ejercicio10.ListaContactos;

public class Ejercicio11 {
	
	private static DataOutputStream escribir;
	
	public static void main(String[] args) {
		
		FileInputStream leerAntiguo, leerNuevo;
		XStream xstream = new XStream();
		ListaContactos lista; 
		ListIterator<Contacto> it;
		Contacto c; int i, j; boolean salir = false;
		
		xstream.alias("Contactos", ListaContactos.class);
		xstream.alias("Contacto", Contacto.class);
		xstream.addImplicitCollection(ListaContactos.class, "lista");
		
		try {
			lista = (ListaContactos) xstream.fromXML(new FileInputStream("Contactos.xml"));
			it = lista.getLista().listIterator();
			
			escribir = new DataOutputStream(new FileOutputStream("ejercicio11ObjectBin.dat"));
			
			while (it.hasNext()){
				c = it.next();
				agregarSinSerializar(c);
			}
			
			escribir.close();
			
			leerAntiguo = new FileInputStream("binario.dat");
			leerNuevo = new FileInputStream("ejercicio11ObjectBin.dat");
			
			while (!salir && (i = leerAntiguo.read()) != -1 && (j = leerNuevo.read()) != -1)
				if (i != j)
					salir = true;
			
			// No va a dar que son iguales en la vida! Porque en el ejercicio 10 para convertir el fichero binario a un fichero .xml con la clase XStream es necesario
			// que el fichero binario sea de objetos serializados y en este ejercicio estoy escribiendo el contenido que leo del .xml sin serializar (Teoría muy mia). 
			if (salir)
				System.out.println("Los ficheros no son iguales.");
			else 
				System.out.println("Los ficheros son iguales.");
					
			leerAntiguo.close();
			leerNuevo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			
		}
	}
	
	public static void agregarSinSerializar(Contacto c) throws IOException{
		escribir.writeUTF(c.nombreCont);
		escribir.writeUTF(c.telefono);
		escribir.writeUTF(c.direccion);
		escribir.writeInt(c.codP);
		escribir.writeBoolean(c.deboMoney);
		escribir.writeFloat(c.cantDeb);
	}
}

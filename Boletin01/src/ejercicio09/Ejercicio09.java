package ejercicio09;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import ejercicio09.Teclado.TipoEntre;

public class Ejercicio09 {
	
	public static RandomAccessFile fichero;
	
	public static void main(String args[]){
		
		Contacto p1 = new Contacto(1,"Pepe", "123", "rosales", 11300, true, 10.2f);
		Contacto p2 = new Contacto(2,"Juan", "4578", "SanPedro", 11300, false, 0f);
		Contacto p3 = new Contacto(3,"Manue", "4578", "SanPedro", 11300, false, 0f);
		Contacto p4 = new Contacto(4,"Rosa", "4578", "SanPedro", 11300, false, 0f);
		int opcion; boolean salir = false;
		
		System.out.println("- Indique la opción que desee: ");
		System.out.println("1. Consultar el fichero completo.");
		System.out.println("2. Consultar un contacto.");
		System.out.println("3. Modificar si le debo dinero y la cantidad.");
		System.out.println("4. Eliminar contacto.");
		System.out.println("5. Compactar fichero.");
		System.out.println("6. Salir.");
		System.out.print("- Respuesta: ");
		opcion = Teclado.leerIntEntre(1, 6, TipoEntre.AMBOS_INC);
		try {
			// Inicializo el fichero aleatorio. 
			fichero = new RandomAccessFile("agenda.dat", "rw");
			// Agrego contactos a la agenda. 
			agregarContactoAgenda(p1);
			agregarContactoAgenda(p2);
			agregarContactoAgenda(p3);
			agregarContactoAgenda(p4);
			do {
				switch (opcion){
				case 1: 
					leerFicheroCompleto();
					break;
				case 2: 
					consultarContacto();
					break;
				case 3: 
					modificarDineroCantidad();
					break;
				case 4:
					eliminarContacto();
					break;
				case 5: 
					compactarFichero();
					break;
				case 6:
					salir = true;
					break;
				}
			} while(!salir);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	public static void leerFicheroCompleto() throws IOException{
		try {
			while (true)
				System.out.println(fichero.read());
		} catch (EOFException e){
			e.printStackTrace();
		}
	}
	public static Contacto consultarContacto(){
		int idContacto;
		System.out.print("- Indique el id del contacto que quiere consultar: ");
		idContacto = Teclado.leerInt();
		return null;
		
	}
	public static void modificarDineroCantidad(){
		
	}
	public static void compactarFichero(){
		
	}
	public static void eliminarContacto(){
		
	}
	public static void agregarContactoAgenda(Contacto c) throws IOException{
		fichero.writeInt(c.id);						
		fichero.writeUTF(c.nombreCont);
		fichero.writeUTF(c.telefono);
		fichero.writeUTF(c.direccion);
		fichero.writeInt(c.codP);
		fichero.writeBoolean(c.deboMoney);
		fichero.writeFloat(c.cantDeb);
	}
}

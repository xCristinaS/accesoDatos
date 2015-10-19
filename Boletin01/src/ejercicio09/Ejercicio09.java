package ejercicio09;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

import ejercicio09.Teclado.TipoEntre;

public class Ejercicio09 {
	/*
	 * Espacio ocupado por contacto: 
	 * 		boolean eliminado = 1 byte.
	 * 		int id = 4 bytes;
	 * 		String nombre = 15 * 2 = 30 bytes. --> 15 caracteres que ocupa la cadena * 2 bytes que ocupa cada char. 
	 * 		String telefono = 12 * 2 = 24 bytes.
	 * 		String direccion = 20 * 2 = 40.
	 * 		int CP = 4 bytes.
	 * 		boolean deboMoney = 1 byte.
	 * 		float CantidadDebida = 4 bytes. 
	 * TOTAL = 108 bytes. 
	 */
	private static File agenda = new File("agenda.dat");
	private static RandomAccessFile fichero = null;
	private static StringBuffer buffer = new StringBuffer();
	private final static int ELIMINAR_CONT = 0, MODIFICAR_CONT = 1, CONSULTAR_CONT = 2, NUM_CHAR_NOMBRE = 15, NUM_CHAR_TLF = 12, NUM_CHAR_DIRECCION = 20;
	private final static byte ESPACIO_CONTACTO = 108, POSICION_DEBOMONEY = 103;
	
	public static void main(String args[]){

		Contacto p1 = new Contacto(1,"Pepe", "644 28 28 28", "rosales", 11300, true, 10.2f);
		Contacto p2 = new Contacto(2,"Juan", "956 66 66 66", "SanPedro", 11300, false, 0f);
		Contacto p3 = new Contacto(3,"Manuel", "856 12 12 12", "Clavel", 11300, false, 0f);
		Contacto p4 = new Contacto(4,"Rosa", "856 78 78 78", "San josé", 11300, false, 0f);
		int opcion; boolean salir = false;
		
		do {
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("Indique la opción que desee: \n");
			System.out.println("1. Consultar el fichero completo.");
			System.out.println("2. Añadir contacto.");
			System.out.println("3. Modificar si le debo dinero y la cantidad.");
			System.out.println("4. Eliminar contacto.");
			System.out.println("5. Compactar fichero.");
			System.out.println("6. Consultar un contacto");
			System.out.println("7. Salir.");
			System.out.print("\nRespuesta: ");
			opcion = Teclado.leerIntEntre(1, 7, TipoEntre.AMBOS_INC);
			System.out.println("---------------------------------------------------------------------------------\n");
			try {
				// Inicializo el fichero aleatorio. 
				if (fichero == null){
					fichero = new RandomAccessFile(agenda, "rw");
					// Agrego contactos a la agenda. 
					agregarContactoAgenda(p1);
					agregarContactoAgenda(p2);
					agregarContactoAgenda(p3);
					agregarContactoAgenda(p4);
				}
				switch (opcion) {
				case 1:
					leerFicheroCompleto();
					break;
				case 2:
					aniadirContacto();
					break;
				case 3:
					modificarDineroCantidad(solicitarIdContacto(MODIFICAR_CONT));
					break;
				case 4:
					eliminarContacto(solicitarIdContacto(ELIMINAR_CONT));
					break;
				case 5:
					compactarFichero();
					break;
				case 6:
					consultarContacto(solicitarIdContacto(CONSULTAR_CONT));
					break;
				case 7:
					salir = true;
					fichero.close();
					break;
				}
			} catch (FileNotFoundException e) {
				
			} catch (IOException e) {

			}
		} while (!salir);
		
	}
	private static void aniadirContacto() throws IOException{
		int id; int resp;
		fichero.seek((fichero.length()-ESPACIO_CONTACTO)+1); // Para colocarme en el id del último contacto en la agenda. 
		id = fichero.readInt() + 1;
		
		System.out.println("1. Agregar contacto al final del fichero.");
		System.out.println("2. Agregar contacto en hueco existente.");
		System.out.println("3. Volver atrás.");
		System.out.print("\nRespuesta: ");
		resp = Teclado.leerIntEntre(1, 3, TipoEntre.AMBOS_INC);
		switch (resp) {
		case 1:
			aniadirAlFinal(id);
			break;
		case 2:
			aniadirEnHueco(id);
			break;
		case 3:
			System.out.println();
			break;
		}
	}
	private static void aniadirAlFinal(int id) throws IOException{
		fichero.seek(fichero.length());
		agregarContactoAgenda(solicitarDatos(id));
		System.out.println("\n- Contacto agregado.\n");
	}
	private static void aniadirEnHueco(int id) throws IOException{
		boolean parar = false;
		fichero.seek(0);
		while (fichero.getFilePointer() != fichero.length() && !parar)
			if (fichero.readBoolean()){
				parar = true;
				fichero.seek(fichero.getFilePointer() - 1);
				agregarContactoAgenda(solicitarDatos(id));
			} else 
				fichero.skipBytes(ESPACIO_CONTACTO-1);
		if (!parar)
			agregarContactoAgenda(solicitarDatos(id));
		
		System.out.println("\n- Contacto agregado.\n");
	}
	private static Contacto solicitarDatos(int id){
		int codP; String nombre, tlf, direccion; boolean deboMoney; float cantDeb = 0;
		System.out.print("\n- Nombre: ");
		nombre = Teclado.leerLinea();
		System.out.print("- Teléfono: ");
		tlf = Teclado.leerLinea();
		System.out.print("- Direccion: ");
		direccion = Teclado.leerLinea();
		System.out.print("- CP: ");
		codP = Teclado.leerInt();
		deboMoney = Teclado.leerBoolean("¿Le debes dinero?: ", "Si", "No");
		if (deboMoney){
			System.out.print("- Cantidad que le debes: ");
			cantDeb = Teclado.leerFloat();
		} 
		return new Contacto(id, nombre, tlf, direccion, codP, deboMoney, cantDeb);
	}
	private static int solicitarIdContacto(int modo){
		int r = 0;
		
		switch (modo){
		case MODIFICAR_CONT:
			System.out.print("- Indique el ID del contacto al que desea modificar: ");
			r = Teclado.leerInt();
			break;
		case ELIMINAR_CONT:
			System.out.print("Indique el ID del contacto que quiere eliminar: ");
			r = Teclado.leerInt();
			break;
		case CONSULTAR_CONT:
			System.out.print("Indique el ID del contacto que quiere consultar: ");
			r = Teclado.leerInt();
			break;
		}
		return r;
	}
	private static void agregarContactoAgenda(Contacto c) throws IOException{
		fichero.writeBoolean(false); // Boolean de baja lógica (true == eliminado). 
		fichero.writeInt(c.id);			
		
		buffer.replace(0,NUM_CHAR_NOMBRE, c.nombreCont);
		buffer.setLength(NUM_CHAR_NOMBRE); 
		fichero.writeChars(buffer.toString());
		buffer.delete(0, NUM_CHAR_NOMBRE);
		
		buffer.replace(0,NUM_CHAR_TLF, c.telefono);
		buffer.setLength(NUM_CHAR_TLF); 
		fichero.writeChars(buffer.toString());
		buffer.delete(0, NUM_CHAR_TLF);
		
		buffer.replace(0,NUM_CHAR_DIRECCION, c.direccion);
		buffer.setLength(NUM_CHAR_DIRECCION); 
		fichero.writeChars(buffer.toString());
		buffer.delete(0, NUM_CHAR_DIRECCION);
		
		fichero.writeInt(c.codP);
		fichero.writeBoolean(c.deboMoney);
		fichero.writeFloat(c.cantDeb);
	}
	private static void leerFicheroCompleto() throws IOException{
		boolean eliminado; long posicion = 0;
		
		if (agendaTieneContenido(0) != 0){
			System.out.println("Contenido del fichero agenda:");
			fichero.seek(posicion);
			while ((posicion = fichero.getFilePointer()) != fichero.length()){
				eliminado = fichero.readBoolean();
				if (!eliminado)
					System.out.println(leerContacto().toString());
				else {
					fichero.skipBytes(ESPACIO_CONTACTO - 1);
				}
			}
		} else 
			System.out.println("La agenda está vacía.");
		System.out.println();
	}
	private static void consultarContacto(int ID) throws IOException{
		long posicion = (ID - 1) * ESPACIO_CONTACTO;
		if (posicion >= 0 && posicion < fichero.length()){ // Compruebo si el id introducido por el usuario es válido.
			fichero.seek(posicion);
			if (!fichero.readBoolean()) // Si no está eliminado, lee el contacto.
				System.out.println(leerContacto().toString());
		} else 
			System.out.println("El id introducido no se corresponde con ningún contacto.");
		
		System.out.println();
	}
	private static void modificarDineroCantidad(int ID) throws FileNotFoundException, IOException{
		boolean resp; long posicion = (ID - 1) * ESPACIO_CONTACTO; float cantDeb = 0;
		
		if (posicion >= 0 && posicion < fichero.length()){
			fichero.seek(posicion);
			if (!fichero.readBoolean()){
				leerContacto();
			
				System.out.println();
				
				fichero.seek(posicion + POSICION_DEBOMONEY);
				resp = Teclado.leerBoolean("¿Debes dinero al contacto?: ", "Si", "No");
				if (resp){
					System.out.print("- Introduzca la cantidad que le debe: ");
					cantDeb = Teclado.leerFloat();
					fichero.writeBoolean(resp);
					fichero.writeFloat(cantDeb);
				} else {
					fichero.writeBoolean(resp);
					fichero.writeFloat(cantDeb);
				}
				
				System.out.println("\n- Datos actualizados:");
				fichero.seek((ID-1)*ESPACIO_CONTACTO);
				if (!fichero.readBoolean())
					System.out.println(leerContacto().toString());
			}
		} else 
			System.out.println("- El id introducido no se corresponde con ningún contacto.");
		
		System.out.println();
	}
	private static void eliminarContacto(int ID) throws FileNotFoundException, IOException{
		long posicion = (ID - 1)*ESPACIO_CONTACTO;
		
		if (posicion >= 0 && posicion < fichero.length()){
			fichero.seek(posicion);
			if (!fichero.readBoolean()){
				fichero.seek(posicion);
				fichero.writeBoolean(true);
				System.out.println("Contacto eliminado.");
			} else 
				System.out.println("El id introducido no se corresponde con ningún contacto.");
		}else 
			System.out.println("El id introducido no se corresponde con ningún contacto.");
		
		System.out.println();
	}
	private static void compactarFichero() throws FileNotFoundException, IOException{
		LinkedList<Contacto> lista = new LinkedList<Contacto>(); int newID = 1;
		fichero.seek(0);
		while (fichero.getFilePointer() != fichero.length())
			if (!fichero.readBoolean())
				lista.add(leerContacto());
			else 
				fichero.skipBytes(ESPACIO_CONTACTO - 1);

		fichero.close();
		agenda.delete();
		agenda.createNewFile();
		fichero = new RandomAccessFile(agenda, "rw");
		for (Contacto c: lista){
			c.setID(newID);
			agregarContactoAgenda(c);
			newID++;
		}
		System.out.println("Fichero compactado.\n");
	}
	private static Contacto leerContacto() throws IOException{
		int id = 0, codP = 0, i; boolean deboMoney = false; float cantDeb = 0;
		String nombre = "", tlf = "", direccion = "";
		
		id = fichero.readInt();
		
		buffer.setLength(NUM_CHAR_NOMBRE);
		buffer.delete(0, NUM_CHAR_NOMBRE);
		for (i = 0; i < NUM_CHAR_NOMBRE; i++)
			buffer.insert(i, fichero.readChar());
		
		nombre = buffer.toString();
		
		buffer.setLength(NUM_CHAR_TLF);
		buffer.delete(0, NUM_CHAR_TLF);
		for (i = 0; i < NUM_CHAR_TLF; i++)
			buffer.insert(i, fichero.readChar());
		
		tlf = buffer.toString();
		
		buffer.setLength(NUM_CHAR_DIRECCION);
		buffer.delete(0, NUM_CHAR_DIRECCION);
		for (i = 0; i < NUM_CHAR_DIRECCION; i++)
			buffer.insert(i, fichero.readChar());
		
		direccion = buffer.toString();
		codP = fichero.readInt();
		deboMoney = fichero.readBoolean();
		cantDeb = fichero.readFloat();
		
		return new Contacto(id,nombre, tlf, direccion, codP, deboMoney, cantDeb);
	}
	private static int agendaTieneContenido(long posicion) throws IOException{
		int cont = 0;
		fichero.seek(posicion);
		if (fichero.getFilePointer() != fichero.length()){
			if (!fichero.readBoolean())
				cont++;
			else 
				cont = agendaTieneContenido(posicion + ESPACIO_CONTACTO);
		}
		return cont;
	}
}

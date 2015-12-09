package examen02;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.SessionFactoryUtil;

public class Ejercicio02 {
	// Obtener mediante HQL codigo del curso (codOe, codCurso), nombre del tutor, numero de asignaturas (tratarlo como número), número de profesores (tratarlo como número)
	// Crear un fichero de acceso aleatorio con el resultado de la HQL. 
	// Programa que pida un curso al usuario y lea del fichero aleatorio los datos del curso para mostrarlos por consola. Mostrarle indices al usuario para que pueda seleccionar curso. 
	
	// select --> select r.codOe, r.codCurso, p.nombre, count(distinct codAsignatura), count(distinct r.codProf) from Curso c, Profesor p, Reparto r where p.codProf = c.tutor and c.codOe = r.codOe and c.codcurso = r.codcurso 
	//group by 1,2;
	
	// int identificado --> 4 bytes.
	// CodOe -> string de 4. = 4*2 = 8 bytes.
	// codcurso -> string de 2. = 2*2 = 4 bytes.
	// nombre -> string de 60 = 60*2 = 120 bytes. 
	// numAsignaturas --> long = 8 bytes.
	// numProfesores --> long = 8 bytes. 
	// total = 
	private static RandomAccessFile fichero;
	private static StringBuffer buffer = new StringBuffer();
	private static int ESPACIO_REGISTRO = 152, NUM_CHAR_NOMBRE = 60, NUM_CHAR_CODOE = 4, NUM_CHAR_CODCURSO = 2, ESPACIO_IDENTIFICACION = 16;
	private static int id = 0; 
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		String codOe, codCurso, nombre;
		long numAsignaturas, numProfesores, posicion = 0;
		int respuesta;
		String select = "select r.id.codOe, r.id.codCurso, p.nombre, count(distinct r.asignatura.codAsignatura), count(distinct r.profesor.codProf) from Curso c, Profesor p, Reparto r where p.codProf = c.profesor.codProf and c.id.codOe = r.curso.id.codOe and"
				+ " c.id.codCurso = r.curso.id.codCurso group by 1, 2";
		Query cons = sesion.createQuery(select);
		List<Object[]> filas=cons.list();
		Object[] filaActual;
		try {
			fichero = new RandomAccessFile("Ejercicio02binario.dat", "rw");
			
			for (int i = 0; i < filas.size(); i++) {
				filaActual=filas.get(i);
				codOe = (String) filaActual[0];
				codCurso = (String) filaActual[1];
				nombre = (String) filaActual[2];
				numAsignaturas = (Long) filaActual[3];
				numProfesores = (Long) filaActual[4];
				agregarRegistro(codOe, codCurso, nombre, numAsignaturas, numProfesores);
				//System.out.printf("%s, %s, %s, %d, %d\n",filaActual[0],filaActual[1],filaActual[2], filaActual[3], filaActual[4]);
			}
			//long j = fichero.length();
			fichero.seek(0);
			System.out.println("- CONTENIDO DEL FICHERO:");
			while ((posicion = fichero.getFilePointer()) != fichero.length()){
				System.out.println(mostrarIndices(posicion));
				fichero.skipBytes(ESPACIO_REGISTRO - ESPACIO_IDENTIFICACION);
			}
			System.out.print("\n ¿Qué curso desea consultar?: ");
			respuesta = Teclado.leerInt();
			System.out.println(leerRegistro(respuesta));
			sesion.close();
			fichero.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public static void agregarRegistro(String codOe, String codCurso, String nombre, long numAsignatura, long numProfesores) throws IOException {
		fichero.writeInt(id);			
		
		buffer.replace(0,NUM_CHAR_CODOE, codOe);
		buffer.setLength(NUM_CHAR_CODOE); 
		fichero.writeChars(buffer.toString());
		buffer.delete(0, NUM_CHAR_CODOE);
		
		buffer.replace(0,NUM_CHAR_CODCURSO, codCurso);
		buffer.setLength(NUM_CHAR_CODCURSO); 
		fichero.writeChars(buffer.toString());
		buffer.delete(0, NUM_CHAR_CODCURSO);
		
		buffer.replace(0,NUM_CHAR_NOMBRE, nombre);
		buffer.setLength(NUM_CHAR_NOMBRE); 
		fichero.writeChars(buffer.toString());
		buffer.delete(0, NUM_CHAR_NOMBRE);
		
		fichero.writeLong(numAsignatura);
		fichero.writeLong(numProfesores);
		id++;
	}
	
	public static String mostrarIndices(long posicion) throws IOException {
		int id, i; 
		String codOe, codCurso;
		
		fichero.seek(posicion);
		
		id = fichero.readInt() + 1;

		buffer.setLength(NUM_CHAR_CODOE);
		buffer.delete(0, NUM_CHAR_CODOE);
		for (i = 0; i < NUM_CHAR_CODOE; i++)
			buffer.insert(i, fichero.readChar());
		
		codOe = buffer.toString();
		
		buffer.setLength(NUM_CHAR_CODCURSO);
		buffer.delete(0, NUM_CHAR_CODCURSO);
		for (i = 0; i < NUM_CHAR_CODCURSO; i++)
			buffer.insert(i, fichero.readChar());
		
		codCurso = buffer.toString();
		return String.format("- ID: %s -> CodOe: %s, CodCurso: %s",id, codOe, codCurso);
	}
	
	public static String leerRegistro(int identificador) throws IOException{
		int id, i; 
		String codOe, codCurso, nombre, resultado = "- El identificador introducido no es válido.";
		long numAsignaturas, numProfesores, posicion = (identificador - 1) * ESPACIO_REGISTRO;
		if (posicion >= 0 && posicion < fichero.length()){
			fichero.seek(posicion);
			
			id = fichero.readInt() +1;
	
			buffer.setLength(NUM_CHAR_CODOE);
			buffer.delete(0, NUM_CHAR_CODOE);
			for (i = 0; i < NUM_CHAR_CODOE; i++)
				buffer.insert(i, fichero.readChar());
			
			codOe = buffer.toString();
			
			buffer.setLength(NUM_CHAR_CODCURSO);
			buffer.delete(0, NUM_CHAR_CODCURSO);
			for (i = 0; i < NUM_CHAR_CODCURSO; i++)
				buffer.insert(i, fichero.readChar());
			
			codCurso = buffer.toString();
			
			buffer.setLength(NUM_CHAR_NOMBRE);
			buffer.delete(0, NUM_CHAR_NOMBRE);
			for (i = 0; i < NUM_CHAR_NOMBRE; i++)
				buffer.insert(i, fichero.readChar());
			
			nombre = buffer.toString().trim();
			
			numAsignaturas = fichero.readLong();
			numProfesores = fichero.readLong();
			resultado = String.format("- ID: %s -> CodOe: %s, CodCurso: %s, Nombre tutor: %s, Número de asignaturas: %d, Número de profesores: %d",id, codOe, codCurso, nombre, numAsignaturas, numProfesores);
		}
		
		return resultado;
	}
}

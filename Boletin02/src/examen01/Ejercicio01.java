package examen01;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Ejercicio01 {
	// select de codigo, nombre, fecha nacimiento, clave primaria del curso que es tutor (si no es tutor guardar espacios) y numero de asignaturas que imparte. 
	// crear un fichero json con los objetos obtenidos.
	public static void main(String[] args) {
		Connection conexion = BddConexion.newConexion("horario");
		String select = "select p.codProf, nombre, FechaDeNacimiento, c.codoe, c.codcurso, count(*) from reparto r, profesor p Left Join curso c on tutor = p.codprof where p.CodProf = r.codprof group by r.codprof;";
		PreparedStatement sentencia; ResultSet result;
		String codProf, nombre, fechaNacimiento, codOe, codCurso;
		int numAsignaturas;
		
		FileWriter escritor;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ListaProfesores listaProf = new ListaProfesores();
		
		try {
			sentencia = conexion.prepareStatement(select);
			result = sentencia.executeQuery();
			while (result.next()){
				codProf = result.getString(1);
				nombre = result.getString(2);
				fechaNacimiento = result.getString(3);
				codOe = result.getString(4);
				codCurso = result.getString(5);
				numAsignaturas = result.getInt(6);
				listaProf.agregarProfesor(new Profesor(codProf, nombre, fechaNacimiento, codOe, codCurso, numAsignaturas));
			}
			
			sentencia.close();
			result.close();
			
			escritor = new FileWriter("Ejercicio1JSON.json");
			escritor.write(gson.toJson(listaProf.getLista()));
			escritor.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		BddConexion.closeConexion(conexion);
		
		// Segunda parte. Solicitar los datos de un profesor cuyo código nos indique el usuario haciendo una búsqueda en el fichero json. 
		String codProfConsultar, resultadoBusqueda, curso; LinkedList<String> listaResultados;
		String[] datosProf;
		
		System.out.print("- Indique el código de profesor que desea consultar: ");
		codProfConsultar = Teclado.leerPalabra();
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put("codProf", codProfConsultar);
		listaResultados = ConsultJSON.buscarEnJSON(mapa, "Ejercicio1JSON.json");
		if (listaResultados.size() > 0){
			resultadoBusqueda = listaResultados.getFirst();
			datosProf = resultadoBusqueda.split(",");
			System.out.println("\n- Código de profesor: " + datosProf[0]);
			System.out.println("- Nombre: " + datosProf[1]);
			System.out.println("- Fecha de nacimiento " + datosProf[2]);
			System.out.println("- Número de asignaturas que imparte " + datosProf[5]);
			curso = datosProf[3] + " " + datosProf[4];
			if (curso.equals(" "))
				System.out.println("- No es tutor/a");
			else 
				System.out.printf("- Es tutor de: %s", curso);
		} else 
			System.out.println("- No existe ningún profesor con ese código.");
	}

}

package ejercicio03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicio03 {
	/*
	Añade el primer curso de la FP Básica con la tutora Pilar Baena García y las siguientes asignaturas:
	ASIGNATURAS 1º CURSO; HORAS SEMANALES; HORA	TOTALES; PROFESOR
	Equipos eléctricos y electrónicos; 6 ;186 ;Miguel Ángel Ronda Carracao
	Montaje y mantenimiento de sistemas y componentes informáticos; 9 ;279 ;José Manuel Escribano Romero
	*/
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/horario", "root", "root");
			// Agrego el curso.
			String insert = "insert into curso values (?, ?, ?)"; // curso: codOe, codCur, tutor
			PreparedStatement sentencia = conexion.prepareStatement(insert);
			sentencia.setString(1, "FPB");
			sentencia.setString(2, "1A");
			sentencia.setString(3, "PBG");
			System.out.println("curso agregado: " + sentencia.executeUpdate());
			// Agrego 1 asignatura.
			insert = "insert into asignatura values (?, ?, ?, ?)"; // asignatura: codAsignatura, nombre, horasSemanales, horasTotales
			sentencia = conexion.prepareStatement(insert);
			sentencia.setString(1, "EEE");
			sentencia.setString(2, "Equipos eléctricos y electrónicos");
			sentencia.setInt(3, 6);
			sentencia.setInt(4, 186);
			System.out.println("Asignatura agregada: " + sentencia.executeUpdate());
				// Agrego el profesor que da la 1ra asignatura, porque no estaba. 
				insert = "insert into profesor (codProf, nombre) values (?, ?)"; // profesor: codProf, nombre, (alta, fechaDenacimiento) == null
				sentencia = conexion.prepareStatement(insert);
				sentencia.setString(1, "MRC");
				sentencia.setString(2, "Miguel Ángel Ronda Carrasco");
				System.out.println("Profesor agregado: " + sentencia.executeUpdate());
			// Agrego la asignatura a la tabla reparto
			insert = "insert into reparto values (?, ?, ?, ?)"; // reparto: codOe, codCurso, codAsignatura, codProf
			sentencia = conexion.prepareStatement(insert);
			sentencia.setString(1, "FPB");
			sentencia.setString(2, "1A");
			sentencia.setString(3, "EEE");
			sentencia.setString(4, "MRC");
			System.out.println("Reparto: " + sentencia.executeUpdate());
			System.out.println("\n\n");
			// Agrego 1 asignatura.
			insert = "insert into asignatura values (?, ?, ?, ?)"; // asignatura: codAsignatura, nombre, horasSemanales, horasTotales
			sentencia = conexion.prepareStatement(insert);
			sentencia.setString(1, "MMSCI");
			sentencia.setString(2, "Montaje y manetenimientos de sistemas y componentes informáticos");
			sentencia.setInt(3, 9);
			sentencia.setInt(4, 279);
			System.out.println("Asignatura agregada: " + sentencia.executeUpdate());
			// Agrego la asignatura a la tabla reparto
			insert = "insert into reparto values (?, ?, ?, ?)"; // reparto: codOe, codCurso, codAsignatura, codProf
			sentencia = conexion.prepareStatement(insert);
			sentencia.setString(1, "FPB");
			sentencia.setString(2, "1A");
			sentencia.setString(3, "MMSCI");
			sentencia.setString(4, "JER");
			System.out.println("Reparto: " + sentencia.executeUpdate());
			
			sentencia.close();
			conexion.close();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e){}
	}

}

package ejercicio15;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ejercicio11.BddConexion;

public class Ejercicio15 {

	public static void main(String[] args) {
		/*
		Mostrar de cada asignatura el nombre, el número de horas a la semana, el número de cursos
		distintos donde se imparte, el número de ofertas educativas distintas donde se imparte, de
		aquellas asignaturas que tengan 3 o más horas a la semana. 
		*/
		Connection conexion = BddConexion.newConexion("horario");
		String select = "select nombre, horasSemanales, (select count(distinct(codOe)) from reparto where CodAsignatura = a.CodAsignatura), "
				+ "(select count(distinct(codCurso)) from reparto where CodAsignatura = a.CodAsignatura) from asignatura a where horasSemanales >= 3;";
		Statement sentencia; ResultSet result;
		
		try {
			sentencia = conexion.createStatement();
			result = sentencia.executeQuery(select);
			while (result.next())
				System.out.println("Nombre asignatura: " + result.getString(1) +" | Horas semanales: "+ result.getString(2) + " | Nº ofertas educativas: " + result.getString(3) + " | Nº cursos: " + result.getString(4));
			
			result.close();
			sentencia.close();
			result.close();
		} catch (SQLException e){e.printStackTrace();}
		BddConexion.closeConexion(conexion);
	}

}

package ejercicio09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio09 {
	/*
	Mostrar de todos los cursos el nombre de la oferta educativa, la clave primaria del curso y el
	nombre del tutor.
	*/
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/horario", "root", "root");
			String select = "select o.nombre,c.codOe, Codcurso, p.nombre from ofertaEducativa o, curso c, profesor p where p.CodProf = tutor and o.codOe = c.codOe"; 
			ResultSet resultadoSQL; PreparedStatement sentencia; 
			
			sentencia = conexion.prepareStatement(select);
			resultadoSQL = sentencia.executeQuery();
			while (resultadoSQL.next())
				System.out.printf("%-60s | %s | %s | %s \n",resultadoSQL.getString(1), resultadoSQL.getString(2), resultadoSQL.getString(3), resultadoSQL.getString(4));
			
			sentencia.close();
			resultadoSQL.close();
			conexion.close();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e){}
	}

}

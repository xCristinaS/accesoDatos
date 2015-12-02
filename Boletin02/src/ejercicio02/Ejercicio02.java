package ejercicio02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
  	oferta educativa:
	cod_OE: FPB
	nombre: FP Básica Informática y comunicaciones
	tipo: FPB
	descripción: La formación profesional básica de informática y comunicaciones tiene una
	duración de 2000 horas repartidas entre dos cursos académicos incluyendo 240 horas de
	Formación en centros de trabajo (FCT) en empresas del Sector
*/
public class Ejercicio02 {
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/horario", "root", "root");
			String insert = "insert into ofertaeducativa values (?, ?, ?, ?)";
			PreparedStatement sentencia = conexion.prepareStatement(insert);
			sentencia.setString(1,"FPB");
			sentencia.setString(2, "FP Básica Informática y comunicaciones");
			sentencia.setString(3, "La formación profesional básica de informática y comunicaciones tiene una duración de 2000 horas repartidas entre dos cursos académicos incluyendo 240 horas deFormación en centros de trabajo (FCT) en empresas del Sector");
			sentencia.setString(4, "FPB");	
			System.out.println(sentencia.executeUpdate());
			sentencia.close();
			conexion.close();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e){}
	}
}

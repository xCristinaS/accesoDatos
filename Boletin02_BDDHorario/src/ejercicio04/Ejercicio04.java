package ejercicio04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicio04 {
	/*
	Aumenta las horas semanales y las horas totales en un 10% de aquellas asignaturas de la FP
	Básica que empiecen por M.
	*/
	public static void main(String[] args) {
		try {
			float inc = 10;
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/horario", "root", "root");
			String update = "update asignatura set horasSemanales = horasSemanales + horasSemanales * ?/100 where codAsignatura in "
					+ "(select CodAsignatura from reparto r where asignatura.CodAsignatura = r.CodAsignatura and CodOe = \"FPB\") and nombre like 'M%'";
			PreparedStatement sentencia = conexion.prepareStatement(update);
			sentencia.setFloat(1, inc);
			System.out.println(sentencia.executeUpdate());
			update = "update asignatura set horasTotales = horasTotales + horasTotales * ?/100 where codAsignatura in "
					+ "(select CodAsignatura from reparto r where asignatura.CodAsignatura = r.CodAsignatura and CodOe = \"FPB\") and nombre like 'M%'";
			sentencia = conexion.prepareStatement(update);
			sentencia.setFloat(1, inc);
			System.out.println(sentencia.executeUpdate());
			sentencia.close();
			conexion.close();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e){}
	}

}

package ejercicio05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicio05 {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/horario", "root", "root");
			String delete = "delete from ofertaeducativa where codOE = 'FPB'";
			PreparedStatement sentencia = conexion.prepareStatement(delete);
			sentencia.executeUpdate();
			sentencia.close();
			conexion.close();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e){}
	}

}

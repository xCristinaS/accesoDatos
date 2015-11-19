package bddConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BddConexion {

	public static Connection newConexion(String bdd){
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(String.format("jdbc:mysql://localhost/%s", bdd), "root", "root");
		} catch (ClassNotFoundException | SQLException e){e.printStackTrace();}
		return c;
	}
	public static Connection newConexion(String bdd, String usuario, String contra){
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(String.format("jdbc:mysql://localhost/%s", bdd), usuario, contra);
		} catch (ClassNotFoundException | SQLException e){e.printStackTrace();}
		return c;
	}
	public static void closeConexion(Connection conexion){
		try {
			conexion.close();
		} catch (SQLException e) {e.printStackTrace();}
	}
}

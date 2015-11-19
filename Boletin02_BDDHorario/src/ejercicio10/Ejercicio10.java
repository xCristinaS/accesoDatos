package ejercicio10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ejercicio06.Teclado;

public class Ejercicio10 {
	// Mostrar cuándo se imparte una asignatura en un curso concreto.
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/horario", "root", "root");
			String select = "select codTramo from horario where CodAsignatura = ? and codOe = ?"; ResultSet resultado;
			PreparedStatement sentencia = conexion.prepareStatement(select); 
			String codOe, codAsig;
			
			System.out.print("Indique asignatura: ");
			codAsig = Teclado.leerPalabra();
			System.out.print("Indique oferta educativa: ");
			codOe = Teclado.leerPalabra();
			
			sentencia.setString(1, codAsig);
			sentencia.setString(2, codOe);
			resultado = sentencia.executeQuery();
			while (resultado.next())
				System.out.println(resultado.getString(1));
			
			sentencia.close();
			resultado.close();
			conexion.close();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e){}
	}

}

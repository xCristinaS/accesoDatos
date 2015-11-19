package ejercicio11;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ejercicio06.Teclado;


public class Ejercicio11 {
	// Mostrar qué asignaturas imparte un profesor.
	public static void main(String[] args) {
		Connection conexion = BddConexion.newConexion("horario");
		String select = "select r.codAsignatura, a.nombre from reparto r, asignatura a where a.CodAsignatura = r.CodAsignatura and codprof = ?", prof;
		PreparedStatement sentencia; ResultSet result;
		
		System.out.print("Introduzca código de profesor: ");
		prof = Teclado.leerPalabra();
		
		try {
			sentencia = conexion.prepareStatement(select);
			sentencia.setString(1, prof);
			result = sentencia.executeQuery();
			while (result.next())
				System.out.printf("\n- Código asignatura: %-8s | nombre: %s", result.getString(1), result.getString(2));
			
			sentencia.close();
			result.close();
		} catch (SQLException e){e.printStackTrace();}
		BddConexion.closeConexion(conexion);
	}
}

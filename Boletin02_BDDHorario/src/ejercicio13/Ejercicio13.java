package ejercicio13;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ejercicio06.Teclado;
import ejercicio11.BddConexion;

public class Ejercicio13 {

	public static void main(String[] args) {
		// Mostrar dónde se encuentra un profesor en un tramo horario concreto.
		Connection conexion = BddConexion.newConexion("horario");
		String select = "select codOe, codCurso from horario where codTramo = ? and codAsignatura in (select codAsignatura from reparto where codProf = ?);";
		String codTramo, codProf; 
		PreparedStatement sentencia; ResultSet result;
		
		System.out.print("Introduzca el codigo del tramo: ");
		codTramo = Teclado.leerPalabra();
		System.out.print("Introduzca el codigo del profesor: ");
		codProf = Teclado.leerPalabra();
		System.out.println();
		try {
			sentencia = conexion.prepareStatement(select);
			sentencia.setString(1, codTramo);
			sentencia.setString(2, codProf);
			result = sentencia.executeQuery();
			while (result.next())
				System.out.println("Curso: " + result.getString(1) +" "+ result.getString(2));
			
			result.close();
			sentencia.close();
			result.close();
		} catch (SQLException e){e.printStackTrace();}
		BddConexion.closeConexion(conexion);
	}

}

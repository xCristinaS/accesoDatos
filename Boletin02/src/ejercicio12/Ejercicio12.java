package ejercicio12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ejercicio06.Teclado;
import ejercicio11.BddConexion;

public class Ejercicio12 {

	public static void main(String[] args) {
		// Mostrar el horario de un curso.
		Connection conexion = BddConexion.newConexion("horario");
		String select = "select CodAsignatura from horario where codOe = ? and CodCurso = ? and CodAsignatura not in "
				+ "(select CodAsignatura from horario h1 where CodAsignatura like '@%' and codTramo in (Select CodTramo from horario h2 where h1.codtramo = h2.codtramo))"
				+ " order by substring(codTramo, 2), substring(codTramo, 1) like 'L%' desc, substring(codTramo, 1) like 'M%' desc, substring(codTramo, 1) like 'X%' desc, "
				+ "substring(codTramo, 1) like 'J%' desc;", codOe, codCurso; int cont = 1;
		PreparedStatement sentencia; ResultSet result;
		
		System.out.print("Introduzca el codigo de la oferta educativa: ");
		codOe = Teclado.leerPalabra();
		System.out.print("Introduzca el codigo del curso: ");
		codCurso = Teclado.leerPalabra();
		System.out.println();
		try {
			sentencia = conexion.prepareStatement(select);
			sentencia.setString(1, codOe);
			sentencia.setString(2, codCurso);
			result = sentencia.executeQuery();
			while (result.next()){
				if (cont <= 5)
					System.out.printf("%5s%-8s |", "", result.getString(1));
				else {
					System.out.printf("\n%5s%-8s |", "", result.getString(1));
					cont = 1;
				}
				cont ++;
			}
			result.close();
			sentencia.close();
			result.close();
		} catch (SQLException e){e.printStackTrace();}
		BddConexion.closeConexion(conexion);
	}

}

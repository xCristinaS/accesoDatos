package ejercicio14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ejercicio06.Teclado;
import ejercicio11.BddConexion;

public class Ejercicio14 {

	public static void main(String[] args) {
		// Mostrar dónde se encuentra un profesor ahora, es decir, en el momento actual.
		Connection conexion = BddConexion.newConexion("horario");
		String select = "select codOe, codCurso, CodAsignatura from horario where codTramo = ? and CodAsignatura in (select CodAsignatura from reparto where codprof = ?);";
		String codProf, codTramo = ""; String ahora, dia; int hora, min;
		PreparedStatement sentencia; ResultSet result;
		SimpleDateFormat format = new SimpleDateFormat("EEEE:HH:mm");
		
		System.out.print("Introduzca el codigo del profesor: ");
		codProf = Teclado.leerPalabra();
		System.out.println();
		ahora = format.format(new Date());
		dia = ahora.split(":")[0];
		hora = Integer.parseInt(ahora.split(":")[1]);
		min = Integer.parseInt(ahora.split(":")[2]);
		switch (dia){
		case "lunes": codTramo = "L"; break;
		case "martes": codTramo = "M"; break;
		case "miercoles": codTramo = "X"; break;
		case "jueves": codTramo = "J"; break;
		case "viernes": codTramo = "V"; break;
		}
		if (hora >= 13 && min >= 45 && (hora < 14 & min < 45))
			codTramo += 6;
		else if (hora >= 12 && min >= 45 && (hora < 13 & min < 45))
			codTramo += 5;
		else if (hora >= 11 && min >= 45 && (hora < 12 & min < 45))
			codTramo += 4;
		else if (hora >= 10 && min >= 15 && (hora < 11 & min < 15))
			codTramo += 3;
		else if (hora >= 9 && min >= 15 && (hora < 10 & min < 15))
			codTramo += 2;
		else if (hora >= 8 && min >= 15 && (hora < 9 & min < 15))
			codTramo += 1;
		try {
			sentencia = conexion.prepareStatement(select);
			sentencia.setString(1, codTramo);
			sentencia.setString(2, codProf);
			result = sentencia.executeQuery();
			while (result.next())
				System.out.println("Curso: " + result.getString(1) +" "+ result.getString(2) + " | Asignatura: " + result.getString(3));
			
			result.close();
			sentencia.close();
			result.close();
		} catch (SQLException e){e.printStackTrace();}
		BddConexion.closeConexion(conexion);
	}

}

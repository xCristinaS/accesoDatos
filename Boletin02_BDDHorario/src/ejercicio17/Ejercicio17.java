package ejercicio17;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import ejercicio06.Teclado;
import ejercicio11.BddConexion;

public class Ejercicio17 {

	public static void main(String[] args) {
		String codOe, codCur, codAsig; Connection conexion = BddConexion.newConexion("horario");
		String sql = "{ call dameProfeHoras(?, ?, ?, ?, ?)}";
		CallableStatement llamada; 
		System.out.print("Indique oferta educativa: ");
		codOe = Teclado.leerPalabra();
		System.out.print("Indique curso: ");
		codCur = Teclado.leerPalabra();
		System.out.print("Indique asignatura: ");
		codAsig = Teclado.leerPalabra();
		
		try {
			llamada = conexion.prepareCall(sql);
			llamada.setString(1, codOe);
			llamada.setString(2, codCur);
			llamada.setString(3, codAsig);
			llamada.registerOutParameter(4, Types.INTEGER);
			llamada.registerOutParameter(5, Types.VARCHAR);
			llamada.executeUpdate();
			System.out.printf("Horas: %s \nProfesor:%s",llamada.getString(4), llamada.getString(5));
			llamada.close();
		} catch (SQLException e){e.printStackTrace();}
		
		BddConexion.closeConexion(conexion);

	}

}

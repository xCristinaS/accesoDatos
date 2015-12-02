package ejercicio16;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import ejercicio06.Teclado;
import ejercicio11.BddConexion;

public class Ejercicio16 {

	public static void main(String[] args) {
		// Realiza una función en MySql que dado un curso, devuelva el nombre del tutor. Haz un programa en java que llame a dicha función y muestre por consola el resultado.
		String codOe, codCur; Connection conexion = BddConexion.newConexion("horario");
		String sql = "{ ? = call dameTutor(?, ?)}";
		CallableStatement llamada; 
		System.out.print("Indique oferta educativa: ");
		codOe = Teclado.leerPalabra();
		System.out.print("Indique curso: ");
		codCur = Teclado.leerPalabra();
		
		try {
			llamada = conexion.prepareCall(sql);
			llamada.registerOutParameter(1, Types.VARCHAR);
			llamada.setString(2, codOe);
			llamada.setString(3, codCur);
			llamada.executeUpdate();
			System.out.println(llamada.getString(1));
			llamada.close();
		} catch (SQLException e){e.printStackTrace();}
		
		BddConexion.closeConexion(conexion);
	}
}

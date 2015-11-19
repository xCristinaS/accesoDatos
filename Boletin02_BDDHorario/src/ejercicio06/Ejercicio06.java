package ejercicio06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ejercicio06.Teclado.TipoEntre;

public class Ejercicio06 {
	/*
	Mostrar todos los datos de los profesores ordenados por:
		a) Fecha de nacimiento en orden ascendente.
		b) Fecha de alta en el sistema en orden descendente.
	*/
	public static void main(String[] args) {
		int resp;
		
		System.out.println("¿Cómo quiere mostrar los profesores?");
		System.out.println("1. Fecha de nacimiento en orden ascendente.");
		System.out.println("2. Fecha de alta en el sistema en orden descendente.");
		System.out.print("Respuesta: ");
		resp = Teclado.leerIntEntre(1, 2, TipoEntre.AMBOS_INC);
		ResultSet resultadoSQL;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/horario", "root", "root");
			String select = ""; 
			
			switch (resp){
			case 1: 
				select = "select * from profesor order by fechaDeNacimiento ASC";
				break;
			case 2: 
				select = "select * from profesor order by alta desc";
				break;
			}
			System.out.println();
			PreparedStatement sentencia = conexion.prepareStatement(select);
			resultadoSQL = sentencia.executeQuery();
			while (resultadoSQL.next()){
				System.out.printf("%s | %-30s | %15s | %s\n",resultadoSQL.getString(1), resultadoSQL.getString(2), resultadoSQL.getString(3), resultadoSQL.getString(4));
			}
			sentencia.close();
			resultadoSQL.close();
			conexion.close();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e){}
	}

}

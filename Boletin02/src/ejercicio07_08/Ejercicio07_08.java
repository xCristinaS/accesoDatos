package ejercicio07_08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Ejercicio07_08 {
	/*
	Mostrar todos los datos de los profesores y de los cursos de los que son tutores. Si no son
	tutores, que aparezca “Este profesor no es tutor” en lugar de los datos del curso.
	*/
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/horario", "root", "root");
			String select = "select p.*, CodOE, CodCurso from profesor p, curso where CodProf = tutor"; ResultSet resultadoSQL;
			PreparedStatement sentencia; 
			
			sentencia = conexion.prepareStatement(select);
			resultadoSQL = sentencia.executeQuery();
			while (resultadoSQL.next()){
				System.out.printf("%s | %-30s | %s | %-10s | %s | %s\n",resultadoSQL.getString(1), resultadoSQL.getString(2), resultadoSQL.getString(3), resultadoSQL.getString(4), resultadoSQL.getString(5), resultadoSQL.getString(6));
			}
			
			select = "select * from profesor where CodProf not in (select tutor from curso)";
			sentencia = conexion.prepareStatement(select);
			resultadoSQL = sentencia.executeQuery();
			while (resultadoSQL.next()){
				System.out.printf("%s | %-30s | %s | %-10s | %s\n",resultadoSQL.getString(1), resultadoSQL.getString(2), resultadoSQL.getString(3), resultadoSQL.getString(4), "No es tutor.");
			}
			
			// Ejercicio 08
			/*
			Del resultado de la consulta del ejercicio anterior, muestra el nombre de la columna, el tipo
			de dato de dicha columna, si puede contener valores nulos y el máximo ancho de caracteres
			de la columna.
			*/
			ResultSetMetaData metadatos; int numColumnas;
			System.out.println("\n EJERCICIO 08");
			metadatos = resultadoSQL.getMetaData();
			numColumnas = metadatos.getColumnCount();
			for (int i = 1; i <= numColumnas; i++){
				System.out.println("- COLUMNA" +i+":");
				System.out.println("\tNombre columnas: " + metadatos.getColumnName(i));
				System.out.println("\tTipo: " + (metadatos.getColumnTypeName(i)).toLowerCase());
				System.out.printf("\t¿Puede ser nula?: %s\n", metadatos.isNullable(i)== 0? "no":"si");
				System.out.println("\tMáximo de caracteres: " + metadatos.getColumnDisplaySize(i) + "\n");
			}
			sentencia.close();
			resultadoSQL.close();
			conexion.close();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e){}
	}

}

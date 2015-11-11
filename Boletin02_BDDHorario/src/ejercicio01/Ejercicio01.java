package ejercicio01;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio01 {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/horario", "root", "root");
			DatabaseMetaData datos = conexion.getMetaData();
			ResultSet result = datos.getTables(null, null, null, null);
			String catalogo, esquema, tabla, tipo, nombreCol, tamanioCol, tipoCol, pk = "";
			
			System.out.println("INFORMACIÓN DE LA BDD:");
			while (result.next()){
				catalogo = result.getString(1);
				esquema = result.getString(2);
				tabla = result.getString(3);
				tipo = result.getString(4);
				System.out.printf("- Tipo: %s, Catálogo: %s, Esquema: %s, Tabla: %s\n", tipo, catalogo, esquema, tabla);
			}
			System.out.println("\nINFORMACIÓN DE LAS TABLAS:");
			result = datos.getColumns(null, null, null, null);
			while (result.next()){
				tabla = (result.getString(3)).toUpperCase();
				nombreCol = result.getString("COLUMN_NAME");
				tipoCol = result.getString("TYPE_NAME");
				tamanioCol = result.getString("COLUMN_SIZE");
				System.out.printf("- Tabla: %s, NombreCol: %s, TipoCol: %s, TamanioCol: %s\n", tabla, nombreCol, tipoCol, tamanioCol);
			}
			System.out.println("\nINFORMACIÓN DE LAS ClAVES PRIMARIAS:");
			result = datos.getPrimaryKeys(null, null, null);
			while (result.next()){
				//tabla = (result.getString(3)).toUpperCase();
				pk = result.getString(4) + " ";
				System.out.printf("- Tabla: %s, Clave Primaria: %s\n", tabla = "", pk);
			}
			
			result.close();
			conexion.close();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e){}
	}

}

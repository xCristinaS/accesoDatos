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
			String catalogo, esquema, tabla, tipo, nombreCol, tamanioCol, tipoCol;
			
			result = datos.getColumns(null, null, "curso", null);
			System.out.println("INFO COLUMNAS TABLA CURSO");
			while (result.next()){
				System.out.printf("- Nombre columna: %s\n", result.getString("COLUMN_NAME"));
				System.out.printf("- Tipo: %s\n", result.getString("TYPE_NAME"));
				System.out.printf("- Tamañio: %s\n", result.getString("COLUMN_SIZE"));
				System.out.printf("- Nula: %s\n\n", result.getString("IS_NULLABLE"));
			}
			System.out.println();
			System.out.println("INFO PRIMARY KEYS TABLA CURSO");
			result = datos.getPrimaryKeys(null, null, "curso");
			while(result.next()){
				System.out.println("- " + result.getString("COLUMN_NAME"));
			}
			System.out.println();
			System.out.println("INFO CLAVES AJENAS QUE USAN LAS PK DE LA TABLA CURSO");
			result = datos.getExportedKeys(null, "horario", "curso");
			while(result.next()){
				System.out.printf("- Tabla FK: %s, Clave primaria: %s\n", result.getString("FKTABLE_NAME"), result.getString("FKCOLUMN_NAME"));
			}
			System.out.println();
			System.out.println("INFO CLAVES AJENAS QUE USAN LA TABLA CURSO");
			result = datos.getImportedKeys(null, "horario", "curso");
			while(result.next()){
				System.out.printf("- Tabla FK: %s, Clave primaria: %s\n", result.getString("FKTABLE_NAME"), result.getString("FKCOLUMN_NAME"));
			}
			
			System.out.println("\n\n-------------------- PRUEBAS --------------------");
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
			
			result.close();
			conexion.close();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e){}
	}

}

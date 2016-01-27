package ejercicio02;

import Utilidad.BddConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cristina on 16/01/2016.
 */
public class Ejercicio02 {
    public static void main(String[] args) {
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        String insert = "insert into producto values (?, ?, ?, ?, ?, ?);";
        PreparedStatement sentencia;
        FileInputStream lector;

        try {
            sentencia = conexion.prepareStatement(insert);
            sentencia.setString(1, "prod4");
            sentencia.setString(2, "descripción del producto 4.");
            sentencia.setDouble(3, 354.45);
            sentencia.setInt(4, 10);
            sentencia.setString(5, "Hola soy un CLOB");

            File image = new File("usb.png");
            lector = new FileInputStream(image);
            sentencia.setBinaryStream(6, lector, (int) image.length());

            sentencia.execute();
            sentencia.close();
            lector.close();
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }
        BddConnection.closeConexion(conexion);
    }
}

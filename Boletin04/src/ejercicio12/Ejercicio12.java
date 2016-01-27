package ejercicio12;

import Utilidad.BddConnection;
import objetosBDD.Direccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Cristina on 26/01/2016.
 */
public class Ejercicio12 {
    public static void main(String[] args) {
        // 12. Realiza un programa que actualice la provincia.

        String select = "select direccion from cliente where idCliente = 3;", update;
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        PreparedStatement sentencia;
        Direccion dirCliente;
        ResultSet resul;

        try {
            sentencia = conexion.prepareStatement(select);
            resul = sentencia.executeQuery();
            if (resul.next()){
                dirCliente = Direccion.newInstance(resul.getString(1));
                dirCliente.getPoblacion().getProvincia().setProvincia("Granada update");
                update = "update cliente set direccion = " + dirCliente.getStringTypeT_Direccion() + " where idCliente = 3";
                sentencia = conexion.prepareStatement(update);
                sentencia.executeUpdate();
            }
            resul.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

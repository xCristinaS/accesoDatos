package ejercicio13;

import Utilidad.BddConnection;
import objetosBDD.Direccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Cristina on 26/01/2016.
 */
public class Ejercicio13 {
    public static void main(String[] args) {
        //13. Realiza un programa que actualice la comunidad autónoma.

        String select = "select direccion from cliente where idCliente = 1;", update;
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        PreparedStatement sentencia;
        Direccion dirCliente;
        ResultSet resul;

        try {
            sentencia = conexion.prepareStatement(select);
            resul = sentencia.executeQuery();
            if (resul.next()){
                dirCliente = Direccion.newInstance(resul.getString(1));
                dirCliente.getPoblacion().getProvincia().setComunidadAutonoma("Comunidad update");
                update = "update cliente set direccion = CAST(" + dirCliente.getStringTypeT_Direccion() + " AS t_direccion) where idCliente = 1";
                sentencia = conexion.prepareStatement(update);
                sentencia.executeUpdate();
                System.out.println(dirCliente);
                System.out.println(dirCliente.getStringTypeT_Direccion());
            }
            resul.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

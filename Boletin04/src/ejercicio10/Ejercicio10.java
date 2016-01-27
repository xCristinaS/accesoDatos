package ejercicio10;

import Utilidad.BddConnection;
import objetosBDD.Direccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Cristina on 26/01/2016.
 */
public class Ejercicio10 {
    public static void main(String[] args) {
        //10. Realiza un programa que dada una dirección muestre todos los clientes que tienen dicha
        //dirección.

        String dirBuscar = "Calle clavel";
        String select = "select idCliente, nombre, direccion from cliente;";
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        PreparedStatement sentencia;
        ResultSet resul;
        Direccion dirCliente;

        try {
            sentencia = conexion.prepareStatement(select);
            resul = sentencia.executeQuery();
            while (resul.next()){
                dirCliente = Direccion.newInstance(resul.getString(3));
                if (dirCliente.getCalle().equals(dirBuscar))
                    System.out.printf("idCliente: %d, Nombre: %s\n", resul.getInt(1), resul.getString(2));
            }
            sentencia.close();
            resul.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

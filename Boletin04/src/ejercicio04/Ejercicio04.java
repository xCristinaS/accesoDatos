package ejercicio04;

import Utilidad.BddConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Cristina on 25/01/2016.
 */
public class Ejercicio04 {
    public static void main(String[] args) {
        //4. Realiza un programa que dado un cliente muestre:
        //4.1. La población (campo compuesto)
        //4.2. La población (campo simple)
        //4.3. La provincia (campo compuesto)
        //4.4. La provincia (campo simple)
        //4.5. La comunidad autónoma
        //4.6. La lista de teléfonos
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        PreparedStatement sentencia;
        ResultSet result;
        String select = "select (direccion).poblacion from cliente where idCliente = 1;";
        try {
            sentencia = conexion.prepareStatement(select);
            result = sentencia.executeQuery();
            if (result.next())
                System.out.printf("Población (campo compuesto): %s", result.getString(1));

            select = "select (direccion).poblacion.poblacion from cliente where idcliente = 1;";
            sentencia = conexion.prepareStatement(select);
            result = sentencia.executeQuery();
            if (result.next())
                System.out.printf("\nPoblación (campo simple): %s", result.getString(1));

            select = "select (direccion).poblacion.provincia from cliente where idcliente = 1;";
            sentencia = conexion.prepareStatement(select);
            result = sentencia.executeQuery();
            if (result.next())
                System.out.printf("\nProvincia (campo compuesto): %s", result.getString(1));

            select = "select (direccion).poblacion.provincia.provincia from cliente where idcliente = 1;";
            sentencia = conexion.prepareStatement(select);
            result = sentencia.executeQuery();
            if (result.next())
                System.out.printf("\nProvincia (campo simple): %s", result.getString(1));

            select = "select (direccion).poblacion.provincia.comunidadAutonoma from cliente where idcliente = 1;";
            sentencia = conexion.prepareStatement(select);
            result = sentencia.executeQuery();
            if (result.next())
                System.out.printf("\nComunidad autónoma: %s", result.getString(1));

            select = "select telefonos from cliente where idcliente = 1;";
            sentencia = conexion.prepareStatement(select);
            result = sentencia.executeQuery();
            if (result.next())
                System.out.printf("\nTeléfonos: %s", result.getString(1));

            result.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

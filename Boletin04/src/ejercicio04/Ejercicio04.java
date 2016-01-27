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
        //4.1. La poblaci�n (campo compuesto)
        //4.2. La poblaci�n (campo simple)
        //4.3. La provincia (campo compuesto)
        //4.4. La provincia (campo simple)
        //4.5. La comunidad aut�noma
        //4.6. La lista de tel�fonos
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        PreparedStatement sentencia;
        ResultSet result;
        String select = "select (direccion).poblacion from cliente where idCliente = 1;";
        try {
            sentencia = conexion.prepareStatement(select);
            result = sentencia.executeQuery();
            if (result.next())
                System.out.printf("Poblaci�n (campo compuesto): %s", result.getString(1));

            select = "select (direccion).poblacion.poblacion from cliente where idcliente = 1;";
            sentencia = conexion.prepareStatement(select);
            result = sentencia.executeQuery();
            if (result.next())
                System.out.printf("\nPoblaci�n (campo simple): %s", result.getString(1));

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
                System.out.printf("\nComunidad aut�noma: %s", result.getString(1));

            select = "select telefonos from cliente where idcliente = 1;";
            sentencia = conexion.prepareStatement(select);
            result = sentencia.executeQuery();
            if (result.next())
                System.out.printf("\nTel�fonos: %s", result.getString(1));

            result.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

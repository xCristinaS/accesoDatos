package ejercicio08;

import Utilidad.BddConnection;
import objetosBDD.Telefono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Cristina on 26/01/2016.
 */
public class Ejercicio08 {
    public static void main(String[] args) {
        //8. Realiza un programa que dado un teléfono muestre todos los clientes que tienen dicho
        //teléfono.

        String select = "select idCliente, nombre, nif, telefonos from cliente;";
        PreparedStatement sentencia;
        ResultSet result, resultTlf = null;
        Telefono tel;
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        boolean mostrado = false;

        try {
            sentencia = conexion.prepareStatement(select);
            result = sentencia.executeQuery();
            while(result.next()) {
                mostrado = false;
                resultTlf = result.getArray(4).getResultSet();
                while (!mostrado && resultTlf.next()){
                    tel = Telefono.newInstance(resultTlf.getString(2));
                    if (tel.getNumeroTlf().getNumero().equals("444444444")) {
                        System.out.printf("- idCliente: %d, Nombre: %s, DNI: %s\n", result.getInt(1), result.getString(2), result.getString(3));
                        mostrado = true;
                    }
                }
            }
            resultTlf.close();
            sentencia.close();
            result.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

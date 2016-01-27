package ejercicio06;

import Utilidad.BddConnection;
import objetosBDD.Telefono;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Cristina on 25/01/2016.
 */
public class Ejercicio06 {
    public static void main(String[] args) {
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        PreparedStatement sentencia;
        ResultSet result, aux;
        String select = "select telefonos from cliente where idCliente = 3;", update, cadenaArrayTelef="";
        ArrayList<Telefono> listaTlf = new ArrayList<>();
        try {
            sentencia = conexion.prepareStatement(select);
            result = sentencia.executeQuery();
            if (result.next()) {
                aux = result.getArray(1).getResultSet();
                while (aux.next())
                    listaTlf.add(Telefono.newInstance(aux.getString(2)));

                aux.close();
            }

            for (int i = 0; i < listaTlf.size(); i++)
                listaTlf.get(i).getNumeroTlf().setNumero("555");

            cadenaArrayTelef = Telefono.newArrayT_Telefono(listaTlf);

            update = "update cliente set telefonos = " +  cadenaArrayTelef + " where idcliente = 3;";
            sentencia = conexion.prepareStatement(update);
            sentencia.executeUpdate();
            result.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

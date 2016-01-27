package ejercicio09;

import Utilidad.BddConnection;
import objetosBDD.Telefono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristina on 26/01/2016.
 */
public class Ejercicio09 {
    public static void main(String[] args) {
        //9. Realiza un programa que dado un teléfono modifique el prefijo del país a todos los clientes
        //que disponen de dicho teléfono.

        String select = "select idCliente, telefonos from cliente;", update;
        PreparedStatement sentencia;
        ResultSet result, resultTlf = null;
        Telefono tel;
        ArrayList<Telefono> listaTlf = new ArrayList<>();
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        int idCliente;

        try {
            sentencia = conexion.prepareStatement(select);
            result = sentencia.executeQuery();
            while(result.next()) {
                idCliente = result.getInt(1);
                resultTlf = result.getArray(2).getResultSet();
                listaTlf.clear();
                while (resultTlf.next()){
                    tel = Telefono.newInstance(resultTlf.getString(2));
                    listaTlf.add(tel);
                    if (tel.getNumeroTlf().getNumero().equals("444444444"))
                        tel.getNumeroTlf().setPrefijoPais("+23");
                }
                update = "update cliente set telefonos = " + Telefono.newArrayT_Telefono(listaTlf)+ " where idCliente = ?";
                sentencia = conexion.prepareStatement(update);
                sentencia.setInt(1,idCliente);
                sentencia.executeUpdate();
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

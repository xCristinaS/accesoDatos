package ejercicio07;

import Utilidad.BddConnection;
import Utilidad.Teclado;
import objetosBDD.Telefono;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Cristina on 26/01/2016.
 */
public class Ejercicio07 {
    public static void main(String[] args) {
        //7. Realiza un programa que actualice un teléfono de un cliente. Hay que preguntarle al usuario
        //el teléfono antiguo y el nuevo y sustituir uno por otro.

        int idCliente;
        String telefonoAntiguo, telefonoNuevo, update, select;
        PreparedStatement sentencia;
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        ResultSet result, aux;
        ArrayList<Telefono> listaTlf = new ArrayList<>();

        System.out.print("Indique el id del cliente: ");
        idCliente = Teclado.leerInt();
        System.out.print("Indique el número de teléfono antiguo: ");
        telefonoAntiguo = Teclado.leerPalabra();
        System.out.print("Indique el nuevo número de teléfono: ");
        telefonoNuevo = Teclado.leerPalabra();

        select = "select telefonos from cliente where idCliente = ?;";

        try {
            sentencia = conexion.prepareStatement(select);
            sentencia.setInt(1, idCliente);
            result = sentencia.executeQuery();

            if (result.next()) {
                aux = result.getArray(1).getResultSet();
                while (aux.next())
                    listaTlf.add(Telefono.newInstance(aux.getString(2)));
                aux.close();
            }

            for (Telefono t: listaTlf)
                if (t.getNumeroTlf().getNumero().equals(telefonoAntiguo))
                    t.getNumeroTlf().setNumero(telefonoNuevo);

            update = "update cliente set telefonos = " + Telefono.newArrayT_Telefono(listaTlf)+ " where idCliente = ?;";
            sentencia = conexion.prepareStatement(update);
            sentencia.setInt(1, idCliente);
            sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

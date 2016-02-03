package examen;

import utilidad.BddConnection;
import utilidad.Teclado;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cristina on 03/02/2016.
 */
public class Ejercicio01 {
    // actualizar uno de los precios a petición del usuario.
    public static void main(String[] args) {
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        PreparedStatement sentencia;
        String update, idProd;
        int resp, returnUpdate;
        double nuevoPrecio;

        System.out.print("Indique el id del producto que desea modificar: ");
        idProd = Teclado.leerPalabra();
        System.out.println("¿Qué precio desea modificar?: ");
        System.out.println("1. Temporada alta.");
        System.out.println("2. Temporada media.");
        System.out.println("3. Temporada baja.");
        System.out.print("Respuesta: ");
        resp = Teclado.leerIntEntre(1, 3, Teclado.TipoEntre.AMBOS_INC);
        System.out.print("Indique el nuevo precio: ");
        nuevoPrecio = Teclado.leerDouble();
        resp--;
        update = "update producto set pvp["+ resp +"] = "+ nuevoPrecio + " where idProducto = ?";
        try {
            sentencia = conexion.prepareStatement(update);
            sentencia.setString(1, idProd);
            returnUpdate = sentencia.executeUpdate();
            if (returnUpdate != 0)
                System.out.println("\nprecio actualizado");
            else
                System.out.println("\nno se pudo actualizar, probablemente el id de producto introducido no sea válido.");
            sentencia.close();
            conexion.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

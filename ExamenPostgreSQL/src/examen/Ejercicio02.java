package examen;

import utilidad.BddConnection;
import utilidad.Teclado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cristina on 03/02/2016.
 */
public class Ejercicio02 {
    // Programa que actualice la descripcion (cualquier campo) a petición del usuario.
    public static void main(String[] args) {
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        PreparedStatement sentencia;
        String update = "", idProd, nuevoNom, nuevaDesc;
        int resp, returnUpdate;
        boolean esVip;

        System.out.print("Indique el id del producto que desea modificar: ");
        idProd = Teclado.leerPalabra();
        System.out.println("¿Qué desea modificar?: ");
        System.out.println("1. Nombre.");
        System.out.println("2. Descripcion.");
        System.out.println("3. Producto vip.");
        System.out.print("Respuesta: ");
        resp = Teclado.leerIntEntre(1, 3, Teclado.TipoEntre.AMBOS_INC);
        switch (resp){
            case 1:
                System.out.print("\n indique el nuevo nombre: ");
                nuevoNom = Teclado.leerPalabra();
                update = "update producto set descripcion.nombre = '"+nuevoNom+"' where idProducto = ?";
                break;
            case 2:
                System.out.print("\n indique la nueva descripcion: ");
                nuevaDesc = Teclado.leerLinea();
                update = "update producto set descripcion.descripcion = '"+nuevaDesc+"' where idProducto = ?";
                break;
            case 3:
                System.out.println("\n ¿es un producto vip?: ");
                System.out.println("1. Si");
                System.out.println("2. No");
                System.out.print("Respuesta: ");
                resp = Teclado.leerIntEntre(1,2, Teclado.TipoEntre.AMBOS_INC);
                if (resp == 1)
                    esVip = true;
                else
                    esVip = false;

                update = "update producto set descripcion.vip = "+esVip+" where idProducto = ?";
                break;
        }

        try {
            sentencia = conexion.prepareStatement(update);
            sentencia.setString(1, idProd);
            returnUpdate = sentencia.executeUpdate();
            if (returnUpdate != 0)
                System.out.println("\nproducto actualizado");
            else
                System.out.println("\nno se pudo actualizar, probablemente el id de producto introducido no sea válido.");
            sentencia.close();
            conexion.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

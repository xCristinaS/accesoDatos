package ejercicio05;

import objetosBDD.*;
import Utilidad.BddConnection;
import org.postgresql.PGConnection;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by Cristina on 17/01/2016.
 */
public class Ejercicio05 {
    //Realiza un programa que dado un cliente muestre todos sus datos y todos los datos de sus
    //ventas, líneas de ventas y productos.
    public static void main(String[] args)  {
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        String select = "select c.*, l.idventa, l.numlinea, fechaventa, facturada, cantidad, p.* from cliente c, venta v, lineaventa l, producto p where idcliente = cliente and v.idventa = l.idventa " +
                        "and producto = idproducto and idcliente = '1';";
        String nomC, nif, idProducto, descripcion, web;
        int idCliente, idVenta, cantidad, numLinea, stock, cont = 0, auxIdVenta = 0;
        boolean facturada;
        Date fechaVenta;
        double pvp;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        Array telefonos;
        ResultSet resultTlf;

        ResultSet result;
        try {
            ((PGConnection)conexion).addDataType("t_direccion", Direccion.class);
            ((PGConnection)conexion).addDataType("t_poblacion", Poblacion.class);
            ((PGConnection)conexion).addDataType("t_provincia", Provincia.class);
            PreparedStatement sentencia = conexion.prepareStatement(select);
            result = sentencia.executeQuery();
            while (result.next()){
                if (cont == 0){
                    idCliente = result.getInt(1);
                    nomC = result.getString(2);
                    nif = result.getString(3);
                    telefonos = result.getArray(4);
                    resultTlf = telefonos.getResultSet();
                    String direccion = result.getString(5);
                    System.out.printf("DATOS CLIENTE:\n\n- idCliente: %d, nombre: %s, nif: %s\n- Dirección: %s\n\n", idCliente, nomC, nif, direccion);
                    System.out.println("- Teléfonos:");
                    while (resultTlf.next())
                        System.out.println("- " + resultTlf.getString(2));

                    System.out.printf("\nDATOS VENTAS:");
                }

                idVenta = result.getInt(6);
                numLinea = result.getInt(7);
                fechaVenta = result.getDate(8);
                facturada = result.getBoolean(9);
                cantidad = result.getInt(10);
                idProducto = result.getString(11);
                descripcion = result.getString(12);
                pvp = result.getDouble(13);
                stock = result.getInt(14);
                web = result.getString(15);

                if (auxIdVenta != idVenta || auxIdVenta == 0)
                    System.out.printf("\n\n - idVenta: %d, fecha de venta: %s, facturada: %b", idVenta, formato.format(fechaVenta), facturada);

                System.out.printf("\n\t- Número de línea: %d, cantidad: %d\n" +
                                "\t\t- idProducto: %s, descripción: %s, PVP: %.2f, stock: %d, Web: %s", numLinea, cantidad, idProducto, descripcion, pvp, stock, web);

                auxIdVenta = idVenta;
                cont++;
            }
            result.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

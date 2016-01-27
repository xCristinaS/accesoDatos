package ejercicio03;

import Utilidad.BddConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cristina on 16/01/2016.
 */
public class Ejercicio03 {
    public static void main(String[] args) {
        //Realiza un programa que inserte dos clientes. A continuación, insértale dos nuevas ventas a
        //cada uno con tres productos en cada venta.

        // Cliente: idCliente, nombre, nif, Telefonos[] (type), direccion (type).
            // telefono: tipo (enum), numero (type)
                // numeroTlf: prefijoPais, numero
            // direccion: calle, numero, poblacion (type), codPostal
                // poblacion: poblacion, provincia (type), pais
                    // provincia: provincia, comunidadAutonoma.

        // Venta: idVenta, fechaVenta, facturada (boolean), cliente
        // LineasVenta: idVenta, numLinea, cantidad, producto
        // Producto idproducto, descripcion, pvp, stockactual, web, imgprod
        Connection conexion = BddConnection.newConexionPostgreSQL("Ventas");
        PreparedStatement sentencia;
        String insert;
        try {
            /*
            insert = "insert into cliente values ('6', 'Juanito', '12345678p', CAST (ARRAY [('FIJO_PERSONAL',('+34', '956789547')), ('MOVIL_PERSONAL',('+34', '147852369'))] as t_telefono ARRAY), ('Calle clavel', '105', ('La Línea', ('Cádiz', 'Andalucía'), 'España'), '11300'))";
            sentencia = conexion.prepareStatement(insert);
            sentencia.execute();
            insert = "insert into cliente values ('2', 'Maria', '22334489p', CAST (ARRAY [('FIJO_TRABAJO',('+34', '332211446')), ('MOVIL_TRABAJO',('+34', '123456788'))] as t_telefono ARRAY), ('Rocio', '12', ('Dos hermanas', ('Sevilla', 'Andalucia'), 'España'), '11312'))";
            sentencia = conexion.prepareStatement(insert);
            sentencia.execute();
            */
            insert = "insert into venta  values (1, now(), true, 1);";
            sentencia = conexion.prepareStatement(insert);
            sentencia.execute();
            insert = "insert into venta values (2, now(),  true, 1);";
            sentencia = conexion.prepareStatement(insert);
            sentencia.execute();
            insert = "insert into venta values (3, now(), true, 2);";
            sentencia = conexion.prepareStatement(insert);
            sentencia.execute();
            insert = "insert into venta values (4, now(), true, 2);";
            sentencia = conexion.prepareStatement(insert);
            sentencia.execute();

            insert = "insert into lineaventa values (1, 1, 1, 'prod1');";
            sentencia = conexion.prepareStatement(insert);
            sentencia.execute();
            insert = "insert into lineaventa values (1, 2, 3, 'prod2');";
            sentencia = conexion.prepareStatement(insert);
            sentencia.execute();
            insert = "insert into lineaventa values (1, 3, 2, 'prod3');";
            sentencia = conexion.prepareStatement(insert);
            sentencia.execute();
            insert = "insert into lineaventa values (2, 4, 3, 'prod4');";
            sentencia = conexion.prepareStatement(insert);
            sentencia.execute();
            insert = "insert into lineaventa values (2, 5, 1, 'prod2');";
            sentencia = conexion.prepareStatement(insert);
            sentencia.execute();
            insert = "insert into lineaventa values (2, 6, 6, 'prod1');";
            sentencia = conexion.prepareStatement(insert);
            sentencia.execute();

            sentencia.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        BddConnection.closeConexion(conexion);
    }
}

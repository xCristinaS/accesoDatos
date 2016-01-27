package ejercicio02;

import Utilidad.BddConnection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Cristina on 16/01/2016.
 */
public class PruebaLeerImagen {
    public static void main(String[] args) {
        Connection connection = BddConnection.newConexionPostgreSQL("Ventas");
        File image;
        FileOutputStream out = null;
        InputStream is = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs;

            rs = statement.executeQuery("SELECT imgprod FROM Producto WHERE idproducto = 'prod1'");

            while(rs.next()){
                image = new File("nuevaImagen.jpg");
                out = new FileOutputStream(image);

                byte[] buffer = new byte[1];
                is = rs.getBinaryStream(1);

                while (is.read(buffer) > 0) {
                    out.write(buffer);
                }
            }
            statement.close();
            rs.close();
            if (out != null) {
                out.close();
                is.close();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        BddConnection.closeConexion(connection);
    }
}

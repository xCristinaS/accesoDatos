package objetosBDD;

import org.postgresql.util.PGobject;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/**
 * Created by Cristina on 16/01/2016.
 */
public class Provincia {

    private String provincia;
    private String comunidadAutonoma;

    public Provincia(String provincia, String comunidadAutonoma){
        this.provincia = provincia;
        this.comunidadAutonoma = comunidadAutonoma;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComunidadAutonoma() {
        return comunidadAutonoma;
    }

    public void setComunidadAutonoma(String comunidadAutonoma) {
        this.comunidadAutonoma = comunidadAutonoma;
    }

    public String getStringTypeT_Provincia(){
        return String.format("('%s', '%s')", provincia, comunidadAutonoma);
    }

    public static Provincia newInstance(String cadenaProv){
        String aux[];
        cadenaProv = cadenaProv.replaceAll("\"", "");
        cadenaProv = cadenaProv.replaceAll("\\)", "");
        cadenaProv = cadenaProv.replaceAll("\\(", "");
        aux = cadenaProv.split(",");

        return new Provincia("", "");
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "provincia='" + provincia + '\'' +
                ", comunidadAutonoma='" + comunidadAutonoma + '\'' +
                '}';
    }
}

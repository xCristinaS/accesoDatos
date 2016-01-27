package objetosBDD;

import org.postgresql.util.PGobject;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.List;

/**
 * Created by Cristina on 16/01/2016.
 */
public class Telefono{

    private TiposTlf tipo;
    private NumeroTlf numeroTlf;

    public Telefono(TiposTlf tipo, NumeroTlf numeroTlf){
        this.tipo = tipo;
        this.numeroTlf = numeroTlf;
    }

    public TiposTlf getTipo() {
        return tipo;
    }

    public void setTipo(TiposTlf tipo) {
        this.tipo = tipo;
    }

    public NumeroTlf getNumeroTlf() {
        return numeroTlf;
    }

    public void setNumeroTlf(NumeroTlf numeroTlf) {
        this.numeroTlf = numeroTlf;
    }

    public static Telefono newInstance(String cadenaTlf){
        TiposTlf tipo = null;
        String aux[];
        cadenaTlf = cadenaTlf.replaceAll("\"", "");
        cadenaTlf = cadenaTlf.replaceAll("\\)", "");
        cadenaTlf = cadenaTlf.replaceAll("\\(", "");
        aux = cadenaTlf.split(",");
        for (TiposTlf t: TiposTlf.values())
            if (aux[0].equals(t.toString()))
                tipo = t;

        return new Telefono(tipo, new NumeroTlf(aux[1], aux[2]));
    }

    public static String newArrayT_Telefono(List<Telefono> listaTlf){
        String cadenaArrayTelef = "CAST(ARRAY[";
        for (int i = 0; i < listaTlf.size(); i++) {
            cadenaArrayTelef += listaTlf.get(i).getStringTypeT_telefono();
            if (i != listaTlf.size()-1)
                cadenaArrayTelef += ", ";
            else
                cadenaArrayTelef += "] AS t_telefono ARRAY)";
        }
        return cadenaArrayTelef;
    }

    public String getStringTypeT_telefono(){
        return String.format("('%s', %s)", getTipo(), getNumeroTlf().getStringTypeT_numeroTlf());
    }

    @Override
    public String toString() {
        return "Telefono{" +
                "tipo=" + tipo +
                ", numeroTlf=" + numeroTlf +
                '}';
    }
}

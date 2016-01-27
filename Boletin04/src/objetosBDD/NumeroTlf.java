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
public class NumeroTlf{

    private String prefijoPais;
    private String numero;

    public NumeroTlf(String prefijoPais, String numero){
        this.prefijoPais = prefijoPais;
        this.numero = numero;
    }

    public String getPrefijoPais() {
        return prefijoPais;
    }

    public void setPrefijoPais(String prefijoPais) {
        this.prefijoPais = prefijoPais;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getStringTypeT_numeroTlf(){
        return String.format("('%s', '%s')", getPrefijoPais(), getNumero());
    }

    @Override
    public String toString() {
        return "NumeroTlf{" +
                "prefijoPais='" + prefijoPais + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}

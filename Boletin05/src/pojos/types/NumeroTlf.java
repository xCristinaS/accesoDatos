package pojos.types;

import javax.persistence.Embeddable;

/**
 * Created by Cristina on 16/02/2016.
 */

@Embeddable
public class NumeroTlf {

    private String prefijo, numero;

    public NumeroTlf(String prefijo, String numero){
        this.prefijo = prefijo;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return String.format(" (%s) %s", prefijo, numero);
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object obj) {
        boolean r = false;
        if (obj instanceof NumeroTlf){
            NumeroTlf n = (NumeroTlf) obj;
            if (prefijo.equals(n.prefijo) && numero.equals(n.numero))
                r = true;
        }
        return r;
    }
}

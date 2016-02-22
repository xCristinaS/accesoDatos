package pojos.types;

import javax.persistence.*;

/**
 * Created by Cristina on 16/02/2016.
 */

@Embeddable
public class Telefono {

    @Enumerated
    private TipoTlf tipo;

    @Embedded
    private NumeroTlf numeroTlf;

    public Telefono(TipoTlf tipo, NumeroTlf numeroTlf){
        this.tipo = tipo;
        this.numeroTlf = numeroTlf;
    }

    @Override
    public String toString() {
        return String.format("- Tipo: %s, Número: %s", tipo.toString().toLowerCase(), numeroTlf.toString());
    }

    public void setTipo(TipoTlf tipo) {
        this.tipo = tipo;
    }

    public void setNumeroTlf(NumeroTlf numeroTlf) {
        this.numeroTlf = numeroTlf;
    }

    public NumeroTlf getNumeroTlf() {
        return numeroTlf;
    }

    public TipoTlf getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object obj) {
        boolean r = false;
        if (obj instanceof Telefono) {
            Telefono t = (Telefono)obj;
            if (tipo == t.tipo && ((numeroTlf == null && t.numeroTlf == null) || (numeroTlf != null && t.numeroTlf != null && numeroTlf.equals(t.numeroTlf))))
                r = true;
        }
        return r;
    }
}

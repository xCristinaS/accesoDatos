package pojos.types;

import javax.persistence.Embeddable;

/**
 * Created by Cristina on 16/02/2016.
 */

@Embeddable
public class Provincia {

    private String provincia, comunidadAutonoma;

    public Provincia(String provincia, String comunidadAutonoma){
        this.provincia = provincia;
        this.comunidadAutonoma = comunidadAutonoma;
    }

    @Override
    public String toString() {
        return String.format("- Provincia: %s, Comunidad Autónoma: %s", provincia, comunidadAutonoma);
    }

    @Override
    public boolean equals(Object obj) {
        boolean r = false;
        if (obj instanceof Provincia){
            Provincia p = (Provincia) obj;
            if (provincia.equals(p.provincia) && comunidadAutonoma.equals(p.comunidadAutonoma))
                r = true;
        }
        return r;
    }
}

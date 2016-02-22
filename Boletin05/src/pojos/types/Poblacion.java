package pojos.types;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * Created by Cristina on 16/02/2016.
 */

@Embeddable
public class Poblacion {

    private String poblacion, pais;

    @Embedded
    private Provincia provincia;

    public Poblacion(String poblacion, String pais, Provincia provincia){
        this.poblacion = poblacion;
        this.pais = pais;
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return String.format("\t- Población: %s, País: %s, Provincia: \n\t\t\t\t%s", poblacion, pais, provincia.toString());
    }

    @Override
    public boolean equals(Object obj) {
        boolean r = false;
        if (obj instanceof Poblacion){
            Poblacion p = (Poblacion) obj;
            if (poblacion.equals(p.poblacion) && pais.equals(p.pais) && ((provincia != null && p.provincia != null && provincia.equals(p.provincia)) || (provincia == null && p.provincia == null)))
                r = true;
        }
        return r;
    }
}

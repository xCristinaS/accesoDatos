package pojos.types;

import javax.persistence.*;

/**
 * Created by Cristina on 16/02/2016.
 */

@Embeddable
public class Direccion {

    private String calle, codPostal;
    private byte numero;

    @Embedded
    private Poblacion poblacion;

    public Direccion(String calle, String codPostal, byte numero, Poblacion poblacion){
        this.calle = calle;
        this.codPostal = codPostal;
        this.numero = numero;
        this.poblacion = poblacion;
    }

    public Poblacion getPoblacion() {
        return poblacion;
    }

    @Override
    public String toString() {
        return String.format("- Calle: %s, Código Postal: %s, Número: %d, Población: \n\t\t%s", calle, codPostal, numero, poblacion.toString());
    }

    @Override
    public boolean equals(Object obj) {
        boolean r = false;
        if(obj instanceof Direccion){
            Direccion d = (Direccion) obj;
            if (calle.equals(d.calle) && codPostal.equals(d.codPostal) && numero == d.numero && ((poblacion != null && d.poblacion!= null && poblacion.equals(d.poblacion)) || (poblacion == null && d.poblacion == null)))
                r = true;
        }
        return r;
    }
}

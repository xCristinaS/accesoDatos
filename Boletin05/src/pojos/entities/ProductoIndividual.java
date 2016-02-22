package pojos.entities;

import javax.persistence.*;

/**
 * Created by Cristina on 16/02/2016.
 */

@Entity
public class ProductoIndividual extends Producto{

    private float pvp;

    public ProductoIndividual(String idProducto, String descripcion, int stockActual, float pvp){
        super(idProducto, descripcion, stockActual);
        this.pvp = pvp;
    }

    @Override
    public String toString() {
        return String.format("%s, PVP: %.2f", super.toString(), pvp);
    }

    @Override
    public boolean equals(Object obj) {
        boolean r = false;
        if (obj instanceof ProductoIndividual){
            ProductoIndividual p = (ProductoIndividual) obj;
            if (super.equals(p) && pvp == p.pvp)
                r = true;
        }
        return r;
    }
}

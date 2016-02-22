package pojos.entities;

import javax.persistence.*;

/**
 * Created by Cristina on 16/02/2016.
 */

@Entity
public class ProductoPorMayor extends Producto{

    private float pvp1, pvp2, pvp3;

    public ProductoPorMayor(String idProducto, String descripcion, int stockActual, float pvp1, float pvp2, float pvp3){
        super(idProducto, descripcion, stockActual);
        this.pvp1 = pvp1;
        this.pvp2 = pvp2;
        this.pvp3 = pvp3;
    }

    @Override
    public String toString() {
        return String.format("%s, PVP1: %.2f, PVP2 %.2f, PVP3: %.2f", super.toString(), pvp1, pvp2, pvp3);
    }

    @Override
    public boolean equals(Object obj) {
        boolean r = false;
        if (obj instanceof ProductoPorMayor){
            ProductoPorMayor p = (ProductoPorMayor) obj;
            if (super.equals(p) && pvp1 == p.pvp1 && pvp2 == p.pvp2 && pvp3 == p.pvp3)
                r = true;
        }
        return r;
    }
}

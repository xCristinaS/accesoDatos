package pojos.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PreRemove;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Cristina on 16/02/2016.
 */

@Entity
public class Producto {

    @Id
    protected String idProducto;
    protected String descripcion;
    protected int stockActual;

    public Producto(String idProducto, String descripcion, int stockActual){
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.stockActual = stockActual;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Descripción: %s, Stock: %d", idProducto, descripcion, stockActual);
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    @PreRemove
    private void escribirEnFichero(){
        FileWriter escritor;
        try {
            escritor = new FileWriter(new File("./eliminados.txt"), true);
            escritor.write(this.toString()+"\n\n");
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

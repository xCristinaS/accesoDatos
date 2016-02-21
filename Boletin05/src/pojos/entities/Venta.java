package pojos.entities;

import javax.persistence.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Cristina on 16/02/2016.
 */

@Entity
public class Venta {

    @Id
    private int idVenta;

    @OneToOne
    private Cliente cliente;

    @Embedded
    @ElementCollection(fetch = FetchType.EAGER)
    LinkedList<LineasVenta> lineas;

    private boolean facturada;
    private Date fechaVenta;

    public Venta() {}

    public Venta(int idVenta, boolean facturada, Cliente cliente, LinkedList<LineasVenta> lineas){
        this.idVenta = idVenta;
        this.facturada = facturada;
        this.cliente = cliente;
        fechaVenta = new Date();
        if (lineas == null)
            this.lineas = new LinkedList<LineasVenta>();
        else
            this.lineas = lineas;
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

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setLineasVentas(LinkedList<LineasVenta> lineas){
        this.lineas = lineas;
    }

    @Override
    public String toString() {
        String cadena = String.format("- Cliente: %s\n\n- Venta: %d", cliente.toString(), idVenta);
        if (lineas != null){
            cadena += "\n\t- ";
            for (LineasVenta l: lineas) {
                cadena += l.toString();
                if (lineas.indexOf(l) != lineas.size()-1)
                    cadena += "\n\t- ";
            }
        }
        return cadena;
    }

    @Embeddable
        public static class LineasVenta {

            int cantidadProductos;

            @OneToOne
            private Producto producto;

            public LineasVenta(){}

            public LineasVenta(int cantidadProductos, Producto producto){
                this.cantidadProductos = cantidadProductos;
                this.producto = producto;
            }

            @Override
            public String toString() {
                return String.format("Cantidad: %d, Producto: %s", cantidadProductos, producto.toString());
            }
        }
}

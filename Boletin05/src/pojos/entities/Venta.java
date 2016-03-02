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

    @OneToOne(cascade = CascadeType.PERSIST)
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
        setCliente(cliente);
        fechaVenta = new Date();
        if (lineas == null)
            this.lineas = new LinkedList<LineasVenta>();
        else
            this.lineas = lineas;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LinkedList<LineasVenta> getLineas() {
        return lineas;
    }

    public void setLineas(LinkedList<LineasVenta> lineas) {
        this.lineas = lineas;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public boolean isFacturada() {
        return facturada;
    }

    public void setFacturada(boolean facturada) {
        this.facturada = facturada;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null)
            throw new IllegalArgumentException("El cliente no puede ser null");
        this.cliente = cliente;
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

    @Override
    public boolean equals(Object obj) {
        boolean r = true, encontrado;
        if (obj instanceof Venta){
            Venta v = (Venta)obj;
            if (idVenta != v.idVenta || (cliente == null && v.cliente != null) || (cliente != null && v.cliente == null) || (cliente != null && v.cliente != null && !cliente.equals(v.cliente)) || facturada != v.facturada || (fechaVenta == null && v.fechaVenta != null) || (fechaVenta != null && v.fechaVenta == null) || (fechaVenta != null && v.fechaVenta != null && !fechaVenta.equals(v.fechaVenta)))
                r = false;
            if (r && lineas != null && v.lineas != null){
                if (lineas.size() == v.lineas.size()) {
                    for (int i = 0; r && i < lineas.size(); i++) {
                        encontrado = false;
                        for (int j = 0; !encontrado && j < v.lineas.size(); j++)
                            if (lineas.get(i).equals(v.lineas.get(j)))
                                encontrado = true;

                        if (!encontrado)
                            r = false;
                    }
                } else
                    r = false;
            } else if (r && ((lineas == null && v.lineas != null) || (lineas != null && v.lineas == null)))
                r = false;
        }
        return r;
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

        public int getCantidadProductos() {
            return cantidadProductos;
        }

        public void setCantidadProductos(int cantidadProductos) {
            this.cantidadProductos = cantidadProductos;
        }

        public Producto getProducto() {
            return producto;
        }

        public void setProducto(Producto producto) {
            this.producto = producto;
        }

        @Override
        public String toString() {
            return String.format("Cantidad: %d, Producto: %s", cantidadProductos, producto.toString());
        }

        @Override
        public boolean equals(Object obj) {
            boolean r = false;
            if (obj instanceof LineasVenta){
                LineasVenta l = (LineasVenta) obj;
                if (cantidadProductos == l.cantidadProductos && ((producto == null && l.producto == null) || (producto != null && l.producto != null && producto.equals(l.producto))))
                    r = true;
            }
            return r;
        }
    }
}

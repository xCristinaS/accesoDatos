package examen2doTrimestre;

import pojos.entities.Producto;
import pojos.entities.ProductoIndividual;
import pojos.entities.ProductoPorMayor;
import pojos.entities.Venta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Cristina on 02/03/2016.
 */
public class Examen {
    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        Calendar c = Calendar.getInstance();
        Date fechaMesAnterior;
        String select = "select v from Venta v";
        List<Venta> ventas;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/ventas.odb");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery(select);
        ventas = query.getResultList();

        c.add(Calendar.MONTH, -1);
        fechaMesAnterior = (Date) c.getTime().clone();
        for (Venta v : ventas) {
            if (!v.isFacturada() && format.format(fechaMesAnterior).equals(format.format(v.getFechaVenta()))) {
                v.setFacturada(true);
                System.out.println(detallesVenta(v));
                escribirEnFichero(detallesVenta(v));
            }
        }
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    private static String detallesVenta(Venta v) {
        String result = "";
        SimpleDateFormat formato = new SimpleDateFormat("EEEE dd/MM/yyyy");
        result += String.format("DETALLES DE VENTA:\n- Nombre de cliente: %s\n- ID de venta: %d\n- Fecha de la venta: %s\n- Importe total de la venta: %.2f\n\nDETALLES DE PRODUCTOS EN VENTA:\n",
                v.getCliente().getNombre(), v.getIdVenta(), formato.format(v.getFechaVenta()), calcularImporteTotalVenta(v));
        for (Venta.LineasVenta l : v.getLineas())
            result += detallesProducto(l);
        return result;
    }

    private static String detallesProducto(Venta.LineasVenta l){
        String result = "";
        Producto p = l.getProducto();
        result += String.format("\t- Tipo de producto: %s, Descripción del producto: %s, Precio: %.2f, Cantidad de productos comprados: %d, Cantidad del importe producto en venta: %.2f\n",
                p.getClass().getSimpleName().equals("ProductoIndividual")? "producto individual": "producto al por mayor", p.getDescripcion(),
                p instanceof ProductoIndividual? ((ProductoIndividual) p).getPvp(): obtenerPrecioDeProductoPorMayorSegunVentas(l), l.getCantidadProductos(),
                p instanceof ProductoIndividual? ((ProductoIndividual) p).getPvp()*l.getCantidadProductos(): obtenerPrecioDeProductoPorMayorSegunVentas(l)*l.getCantidadProductos());
        return result;
    }

    private static float calcularImporteTotalVenta(Venta v) {
        float result = 0;
        for (Venta.LineasVenta l : v.getLineas())
            if (l.getProducto() instanceof ProductoIndividual) {
                result += ((ProductoIndividual) l.getProducto()).getPvp() * l.getCantidadProductos();
            } else if (l.getProducto() instanceof ProductoPorMayor) {
                result += obtenerPrecioDeProductoPorMayorSegunVentas(l) * l.getCantidadProductos();
            }
        return result;
    }

    private static float obtenerPrecioDeProductoPorMayorSegunVentas(Venta.LineasVenta l){
        float result = 0;
        if (l.getCantidadProductos() <= 100)
            result += ((ProductoPorMayor) l.getProducto()).getPvp1();
        else if (l.getCantidadProductos() > 100 && l.getCantidadProductos() <= 1000)
            result += ((ProductoPorMayor) l.getProducto()).getPvp2();
        else
            result += ((ProductoPorMayor) l.getProducto()).getPvp3();
        return result;
    }

    private static void escribirEnFichero(String s) {
        FileWriter escritor;
        try {
            escritor = new FileWriter(new File("./factura.txt"), true);
            escritor.write(s + "\n\n");
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

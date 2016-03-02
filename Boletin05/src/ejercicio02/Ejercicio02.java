package ejercicio02;

import pojos.entities.*;
import pojos.types.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedList;

/**
 * Created by Cristina on 16/02/2016.
 */
public class Ejercicio02 {

    // Realiza un programa que inserte dos clientes. A continuación, insértale dos nuevas ventas a cada uno con tres productos en cada venta.
    public static void main(String[] args) {
        LinkedList<Venta.LineasVenta> lineas = new LinkedList<Venta.LineasVenta>();
        LinkedList<Telefono> telefonos = new LinkedList<Telefono>();
        Cliente c1 = new Cliente("cliente4", "pepito", "74782487r", new Direccion("calle clavel", "11300", (byte)10, new Poblacion("La linea", "España", new Provincia("Cadiz", "Andalucia"))), null);
        Cliente c2 = new Cliente("cliente5", "manolito", "12457877g", new Direccion("calle garcia lorca", "11315", (byte)35, new Poblacion("algeciras", "España", new Provincia("Cadiz", "Andalucia"))), null);
        Venta v1, v2;
        Producto p;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/ventas.odb");
        EntityManager em = emf.createEntityManager();

        p = em.find(Producto.class, "prodIn1");
        lineas.addLast(new Venta.LineasVenta(2, p));
        p = em.find(Producto.class, "prodMay1");
        lineas.addLast(new Venta.LineasVenta(3, p));
        p = em.find(Producto.class, "prodIn2");
        lineas.addLast(new Venta.LineasVenta(1, p));

        telefonos.addLast(new Telefono(TipoTlf.FIJO_PERSONAL, new NumeroTlf("956", "956690674")));
        telefonos.addLast(new Telefono(TipoTlf.FIJO_TRABAJO, new NumeroTlf("+956", "956745457")));
        telefonos.addLast(new Telefono(TipoTlf.MOVIL_PERSONAL, new NumeroTlf("+34", "644574578")));
        c1.setTelefonos(telefonos);
        v1 = new Venta(6, false, c1, lineas);

        lineas.clear(); telefonos.clear();
        p = em.find(Producto.class, "prodIn2");
        lineas.addLast(new Venta.LineasVenta(2, p));
        p = em.find(Producto.class, "prodMay1");
        lineas.addLast(new Venta.LineasVenta(3, p));
        p = em.find(Producto.class, "prodMay2");
        lineas.addLast(new Venta.LineasVenta(1, p));

        telefonos.addLast(new Telefono(TipoTlf.FIJO_PERSONAL, new NumeroTlf("956", "956565656")));
        telefonos.addLast(new Telefono(TipoTlf.FIJO_TRABAJO, new NumeroTlf("+956", "956646464")));
        telefonos.addLast(new Telefono(TipoTlf.MOVIL_TRABAJO, new NumeroTlf("+34", "621457878")));
        c2.setTelefonos(telefonos);
        v2 = new Venta(5, false, c2, lineas);

        em.getTransaction().begin();
        //em.persist(c1);
        //em.persist(c2);
        em.persist(v1);
        em.persist(v2);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}

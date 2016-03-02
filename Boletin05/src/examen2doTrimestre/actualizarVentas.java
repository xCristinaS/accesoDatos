package examen2doTrimestre;

import pojos.entities.Venta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Cristina on 02/03/2016.
 */
public class actualizarVentas {
    public static void main(String[] args) {
        SimpleDateFormat formato = new SimpleDateFormat("MM");
        String select = "select v from Venta v";
        List<Venta> ventas;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/ventas.odb");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery(select);
        ventas = query.getResultList();

        ventas.get(0).getLineas().get(0).setCantidadProductos(200);
        ventas.get(0).getLineas().get(1).setCantidadProductos(20);
        ventas.get(0).setFacturada(false);

        ventas.get(1).getLineas().get(0).setCantidadProductos(100);
        ventas.get(1).getLineas().get(1).setCantidadProductos(500);
        ventas.get(1).setFacturada(false);

        ventas.get(2).getLineas().get(0).setCantidadProductos(600);
        ventas.get(2).getLineas().get(1).setCantidadProductos(1500);
        ventas.get(2).setFacturada(false);

        em.getTransaction().begin();
        em.persist(ventas.get(0));
        em.persist(ventas.get(1));
        em.persist(ventas.get(2));
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}

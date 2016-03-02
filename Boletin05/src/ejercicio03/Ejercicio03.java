package ejercicio03;

import pojos.entities.Cliente;
import pojos.entities.Venta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Cristina on 17/02/2016.
 */
public class Ejercicio03 {
    //Realiza un programa que dado un cliente muestre todos sus datos y todos los datos de sus
    //ventas, líneas de ventas y productos.
    public static void main(String[] args) {
        String idCliente = "cliente2", select = "select v from Venta v where v.cliente.idCliente = ?1";
        List<Venta> ventas;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/ventas.odb");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery(select);
        query.setParameter(1, idCliente);
        ventas = query.getResultList();
        System.out.println();
        for(Venta v : ventas)
            System.out.println(v);

        em.close();
        emf.close();
    }
}

package ejercicio08;

import pojos.entities.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Cristina on 21/02/2016.
 */
public class Ejercicio08 {
    // Realiza un programa que dada una dirección muestre todos los clientes que tienen dicha dirección.
    public static void main(String[] args) {
        String calleBuscar = "calle garcia lorca", select = "select distinct c from Cliente c where c.direccion.calle = '" + calleBuscar +"'";
        List<Cliente> clientes;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/ventas.odb");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery(select);
        clientes = query.getResultList();
        System.out.println();
        for(Cliente c : clientes)
            System.out.println(c+"\n");

        em.close();
        emf.close();
    }
}

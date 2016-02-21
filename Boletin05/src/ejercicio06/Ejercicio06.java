package ejercicio06;

import pojos.entities.Cliente;
import pojos.entities.Venta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Cristina on 21/02/2016.
 */
public class Ejercicio06 {
    // Realiza un programa que dado un teléfono muestre todos los clientes que tienen dicho teléfono.
    public static void main(String[] args) {
        String tlfBuscar = "956565656", select = "select distinct c from Cliente c where c.telefonos.numeroTlf.numero = '" + tlfBuscar +"'";
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

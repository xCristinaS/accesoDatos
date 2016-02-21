package ejercicio07;

import pojos.entities.Cliente;
import pojos.types.Telefono;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cristina on 21/02/2016.
 */
public class Ejercicio07 {
    // Realiza un programa que dado un teléfono modifique el prefijo del país a todos los clientes
    //que disponen de dicho teléfono.
    public static void main(String[] args) {
        String tlfBuscar = "956565656", select = "select distinct c from Cliente c where c.telefonos.numeroTlf.numero = '" + tlfBuscar +"'";
        List<Cliente> clientes;
        LinkedList<Telefono> telefonos;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/ventas.odb");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery(select);
        clientes = query.getResultList();
        em.getTransaction().begin();
        for(Cliente c : clientes){
            telefonos = c.getTelefonos();
            for (Telefono t: telefonos)
                if (t.getNumeroTlf().getNumero().equals(tlfBuscar))
                    t.getNumeroTlf().setPrefijo("(prefCambiado) 956");

            em.persist(c);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}

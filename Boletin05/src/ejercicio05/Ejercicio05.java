package ejercicio05;

import pojos.entities.Cliente;
import pojos.types.Telefono;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Cristina on 17/02/2016.
 */
public class Ejercicio05 {
    // Realiza un programa que actualice un teléfono de un cliente. Hay que preguntarle al usuario
    // el teléfono antiguo y el nuevo y sustituir uno por otro.
    public static void main(String[] args) {
        String tlfAntiguo = "956646464", tlfNuevo = "856121212", select = "select c from Cliente c where c.telefonos.numeroTlf.numero = '"+tlfAntiguo+"'";
        List<Telefono> telefonos;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/ventas.odb");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(select);
        List<Cliente> clientes = query.getResultList();
        for (Cliente c: clientes) {
            telefonos = c.getTelefonos();
            for (Telefono t: telefonos)
                if (t.getNumeroTlf().getNumero().equals(tlfAntiguo))
                    t.getNumeroTlf().setNumero(tlfNuevo);
        }
        em.getTransaction().begin();
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}

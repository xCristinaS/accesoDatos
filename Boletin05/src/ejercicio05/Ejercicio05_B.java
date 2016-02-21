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
public class Ejercicio05_B {
    // Realiza un programa que actualice un teléfono de un cliente. Hay que preguntarle al usuario
    // el teléfono antiguo y el nuevo y sustituir uno por otro.
    public static void main(String[] args) {
        String tlfNuevo = "777777777", tlfAntiguo = "856121212", update = "update Cliente c set c.telefonos.numeroTlf.numero = '"+tlfNuevo+"' where telefonos.numeroTlf.numero = '"+tlfAntiguo+"'";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/ventas.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(update);
        query.executeUpdate();
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}

package ejercicio04;

import pojos.entities.Cliente;
import pojos.types.Telefono;
import pojos.types.TipoTlf;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Cristina on 17/02/2016.
 */
public class Ejercicio04 {
    // Realiza un programa que actualice todos los teléfonos de un cliente.
    public static void main(String[] args) {
        String idCliente = "cliente1";
        Cliente c;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/ventas.odb");
        EntityManager em = emf.createEntityManager();

        c = em.find(Cliente.class, idCliente);
        for (Telefono t: c.getTelefonos()) {
            t.getNumeroTlf().setNumero("777777777");
            t.getNumeroTlf().setPrefijo("777");
            t.setTipo(TipoTlf.MOVIL_PERSONAL);
        }
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}

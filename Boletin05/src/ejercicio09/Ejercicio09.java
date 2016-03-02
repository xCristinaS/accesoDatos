package ejercicio09;

import pojos.entities.Cliente;
import pojos.types.Direccion;
import pojos.types.NumeroTlf;
import pojos.types.Telefono;
import pojos.types.TipoTlf;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cristina on 21/02/2016.
 */
public class Ejercicio09 {
    //Realiza un programa que haga lo siguiente con un cliente en este órden:
    //  9.1. actualice la dirección de un cliente en la base de datos
    //  9.2. separe dicha entidad cliente del contexto de persistencia
    //  9.3. actualice el nombre del cliente
    //  9.4. persistir el cliente de nuevo con el nuevo nombre
    //  9.5. lo actualice en la base de datos con el nuevo nombre
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/ventas.odb");
        EntityManager em = emf.createEntityManager();

        Cliente c = em.find(Cliente.class, "cliente1");
        c.setDireccion(new Direccion("calle ronda golf (actualizada)", "11315", (byte) 106, c.getDireccion().getPoblacion()));
        /*
        LinkedList<Telefono> t = new LinkedList<>();
        t.addLast(new Telefono(TipoTlf.FIJO_PERSONAL, new NumeroTlf("956", "956474787")));
        t.addLast(new Telefono(TipoTlf.FIJO_PERSONAL, new NumeroTlf("856", "888888888")));
        t.addLast(new Telefono(TipoTlf.FIJO_PERSONAL, new NumeroTlf("856", "777777777")));
        t.addLast(new Telefono(TipoTlf.FIJO_PERSONAL, new NumeroTlf("910", "474747878")));
        t.addLast(new Telefono(TipoTlf.FIJO_PERSONAL, new NumeroTlf("956", "447878787")));
        c.setTelefonos(t);
        */

        em.flush(); // Fuerza la escritura en la bdd de los objetos persistentes hasta este punto de la ejecucion

        /*
        em.getTransaction().begin();
        em.getTransaction().commit();
        */


        em.detach(c);
        c.setNombre("Baldomero (actualizado)");
        em.merge(c);
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}

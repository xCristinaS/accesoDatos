package ejercicio01;

import pojos.entities.Producto;
import pojos.entities.ProductoIndividual;
import pojos.entities.ProductoPorMayor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Created by Cristina on 16/02/2016.
 */
public class Ejercicio01 {
    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/ventas.odb");
        EntityManager em = emf.createEntityManager();

        Producto[] productos = {new ProductoIndividual("prodIn1", "pendrive", 5, 10.5f), new ProductoIndividual("prodIn2", "portatil asus", 20, 859.99f),
                new ProductoPorMayor("prodMay1", "Playstation 4", 100, 859.99f, 418.5f, 501.7f), new ProductoPorMayor("prodMay2", "Pantalla LG", 300, 689.75f, 758.5f, 1024.7f)};

        em.getTransaction().begin();
        for (int i = 0; i < productos.length; i++)
            em.persist(productos[i]);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}

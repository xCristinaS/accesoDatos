package ejercicio10;

import pojos.entities.Cliente;
import pojos.entities.Producto;
import pojos.entities.Venta;
import utilidad.Teclado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Cristina on 21/02/2016.
 */
public class Ejercicio10 {
    //Realiza un programa que elimine a petici�n del usuario un cliente, una venta o un producto.
    //Utiliza operaciones en cascada y clases listener para guardar en ficheros de texto la
    //informaci�n que se ha borrado.
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/ventas.odb");
        EntityManager em = emf.createEntityManager();
        String selectClientes = "select c from Cliente c", selectVenta = "select v from Venta v", selectProducto = "select p from Producto p";
        List<Venta> ventas;
        List<Cliente> clientes;
        List<Producto> productos;
        Query q;
        int resp;

        q = em.createQuery(selectClientes);
        clientes = q.getResultList();
        q = em.createQuery(selectProducto);
        productos = q.getResultList();
        q = em.createQuery(selectVenta);
        ventas = q.getResultList();

        do {
            System.out.println("¿Qué desea borrar?");
            System.out.println("1. Un cliente");
            System.out.println("2. Un producto");
            System.out.println("3. Una venta");
            System.out.printf("Respuesta: ");
            resp = Teclado.leerIntEntre(1, 3, Teclado.TipoEntre.AMBOS_INC);
        } while (resp < 1 || resp > 3);

        switch (resp){
            case 1:
                do {
                    System.out.println("\nIndique qué cliente quiere eliminar:");
                    for (int i = 0; i < clientes.size(); i++)
                        System.out.printf("%d. ID: %s, Nombre: %s\n", i + 1, clientes.get(i).getIdCliente(), clientes.get(i).getNombre());
                    System.out.print("Respuesta:");
                    resp = Teclado.leerIntEntre(1, clientes.size(), Teclado.TipoEntre.AMBOS_INC);
                } while (resp < 1 || resp > clientes.size());
                em.getTransaction().begin();
                em.remove(clientes.get(resp-1));
                em.getTransaction().commit();
                break;
            case 2:
                do {
                    System.out.println("\nIndique qué producto quiere eliminar:");
                    for (int i = 0; i < productos.size(); i++)
                        System.out.printf("%d. ID: %s, Descripción: %s\n", i + 1, productos.get(i).getIdProducto(), productos.get(i).getDescripcion());
                    System.out.print("Respuesta:");
                    resp = Teclado.leerIntEntre(1, productos.size(), Teclado.TipoEntre.AMBOS_INC);
                } while (resp < 1 || resp > productos.size());
                //em.getTransaction().begin();
                //em.remove(productos.get(resp-1));
                //em.getTransaction().commit();
                System.out.println("Operación no permitida, no se puede borrar un producto.");
                break;
            case 3:
                do {
                    System.out.println("\nIndique qué venta quiere eliminar:");
                    for (int i = 0; i < ventas.size(); i++)
                        System.out.printf("%d. ID: %s\n", i + 1, ventas.get(i).getIdVenta());
                    System.out.print("Respuesta:");
                    resp = Teclado.leerIntEntre(1, ventas.size(), Teclado.TipoEntre.AMBOS_INC);
                } while (resp < 1 || resp > ventas.size());
                em.getTransaction().begin();
                em.remove(ventas.get(resp - 1));
                em.getTransaction().commit();
                break;
        }
        em.close();
        emf.close();
    }
}

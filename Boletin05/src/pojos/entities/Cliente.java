package pojos.entities;

import pojos.types.Direccion;
import pojos.types.Telefono;

import javax.persistence.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cristina on 16/02/2016.
 */

@Entity
public class Cliente {

    @Id
    private String idCliente;
    private String nombre, nif;

    @Embedded
    private Direccion direccion;

    @Embedded
    @ElementCollection(fetch = FetchType.EAGER)
    private LinkedList<Telefono> telefonos;

    public Cliente(String idCliente, String nombre, String nif, Direccion direccion, LinkedList<Telefono> telefonos) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        if (telefonos == null)
            this.telefonos = new LinkedList<>();
        else
            this.telefonos = telefonos;
    }

    @Override
    public boolean equals(Object obj) {
        boolean r = true, encontrado;
        if (obj instanceof Cliente) {
            Cliente c = (Cliente) obj;
            if (idCliente != c.idCliente || !nombre.equals(c.nombre) || !nif.equals(c.nif) || (direccion == null && c.direccion != null) || (direccion != null && c.direccion == null) || (direccion != null && c.direccion != null && !direccion.equals(c.direccion)))
                r = false;
            if (r && telefonos != null && c.telefonos != null) {
                if (telefonos.size() == c.telefonos.size()) {
                    for (int i = 0; r && i < telefonos.size(); i++) {
                        encontrado = false;
                        for (int j = 0; !encontrado && j < c.telefonos.size(); j++)
                            if (telefonos.get(i).equals(c.telefonos.get(j)))
                                encontrado = true;

                        if (!encontrado)
                            r = false;
                    }
                } else
                    r = false;
            } else if (r && ((telefonos == null && c.telefonos != null) || (telefonos != null && c.telefonos == null)))
                r = false;
        }
        return r;
    }

    public void setTelefonos(LinkedList<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @Override
    public String toString() {
        String cadena = String.format("ID: %s, Nombre: %s, NIF: %s", idCliente, nombre, nif);
        if (direccion != null)
            cadena += "\n\t- Dirección:\n\t\t" + direccion.toString();

        if (telefonos != null) {
            cadena += "\n\n\t- Telefonos: \n\t\t";
            for (int i = 0; i < telefonos.size(); i++)
                if (telefonos.get(i) != null)
                    cadena += telefonos.get(i).toString() + "\n\t\t";
        }
        return cadena;
    }

    public LinkedList<Telefono> getTelefonos() {
        return telefonos;
    }

    @PostRemove
    private void eliminarVentas() {
        String select = "select v from Venta v where v.cliente.idCliente = '" + idCliente + "'";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/ventas.odb");
        EntityManager em = emf.createEntityManager();
        List<Venta> ventas;
        Query query = em.createQuery(select);
        ventas = query.getResultList();
        em.getTransaction().begin();
        for (Venta v : ventas)
            em.remove(v);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @PreRemove
    private void escribirEnFichero() {
        FileWriter escritor;
        try {
            escritor = new FileWriter(new File("./eliminados.txt"), true);
            escritor.write(this.toString() + "\n\n");
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ejercicio02;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clasesHibernate.Profesor;
import clasesHibernate.SessionFactoryUtil;

public class Ejercicio02 {

	public static void main(String[] args) {
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		Transaction transaccion = sesion.beginTransaction();
		
		Profesor profe = new Profesor();
		profe = (Profesor) sesion.load(Profesor.class, "PR1");
		System.out.println("Nombre antiguo: " + profe.getNombre());
		profe.setNombre("Pepito Perez Cambiado");
		System.out.println("Nuevo nombre: " + profe.getNombre());
		
		sesion.update(profe);
		transaccion.commit();
		System.out.println("Profe Modificado.");
		sesion.close();
	}

}

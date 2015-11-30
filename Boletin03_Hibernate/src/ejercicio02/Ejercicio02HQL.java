package ejercicio02;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clasesHibernate.SessionFactoryUtil;

public class Ejercicio02HQL {

	public static void main(String[] args) {
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		Transaction transaccion = sesion.beginTransaction();
		int i;
		
		String updateHQL = "update Profesor p set p.nombre = :nuevoNombre where p.codProf = :codProf";
		i = sesion.createQuery(updateHQL).setString("nuevoNombre", "Juanito Jiménez Mod2").setString("codProf", "PR1").executeUpdate();
		
		if (i == 1)
			System.out.println("Profe modificado, otra vez.");
		
		transaccion.commit();
		sesion.close();
	}

}

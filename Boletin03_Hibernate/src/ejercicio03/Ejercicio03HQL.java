package ejercicio03;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clasesHibernate.SessionFactoryUtil;

public class Ejercicio03HQL {

	public static void main(String[] args) {
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		Transaction transaccion = sesion.beginTransaction();
		int i;
		
		String insertHQL = "insert into Historicoprofesores (codProf, nombre, alta, fechaDeNacimiento) "
				+ "select p.codProf, p.nombre, p.alta, p.fechaDeNacimiento from Profesor p where p.codProf = :codProf";
		i = sesion.createQuery(insertHQL).setString("codProf", "PR1").executeUpdate();
		
		if (i == 1)
			System.out.println("Profesor agregado.");
		
		transaccion.commit();
		sesion.close();
	}

}

package ejercicio09;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.SessionFactoryUtil;

public class Ejercicio09 {

	public static void main(String[] args) {
		// Dada una oferta educativa, obtener la media de horas semanales de sus asignaturas.
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		String select = "select avg(horasSemanales) from Asignatura where codAsignatura in "
				+ "(select asignatura from Reparto r where codOe = ?)";
		double media = (Double)sesion.createQuery(select).setString(0, "DAM").uniqueResult();
		System.out.println(media);
		sesion.close();
	}

}

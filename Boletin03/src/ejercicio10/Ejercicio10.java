package ejercicio10;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.*;

public class Ejercicio10 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//Obtener aquellos profesores que impartan asignaturas las cuales superan la media del
		//ejercicio anterior. Dichas asignaturas deben pertenecer a dicha oferta educativa.
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		String codOe = "DAM";
		String select = "select avg(horasSemanales) from Asignatura where codAsignatura in "
				+ "(select asignatura from Reparto r where codOe = ?)";
		double media = (Double)sesion.createQuery(select).setString(0, codOe).uniqueResult();
		select = "from Profesor where codProf in "
				+ "(select profesor from Reparto where asignatura in "
				+ "(select codAsignatura from Asignatura where horasSemanales > ?) and curso in"
				+ "(from Curso where codOe = :oferta))";
		List<Profesor> lista = sesion.createQuery(select).setDouble(0, media).setString("oferta", codOe).list();
		for (Profesor p: lista)
			System.out.println(p.getNombre());
		sesion.close();
	}

}

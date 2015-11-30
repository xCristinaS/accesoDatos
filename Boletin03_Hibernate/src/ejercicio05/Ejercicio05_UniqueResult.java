package ejercicio05;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.Asignatura;
import clasesHibernate.SessionFactoryUtil;

public class Ejercicio05_UniqueResult {

	public static void main(String[] args) {
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		
		Asignatura asignatura = (Asignatura) sesion.createQuery("from Asignatura where codAsignatura = ?").setString(0, "PROG").uniqueResult();
		System.out.printf("CodAsignatura: %s, Nombre: %s, Horas semanales: %d, Horas totales: %d\n", asignatura.getCodAsignatura(), asignatura.getNombre(), asignatura.getHorasSemanales(), asignatura.getHorasTotales());
		
		Asignatura asignatura2 = (Asignatura) sesion.createQuery("from Asignatura a where a.nombre = :nombre").setString("nombre", "Bases de datos").uniqueResult();
		System.out.printf("CodAsignatura: %s, Nombre: %s, Horas semanales: %d, Horas totales: %d", asignatura2.getCodAsignatura(), asignatura2.getNombre(), asignatura2.getHorasSemanales(), asignatura2.getHorasTotales());
		
		sesion.close();
	}

}

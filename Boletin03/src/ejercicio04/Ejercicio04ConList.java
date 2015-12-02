package ejercicio04;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.*;

public class Ejercicio04ConList {

	public static void main(String[] args) {
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		String select = "from Profesor";
		Query consulta = sesion.createQuery(select);
		
		@SuppressWarnings("unchecked")
		List<Profesor> lista = consulta.list();
		
		for (Profesor p:lista)
			System.out.printf("Código: %s, Nombre: %s\n",p.getCodProf(), p.getNombre());
		
		sesion.close();
	}

}

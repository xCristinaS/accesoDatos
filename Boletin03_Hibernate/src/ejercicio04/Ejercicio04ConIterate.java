package ejercicio04;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.Profesor;
import clasesHibernate.SessionFactoryUtil;

public class Ejercicio04ConIterate {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		String select = "from Profesor";
		Query consulta = sesion.createQuery(select);
		List<Profesor> lista; 
		Iterator<Profesor> it;
		Profesor p;
		
		consulta.setFetchSize(5);
		lista = consulta.list();
		it = lista.iterator();
		
		while (it.hasNext()){
			p = it.next();
			System.out.printf("Código: %s, Nombre: %s, Fecha de nacimiento: %s\n",p.getCodProf(), p.getNombre(), p.getFechaDeNacimiento());
		}
		sesion.close();
	}

}

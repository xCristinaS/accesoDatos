package prueba01;


import java.util.Iterator;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.Asignatura;
import clasesHibernate.Reparto;
import clasesHibernate.SessionFactoryUtil;

public class Prueba {

	public static void main(String[] args) {
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		Asignatura asignatura = new Asignatura();  Iterator<Reparto> it; Reparto r;
		try {
			
			asignatura = (Asignatura) sesion.load(Asignatura.class, "FOL");
			
			System.out.println(asignatura.getCodAsignatura());
			System.out.println(asignatura.getNombre());
			System.out.println(asignatura.getHorasSemanales());
			System.out.println(asignatura.getHorasTotales());
			
			it = asignatura.getRepartos().iterator();
			while (it.hasNext()){
				r = it.next();
				System.out.println(r.getId().getCodOe());
				System.out.println(r.getId().getCodCurso());
				System.out.println(r.getId().getCodAsignatura());
				System.out.println(r.getAsignatura().getNombre());
				System.out.println(r.getAsignatura().getCodAsignatura());
				System.out.println(r.getProfesor().getCodProf());
			}
		} catch (ObjectNotFoundException e){
			System.out.println("no lo encuentro");
		}
		sesion.close();
	}

}

package ejercicio07;

import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.*;

public class Ejercicio07 {

	public static void main(String[] args) {
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		Reparto r; Asignatura a; Iterator<Reparto> it;
		Curso curso = (Curso) sesion.createQuery("from Curso where codOe = ? and codcurso = ?").setString(0, "DAM").setString(1, "1A").uniqueResult();
		it = curso.getRepartos().iterator();
		while (it.hasNext()){
			r = it.next();
			a = r.getAsignatura();
			System.out.printf("Codigo Asignatura: %s, Nombre: %s\n",a.getCodAsignatura(), a.getNombre());
		}
		sesion.close();
	}

}

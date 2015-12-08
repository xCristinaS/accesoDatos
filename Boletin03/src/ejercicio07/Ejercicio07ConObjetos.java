package ejercicio07;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.*;

public class Ejercicio07ConObjetos {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		Asignatura a; Iterator<Object> it;
		
		String select = "select asignatura from Reparto where curso = (select id from Curso where codOe = ? and codCurso = ?)";
		Query q = sesion.createQuery(select).setString(0, "DAM").setString(1, "2A");
		it = q.iterate();
		
		while (it.hasNext()){
			a = (Asignatura) it.next();
			System.out.printf("Codigo Asignatura: %s, Nombre: %s\n",a.getCodAsignatura(), a.getNombre());
		}
		
		sesion.close();
	}
}

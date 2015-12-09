package ejercicio12;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.*;

public class Ejercicio12 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Mostrar los datos de todos los profesores junto con los datos de los cursos donde son tutores.
		// Si no son tutores, indicarlo.
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		String select = "from Profesor p left join p.cursos";
		Query cons = sesion.createQuery(select);
		Iterator<Object[]> it = cons.iterate();
		Object[] conjunto;
		Profesor p; Curso c; 
		while (it.hasNext()){
			conjunto = (Object[])it.next();
			p = (Profesor)conjunto[0];
			c = (Curso)conjunto[1];
			if (c != null)
				System.out.printf("- CodProf: %s | Nombre: %s | Fecha de nacimiento: %s | Alta: %s | Tutor del curso: %s %s\n", p.getCodProf(), p.getNombre(), p.getFechaDeNacimiento(), p.getAlta(), c.getId().getCodCurso(), c.getId().getCodOe());
			else 
				System.out.printf("- CodProf: %s | Nombre: %s | Fecha de nacimiento: %s | Alta: %s -- %s\n", p.getCodProf(), p.getNombre(), p.getFechaDeNacimiento(), p.getAlta(), "No es tutor");
		}
		sesion.close();
	}

}

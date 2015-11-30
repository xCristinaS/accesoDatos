package ejercicio03;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clasesHibernate.*;

public class Ejercicio03Sesion {

	public static void main(String[] args) {
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		Transaction transaccion = sesion.beginTransaction();
		
		Profesor profe = (Profesor)sesion.load(Profesor.class, "PR1");
		Historicoprofesores newProf = new Historicoprofesores();
		
		newProf.setCodProf(profe.getCodProf());
		newProf.setNombre(profe.getNombre());
		newProf.setAlta(profe.getAlta());
		newProf.setFechaDeNacimiento(profe.getFechaDeNacimiento());
		
		sesion.save(newProf);
		transaccion.commit();
		sesion.close();
		System.out.println("Profe agregado.");
	}

}

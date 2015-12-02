package ejercicio01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clasesHibernate.*;

public class Ejercicio01 {

	public static void main(String[] args) {
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		Transaction transaccion = sesion.beginTransaction();
		String fechaNac = "1970-02-10";
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		Profesor profe = new Profesor();
		profe.setCodProf("PR1");
		profe.setNombre("Profesor Uno");
		try {
			profe.setFechaDeNacimiento(formato.parse(fechaNac));
		} catch (ParseException e) { e.printStackTrace(); }
		profe.setAlta(new Date());
		
		sesion.save(profe);
		transaccion.commit();
		System.out.println("Profe insertado.");
		sesion.close();
	}

}

package ejercicio11;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.*;

public class Ejercicio11SinFetch {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//Mostrar los datos de todas las ofertas educativas y todos los datos de sus cursos. Si la oferta
		//educativa no tiene ningún curso, indicarlo. 
		
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		String select = "from Ofertaeducativa o left join o.cursos";
		Query cons = sesion.createQuery(select);
		Iterator<Object> it = cons.iterate();
		Object[] conjunto;
		Ofertaeducativa oe; Curso c; 
		while (it.hasNext()){
			conjunto = (Object[])it.next();
			oe = (Ofertaeducativa)conjunto[0];
			c = (Curso)conjunto[1];
			if (c != null)
				System.out.printf("- Oferta educativa: %s | idCurso: %s | tutor: %s\n", oe.getCodOe(), c.getId().getCodCurso(), c.getProfesor().getNombre());
			else 
				System.out.printf("- Oferta educativa: %s | %s\n", oe.getCodOe(), "No tiene curso.");
		}
		sesion.close();
	}

}

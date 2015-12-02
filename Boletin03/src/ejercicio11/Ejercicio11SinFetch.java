package ejercicio11;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.*;

public class Ejercicio11SinFetch {

	public static void main(String[] args) {
		//Mostrar los datos de todas las ofertas educativas y todos los datos de sus cursos. Si la oferta
		//educativa no tiene ningún curso, indicarlo. 
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		String select = "from Ofertaeducativa o left join o.cursos";
		Query cons = sesion.createQuery(select);
		Iterator<Object> it = cons.iterate();
		Ofertaeducativa oe; Curso c; 
		while (it.hasNext()){

		}
		sesion.close();
	}

}

package ejercicio11;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.*;

public class Ejercicio11SinFetch {

	public static void main(String[] args) {
		//Mostrar los datos de todas las ofertas educativas y todos los datos de sus cursos. Si la oferta
		//educativa no tiene ningún curso, indicarlo. 
		//Mostrar los datos de todas las ofertas educativas y todos los datos de sus cursos. Si la oferta
		//educativa no tiene ningún curso, indicarlo. 
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		String select = "from OfertaEducativa left join ";
		
		sesion.close();
	}

}

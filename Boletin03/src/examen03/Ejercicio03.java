package examen03;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.*;

public class Ejercicio03 {

	public static void main(String[] args) {
		PrintWriter escritor;
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		String select = "from Asignatura";
		Query consulta = sesion.createQuery(select);
		
		@SuppressWarnings("unchecked")
		List<Asignatura> lista = consulta.list();
		
		try {
			escritor = new PrintWriter("Ejercicio03.txt");
			for (Asignatura a:lista){
				ClaseAuxiliarAsignatura aux = new ClaseAuxiliarAsignatura(a);
				escritor.println(aux.dameAsignatura());
				escritor.println(aux.dameCursos());
			}
			
			escritor.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		sesion.close();
	}

}

package ejercicio13;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.SessionFactoryUtil;

public class Ejercicio13Manue {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory fabrica = SessionFactoryUtil.getSessionFactory();
		Session session = fabrica.openSession();
		
		Query cons = session.createQuery("SELECT h.curso.id.codOe, h.curso.id.codCurso, h.tramohorario.dia, h.asignatura.nombre, count(*) FROM Horario h Group by 1,3,4 ORDER BY 1,2,3,4");
		List<Object[]> filas = cons.list();
		Object[] filaActual;
		
		for (int i = 0; i < filas.size(); i++) {
			filaActual=filas.get(i);
			System.out.printf("%s, %s, %s, %s, %d\n",filaActual[0],filaActual[1],filaActual[2],filaActual[3],filaActual[4]);
		}
		
		session.close();
	}

}

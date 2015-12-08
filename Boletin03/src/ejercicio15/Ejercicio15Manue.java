package ejercicio15;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.SessionFactoryUtil;

public class Ejercicio15Manue {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory fabrica = SessionFactoryUtil.getSessionFactory();
		Session session = fabrica.openSession();
		Query cons = session.createQuery("SELECT h.curso.id.codOe, h.curso.id.codCurso, h.curso.ofertaeducativa.nombre FROM Horario h Group by 1,2 HAVING avg(h.asignatura.horasTotales) < (SELECT avg(r.asignatura.horasTotales) FROM Reparto r WHERE"
				+ " r.profesor.codProf = :cod)");
		
		cons.setString("cod", "EMP");
		List<Object[]> filas=cons.list();
		Object[] filaActual;
		
		for (int i = 0; i < filas.size(); i++) {
			filaActual=filas.get(i);
			System.out.printf("%s, %s, %s\n",filaActual[0],filaActual[1],filaActual[2]);
		}
		
		System.out.println("aa");
		session.close();

	}

}

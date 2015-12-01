package ejercicio11;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clasesHibernate.*;

public class Ejercicio11ConFetch {
	
	
	public static void main(String[] args) {
		//Mostrar los datos de todas las ofertas educativas y todos los datos de sus cursos. Si la oferta
		//educativa no tiene ningún curso, indicarlo. 
		SessionFactory sesionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionFactory.openSession();
		String select = "from Ofertaeducativa o left join fetch o.cursos";
		List<Ofertaeducativa> listaOe = sesion.createQuery(select).list();
		Iterator<Curso> itCursos; Curso c;
		for (Ofertaeducativa o:listaOe){
			System.out.printf("- CodOe: %s, Nombre: %s, Tipo: %s Descripción: %s\n", o.getCodOe(), o.getNombre(), o.getTipo(), o.getDescripcion());
			if (o.getCursos().size() != 0){
				itCursos = o.getCursos().iterator();
				while (itCursos.hasNext()){
					c = itCursos.next();
					System.out.printf("\t- CodOe: %s, CodCurso: %s, Tutor: %s\n", c.getOfertaeducativa().getCodOe(), c.getId().getCodCurso(), c.getProfesor().getCodProf());
				}
			} else 
				System.out.println("\t- No tiene cursos.");
		}
		sesion.close();
	}

}

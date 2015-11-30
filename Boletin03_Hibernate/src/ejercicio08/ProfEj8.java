package ejercicio08;

import java.util.Iterator;
import java.util.Set;

import clasesHibernate.*;

public class ProfEj8 {
	
	private String codigo;
	private String nombre;
	private byte numAsig;
	private byte numHorasClases = 0;
	private byte numCursosDist;
	
	public ProfEj8 (Profesor p){
		Set<Reparto> reparto = p.getRepartos(); Iterator<Reparto> it = reparto.iterator(); Asignatura a, aux = null; Reparto r; byte contA = 0, contC = 0; 
		CursoId idCur, aux2 = null; boolean primeraVuelta = true;
		codigo = p.getCodProf();
		nombre = p.getNombre();
		while (it.hasNext()){ 
			r = it.next();  
			a = r.getAsignatura(); 
			
			if (!primeraVuelta && !a.getCodAsignatura().equals(aux.getCodAsignatura()) && !a.getCodAsignatura().equals("@"+a.getCodAsignatura())){ 
				numHorasClases += a.getHorasSemanales(); 
				contA++;  
			}
			aux = a;  
			idCur = r.getCurso().getId(); 
			if (!primeraVuelta && !idCur.equals(aux2))  
				contC++;
			aux2 = idCur;
			if (primeraVuelta){
				numHorasClases += a.getHorasSemanales();
				contA++;
				contC++; 
				primeraVuelta = false;
			}
		}
		numAsig = contA; // el número de asignaturas diferentes es contA.
		numCursosDist = contC; // el número de cursos diferentes es contC. 
	}

	@Override
	public String toString() {
		return "ProfEj8 [codigo=" + codigo + ", nombre=" + nombre
				+ ", numAsig=" + numAsig + ", numHorasClases=" + numHorasClases
				+ ", numCursosDist=" + numCursosDist + "]";
	}
}

package examen03;

import java.util.Iterator;
import java.util.Set;

import clasesHibernate.*;

public class ClaseAuxiliarAsignatura {

	String codAsignatura, nombre; 
	short horasSemanales;
	String[] cursos;
	String[] tramos;
	
	public ClaseAuxiliarAsignatura(Asignatura a){
		String codCurso = "", tramoHorario = "";
		Set<Reparto> reparto = a.getRepartos();
		Set<Horario> horario = a.getHorarios();
		Iterator<Reparto> itReparto = reparto.iterator();
		Iterator<Horario> itHorario = horario.iterator();
		Reparto r;
		Horario h;
		
		codAsignatura = a.getCodAsignatura();
		nombre = a.getNombre();
		horasSemanales = a.getHorasSemanales();
		while (itReparto.hasNext()){
			r = itReparto.next();
			codCurso += r.getCurso().getId().getCodOe() + " " + r.getCurso().getId().getCodCurso() + ",";
		}
		while (itHorario.hasNext()){
			h = itHorario.next();
			tramoHorario += h.getTramohorario().getCodTramo() + ",";
		}
		
		cursos = codCurso.split(",");
		tramos = tramoHorario.split(",");
	}
	
	public String dameAsignatura(){
		return String.format("Código: %-8s Nombre: %-55s Horas semanales: %d",codAsignatura, nombre, horasSemanales);
	}
	
	public String dameCursos() {
		String cadena = "";
		for (int i = 0; i < cursos.length; i++)
			cadena += String.format("\n\tCódigo curso: %-12s Tramos horarios: %s\n", cursos[i], tramos[i]);
		return cadena;
	}
	
}

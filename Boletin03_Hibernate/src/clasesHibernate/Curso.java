package clasesHibernate;

// Generated 30-nov-2015 12:27:56 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Curso generated by hbm2java
 */
public class Curso implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private CursoId id;
	private Ofertaeducativa ofertaeducativa;
	private Profesor profesor;
	private Set<Reparto> repartos = new HashSet<Reparto>(0);
	private Set<Horario> horarios = new HashSet<Horario>(0);

	public Curso() {
	}

	public Curso(CursoId id, Ofertaeducativa ofertaeducativa, Profesor profesor) {
		this.id = id;
		this.ofertaeducativa = ofertaeducativa;
		this.profesor = profesor;
	}

	public Curso(CursoId id, Ofertaeducativa ofertaeducativa,
			Profesor profesor, Set<Reparto> repartos, Set<Horario> horarios) {
		this.id = id;
		this.ofertaeducativa = ofertaeducativa;
		this.profesor = profesor;
		this.repartos = repartos;
		this.horarios = horarios;
	}

	public CursoId getId() {
		return this.id;
	}

	public void setId(CursoId id) {
		this.id = id;
	}

	public Ofertaeducativa getOfertaeducativa() {
		return this.ofertaeducativa;
	}

	public void setOfertaeducativa(Ofertaeducativa ofertaeducativa) {
		this.ofertaeducativa = ofertaeducativa;
	}

	public Profesor getProfesor() {
		return this.profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Set<Reparto> getRepartos() {
		return this.repartos;
	}

	public void setRepartos(Set<Reparto> repartos) {
		this.repartos = repartos;
	}

	public Set<Horario> getHorarios() {
		return this.horarios;
	}

	public void setHorarios(Set<Horario> horarios) {
		this.horarios = horarios;
	}

}

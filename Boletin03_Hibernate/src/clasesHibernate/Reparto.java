package clasesHibernate;

// Generated 18-nov-2015 12:47:59 by Hibernate Tools 3.4.0.CR1

/**
 * Reparto generated by hbm2java
 */
public class Reparto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RepartoId id;
	private Profesor profesor;
	private Asignatura asignatura;
	private Curso curso;

	public Reparto() {
	}

	public Reparto(RepartoId id, Asignatura asignatura, Curso curso) {
		this.id = id;
		this.asignatura = asignatura;
		this.curso = curso;
	}

	public Reparto(RepartoId id, Profesor profesor, Asignatura asignatura,
			Curso curso) {
		this.id = id;
		this.profesor = profesor;
		this.asignatura = asignatura;
		this.curso = curso;
	}

	public RepartoId getId() {
		return this.id;
	}

	public void setId(RepartoId id) {
		this.id = id;
	}

	public Profesor getProfesor() {
		return this.profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Asignatura getAsignatura() {
		return this.asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	
}

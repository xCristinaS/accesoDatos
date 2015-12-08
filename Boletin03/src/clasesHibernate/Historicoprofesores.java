package clasesHibernate;
// Generated 08-dic-2015 11:53:41 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Historicoprofesores generated by hbm2java
 */
public class Historicoprofesores implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codProf;
	private String nombre;
	private Date alta;
	private Date fechaDeNacimiento;

	public Historicoprofesores() {
	}

	public Historicoprofesores(String codProf, String nombre, Date alta) {
		this.codProf = codProf;
		this.nombre = nombre;
		this.alta = alta;
	}

	public Historicoprofesores(String codProf, String nombre, Date alta, Date fechaDeNacimiento) {
		this.codProf = codProf;
		this.nombre = nombre;
		this.alta = alta;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public String getCodProf() {
		return this.codProf;
	}

	public void setCodProf(String codProf) {
		this.codProf = codProf;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getAlta() {
		return this.alta;
	}

	public void setAlta(Date alta) {
		this.alta = alta;
	}

	public Date getFechaDeNacimiento() {
		return this.fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

}

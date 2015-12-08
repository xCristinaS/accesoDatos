package clasesHibernate;
// Generated 08-dic-2015 11:53:41 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tramohorario generated by hbm2java
 */
public class Tramohorario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codTramo;
	private Date horaInicio;
	private Date horaFin;
	private String dia;
	private Set<Horario> horarios = new HashSet<Horario>(0);

	public Tramohorario() {
	}

	public Tramohorario(String codTramo) {
		this.codTramo = codTramo;
	}

	public Tramohorario(String codTramo, Date horaInicio, Date horaFin, String dia, Set<Horario> horarios) {
		this.codTramo = codTramo;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.dia = dia;
		this.horarios = horarios;
	}

	public String getCodTramo() {
		return this.codTramo;
	}

	public void setCodTramo(String codTramo) {
		this.codTramo = codTramo;
	}

	public Date getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Set<Horario> getHorarios() {
		return this.horarios;
	}

	public void setHorarios(Set<Horario> horarios) {
		this.horarios = horarios;
	}

}

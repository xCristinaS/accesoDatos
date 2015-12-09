package examen01;

public class Profesor {

	private String codProf, nombre, fechaNacimiento, codOe, codCurso;
	private int numAsignaturas;
	
	public Profesor(String codProf, String nombre, String fechaNacimiento, String codOe, String codCurso, int numAsignatura){
		this.codProf = codProf;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		if (codOe != null)
			this.codOe = codOe;
		else 
			this.codOe = " ";
		if (codCurso != null)
			this.codCurso = codCurso;
		else 
			this.codCurso = " ";
		this.numAsignaturas = numAsignatura;
	}

	@Override
	public String toString() {
		return "Profesor [codProf=" + codProf + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento
				+ ", codOe=" + codOe + ", codCurso=" + codCurso + ", numAsignaturas=" + numAsignaturas + "]";
	}
	
}

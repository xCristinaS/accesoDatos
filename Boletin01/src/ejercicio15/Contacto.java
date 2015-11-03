package ejercicio15;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contacto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String nombreCont, telefono, direccion; Date fechaNacimiento;
	public int codP; public boolean deboMoney; public float cantDeb;
	
	public Contacto(String nomCont, String tel, String dir, int codP, Date fechaNac, boolean deboMoney, float cantDeb){
		nombreCont = nomCont;
		telefono = tel;
		direccion = dir;
		this.codP = codP;
		this.fechaNacimiento = fechaNac;
		this.deboMoney = deboMoney;
		this.cantDeb = cantDeb;
	}
	public String toString(){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return String.format("\t\t\t\t%-20s %s%n"
				+ "\t\t\t\t%-20s %s%n"
				+ "\t\t\t\t%-20s %s%n"
				+ "\t\t\t\t%-20s %d%n"
				+ "\t\t\t\t%-20s %s%n"
				+ "\t\t\t\t%-20s %s%n"
				+ "\t\t\t\t%-20s %.2f\n",
				"Nombre:",nombreCont,"Telefono:" ,telefono,"Direccio:", direccion,"CP:", codP,"Fecha de nacimiento:", formato.format(fechaNacimiento),"¿Debo dinero?:",deboMoney?"Si":"No le debo dinero.", "Cúanto:",cantDeb);
	}
}

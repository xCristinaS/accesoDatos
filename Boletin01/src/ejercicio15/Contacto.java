package ejercicio15;

import java.io.Serializable;

public class Contacto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String nombreCont, telefono, direccion;
	public int codP; public boolean deboMoney; public float cantDeb;
	
	public Contacto(String nomCont, String tel, String dir, int codP, boolean deboMoney, float cantDeb){
		nombreCont = nomCont;
		telefono = tel;
		direccion = dir;
		this.codP = codP;
		this.deboMoney = deboMoney;
		this.cantDeb = cantDeb;
	}
	public String toString(){
		float dinero;
		return String.format("Nombre: %s | Telefono: %s | Direccio: %s | CP: %d | ¿Debo dinero?: %s %s",
				nombreCont, telefono, direccion, codP, deboMoney?"Si":"No le debo dinero.",  (dinero = cantDeb) == 0?"":"| Cantidad debida: " + dinero);
	}
}

package ejercicio10;

import java.util.LinkedList;

import ejercicio08.Contacto;

public class ListaContactos {
	
	private LinkedList<Contacto> lista = new LinkedList<Contacto>();
	
	public void agregarContacto(Contacto c){
		lista.add(c);
	}
	public LinkedList<Contacto> getLista(){
		return lista;
	}
}

package examen01;

import java.util.LinkedList;

public class ListaProfesores {
	private LinkedList<Profesor> lista = new LinkedList<Profesor>();
	
	public void agregarProfesor(Profesor p){
		lista.add(p);
	}
	
	public LinkedList<Profesor> getLista(){
		return lista;
	}
}

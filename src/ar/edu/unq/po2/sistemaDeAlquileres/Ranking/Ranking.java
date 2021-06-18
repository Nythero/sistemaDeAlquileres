package ar.edu.unq.po2.sistemaDeAlquileres.Ranking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import junit.framework.AssertionFailedError;

public class Ranking {
	private Map<String,ArrayList<Integer>> registroPorCategorias;
	
	public Ranking() {
		this.registroPorCategorias= new HashMap<>();
	}
	
	public void addCategoria(String categoria) {
		if(!this.registroPorCategorias.containsKey(categoria)) {
			this.registroPorCategorias.put(categoria, new ArrayList<Integer>());
		}
	}
	
	/**
	 * Dada una categoria y un puntaje la agrega a los registros
	 * Si el puntaje no se encuentra entre 1 y 5 inluidos o no se encuentra la categoria
	 * ambos casos devuelve error
	 * @param categoria
	 * @param puntaje
	 */
	public void addPuntajePorCategoria(String categoria, int puntaje) {
		if (!esPuntajeValido(puntaje) || !this.registroPorCategorias.containsKey(categoria)) {
			throw new AssertionFailedError("El puntaje no es valido o no esta la categoria a puntuar");
		}
		else {
			ArrayList<Integer> puntajes = this.registroPorCategorias.get(categoria);
			puntajes.add(puntaje);
			this.registroPorCategorias.put(categoria, puntajes);
		}
	}
	
	private boolean esPuntajeValido (int puntaje) {
		return puntaje >= 1 && puntaje <= 5;
	}

	/**
	 * Dada una categoria devuelve su puntaje promedio.
	 * En caso de estar vacia la lista o no encontrar la clave devuelve 0
	 * @param categoria
	 * @return
	 */
	public int puntajePromedioPorCategoria(String categoria) {
		ArrayList<Integer> puntajePorCategoria = this.registroPorCategorias.get(categoria);
		if(!this.registroPorCategorias.containsKey(categoria) || puntajePorCategoria.size()==0) {
			return 0;
		}
		else {
			int result= 0;
			for(int puntaje :puntajePorCategoria) {
				result+= puntaje;
			}
		return result/puntajePorCategoria.size();
		}
	}
	
	/**
	 * Devuelve el puntaje total del promedio (incluyendo los puntajes por categorias y los generales)
	 * Si ambas listas estan vacias retornan 0.
	 * @return
	 */
	public int puntajeTotalPromedio() {
		if (this.registroPorCategorias.size()==0){
			return 0;
		}
		else {
			ArrayList<String> elementos = new ArrayList<String >(this.registroPorCategorias.keySet());
			int contador = 0;
			int result = 0;
			for(String categoria:elementos) {
				result+= this.registroPorCategorias.get(categoria).stream().reduce(0, (a,b)-> a + b);
				contador+=this.cantidadDePuntajesSegunCategoria(categoria);
			}
			return result/ contador;
		}
	}

	/**
	 * Devuelve la cantidad de puntos segun la categoria dada.
	 * En caso de no encontrar la clave devuelve un error
	 * @param categoria
	 * @return
	 */
	public int cantidadDePuntajesSegunCategoria(String categoria) {
		if(!this.registroPorCategorias.containsKey(categoria)) {
			throw new AssertionFailedError();
		}
		else {
			return this.registroPorCategorias.get(categoria).size();
		}
	}
}

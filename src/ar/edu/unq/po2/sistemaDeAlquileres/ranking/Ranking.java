package ar.edu.unq.po2.sistemaDeAlquileres.ranking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import junit.framework.AssertionFailedError;

public class Ranking {
	private final Map<String,ArrayList<Integer>> registroPorCategorias;
	private final ArrayList<Integer> puntajeGeneral;
	private final ArrayList<String> comentarios;
	
	public Ranking() {
		this.registroPorCategorias= new HashMap<>();
		this.puntajeGeneral= new ArrayList<Integer>();
		this.comentarios = new ArrayList<String>();
	}
	
	/**
	 * Dada una categoria y un puntaje la agrega a los registros
	 * Si el puntaje no se encuentra entre 1 y 5 inluidos ambos devuelve error
	 * @param categoria
	 * @param puntaje
	 */
	public void addPuntajePorCategoria(String categoria, int puntaje) {
		if (puntaje < 1 || puntaje > 5) {
			throw new AssertionFailedError("El puntaje no es valido");
		}
		else {
			this.agregarComoCorresponde(categoria,puntaje);
		}
	}
	
	/**
	 * Añade un puntaje general a la lista de puntajes generales
	 * Si el puntaje no se encuentra entre 1 y 5 inluidos ambos devuelve error
	 * @param puntaje
	 */
	public void addPuntajeGeneral(int puntaje) {
		if (puntaje < 1 || puntaje > 5) {
			throw new AssertionFailedError();
		}
		else {
			this.puntajeGeneral.add(puntaje);
		}
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
		if (this.registroPorCategorias.size()==0 && this.puntajeGeneral.size()==0){
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
			for (Integer puntaje: this.puntajeGeneral) {
				result+= puntaje;
				contador+=1; 
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

	/**
	 * Devuelve la cantidad de puntos generales.
	 * @return
	 */
	public int cantidadDePuntajesGeneral() {
		return this.puntajeGeneral.size();
	}
}

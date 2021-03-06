package ar.edu.unq.po2.sistemaDeAlquileres.Ranking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ranking {
	private final Map<String,ArrayList<Integer>> registroPorCategorias;
	private final ArrayList<String> comentarios;
	
	public Ranking() {
		this.registroPorCategorias = new HashMap<>();
		this.comentarios = new ArrayList<String>();
	}
	
	/**
	 * Retorna los registrosPorCategorias
	 * @return
	 */
	private Map<String,ArrayList<Integer>> getRegistroPorCategorias() {
		return this.registroPorCategorias;
	}
	
	/**
	 * Retorna los comentarios
	 * @return
	 */
	private ArrayList<String> getComentarios() {
		return this.comentarios;
	}
	
	/**
	 * Dada una categoria la a?ade.
	 * En caso de existir la categoria retorna error
	 * @param categoria
	 * @throws Exception
	 */
	public void addCategoria(String categoria) throws Exception {
		try {
			this.verificarCategoria(categoria);
			throw new Exception("La categoria ya existe");
		}
		catch(Exception error) {
			if (error.getMessage() == "La categoria no existe") {
				this.registroPorCategorias.put(categoria, new ArrayList<Integer>());
			}
			else {
				throw error;
			}
		}
	}
	
	/**
	 * Dada una categoria y un puntaje la agrega a los registros
	 * Si el puntaje no se encuentra entre 1 y 5 inluidos devuelve error
	 * @param categoria
	 * @param puntaje
	 * @throws Exception 
	 */
	public void addPuntajePorCategoria(String categoria, int puntaje) throws Exception {
		this.verificarCategoria(categoria);
		if (!esPuntajeValido(puntaje)) {
			throw new Exception("El puntaje no es valido");
		}
		else {
			ArrayList<Integer> puntajes = this.registroPorCategorias.get(categoria);
			puntajes.add(puntaje);
			this.registroPorCategorias.put(categoria, puntajes);
		}
	}
	
	/**
	 * Dado un puntaje retorna si es valido, es decir, si se encuentra entre 1 y 5 incluidos
	 * @param puntaje
	 * @return
	 */
	private boolean esPuntajeValido (int puntaje) {
		return puntaje >= 1 && puntaje <= 5;
	}
	
	/**
	 * Dada una categoria retorna si ya se encuentra la misma registrada
	 * @param categoria
	 * @throws Exception
	 */
	private void verificarCategoria(String categoria) throws Exception {
		if(!this.getRegistroPorCategorias().containsKey(categoria)) {
			throw new Exception("La categoria no existe");
		}
	}

	/**
	 * Dada una categoria devuelve su puntaje promedio.
	 * En caso de estar vacia la lista o no encontrar la clave devuelve 0
	 * @param categoria
	 * @return
	 * @throws Exception 
	 */
	public int puntajePromedioPorCategoria(String categoria) throws Exception {
		this.verificarCategoria(categoria);
		ArrayList<Integer> puntajePorCategoria = this.getRegistroPorCategorias().get(categoria);
		
		int result = 0;
		for(int puntaje : puntajePorCategoria) {
			result += puntaje;
		}
		
		return result / ((puntajePorCategoria.size() == 0)? 1 : puntajePorCategoria.size());
	}
	
	/**
	 * Devuelve el puntaje total del promedio
	 * @return
	 * @throws Exception 
	 */
	public int puntajeTotalPromedio() throws Exception {
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
	 * @throws Exception 
	 */
	public int cantidadDePuntajesSegunCategoria(String categoria) throws Exception {
		if(!this.registroPorCategorias.containsKey(categoria)) {
			throw new Exception("La categoria no existe");
		}
		else {
			return this.registroPorCategorias.get(categoria).size();
		}
	}
	
	/**
	 * Dado un comentario lo a?ade
	 * @param comentario
	 */
	public void agregarComentario(String comentario) {
		this.getComentarios().add(comentario);
	}
}


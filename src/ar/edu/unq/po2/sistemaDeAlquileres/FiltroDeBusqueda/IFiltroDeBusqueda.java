package ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

public interface IFiltroDeBusqueda {
	/**
	 * Dado un filtro lo remueve.
	 * En caso de no poder realizarse devuelve error
	 * @param filtro
	 * @throws Exception
	 */
	public void removerFiltro(IFiltroDeBusqueda filtro) throws Exception;
	
	/**
	 * Dado un filtro lo agrega.
	 * En caso de no poder realizarse devuelve error
	 * @param filtro
	 * @throws Exception
	 */
	public void agregarFiltro(IFiltroDeBusqueda filtro) throws Exception;
	
	/**
	 * Dado un inmueble retorna si cumple con el filtro
	 * @param inmueble
	 * @return
	 */
	public boolean cumpleConElFiltro(Inmueble inmueble);
}

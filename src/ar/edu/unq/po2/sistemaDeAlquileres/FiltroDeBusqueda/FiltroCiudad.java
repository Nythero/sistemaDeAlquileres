package ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

public class FiltroCiudad implements IFiltroDeBusqueda{
	private String ciudad;

	public FiltroCiudad(String ciudad) throws Exception {
		this.setCiudad(ciudad);
	}
	
	/**
	 * Retorna la ciudad del filtro
	 * @return
	 */
	public String getCiudad() {
		return ciudad;
	}
	
	/**
	 * Dada una ciudad la setea
	 * En caso de ser Null devuelve error
	 * @param ciudad
	 * @throws Exception
	 */
	public void setCiudad(String ciudad) throws Exception {
		if (ciudad == null) {
			throw new Exception("La ciudad no debe ser null");
		}
		this.ciudad = ciudad;
	}

	@Override
	public void removerFiltro(IFiltroDeBusqueda filtro) throws Exception {
		throw new Exception("Operacion no valida");
	}

	@Override
	public void agregarFiltro(IFiltroDeBusqueda filtro) throws Exception {
		throw new Exception("Operacion no valida");
	}

	@Override
	public boolean cumpleConElFiltro(Inmueble inmueble) {
		return (inmueble.getCiudad() == this.getCiudad());
	}
	
}

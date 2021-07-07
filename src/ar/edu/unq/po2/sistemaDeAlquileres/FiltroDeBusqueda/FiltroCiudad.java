package ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

public class FiltroCiudad implements IFiltroDeBusqueda{
	private String ciudad;

	public FiltroCiudad(String ciudad) throws Exception {
		this.setCiudad(ciudad);
	}
	
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) throws Exception {
		if (ciudad == null) {
			throw new Exception("La ciudad no debe ser null");
		}
		this.ciudad = ciudad;
	}

	@Override
	public void removerFiltro(IFiltroDeBusqueda filtro) {
		//throw new Exception("Operacion no valida");
	}

	@Override
	public void agregarFiltro(IFiltroDeBusqueda filtro) {
		//throw new Exception("Operacion no valida");
	}

	@Override
	public boolean cumpleConElFiltro(Inmueble inmueble) {
		return (inmueble.getCiudad() == this.getCiudad());
	}
	
}

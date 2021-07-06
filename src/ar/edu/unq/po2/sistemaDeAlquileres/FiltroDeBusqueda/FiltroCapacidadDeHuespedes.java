package ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

public class FiltroCapacidadDeHuespedes implements IFiltroDeBusqueda{
	private Integer cantidad;

	public FiltroCapacidadDeHuespedes(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Integer getCapacidad() {
		return cantidad;
	}

	@Override
	public void removerFiltro(IFiltroDeBusqueda filtro) {
	}

	@Override
	public void agregarFiltro(IFiltroDeBusqueda filtro) {
	}

	@Override
	public boolean cumpleConElFiltro(Inmueble inmueble) {
		return (this.getCapacidad() == null || 
				(inmueble.getCapacidad() == this.getCapacidad()));
	}
}
package ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

public interface IFiltroDeBusqueda {
	public void removerFiltro(IFiltroDeBusqueda filtro);
	public void agregarFiltro(IFiltroDeBusqueda filtro);
	public boolean cumpleConElFiltro(Inmueble inmueble);
}

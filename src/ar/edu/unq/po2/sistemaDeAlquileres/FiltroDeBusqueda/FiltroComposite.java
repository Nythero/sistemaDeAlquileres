package ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda;

import java.util.ArrayList;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

public class FiltroComposite implements IFiltroDeBusqueda {
	private ArrayList<IFiltroDeBusqueda>filtros;  
	
	public FiltroComposite(){
		this.filtros = new ArrayList<IFiltroDeBusqueda>();
	}
	
	public ArrayList<IFiltroDeBusqueda> getFiltros(){
		return this.filtros;
	}
	
	@Override
	public void removerFiltro(IFiltroDeBusqueda filtro) {
		this.getFiltros().remove(filtro);
	}

	@Override
	public void agregarFiltro(IFiltroDeBusqueda filtro) {
		this.getFiltros().add(filtro);
	}

	@Override
	public boolean cumpleConElFiltro(Inmueble inmueble) {
		boolean resultado = true;
		for(IFiltroDeBusqueda filtro : this.getFiltros()) {
			resultado &= filtro.cumpleConElFiltro(inmueble);
		}
		return resultado;
	}
}

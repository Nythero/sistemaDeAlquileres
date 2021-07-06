package ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda;

import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

public class FiltroPrecioMinimoYMaximo implements IFiltroDeBusqueda {
	private Float precioMinimo;
	private Float precioMaximo;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	
	public FiltroPrecioMinimoYMaximo(Float precioMinimo, Float precioMaximo,
									 LocalDate fechaEntrada,LocalDate fechaSalida){
		this.precioMinimo = precioMinimo;
		this.precioMaximo = precioMaximo;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida  = fechaSalida;
	}
	
	public LocalDate getFechaEntrada() {
		return this.fechaEntrada;
	}
	
	public LocalDate getFechaSalida() {
		return this.fechaEntrada;
	}
	
	public Float getPrecioMaximo() {
		return precioMaximo;
	}
	
	public void setPrecioMaximo(Float precioMaximo) {
		if (precioMaximo == null) {
			this.precioMaximo = Float.MAX_VALUE;
		}
		else { 
			this.precioMaximo = precioMaximo;
		}
	}
	
	public Float getPrecioMinimo() {
		return precioMinimo;
	}
	
	public void setPrecioMinimo(Float precioMinimo) {
		if (precioMinimo == null) {
			this.precioMinimo = 0f;
		}
		else {
			this.precioMinimo = precioMinimo;
		}
	}

	@Override
	public void removerFiltro(IFiltroDeBusqueda filtro) {
	}

	@Override
	public void agregarFiltro(IFiltroDeBusqueda filtro) {
	}

	@Override
	public boolean cumpleConElFiltro(Inmueble inmueble) {
		return (inmueble.precioMaximoDelRangoDeFechasEntre(this.fechaEntrada, this.fechaSalida) >= 
				this.getPrecioMinimo() && 
				inmueble.precioMaximoDelRangoDeFechasEntre(this.fechaEntrada, this.fechaSalida) <= 
				this.getPrecioMaximo());
	}
}

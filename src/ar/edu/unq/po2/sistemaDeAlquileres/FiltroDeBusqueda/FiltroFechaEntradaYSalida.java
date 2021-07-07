package ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda;

import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

public class FiltroFechaEntradaYSalida implements IFiltroDeBusqueda{
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;

	public FiltroFechaEntradaYSalida(LocalDate fechaEntrada,LocalDate fechaSalida) throws Exception {
		this.setFechaEntrada(fechaEntrada);
		this.setFechaSalida(fechaSalida);
	}
	
	public LocalDate getFechaEntrada() {
		return this.fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) throws Exception {
		if (fechaEntrada == null) {
			throw new Exception("La fecha no debe ser null");
		}
		this.fechaEntrada = fechaEntrada;
	}
	
	public LocalDate getFechaSalida() {
		return this.fechaSalida;
	}
	
	public void setFechaSalida(LocalDate fechaSalida) throws Exception {
		if (fechaSalida == null) {
			throw new Exception("La fecha no debe ser null");
		}
		this.fechaSalida = fechaSalida;
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
		return (inmueble.hayAlgunRangoDeFechasQuePoseaLasFecha(this.getFechaEntrada(),this.getFechaSalida())); 
																
	}
	
}
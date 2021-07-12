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
	
	/**
	 * Retorna la fecha de Entrada
	 * @return
	 */
	public LocalDate getFechaEntrada() {
		return this.fechaEntrada;
	}

	/**
	 * Dada una fechaEntrada setea el filtro.
	 * En caso de ser Null devuelve error
	 * @param fechaEntrada
	 * @throws Exception
	 */
	public void setFechaEntrada(LocalDate fechaEntrada) throws Exception {
		if (fechaEntrada == null) {
			throw new Exception("La fecha no debe ser null");
		}
		this.fechaEntrada = fechaEntrada;
	}
	
	/**
	 * Retorna la fecha de salida
	 * @return
	 */
	public LocalDate getFechaSalida() {
		return this.fechaSalida;
	}
	
	/**
	 * Dada una fechaSalida setea el filtro.
	 * En caso de ser Null devuelve error
	 * @param fechaSalida
	 * @throws Exception
	 */
	public void setFechaSalida(LocalDate fechaSalida) throws Exception {
		if (fechaSalida == null) {
			throw new Exception("La fecha no debe ser null");
		}
		this.fechaSalida = fechaSalida;
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
		return (inmueble.hayAlgunRangoDeFechasQuePoseaLasFecha(this.getFechaEntrada(),this.getFechaSalida())); 
																
	}
	
}
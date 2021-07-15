package ar.edu.unq.po2.sistemaDeAlquileres.Temporada;

import java.time.LocalDate;


import junit.framework.AssertionFailedError;

public class FeriadosInamovibles extends Temporada {
	private float precioEnFeriado;
	private Feriado feriados;

	public FeriadosInamovibles(float precioEnDiaCotidiano, float precioEnFeriado) throws Exception {
		super(precioEnDiaCotidiano);
		this.setPrecioEnFeriado(precioEnFeriado);
		this.feriados= new Feriado();
	}
	
	/**
	 * Dado un precio setea el precio en la Temporada.
	 * En caso de ser negativo devuelve error
	 * @param precio
	 * @throws Exception
	 */
	public void setPrecioEnFeriado(float precio) throws Exception {
		if (precio <0) {
			throw new Exception("El precio no puede ser negativo");
		}
		else {
			this.precioEnFeriado= precio;
		}
	}
	
	@Override
	public float getPrecio(LocalDate fecha) {
		if (esFeriado(fecha)){
			return this.precioEnFeriado;
		}
		else {
			return this.getPrecioCotidiano();
		}
	}

	/**
	 * Retorna si el fecha dada es feriado
	 * @param fecha
	 * @return
	 */
	private boolean esFeriado(LocalDate fecha) {
		return this.feriados.feriadosDelMes(fecha.getMonthValue()).contains(fecha.getDayOfMonth());
	}

	@Override
	public void bajarPrecioEspecial(float precioNuevo) {
		if (precioNuevo > this.precioEnFeriado || precioNuevo <0){
			throw new AssertionFailedError("El precio supera al actual o es negativo");
		}
		else {
			this.precioEnFeriado=precioNuevo;
		}
	}
}

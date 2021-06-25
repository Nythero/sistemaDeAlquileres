package ar.edu.unq.po2.sistemaDeAlquileres.Temporada;

import java.time.LocalDate;


import junit.framework.AssertionFailedError;

public class FeriadosInamovibles extends Temporada {
	private float precioEnFeriado;
	private Feriado feriados;

	public FeriadosInamovibles(float precioEnDiaCotidiano, float precioEnFeriado) throws Exception {
		super(precioEnDiaCotidiano);
		this.precioEnFeriado= precioEnFeriado;
		this.feriados= new Feriado();
	}
	
	@Override
	/**
	 * Si el dia es feriado retorna el precio de feriado.
	 * Caso contrario retorna el precio cotidiano
	 */
	public float getPrecio(LocalDate fecha) {
		if (esFeriado(fecha)){
			return this.precioEnFeriado;
		}
		else {
			return this.getPrecioCotidiano();
		}
	}

	/**
	 * Retorna si el dia dado es feriado
	 * @param dia
	 * @return
	 */
	private boolean esFeriado(LocalDate fecha) {
		return this.feriados.feriadosDelMes(fecha.getMonthValue()).contains(fecha.getDayOfMonth());
	}

	@Override
	/**
	 * Baja los precios.
	 * En caso que no se puedan bajar devuelve error
	 */
	public void bajarPrecioEspecial(float precioNuevo) {
		if (precioNuevo > this.precioEnFeriado || precioNuevo <0){
			throw new AssertionFailedError("El precio supera al actual o es negativo");
		}
		else {
			this.precioEnFeriado=precioNuevo;
		}
	}
}

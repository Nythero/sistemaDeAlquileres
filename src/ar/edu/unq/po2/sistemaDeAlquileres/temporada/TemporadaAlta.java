package ar.edu.unq.po2.sistemaDeAlquileres.Temporada;

import java.time.LocalDate;

import junit.framework.AssertionFailedError;

public class TemporadaAlta extends Temporada {
	private float precioEnTemporada;
	
	public TemporadaAlta(float precioCotidiano, float precioEnTemporada ) {
		super(precioCotidiano);
		this.precioEnTemporada= precioEnTemporada;
	}
	
	@Override
	/**
	 * Retorna el precio en temporada si se encuentra en el mismo.
	 * Caso contrario retorna el precio fuera de temporada
	 */
	public float getPrecio(LocalDate fecha) {
		if (fecha.getMonthValue()== 12 || fecha.getMonthValue() == 1 || fecha.getMonthValue() == 2) {
			return this.precioEnTemporada; 
		}
		else {
			return this.getPrecioCotidiano(); 
		}
	}

	@Override
	/**
	 * Baja los precios.
	 * Si no es posible, retorna error
	 */
	public void bajarPrecioEspecial(float precioNuevo) {
		if (precioNuevo > this.precioEnTemporada || precioNuevo <0){
			throw new AssertionFailedError("El precio supera al actual o es negativo");
		}
		else {
			this.precioEnTemporada=precioNuevo;
		}
	}
}
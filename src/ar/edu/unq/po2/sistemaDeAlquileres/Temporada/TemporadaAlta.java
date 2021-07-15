package ar.edu.unq.po2.sistemaDeAlquileres.Temporada;

import java.time.LocalDate;

import junit.framework.AssertionFailedError;

public class TemporadaAlta extends Temporada {
	private float precioEnTemporada;
	
	public TemporadaAlta(float precioCotidiano, float precioEnTemporada ) throws Exception {
		super(precioCotidiano);
		this.setPrecioEnTemporada(precioEnTemporada);
	}
	
	/**
	 * Dado un precio setea el precio en la Temporada.
	 * En caso de ser negativo devuelve error
	 * @param precio
	 * @throws Exception
	 */
	public void setPrecioEnTemporada(float precio) throws Exception {
		if (precio <0) {
			throw new Exception("El precio no puede ser negativo");
		}
		else {
			this.precioEnTemporada= precio;
		}
	}
	
	@Override
	public float getPrecio(LocalDate fecha) {
		if (fecha.getMonthValue()== 12 || fecha.getMonthValue() == 1 || fecha.getMonthValue() == 2) {
			return this.precioEnTemporada; 
		}
		else {
			return this.getPrecioCotidiano(); 
		}
	}

	@Override
	public void bajarPrecioEspecial(float precioNuevo) {
		if (precioNuevo > this.precioEnTemporada || precioNuevo <0){
			throw new AssertionFailedError("El precio supera al actual o es negativo");
		}
		else {
			this.precioEnTemporada=precioNuevo;
		}
	}
}
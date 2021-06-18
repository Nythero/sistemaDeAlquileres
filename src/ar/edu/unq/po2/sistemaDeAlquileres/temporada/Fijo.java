package ar.edu.unq.po2.sistemaDeAlquileres.temporada;

import java.time.LocalDate;

import junit.framework.AssertionFailedError;

public class Fijo implements Temporada {
	private float precio;
	
	public Fijo(float precio) {
		this.precio= precio;
	}
	@Override
	/**
	 * Retorna el precio para el dia dado. En este caso para cualquier dia retorna
	 * el mismo valor
	 */
	public float getPrecio(LocalDate fecha) {
		return this.precio;
	}
	
	@Override
	/**
	 * Baja el precio.
	 * Si no es posible retorna error
	 */
	public void bajarPrecio(float precioADescontar) {
		if (precioADescontar> this.precio || precioADescontar <0) {
			throw new AssertionFailedError();
		}
		else {
			this.precio-=precioADescontar;
		}
		
	}

}

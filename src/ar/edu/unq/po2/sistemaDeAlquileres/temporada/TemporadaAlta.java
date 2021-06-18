package ar.edu.unq.po2.sistemaDeAlquileres.temporada;

import java.time.LocalDate;

import junit.framework.AssertionFailedError;

public class TemporadaAlta implements Temporada {
	private float precioEnTemporada;
	private float precioFueraDeTemporada;
	
	public TemporadaAlta(Float precioEnTemporada, float precioFueraDeTemporada ) {
		this.precioEnTemporada= precioEnTemporada;
		this.precioFueraDeTemporada= precioFueraDeTemporada;
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
			return this.precioFueraDeTemporada; 
		}
	}

	@Override
	/**
	 * Baja los precios.
	 * Si no es posible, retorna error
	 */
	public void bajarPrecio(float precioADescontar) {
		if (elPrecioADescontarSuperaAlPrecioDeLaSemana(precioADescontar) && 
				elPrecioADescontarSuperaAlPrecioDeFindeLargo(precioADescontar)){
			throw new AssertionFailedError();
		}
		else {
			this.precioEnTemporada-=precioADescontar;
			this.precioFueraDeTemporada-=precioADescontar;
		}

	}

	/**
	 * Verifica si el precio a descontar es valido para bajar el precio
	 * @param precioADescontar
	 * @return
	 */
	private boolean elPrecioADescontarSuperaAlPrecioDeFindeLargo(float precioADescontar) {
		return precioADescontar> this.precioEnTemporada || precioADescontar <0;
	}
	
	/**
	 * Verifica si el precio a descontar es valido para bajar el precio
	 * @param precioADescontar
	 * @return
	 */
	private boolean elPrecioADescontarSuperaAlPrecioDeLaSemana(float precioADescontar) {
		return precioADescontar> this.precioFueraDeTemporada || precioADescontar <0;
	}

}

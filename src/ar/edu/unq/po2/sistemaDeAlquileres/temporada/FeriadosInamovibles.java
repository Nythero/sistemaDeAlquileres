package ar.edu.unq.po2.sistemaDeAlquileres.temporada;

import java.time.LocalDate;


import junit.framework.AssertionFailedError;

public class FeriadosInamovibles implements Temporada {
	private float precioEnFeriado;
	private float precioEnDiaCotidiano;
	private Feriado feriados;

	public FeriadosInamovibles(Float precioEnFeriado, Float precioEnDiaCotidiano) {
		this.precioEnFeriado= precioEnFeriado;
		this.precioEnDiaCotidiano= precioEnDiaCotidiano;
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
			return this.precioEnDiaCotidiano;
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
	public void bajarPrecio(float precioADescontar) {
		if (elPrecioADescontarSuperaAlPrecioDelFeriado(precioADescontar) && 
				elPrecioADescontarSuperaAlPrecioDeDiaCotidiano(precioADescontar)){
			throw new AssertionFailedError();
		}
		else {
			this.precioEnFeriado-=precioADescontar;
			this.precioEnDiaCotidiano-=precioADescontar;
		}

	}

	/**
	 * Retorna si el precio a descontar es valido para bajar el precio
	 * @param precioADescontar
	 * @return
	 */
	private boolean elPrecioADescontarSuperaAlPrecioDelFeriado(float precioADescontar) {
		return precioADescontar> this.precioEnFeriado || precioADescontar <0;
	}
	
	/**
	 * Retorna si el precio a descontar es valido para bajar el precio
	 * @param precioADescontar
	 * @return
	 */
	private boolean elPrecioADescontarSuperaAlPrecioDeDiaCotidiano(float precioADescontar) {
		return precioADescontar> this.precioEnDiaCotidiano || precioADescontar <0;
	}

	
	
	

}

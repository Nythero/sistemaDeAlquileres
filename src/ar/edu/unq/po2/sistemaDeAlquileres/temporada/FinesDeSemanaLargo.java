package ar.edu.unq.po2.sistemaDeAlquileres.temporada;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;

import junit.framework.AssertionFailedError;

public class FinesDeSemanaLargo implements Temporada {
	private float precioEnDiaDeSemana;
	private float precioEnFinDeSemanaLargo;
	
	public FinesDeSemanaLargo(float precioEnDiaDeSemana, float precioEnFinDeSemanaLargo) {
		this.precioEnDiaDeSemana= precioEnDiaDeSemana;
		this.precioEnFinDeSemanaLargo= precioEnFinDeSemanaLargo;
	}
	
	@Override
	/**
	 * Si el dia dado forma parte de finDeSemanaLargo entonces retorna precioEnFinDeSemanaLargo
	 * Caso contrario precioEnDiaDeSemana 
	 */
	public float getPrecio(LocalDate fecha) {
		if (esFinDeSemanaLargo(fecha)) {
			return this.precioEnFinDeSemanaLargo;
		}
		else {
			return this.precioEnDiaDeSemana;
		}
	}

	/**
	 * Verifica si el dia actual es parte de un fin de semana largo
	 * @param diaActual
	 * @return
	 */
	private boolean esFinDeSemanaLargo(LocalDate fecha) {
		return fecha.getDayOfWeek().getValue() == 1|| 
			   fecha.getDayOfWeek().getValue() == 6 || 
			   fecha.getDayOfWeek().getValue() == 7;
	}

	@Override
	/**
	 * Baja los precios.
	 * De no ser posible, retorna error
	 */
	public void bajarPrecio(float precioADescontar) {
		if (elPrecioADescontarSuperaAlPrecioDeLaSemana(precioADescontar) && 
				elPrecioADescontarSuperaAlPrecioDeFindeLargo(precioADescontar)){
			throw new AssertionFailedError("No se puede bajar de precio");
		}
		else {
			this.precioEnFinDeSemanaLargo-=precioADescontar;
			this.precioEnDiaDeSemana-=precioADescontar;
		}

	}

	/**
	 * Verifica si el precioADescontar es valido para bajar el precio
	 * @param precioADescontar
	 * @return
	 */
	private boolean elPrecioADescontarSuperaAlPrecioDeFindeLargo(float precioADescontar) {
		return precioADescontar> this.precioEnFinDeSemanaLargo || precioADescontar <0;
	}
	
	/**
	 * Verifica si el precioADescontar es valido para bajar el precio
	 * @param precioADescontar
	 * @return
	 */
	private boolean elPrecioADescontarSuperaAlPrecioDeLaSemana(float precioADescontar) {
		return precioADescontar> this.precioEnDiaDeSemana || precioADescontar <0;
	}

}

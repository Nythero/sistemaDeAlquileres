package ar.edu.unq.po2.sistemaDeAlquileres.temporada;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

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
	public float getPrecio(Date dia) {
		if (esFeriado(dia)){
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
	private boolean esFeriado(Date dia) {
		return this.feriados.feriadosDelMes(dia.getMonth()).contains(dia.getDay());
			//	getFeriadosInamovibles().get(dia.getMonth()).contains(dia.getDay());
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

	/**
	 * Retorna los feriados (En esta caso el objeto Feriado)
	 * @return
	 */
	public Feriado getFeriados() {
		return this.feriados;
	}
	
	

}

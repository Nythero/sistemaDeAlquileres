package ar.edu.unq.po2.sistemaDeAlquileres.Temporada;
import java.time.LocalDate;

import junit.framework.AssertionFailedError;

public class FinesDeSemanaLargo extends Temporada {
	private float precioEnFinDeSemanaLargo;
	
	public FinesDeSemanaLargo(float precioCotidiano, float precioEnFinDeSemanaLargo) throws Exception {
		super(precioCotidiano);
		this.setPrecioEnFinDeSemanaLargo(precioEnFinDeSemanaLargo);
	}
	
	/**
	 * Dado un precio setea el precio en la Temporada.
	 * En caso de ser negativo devuelve error
	 * @param precio
	 * @throws Exception
	 */
	public void setPrecioEnFinDeSemanaLargo(float precio) throws Exception {
		if (precio <0) {
			throw new Exception("El precio no puede ser negativo");
		}
		else {
			this.precioEnFinDeSemanaLargo= precio;
		}
	}
	
	@Override
	public float getPrecio(LocalDate fecha) {
		if (esFinDeSemanaLargo(fecha)) {
			return this.precioEnFinDeSemanaLargo;
		}
		else {
			return this.getPrecioCotidiano();
		}
	}

	/**
	 * Verifica si fecha es parte de un fin de semana largo
	 * @param fecha
	 * @return
	 */
	private boolean esFinDeSemanaLargo(LocalDate fecha) {
		return fecha.getDayOfWeek().getValue() == 1|| 
			   fecha.getDayOfWeek().getValue() == 6 || 
			   fecha.getDayOfWeek().getValue() == 7;
	}

	@Override
	public void bajarPrecioEspecial(float precioNuevo) {
		if(precioNuevo > this.precioEnFinDeSemanaLargo || precioNuevo <0) {
			throw new AssertionFailedError("El precio supera al actual o es negativo");
		}
		else {
		this.precioEnFinDeSemanaLargo= precioNuevo;
		}
	}
}

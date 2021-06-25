package ar.edu.unq.po2.sistemaDeAlquileres.Temporada;
import java.time.LocalDate;

import junit.framework.AssertionFailedError;

public class FinesDeSemanaLargo extends Temporada {
	private float precioEnFinDeSemanaLargo;
	
	public FinesDeSemanaLargo(float precioCotidiano, float precioEnFinDeSemanaLargo) throws Exception {
		super(precioCotidiano);
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
			return this.getPrecioCotidiano();
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
	
	public void bajarPrecioEspecial(float precioNuevo) {
		if(precioNuevo > this.precioEnFinDeSemanaLargo || precioNuevo <0) {
			throw new AssertionFailedError("El precio supera al actual o es negativo");
		}
		else {
		this.precioEnFinDeSemanaLargo= precioNuevo;
		}
	}
}

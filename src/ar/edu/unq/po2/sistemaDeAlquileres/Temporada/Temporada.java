package ar.edu.unq.po2.sistemaDeAlquileres.Temporada;

import java.time.LocalDate;

import junit.framework.AssertionFailedError;

public abstract class Temporada {
	private float precioCotidiano;
	
	public Temporada(float precioCotidiano) throws Exception {
		this.setPrecioCotidiano(precioCotidiano);
	}
	/**
	 * Dada una fecha devuelve su precio.
	 * @param fecha
	 * @return
	 */
	public abstract float getPrecio(LocalDate fecha);
	
	/**
	 * Dado un precioNuevo setea a la temporada. En caso de ser negativo devuelve error
	 * @param precioNuevo
	 */
	public void bajarPrecioEspecial(float precioNuevo) {
		
	}

	/**
	 * Retorna el precio cotidiano
	 * @return
	 */
	public float getPrecioCotidiano() {
		return this.precioCotidiano;
	}
	
	/**
	 * Dado un precio setea el precio en la Temporada.
	 * En caso de ser negativo devuelve error
	 * @param precioCotidiano
	 * @throws Exception
	 */
	public void setPrecioCotidiano(float precioCotidiano) throws Exception {
		if(precioCotidiano <0) {
			throw new Exception("El precio no puede ser negativo");
		}
		else {
			this.precioCotidiano = precioCotidiano;
		}
	}
	
	/**
	 * Dado un precioNuevo setea a la temporada. En caso de ser negativo devuelve error
	 * @param precioNuevo
	 */
	public void bajarAlPrecioCotidiano(float precioNuevo){
		if (precioNuevo > this.precioCotidiano || precioNuevo <0) {
			throw new AssertionFailedError("El precio supera al actual o es negativo");
		}
		else {
			this.precioCotidiano= precioNuevo;
		}
	}
}



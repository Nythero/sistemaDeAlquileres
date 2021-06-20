package ar.edu.unq.po2.sistemaDeAlquileres.Temporada;

import java.time.LocalDate;

import junit.framework.AssertionFailedError;

public abstract class Temporada {
	private float precioCotidiano;
	
	public Temporada(float precioCotidiano) {
		this.precioCotidiano= precioCotidiano;
	}

	public abstract float getPrecio(LocalDate fecha);
	
	public void bajarPrecioEspecial(float precioNuevo) {
		
	}

	public float getPrecioCotidiano() {
		return this.precioCotidiano;
	}

	public void setPrecioCotidiano(float precioCotidiano) {
		this.precioCotidiano = precioCotidiano;
	}
	
	public void bajarAlPrecioCotidiano(float precioNuevo){
		if (precioNuevo > this.precioCotidiano || precioNuevo <0) {
			throw new AssertionFailedError("El precio supera al actual o es negativo");
		}
		else {
			this.precioCotidiano= precioNuevo;
		}
	}
}



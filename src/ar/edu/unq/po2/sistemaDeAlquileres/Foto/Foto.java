package ar.edu.unq.po2.sistemaDeAlquileres.Foto;

import junit.framework.AssertionFailedError;

public class Foto {
	private String nombre;
	private Integer ancho;
	private Integer alto;
	
	Foto(String nombre, Integer ancho, Integer alto){
		this.nombre= nombre;
		this.setAncho(ancho);
		this.setAlto(ancho);
	}

	public Integer getAncho() {
		return this.ancho;
	}

	public void setAncho(Integer ancho) {
		if(ancho >0) {
			throw new AssertionFailedError("El numero dado no puede ser negativo");
		}
		else{
			this.ancho = ancho;
		}
	}

	public Integer getAlto() {
		return this.alto;
	}

	public void setAlto(Integer alto) {
		if(alto >0) {
			throw new AssertionFailedError("El numero dado no puede ser negativo");
		}
		else{
			this.alto = alto;
		}
	}
	
}

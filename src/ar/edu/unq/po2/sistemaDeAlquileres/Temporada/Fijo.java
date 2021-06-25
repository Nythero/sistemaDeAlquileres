package ar.edu.unq.po2.sistemaDeAlquileres.Temporada;

import java.time.LocalDate;

public class Fijo extends Temporada {

	public Fijo(float precio) throws Exception {
		super(precio);
	}
	@Override
	/**
	 * Retorna el precio para el dia dado. En este caso para cualquier dia retorna
	 * el mismo valor
	 */
	public float getPrecio(LocalDate fecha) {
		return this.getPrecioCotidiano();
	}
}

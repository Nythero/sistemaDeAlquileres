package ar.edu.unq.po2.sistemaDeAlquileres.IObsevers;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

public interface IObserver {
	/**
	 * Dado un inmueble y un arg opcional actualiza a los observers.
	 * @param inmueble
	 * @param arg
	 */
	void update(Inmueble inmueble, Object arg);
}

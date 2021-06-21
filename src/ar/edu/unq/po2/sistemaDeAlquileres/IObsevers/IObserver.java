package ar.edu.unq.po2.sistemaDeAlquileres.IObsevers;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

public interface IObserver {
	
	/**
	 * Actualiza el la pagina cuando le llega la notificacion
	 */
	void update(Inmueble inmueble, Object arg);
}

package ar.edu.unq.po2.sistemaDeAlquileres.IObsevers;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

public interface IObserver {
	void update(Inmueble inmueble, Object arg);
}

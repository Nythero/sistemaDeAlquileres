package ar.edu.unq.po2.sistemaDeAlquileres.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ar.edu.unq.po2.sistemaDeAlquileres.IObsevers.IObserver;
import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import junit.framework.AssertionFailedError;

public class Observable {
	private Map<String,ArrayList<IObserver>> suscripciones;
	
	public Observable() {
		suscripciones = new HashMap<String,ArrayList<IObserver>>();
    }
	
	
	public Integer getCantidadDeSuscritosAUnaSuscripcion(String suscripcion) {
		if(!this.existeLaSuscripcion(suscripcion)) {
			throw new AssertionFailedError("No existe la suscripcion buscada");
		}
		else {
			return this.suscripciones.get(suscripcion).size();
		}
	}
	
	public Integer getCantidadDeSuscripciones() {
		return this.suscripciones.size();
	}
	
	public void add(String tipoDeSuscripcion, IObserver observer) {
		if (!this.existeLaSuscripcion(tipoDeSuscripcion)){
			ArrayList<IObserver> listaConSuscriptoNuevo = new ArrayList<IObserver>();
			listaConSuscriptoNuevo.add(observer);
			this.suscripciones.put(tipoDeSuscripcion, listaConSuscriptoNuevo);
		}
		else if (!estaSuscrito(tipoDeSuscripcion,observer)) {
			ArrayList<IObserver> suscriptores = this.suscripciones.get(tipoDeSuscripcion);
			suscriptores.add(observer);
			this.suscripciones.put(tipoDeSuscripcion, suscriptores);
		}
	}
	
	public void remove(String tipoDeSuscripcion,IObserver observer) {
		if(!this.existeLaSuscripcion(tipoDeSuscripcion) || !estaSuscrito(tipoDeSuscripcion,observer)) {
			throw new AssertionFailedError("No existe la suscripcion buscada o no se encuentra suscrito el usuario dado");
			}
		else {
			this.suscripciones.get(tipoDeSuscripcion).remove(observer);
		}
	}
		
	private boolean existeLaSuscripcion(String tipoDeSuscripcion) {
		return this.suscripciones.containsKey(tipoDeSuscripcion);
	}

	private boolean estaSuscrito(String tipoDeSuscripcion, IObserver observer) {
		return this.suscripciones.get(tipoDeSuscripcion).contains(observer);
	}
	
	public void notify(String tipoDeSuscripcion,Inmueble inmueble, Object arg) {
		if(this.existeLaSuscripcion(tipoDeSuscripcion)) {
			for (IObserver observer : this.suscripciones.get(tipoDeSuscripcion)) {
				observer.update(inmueble, arg);
			}
		}
	}
}


package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;

public abstract class EstadoReserva {
	
	EstadoReserva cancelar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError(this.codigo(), "cancelar");
	};
    
	EstadoReserva aceptar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError(this.codigo(), "aceptarr");
	};
    
	EstadoReserva finalizar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError(this.codigo(), "finalizar");
	};
    
	void comentarInmueble(Reserva reserva, String comentario) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError(this.codigo(),  "comentar el inmueble");
	};
    
	void puntuarDuenho(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError(this.codigo(),  "puntuar al dueño");
	};
    
	void puntuarInquilino(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError(this.codigo(),  "puntuar al inquilino");
	};
    
	void puntuarInmueble(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError(this.codigo(),  "puntuar al inmueble");
	};
     
	boolean estaPendienteDeAprobacion() {
		return false;
	};
    
	boolean estaConcretada() {
		return false;
	};
	
	abstract String codigo();

}

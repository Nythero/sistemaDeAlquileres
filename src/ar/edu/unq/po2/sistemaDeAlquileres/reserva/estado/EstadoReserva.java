package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;

public abstract class EstadoReserva {
	
	public EstadoReserva cancelar(Reserva reserva) throws CambioDeEstadoError, Exception {
		throw new CambioDeEstadoError(this.codigo(), "cancelar");
	};
    
	public EstadoReserva aceptar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError(this.codigo(), "aceptarr");
	};
    
	public EstadoReserva finalizar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError(this.codigo(), "finalizar");
	};
    
	public void comentarInmueble(Reserva reserva, String comentario) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError(this.codigo(),  "comentar el inmueble");
	};
    
	public void puntuarDuenho(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError(this.codigo(),  "puntuar al dueño");
	};
    
	public void puntuarInquilino(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError(this.codigo(),  "puntuar al inquilino");
	};
    
	public void puntuarInmueble(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError(this.codigo(),  "puntuar al inmueble");
	};
	
	abstract String codigo();
	
	public boolean esEstado(String estado) {
		return this.codigo() == estado;
	}
}

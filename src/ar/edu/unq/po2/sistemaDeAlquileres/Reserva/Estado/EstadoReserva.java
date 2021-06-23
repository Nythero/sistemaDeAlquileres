package ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public interface EstadoReserva {

	EstadoReserva cancelar(Reserva reserva) throws CambioDeEstadoError;

	EstadoReserva aceptar(Reserva reserva) throws CambioDeEstadoError;

	EstadoReserva finalizar(Reserva reserva) throws CambioDeEstadoError;

	void setComentario(Reserva reserva, String comentario) throws EstadoEquivocadoError;

	void setPuntajeADuenho(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError;

	void setPuntajeAInquilino(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError;

	void setPuntajeAInmueble(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError;

	boolean estaPendienteDeAprobacion();

	boolean estaConcretada();

}

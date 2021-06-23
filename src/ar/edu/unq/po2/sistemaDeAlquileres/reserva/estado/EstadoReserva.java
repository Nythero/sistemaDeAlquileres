package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;

public interface EstadoReserva {

	EstadoReserva cancelar(Reserva reserva) throws CambioDeEstadoError;

	EstadoReserva aceptar(Reserva reserva) throws CambioDeEstadoError;

	EstadoReserva finalizar(Reserva reserva) throws CambioDeEstadoError;

	void comentarInmueble(Reserva reserva, String comentario) throws EstadoEquivocadoError;

	void puntuarDuenho(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError;

	void puntuarInquilino(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError;

	void puntuarInmueble(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError;

	boolean estaPendienteDeAprobacion();

	boolean estaConcretada();

}

package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;

public class EstadoReservaCancelado implements EstadoReserva {

	@Override
	public EstadoReserva cancelar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError("Cancelado", "cancelar");
	}

	@Override
	public EstadoReserva aceptar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError("Cancelado", "aceptar");
	}

	@Override
	public EstadoReserva finalizar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError("Cancelado", "finalizar");
	}

	@Override
	public void comentarInmueble(Reserva reserva, String comentario) throws EstadoEquivocadoError{
		throw new EstadoEquivocadoError("Cancelado", " publicar un comentario");
	}

	@Override
	public void puntuarDuenho(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Cancelado", " puntuar al dueño");
	}

	@Override
	public void puntuarInquilino(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Cancelado", " puntuar al inquilino");
	}

	@Override
	public void puntuarInmueble(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError{
		throw new EstadoEquivocadoError("Cancelado", " puntuar al inmueble");
	}

	@Override
	public boolean estaPendienteDeAprobacion() {
		return false;
	}

	@Override
	public boolean estaConcretada() {
		return false;
	}
}

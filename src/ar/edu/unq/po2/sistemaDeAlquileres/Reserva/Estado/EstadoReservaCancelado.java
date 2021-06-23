package ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

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
	public void setComentario(Reserva reserva, String comentario) throws EstadoEquivocadoError{
		throw new EstadoEquivocadoError("Cancelado", " publicar un comentario");
	}

	@Override
	public void setPuntajeADuenho(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Cancelado", " puntuar al dueño");
	}

	@Override
	public void setPuntajeAInquilino(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Cancelado", " puntuar al inquilino");
	}

	@Override
	public void setPuntajeAInmueble(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError{
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

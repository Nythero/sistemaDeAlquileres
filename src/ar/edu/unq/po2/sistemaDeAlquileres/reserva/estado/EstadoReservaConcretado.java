package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;

public class EstadoReservaConcretado implements EstadoReserva {

	@Override
	public EstadoReserva cancelar(Reserva reserva) throws CambioDeEstadoError {
		return new EstadoReservaCancelado();
	}

	@Override
	public EstadoReserva aceptar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError("Concretado", "aceptar");
	}

	@Override
	public EstadoReserva finalizar(Reserva reserva) throws CambioDeEstadoError {
		return new EstadoReservaFinalizado();
	}

	@Override
	public void comentarInmueble(Reserva reserva, String comentario) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Concretado", " comentar el inmueble");
	}

	@Override
	public void puntuarDuenho(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Concretado", " puntuar al dueño");
	}

	@Override
	public void puntuarInquilino(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Concretado", " puntuar al inquilino");
	}

	@Override
	public void puntuarInmueble(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Concretado", " puntuar al inmueble");
	}

	@Override
	public boolean estaPendienteDeAprobacion() {
		return false;
	}

	@Override
	public boolean estaConcretada() {
		return true;
	}

}

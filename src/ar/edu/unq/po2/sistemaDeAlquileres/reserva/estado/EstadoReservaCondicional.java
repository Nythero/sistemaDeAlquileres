package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import ar.edu.unq.po2.sistemaDeAlquileres.mail.MailSender;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;

public class EstadoReservaCondicional implements EstadoReserva {

	@Override
	public EstadoReserva cancelar(Reserva reserva) throws CambioDeEstadoError {
		MailSender.mandarMailDeCancelacion(reserva);
		return new EstadoReservaCancelado();
	}

	@Override
	public EstadoReserva aceptar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError("Condicional", "aceptar");
	}

	@Override
	public EstadoReserva finalizar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError("Condicional", "finalizar");
	}

	@Override
	public void comentarInmueble(Reserva reserva, String comentario) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Condicional", " comentar el inmueble");
	}

	@Override
	public void puntuarDuenho(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Condicional", " puntuar al dueño");
	}

	@Override
	public void puntuarInquilino(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Condicional", " puntuar al inquilino");
	}

	@Override
	public void puntuarInmueble(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Condicional", " puntuar al inmueble");
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

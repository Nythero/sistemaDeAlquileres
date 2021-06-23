package ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado;

import ar.edu.unq.po2.sistemaDeAlquileres.Mail.MailSender;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public class EstadoReservaPendienteDeAprobacion implements EstadoReserva {

	@Override
	public EstadoReserva cancelar(Reserva reserva) {
		MailSender.mandarMailDeCancelacion(reserva);
		return new EstadoReservaCancelado();
	}

	@Override
	public EstadoReserva aceptar(Reserva reserva) {
		MailSender.mandarMailDeConfirmacion(reserva);
		if (!reserva.getInmueble().yaEstaReservado(reserva.getRangoDeFechas())) {
			return new EstadoReservaConcretado();
		}
		else {
			return new EstadoReservaCondicional();
		}
	}

	@Override
	public EstadoReserva finalizar(Reserva reserva) throws CambioDeEstadoError{
		throw new CambioDeEstadoError("Pendiente de aprobacion", "finalizar");
	}

	@Override
	public void setComentario(Reserva reserva, String comentario) throws EstadoEquivocadoError{
		throw new EstadoEquivocadoError("Pendiente de Aprobacion", " publicar un comentario");
	}

	@Override
	public void setPuntajeADuenho(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Pendiente de Aprobacion", " puntuar al dueño");
	}

	@Override
	public void setPuntajeAInquilino(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Pendiente de Aprobacion", " puntuar al inquilino");
	}
	
	@Override
	public void setPuntajeAInmueble(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError{
		throw new EstadoEquivocadoError("Pendiente de Aprobacion", " puntuar al inmueble");
	}

	@Override
	public boolean estaPendienteDeAprobacion() {
		return true;
	}

	@Override
	public boolean estaConcretada() {
		return false;
	}
}

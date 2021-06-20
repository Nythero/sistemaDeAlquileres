package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.mail.MailSender;

public class EstadoReservaPendienteDeAprobacion implements EstadoReserva {

	@Override
	public EstadoReserva cancelar(Reserva reserva) {
		reserva.getInmueble().cancelarReserva(reserva);
		MailSender.mandarMailDeCancelacion(reserva);
		return new EstadoReservaCancelado();
	}

	@Override
	public EstadoReserva aceptar(Reserva reserva) {
		reserva.getInmueble().aceptarReserva(reserva);
		MailSender.mandarMailDeConfirmacion(reserva);
		return new EstadoReservaAprobado();
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
}

package ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado;

import ar.edu.unq.po2.sistemaDeAlquileres.Mail.MailSender;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public abstract class EstadoReservaAceptado extends EstadoReserva {
	@Override
	public EstadoReserva cancelar(Reserva reserva) throws CambioDeEstadoError, Exception {
		reserva.getInmueble().cancelarReserva(reserva);
		MailSender.mandarMailDeCancelacion(reserva);
		return new EstadoReservaCancelado();
	}
}

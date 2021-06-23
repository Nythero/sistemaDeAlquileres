package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import ar.edu.unq.po2.sistemaDeAlquileres.mail.MailSender;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;

public class EstadoReservaConcretado extends EstadoReserva {

	@Override
	public EstadoReserva cancelar(Reserva reserva) throws CambioDeEstadoError {
		MailSender.mandarMailDeCancelacion(reserva);
		return new EstadoReservaCancelado();
	}

	@Override
	public EstadoReserva finalizar(Reserva reserva) throws CambioDeEstadoError {
		return new EstadoReservaFinalizado();
	}

	@Override
	public String codigo() {
		return "Concretado";
	}

}

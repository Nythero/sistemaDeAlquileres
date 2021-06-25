package ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado;

import ar.edu.unq.po2.sistemaDeAlquileres.Mail.MailSender;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;


public class EstadoReservaConcretado extends EstadoReserva {

	@Override
	public EstadoReserva cancelar(Reserva reserva) throws CambioDeEstadoError, Exception {
		MailSender.mandarMailDeCancelacion(reserva);
		reserva.getInmueble().cancelarReserva(reserva);
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

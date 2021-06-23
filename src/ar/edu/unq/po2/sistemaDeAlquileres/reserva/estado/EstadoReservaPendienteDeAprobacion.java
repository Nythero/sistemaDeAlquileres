package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.mail.MailSender;

public class EstadoReservaPendienteDeAprobacion extends EstadoReserva {

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
	String codigo() {
		return "PendienteDeAprobacion";
	}
}

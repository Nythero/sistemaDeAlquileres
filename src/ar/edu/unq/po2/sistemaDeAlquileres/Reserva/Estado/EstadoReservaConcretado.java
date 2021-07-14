package ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;


public class EstadoReservaConcretado extends EstadoReservaAceptado {
	@Override
	public EstadoReserva finalizar(Reserva reserva) throws CambioDeEstadoError {
		return new EstadoReservaFinalizado();
	}

	public String codigo() {
		return "Concretado";
	}

}

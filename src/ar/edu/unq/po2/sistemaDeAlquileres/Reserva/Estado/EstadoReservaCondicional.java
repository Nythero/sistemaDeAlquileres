package ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public class EstadoReservaCondicional extends EstadoReservaAceptado {
	@Override
	public EstadoReserva concretar(Reserva reserva) throws CambioDeEstadoError {
		if(reserva.getInmueble().yaEstaReservado(reserva.getRangoDeFechas())) {
			super.concretar(reserva);
		}
		return new EstadoReservaConcretado();
	}
	
	String codigo() {
		return "Condicional";
	}

}

package ar.edu.unq.po2.sistemaDeAlquileres.politicaDeCancelacion;

import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;

public class SinCancelacion extends PoliticaDeCancelacion {

	@Override
	public void cancelarReserva(LocalDate diaActual,Reserva reserva) {
	}
}

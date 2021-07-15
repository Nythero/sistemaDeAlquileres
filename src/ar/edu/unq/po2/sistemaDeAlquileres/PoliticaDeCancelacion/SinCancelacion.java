package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;

import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
//
public class SinCancelacion extends PoliticaDeCancelacion {

	@Override
	public void cancelarReserva(LocalDate diaActual,Reserva reserva) {
	}
}

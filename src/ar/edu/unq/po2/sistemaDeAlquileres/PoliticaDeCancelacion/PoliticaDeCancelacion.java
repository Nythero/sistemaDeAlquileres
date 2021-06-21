package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;


import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public abstract class PoliticaDeCancelacion {
	/**
	 * Dada una reserva la cancela
	 * @param reserva
	 */
	abstract void cancelarReserva(Reserva reserva);
	
	/**
	 * Dada una reserva devuelve la cantidad de dias entre la fecha actual
	 * y la inicial de la reserva
	 * @param reserva
	 * @return
	 */
	public int darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(Reserva reserva) {
	LocalDate diaActual = LocalDate.now();
	int result= 0;
	while (!diaActual.equals(reserva.getFechaInicial())) {
		result+= 1;
		diaActual= diaActual.plusDays(1);
	}
	return result;
	}
}


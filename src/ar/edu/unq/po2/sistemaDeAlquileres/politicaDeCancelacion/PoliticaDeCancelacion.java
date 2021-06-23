package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;


import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import junit.framework.AssertionFailedError;

public abstract class PoliticaDeCancelacion {
	/**
	 * Dada una reserva la cancela
	 * @param reserva
	 */
	abstract void cancelarReserva(LocalDate diaActual, Reserva reserva);
	
	/**
	 * Dada una reserva devuelve la cantidad de dias entre la fecha actual
	 * y la inicial de la reserva
	 * @param reserva
	 * @return
	 */
	public int darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(LocalDate diaActual, Reserva reserva) {
		if(diaActual.isEqual(reserva.getFechaInicial()) || diaActual.isAfter(reserva.getFechaInicial())){
			throw new AssertionFailedError("Ya paso la etapa de cancelacion");
		}
		else {
			int result= 0;
			while (!diaActual.isEqual(reserva.getFechaInicial())) {
				result+= 1;
				diaActual= diaActual.plusDays(1);
			}
			System.out.print(result);
			return result;
		}
	}
}


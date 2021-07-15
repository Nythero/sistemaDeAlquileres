package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;


import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
//
public abstract class PoliticaDeCancelacion {
	/**
	 * Dado un diaActual y una reserva la cancela.
	 * En caso de no poder hacerse devuelve error
	 * @param diaActual
	 * @param reserva
	 * @throws Exception
	 */
	public abstract void cancelarReserva(LocalDate diaActual, Reserva reserva) throws Exception;
}


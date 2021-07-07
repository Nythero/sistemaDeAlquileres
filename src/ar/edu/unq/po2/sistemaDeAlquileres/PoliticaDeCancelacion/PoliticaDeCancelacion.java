package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;


import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
//
public abstract class PoliticaDeCancelacion {
	/**
	 * Dada una reserva la cancela
	 * @param reserva
	 * @throws Exception 
	 */
	public abstract void cancelarReserva(LocalDate diaActual, Reserva reserva) throws Exception;
	
	/**
	 * Dada una reserva devuelve la cantidad de dias entre la fecha actual
	 * y la inicial de la reserva
	 * @param reserva
	 * @return
	 */
}


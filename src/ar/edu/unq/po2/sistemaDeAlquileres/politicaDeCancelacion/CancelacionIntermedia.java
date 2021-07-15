package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;

import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public class CancelacionIntermedia extends PoliticaDeCancelacion {
//
	public CancelacionIntermedia(){
	}

	public void cancelarReserva(LocalDate diaActual, Reserva reserva) throws Exception {
		if (this.tieneFechaEntre19Y10(diaActual,reserva)){
			reserva.getSolicitante().recibirPago(this.montoEntreDias19Y10(reserva));
			reserva.getDuenho().extraerMonto(this.montoEntreDias19Y10(reserva));
		}
		else if (this.tieneFechaMenorA10Dias(diaActual,reserva)) {
			
		}
		else {
			reserva.getSolicitante().recibirPago((reserva.getMontoTotal()));
			reserva.getDuenho().extraerMonto(reserva.getMontoTotal());
		}
	}
	
	/**
	 * Dado un diaActual y una reserva devuelve si la fecha esta entre 19 y 10 dias con 
	 * respecto a la fechaInicial de la reserva 
	 * @param diaActual
	 * @param reserva
	 * @return
	 */
	private boolean tieneFechaEntre19Y10(LocalDate diaActual,Reserva reserva) {
		return (reserva.getRangoDeFechas().darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(diaActual,reserva)<=19) &&
				(reserva.getRangoDeFechas().darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(diaActual,reserva)>=10);
	}

	
	/**
	 * Dado un diaActual y una reserva devuelve si la fecha esta 9 dias con 
	 * respecto a la fechaInicial de la reserva 
	 * @param diaActual
	 * @param reserva
	 * @return
	 */
	private boolean tieneFechaMenorA10Dias(LocalDate diaActual,Reserva reserva) {
		return reserva.getRangoDeFechas().darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(diaActual,reserva)<=9;
	}
	
	/**
	 * Dada una reserva retorna el monto a pagar con fechas enter 19 y 10 dias
	 * @param reserva
	 * @return
	 */
	private float montoEntreDias19Y10(Reserva reserva) {
		return reserva.getMontoTotal()*50/100;
	}
}
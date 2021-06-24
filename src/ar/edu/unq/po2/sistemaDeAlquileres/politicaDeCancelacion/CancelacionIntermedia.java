package ar.edu.unq.po2.sistemaDeAlquileres.politicaDeCancelacion;

import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public class CancelacionIntermedia extends PoliticaDeCancelacion {
//
	public CancelacionIntermedia(){
	}

	public void cancelarReserva(LocalDate diaActual, Reserva reserva) {
		if (this.tieneFechaEntre19Y10(diaActual,reserva)){
			reserva.getInquilino().recibirPago(this.montoEntreDias19Y10(reserva));
			reserva.getDueño().extraerMonto(this.montoEntreDias19Y10(reserva));
		}
		else if (this.tieneFechaMenorA10Dias(diaActual,reserva)) {
			
		}
		else {
			reserva.getInquilino().recibirPago((reserva.getMontoTotal()));
			reserva.getDueño().extraerMonto(reserva.getMontoTotal());
		}
	}
	
	private boolean tieneFechaEntre19Y10(LocalDate diaActual,Reserva reserva) {
		return (reserva.getRangoDeFechas().darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(diaActual,reserva)<=19) &&
				(reserva.getRangoDeFechas().darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(diaActual,reserva)>=10);
	}

	
	private boolean tieneFechaMenorA10Dias(LocalDate diaActual,Reserva reserva) {
		return reserva.getRangoDeFechas().darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(diaActual,reserva)<=9;
	}
	
	
	private float montoEntreDias19Y10(Reserva reserva) {
		return reserva.getMontoTotal()*50/100;
	}
}
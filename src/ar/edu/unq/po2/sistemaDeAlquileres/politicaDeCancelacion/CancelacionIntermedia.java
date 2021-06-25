package ar.edu.unq.po2.sistemaDeAlquileres.politicaDeCancelacion;

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
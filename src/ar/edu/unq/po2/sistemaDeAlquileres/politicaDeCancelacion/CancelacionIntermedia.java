package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;

import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public class CancelacionIntermedia extends PoliticaDeCancelacion {
	
	public CancelacionIntermedia(){
	}

	@Override
	public void cancelarReserva(LocalDate diaActual,Reserva reserva) {
		if (tieneFechaEntre19Y10(diaActual,reserva)){
			reserva.devolverMontoAInquilinoSegunCancelacion(montoEntreDias19Y10(reserva));
			reserva.extraerMontoADueñoSegunCancelacion(montoEntreDias19Y10(reserva));
		}
		else if (tieneFechaMenorA10Dias(diaActual,reserva)) {
			
		}
		else {
			reserva.devolverMontoAInquilinoSegunCancelacion(reserva.getMontoTotal());
			reserva.extraerMontoADueñoSegunCancelacion(reserva.getMontoTotal());
		}
	}
	
	private boolean tieneFechaEntre19Y10(LocalDate diaActual, Reserva reserva) {
		return darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(diaActual,reserva)<=19 &&
				darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(diaActual,reserva)>=10;
	}

	
	private boolean tieneFechaMenorA10Dias(LocalDate diaActual,Reserva reserva) {
		return darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(diaActual,reserva)<=9;
	}
	
	private float montoEntreDias19Y10(Reserva reserva) {
		return reserva.getMontoTotal()*50/100;
	}
}

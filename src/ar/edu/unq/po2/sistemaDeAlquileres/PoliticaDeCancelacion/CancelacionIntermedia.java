package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public class CancelacionIntermedia extends PoliticaDeCancelacion {
	
	public CancelacionIntermedia(){
	}

	@Override
	public void cancelarReserva(Reserva reserva) {
		if (tieneFechaEntre19Y10(reserva)){
			reserva.devolverMontoAInquilinoSegunCancelacion(montoEntreDias19Y10(reserva));
			reserva.extraerMontoADueñoSegunCancelacion(montoEntreDias19Y10(reserva));
		}
		else if (tieneFechaMenorA10Dias(reserva)) {
			
		}
		else {
			reserva.devolverMontoAInquilinoSegunCancelacion(reserva.getMontoTotal());
			reserva.extraerMontoADueñoSegunCancelacion(reserva.getMontoTotal());
		}
	}
	
	private boolean tieneFechaEntre19Y10(Reserva reserva) {
		return darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(reserva)<=19 &&
				darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(reserva)>=10;
	}

	
	private boolean tieneFechaMenorA10Dias(Reserva reserva) {
		return darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(reserva)<=9;
	}
	
	private float montoEntreDias19Y10(Reserva reserva) {
		return reserva.getMontoTotal()*50/100;
	}
}

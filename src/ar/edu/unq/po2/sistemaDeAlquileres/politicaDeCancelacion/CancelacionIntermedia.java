package ar.edu.unq.po2.sistemaDeAlquileres.politicaDeCancelacion;

import java.time.LocalDate;
import java.util.Date;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public class CancelacionIntermedia extends PoliticaDeCancelacion {
	
	public CancelacionIntermedia(){
	}

	@Override
	void cancelarReserva(Reserva reserva) {
		LocalDate diaActual = LocalDate.now();
		if (tieneFechaEntre19Y10(reserva.getRangoDeFecha().getFechaInicial())){
			reserva.getSolicitante().devolverMonto(reserva.getRangoDeFecha()
				    .getMontoTotal()*50/100);
		}
		else if (tieneFechaMenorA10Dias(reserva.getRangoDeFecha().getFechaInicial())) {
			reserva.getSolicitante().devolverMonto(reserva.getRangoDeFecha()
				    .getMontoTotal());
		}
	}
	
	private boolean tieneFechaEntre19Y10(LocalDate fechaInicial) {
		LocalDate fechaActual= LocalDate.now();
		return fechaActual.until(fechaInicial).getDays()<=19 &&
				fechaActual.until(fechaInicial).getDays()>=10;
	}

	
	private boolean tieneFechaMenorA10Dias(LocalDate fechaInicial) {
		LocalDate fechaActual= LocalDate.now();
		return fechaActual.until(fechaInicial).getDays()<=9;
	}

}

package ar.edu.unq.po2.sistemaDeAlquileres.politicaDeCancelacion;

import java.time.LocalDate;
import java.time.Period;

import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado.RangoDeFechaConPrecioDeterminado;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public class CancelacionGratuita extends PoliticaDeCancelacion {
	
	@Override
	void cancelarReserva(Reserva reserva) {
		LocalDate diaActual = LocalDate.now();
		if(diaActual.until(reserva.getRangoDeFecha().getFechaInicial()).getDays()>10) {
			this.pagar2DiasDeReserva(reserva.getRangoDeFecha());
			reserva.getSolicitante().devolverMonto(montoADevolver(reserva));
		}
	}


	private float montoADevolver(Reserva reserva) {
		return reserva.getRangoDeFecha()
		.getMontoTotal()- this.pagar2DiasDeReserva(reserva.getRangoDeFecha());
	}

	
	private float pagar2DiasDeReserva(RangoDeFechaConPrecioDeterminado rangoDeFecha) {
		float result= 0;
		result+= rangoDeFecha.getPrecio().getPrecio(rangoDeFecha.getFechaFinal());
		result+= rangoDeFecha.getPrecio().getPrecio(rangoDeFecha.getFechaFinal().plusDays(1));
		return result;
	}

}

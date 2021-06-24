package ar.edu.unq.po2.sistemaDeAlquileres.politicaDeCancelacion;


import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public class CancelacionGratuita extends PoliticaDeCancelacion {
	
	@Override
	public void cancelarReserva(LocalDate diaActual, Reserva reserva) {
		if(reserva.getRangoDeFechas().darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(diaActual,reserva)>=10) {
			reserva.getInquilino().recibirPago(reserva.getMontoTotal());
			reserva.getDueño().extraerMonto(reserva.getMontoTotal());
		}
		else if(reserva.cantidadDeDias()==1) {
			reserva.getInquilino().extraerMonto(reserva.getRangoDeFechas().darPrecioSegunLaTemporada(reserva.getFechaInicial().plusDays(1)));
		}
		else {
			reserva.getInquilino().recibirPago(reserva.getMontoTotal() - reserva.getRangoDeFechas().obtenerMontoPorCantidadDeDias(2));
			reserva.getDueño().extraerMonto(reserva.getRangoDeFechas().obtenerMontoPorCantidadDeDias(2));
		}
	}
}

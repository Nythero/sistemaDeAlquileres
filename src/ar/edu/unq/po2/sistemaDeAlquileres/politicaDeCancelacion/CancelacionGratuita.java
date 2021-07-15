package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;


import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
//
public class CancelacionGratuita extends PoliticaDeCancelacion {
	
	@Override
	public void cancelarReserva(LocalDate diaActual, Reserva reserva) throws Exception {
		if(reserva.getRangoDeFechas().darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(diaActual,reserva)>=10) {
			reserva.getSolicitante().recibirPago(reserva.getMontoTotal());
			reserva.getDuenho().extraerMonto(reserva.getMontoTotal());
		}
		else if(reserva.getRangoDeFechas().cantidadDeDias()==1) {
			reserva.getSolicitante().extraerMonto(reserva.getInmueble().getPrecio().getPrecio((reserva.getFechaInicial().plusDays(1))));
		}
		else {
			reserva.getSolicitante().recibirPago(reserva.getMontoTotal() - 
					reserva.getRangoDeFechas().obtenerMontoPorCantidadDeDias(reserva.getInmueble().getPrecio(),2));
			reserva.getDuenho().extraerMonto(reserva.getRangoDeFechas().obtenerMontoPorCantidadDeDias(reserva.getInmueble().getPrecio(),2));
		}
	}
}

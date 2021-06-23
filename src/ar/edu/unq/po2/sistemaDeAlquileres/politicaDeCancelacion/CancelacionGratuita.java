package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;


import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public class CancelacionGratuita extends PoliticaDeCancelacion {
	
	@Override
	public void cancelarReserva(LocalDate diaActual, Reserva reserva) {
		if(this.darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(diaActual,reserva)>=10) {
			reserva.devolverMontoAInquilinoSegunCancelacion(reserva.getMontoTotal());
			reserva.extraerMontoADue�oSegunCancelacion(reserva.getMontoTotal());
		}
		else if(reserva.cantidadDeDias()==1) {
			reserva.exigirMontoFaltanteAInquilino();
		}
		else {
			reserva.devolverMontoAInquilinoSegunCancelacion(reserva.getMontoTotal() - reserva.obtenerMontoSegunDias(2));
			reserva.extraerMontoADue�oSegunCancelacion(reserva.obtenerMontoSegunDias(2));
		}
	}
}

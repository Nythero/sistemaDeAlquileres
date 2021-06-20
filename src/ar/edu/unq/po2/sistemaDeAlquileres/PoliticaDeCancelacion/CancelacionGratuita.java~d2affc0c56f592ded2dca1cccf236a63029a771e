package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;



import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public class CancelacionGratuita extends PoliticaDeCancelacion {
	
	@Override
	public void cancelarReserva(Reserva reserva) {
		if(this.darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(reserva)>=10) {
			reserva.devolverMontoAInquilinoSegunCancelacion(reserva.getMontoTotal());
			reserva.extraerMontoADueñoSegunCancelacion(reserva.getMontoTotal());
		}
		else if(reserva.cantidadDeDias()==1) {
			reserva.exigirMontoFaltanteAInquilino();
		}
		else {
			reserva.devolverMontoAInquilinoSegunCancelacion(reserva.getMontoTotal() - reserva.obtenerMontoDeDosDias());
			reserva.extraerMontoADueñoSegunCancelacion(reserva.obtenerMontoDeDosDias());
		}
	}
}

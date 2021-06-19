package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;


import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public abstract class PoliticaDeCancelacion {
	abstract void cancelarReserva(Reserva reserva);
	
	public int darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(Reserva reserva) {
		LocalDate diaActual = LocalDate.now();
		
		LocalDate fechaInicialAVerificar = LocalDate.of(diaActual.getYear(),
				diaActual.getMonthValue(),
				diaActual.getDayOfMonth());
		
		LocalDate fechaFinalAVerificar = LocalDate.of(reserva.getFechaInicial().getYear(),
				reserva.getFechaInicial().getMonthValue(),
				reserva.getFechaInicial().getDayOfMonth());
		
		int result= 0;
		while (fechaInicialAVerificar.getYear() != fechaFinalAVerificar.getYear() ||
				fechaInicialAVerificar.getMonthValue() != fechaFinalAVerificar.getMonthValue() ||
						fechaInicialAVerificar.getDayOfMonth() != fechaFinalAVerificar.getDayOfMonth()) {
			result+= 1;
			fechaInicialAVerificar= fechaInicialAVerificar.plusDays(1);
		}
		return result;
	}
}


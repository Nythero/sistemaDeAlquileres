package ar.edu.unq.po2.sistemaDeAlquileres.politicaDeCancelacion;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public abstract class PoliticaDeCancelacion {
	abstract void cancelarReserva(Reserva reserva);
	 
	int diasEntreDosFechas(Date diaActual, Date fechaInicialDeReserva) {
		long startTime = diaActual.getTime();
	     long endTime = fechaInicialDeReserva.getTime();
	     long diffTime = endTime - startTime;
	     return (int)TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
	}
}

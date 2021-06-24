package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha;

import java.time.LocalDate;
import java.util.ArrayList;

import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado.RangoDeFechaConPrecioDeterminado;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import junit.framework.AssertionFailedError;

public class RangoDeFechas {
	private LocalDate fechaInicial;
	private LocalDate fechaFinal;
	
	public RangoDeFechas(LocalDate fechaInicial, LocalDate fechaFinal){
		this.setFechaInicial(fechaInicial);
		this.setFechaFinal(fechaFinal);
	}

	public LocalDate getFechaInicial() {
		return this.fechaInicial;
	}

	public void setFechaInicial(LocalDate fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public LocalDate getFechaFinal() {
		return this.fechaFinal;
	}

	public void setFechaFinal(LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	public boolean intersectanLosRangos(RangoDeFechas rango) {
        return   this.estaElDiaEntre(rango.getFechaInicial()) || 
                 this.estaElDiaEntre(rango.getFechaFinal()) ||
                 rango.estaElDiaEntre(fechaInicial) || 
                 rango.estaElDiaEntre(fechaFinal);
    }
    
    private boolean estaElDiaEntre(LocalDate dia) {
        return  (this.getFechaInicial().isEqual(dia) || 
        		this.getFechaInicial().isBefore(dia)) && 
                (this.getFechaFinal().isAfter(dia) ||
                this.getFechaInicial().isEqual(dia));
    }

	public int cantidadDeDias() {
		LocalDate diaAVerificar = this.getFechaInicial();
		int result= 0;
		while (!diaAVerificar.isEqual(this.getFechaFinal())) {
			result+= 1;
			diaAVerificar= diaAVerificar.plusDays(1);
		}
		return result;
	}
	
	public int darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(LocalDate diaActual, Reserva reserva) {
		LocalDate fecha= diaActual;
		if(fecha.isEqual(reserva.getFechaInicial()) || diaActual.isAfter(reserva.getFechaInicial())){
			throw new AssertionFailedError("Ya paso la etapa de cancelacion");
		}
		else {
			int result= 0;
			while (!fecha.isEqual(reserva.getFechaInicial())) {
				result+= 1;
				fecha= diaActual.plusDays(1);
			}
			return result;
		}
	}
	
	public boolean lasFechasEstanEnElRango(LocalDate fechaEntrada, LocalDate fechaSalida) {
        return ((this.getFechaInicial().isEqual(fechaEntrada)|| this.getFechaInicial().isBefore(fechaEntrada)) &&
                (this.getFechaFinal().isEqual(fechaSalida) ||this.getFechaFinal().isAfter(fechaSalida)));
    }
	
	public boolean contains(ArrayList<RangoDeFechaConPrecioDeterminado> rangos) {
		boolean result= false;
		int i=0;
		while(!rangos.isEmpty() && i < rangos.size()) {
			result|= this.lasFechasEstanEnElRango(rangos.get(i).getFechaInicial(), rangos.get(i).getFechaFinal());
			i++;
		}
		return result;
	}
	

}
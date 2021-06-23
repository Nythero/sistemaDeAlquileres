package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha;

import java.time.LocalDate;

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
		while (!diaAVerificar.equals(this.getFechaFinal())) {
			result+= 1;
			diaAVerificar= diaAVerificar.plusDays(1);
		}
		return result;
	}

}
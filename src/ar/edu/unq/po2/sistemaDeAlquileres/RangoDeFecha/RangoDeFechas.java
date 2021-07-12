package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha;

import java.time.LocalDate;
import java.util.ArrayList;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;
import junit.framework.AssertionFailedError;

public class RangoDeFechas {
	private LocalDate fechaInicial;
	private LocalDate fechaFinal;
	
	public RangoDeFechas(LocalDate fechaInicial, LocalDate fechaFinal){
		this.setFechaInicial(fechaInicial);
		this.setFechaFinal(fechaFinal);
	}

	/**
	 * Retorna la fecha Inicial
	 * @return
	 */
	public LocalDate getFechaInicial() {
		return this.fechaInicial;
	}
	/**
	 * Dada una fecha inicial la setea
	 * @param fechaInicial
	 */
	public void setFechaInicial(LocalDate fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	/**
	 * Retorna la fecha final
	 * @return
	 */
	public LocalDate getFechaFinal() {
		return this.fechaFinal;
	}

	/**
	 * Dada una fechaFinal la setea
	 * @param fechaFinal
	 */
	public void setFechaFinal(LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	/**
	 * Dado un rango retora si intersectan
	 * @param rango
	 * @return
	 */
	public boolean intersectanLosRangos(RangoDeFechas rango) {
        return   this.estaElDiaEntre(rango.getFechaInicial()) || 
                 this.estaElDiaEntre(rango.getFechaFinal()) ||
                 rango.estaElDiaEntre(fechaInicial) || 
                 rango.estaElDiaEntre(fechaFinal);
    }
    
	/**
	 * Dado un dia retorna si el mismo esta entre la fechaInicial y la fechaFinal
	 * @param dia
	 * @return
	 */
    private boolean estaElDiaEntre(LocalDate dia) {
        return  (this.getFechaInicial().isEqual(dia) || 
        		this.getFechaInicial().isBefore(dia)) && 
                (this.getFechaFinal().isAfter(dia) ||
                this.getFechaInicial().isEqual(dia));
    }

    /**
     * Retorna la cantidad de dias del rango
     * @return
     */
	public int cantidadDeDias() {
		LocalDate diaAVerificar = this.getFechaInicial();
		int result= 0;
		while (!diaAVerificar.isEqual(this.getFechaFinal())) {
			result+= 1;
			diaAVerificar= diaAVerificar.plusDays(1);
		}
		return result;
	}
	
	/**
	 * Dado un diaActual y una reserva devuelve los dias restantes hasta la fechaInicial de la reserva
	 * @param diaActual
	 * @param reserva
	 * @return
	 */
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
	
	/**
	 * Dado una fechaEntrada y una fechaSalida retorna si se encuentran en el rango
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return
	 */
	public boolean lasFechasEstanEnElRango(LocalDate fechaEntrada, LocalDate fechaSalida) {
        return ((this.getFechaInicial().isEqual(fechaEntrada)|| this.getFechaInicial().isBefore(fechaEntrada)) &&
                (this.getFechaFinal().isEqual(fechaSalida) ||this.getFechaFinal().isAfter(fechaSalida)));
    }
	
	/**
	 * Dado una temporada devuelve el monto total
	 * @param temporada
	 * @return
	 */
	public float getMontoTotal(Temporada temporada) {
		LocalDate fechaInicialAVerificar = this.getFechaInicial();
        float result= 0;
        while (!fechaInicialAVerificar.isEqual(this.getFechaFinal())) {
            result+= temporada.getPrecio(fechaInicialAVerificar);
            fechaInicialAVerificar= fechaInicialAVerificar.plusDays(1); 
        }
        return result + temporada.getPrecio(fechaInicialAVerificar);
	}
	   
   /**
    * Dada una temporada, una fechaEntrada y una fechaSalida devuelve el precio maximo entre estas fechas
    * @param temporada
    * @param fechaEntrada
    * @param fechaSalida
    * @return
    */
    public float precioMaximoEntreElRangoDeFechas(Temporada temporada, LocalDate fechaEntrada, LocalDate fechaSalida) {
        float precio = 0;
        for (LocalDate fechaActual = fechaEntrada; fechaActual.isBefore(fechaSalida) || fechaActual.isEqual(fechaSalida); fechaActual = fechaActual.plusDays(1)){
        	precio = (temporada.getPrecio(fechaActual) > precio)
                    ? temporada.getPrecio(fechaActual)
                    : precio;
              }
    return precio;
    }

    /**
     * Dada una temporada y una cantidadDeDias devuelve su monto.
     * @param temporada
     * @param cantidadDeDias
     * @return
     */
	public float obtenerMontoPorCantidadDeDias(Temporada temporada,int cantidadDeDias) {
		float result= 0;
		int diaActual= 0;
		LocalDate diaInicial = this.getFechaInicial();
		while(diaActual != cantidadDeDias){
			result+= temporada.getPrecio(diaInicial);
			diaActual++;
			diaInicial= diaInicial.plusDays(1);
		}
		return result;
	}
	
	/**
	 * Dado un dia retorna si la fecha esta incluida
	 * @param dia
	 * @return
	 */
	public boolean estaIncluidaLaFecha(LocalDate dia) {
        return this.getFechaInicial().isBefore(dia) && this.getFechaFinal().isAfter(dia) ||
               this.getFechaInicial().isEqual(dia) || this.getFechaFinal().isEqual(dia); 
    }
	
	/**
	 * Dado un rango retorna si esta incluida
	 * @param rango
	 * @return
	 */
	public boolean estaIncluidoElRango(RangoDeFechas rango) {
	        return this.estaIncluidaLaFecha(rango.getFechaInicial()) && this.estaIncluidaLaFecha(rango.getFechaFinal());
	}
	

}

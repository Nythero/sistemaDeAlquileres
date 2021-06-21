package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado;

import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;

public class RangoDeFechaConPrecioDeterminado {
	private LocalDate fechaInicial;
	private LocalDate fechaFinal;
	private Temporada precio;
	
	public RangoDeFechaConPrecioDeterminado(LocalDate fechaInicial, LocalDate fechaFinal,Temporada precio){
		this.fechaInicial= fechaInicial;
		this.fechaFinal= fechaFinal;
		this.precio= precio;
	}
	
	/**
	 * Devuelve la fecha Inicial
	 * @return
	 */
	public LocalDate getFechaInicial() {
		return this.fechaInicial;
	}
	
	/**
	 * Devuelve la fecha Final
	 * @return
	 */
	public LocalDate getFechaFinal() {
		return this.fechaFinal;
	}

	/**
	 * Devuelve la Temporada a usar
	 * @return
	 */
	public Temporada getPrecioTemporada() {
		return this.precio;
	}
	
	/**
	 * Devuelve el monto total de la reserva
	 * @return
	 */
	public float getMontoTotal() {
		LocalDate fechaInicialAVerificar = this.fechaInicial;
        float result= 0;
        while (!fechaInicialAVerificar.equals(this.fechaFinal)) {
            result+= this.getPrecioTemporada().getPrecio(fechaInicialAVerificar);
            fechaInicialAVerificar= fechaInicialAVerificar.plusDays(1); 
        }
        return result + this.getPrecioTemporada().getPrecio(fechaInicialAVerificar);
	}

	/**
	 * Dada una fecha devuelve el precio
	 * @param fecha
	 * @return
	 */
	public float darPrecioSegunLaTemporada(LocalDate fecha) {
		return this.getPrecioTemporada().getPrecio(fecha);
	}
	
	
	/**
	 * Dada una fechaEntrada y una FechaSalida devuelve si se encuentra dentro de los 
	 * rangos de la reserva
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return
	 */
    public boolean lasFechasEstanEnElRango(LocalDate fechaEntrada, LocalDate fechaSalida) {
        return ((this.fechaInicial.equals(fechaEntrada)|| this.fechaInicial.isBefore(fechaEntrada)) &&
        		(this.fechaFinal.equals(fechaSalida) ||this.fechaFinal.isAfter(fechaSalida)));
    }
    
    
    /**
     * Dada una fechaEntrada y una FechaSalida devuelve el precio maximo entre
     * el rango de fechas
     * @param fechaEntrada
     * @param fechaSalida
     * @return
     */
    public float precioMaximoEntreElRangoDeFechas(LocalDate fechaEntrada, LocalDate fechaSalida) {
        float precio = 0;
        for (LocalDate fechaActual = fechaEntrada; fechaActual.isBefore(fechaSalida) || fechaActual.equals(fechaEntrada); fechaActual = fechaActual.plusDays(1)){
            precio = (this.getPrecioTemporada().getPrecio(fechaActual) > precio)
                    ? this.getPrecioTemporada().getPrecio(fechaActual)
                    : precio;
              }
    return precio;
    }

}

package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado;

import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;

public class RangoDeFechaConPrecioDeterminado extends RangoDeFechas {
	private Temporada precio;
	
	public RangoDeFechaConPrecioDeterminado(LocalDate fechaInicial, LocalDate fechaFinal,Temporada precio){
		super(fechaInicial,fechaFinal);
		this.precio= precio;
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
		LocalDate fechaInicialAVerificar = this.getFechaInicial();
        float result= 0;
        while (!fechaInicialAVerificar.isEqual(this.getFechaFinal())) {
            result+= this.darPrecioSegunLaTemporada(fechaInicialAVerificar);
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
     * Dada una fechaEntrada y una FechaSalida devuelve el precio maximo entre
     * el rango de fechas
     * @param fechaEntrada
     * @param fechaSalida
     * @return
     */
    public float precioMaximoEntreElRangoDeFechas(LocalDate fechaEntrada, LocalDate fechaSalida) {
        float precio = 0;
        for (LocalDate fechaActual = fechaEntrada; fechaActual.isBefore(fechaSalida) || fechaActual.isEqual(fechaSalida); fechaActual = fechaActual.plusDays(1)){
        	precio = (this.getPrecioTemporada().getPrecio(fechaActual) > precio)
                    ? this.getPrecioTemporada().getPrecio(fechaActual)
                    : precio;
              }
    return precio;
    }

	public float obtenerMontoPorCantidadDeDias(int cantidadDeDias) {
		float result= 0;
		int diaActual= 0;
		LocalDate diaInicial = this.getFechaInicial();
		while(diaActual != cantidadDeDias){
			result+= this.darPrecioSegunLaTemporada(diaInicial);
			diaActual++;
			diaInicial= diaInicial.plusDays(1);
		}
		return result;
	}
}

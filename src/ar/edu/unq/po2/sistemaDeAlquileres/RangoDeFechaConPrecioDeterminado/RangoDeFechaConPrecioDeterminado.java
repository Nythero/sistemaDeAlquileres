package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import ar.edu.unq.po2.sistemaDeAlquileres.temporada.Temporada;

public class RangoDeFechaConPrecioDeterminado {
	private LocalDate fechaInicial;
	private LocalDate fechaFinal;
	private Temporada precio;
	private ArrayList<Integer> colaDeInquilinos; 
	
	RangoDeFechaConPrecioDeterminado(LocalDate fechaInicial, LocalDate fechaFinal,Temporada precio){
		this.fechaInicial= fechaInicial;
		this.fechaFinal= fechaFinal;
		this.precio= precio;
		this.colaDeInquilinos= new ArrayList<>();
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

	public Temporada getPrecio() {
		return this.precio;
	}

	public void setPrecio(Temporada precio) {
		this.precio = precio;
	}

	public ArrayList<Integer> getColaDeInquilinos() {
		return this.colaDeInquilinos;
	}

	public void setColaDeInquilinos(ArrayList<Integer> colaDeInquilinos) {
		this.colaDeInquilinos = colaDeInquilinos;
	}
	
	
	public Temporada getPrecioTemporada() {
		return (this.precio);
	}	

	public float getMontoTotal() {
        LocalDate fechaInicialAVerificar = this.fechaInicial;
        float result= 0;
        while (!fechaInicialAVerificar.equals(this.fechaFinal)) {
            result+= this.getPrecioTemporada().getPrecio(fechaInicialAVerificar);
            fechaInicialAVerificar= fechaInicialAVerificar.plusDays(1);
        }
        return result + this.getPrecioTemporada().getPrecio(fechaInicialAVerificar);
    }

    public float darPrecioSegunLaTemporada(LocalDate fecha) {
        return this.getPrecioTemporada().getPrecio(fecha);
    }
    
    
      /*
     * Indica si las fechas dadas estan dentro del rango
     * */
    public boolean lasFechasEstanEnElRango(LocalDate fechaEntrada, LocalDate fechaSalida) {
        return ((this.getFechaInicial().equals(fechaEntrada)|| this.getFechaInicial().isBefore(fechaEntrada)) &&
                (this.getFechaFinal().equals(fechaSalida) ||this.getFechaFinal().isAfter(fechaSalida)));
    }

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


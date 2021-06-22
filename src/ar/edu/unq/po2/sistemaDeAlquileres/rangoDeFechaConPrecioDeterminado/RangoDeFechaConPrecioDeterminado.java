package ar.edu.unq.po2.sistemaDeAlquileres.rangoDeFechaConPrecioDeterminado;

import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;
import ar.edu.unq.po2.sistemaDeAlquileres.rangoDeFecha.RangoDeFechas;

public class RangoDeFechaConPrecioDeterminado extends RangoDeFechas{
	private Temporada precio;
	
	public RangoDeFechaConPrecioDeterminado(LocalDate fechaInicial, LocalDate fechaFinal,Temporada precio){
		super(fechaInicial, fechaFinal);
		this.precio = precio;
	}

	public Temporada getPrecioTemporada() {
		return this.precio;
	}

	public void setPrecio(Temporada precio) {
		this.precio = precio;
	}
	
	public float getMontoTotal() {
		LocalDate fechaInicialAVerificar = LocalDate.of(fechaInicial.getYear(),
				fechaInicial.getMonthValue(),
				fechaInicial.getDayOfMonth());
		
		LocalDate fechaFinalAVerificar = LocalDate.of(fechaFinal.getYear(),
				fechaFinal.getMonthValue(),
				fechaFinal.getDayOfMonth());
		
		float result= 0;
		while (fechaInicialAVerificar.getYear() != fechaFinalAVerificar.getYear() ||
				fechaInicialAVerificar.getMonthValue() != fechaFinalAVerificar.getMonthValue() ||
						fechaInicialAVerificar.getDayOfMonth() != fechaFinalAVerificar.getDayOfMonth()) {
			result+= this.getPrecioTemporada().getPrecio(fechaInicialAVerificar);
			fechaInicialAVerificar= fechaInicialAVerificar.plusDays(1);
		}
		return result + this.getPrecioTemporada().getPrecio(fechaInicial);
	}

}

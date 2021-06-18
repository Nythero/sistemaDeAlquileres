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

package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado;

import java.time.LocalDate;
import java.util.ArrayList;

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

	public float getMontoTotal() {
		fechaInicial= this.getFechaFinal();
		float result= 0;
		while (fechaInicial != this.getFechaFinal()) {
			result+= this.getPrecio().getPrecio(fechaInicial);
			fechaFinal= fechaFinal.plusDays(1);
		}
		return result + this.getPrecio().getPrecio(fechaInicial);
	}
	
	
}

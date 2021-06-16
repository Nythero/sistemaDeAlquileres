package ar.edu.unq.po2.sistemaDeAlquileres.Reserva;

import java.util.ArrayList;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Dia.Dia;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

public class Reserva {
	
	private ArrayList<Dia> dias;
	private Inmueble inmueble;
	private EstadoReserva estado;
	private Usuario solicitante;

	public Reserva(ArrayList<Dia> dias, Inmueble inmueble, Usuario solicitante, EstadoReserva estado) {
		this.setDias(dias);
		this.setSolicitante(solicitante);
		this.setInmueble(inmueble);
		this.setEstado(estado);
	}
	
	private void setDias(ArrayList<Dia> dias) {
		this.dias = dias;
	}

	private EstadoReserva getEstado() {
		return estado;
	}
	
	private void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}
	
	private Inmueble getInmueble() {
		return this.inmueble;
	}
	
	private void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}
	
	private Usuario getSolicitante() {
		return this.solicitante;
	}
	
	private void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}
	
	private ArrayList<Dia> getDias(){
		return this.dias;
	}
	
	public Dia getDia(int i) {
		return this.getDias().get(i);
	}
	
	public int getCantidadDias() {
		return this.getDias().size();
	}

	public void cancelar() {
		this.setEstado(this.getEstado().cancelar(this));
	}
	
	public void aceptar() {
		this.setEstado(this.getEstado().aceptar(this));
	}
	
	public void finalizar() {
		this.setEstado(this.getEstado().finalizar(this));
	}
}

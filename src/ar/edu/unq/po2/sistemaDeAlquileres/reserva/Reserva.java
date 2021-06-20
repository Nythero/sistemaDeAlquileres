package ar.edu.unq.po2.sistemaDeAlquileres.reserva;

import java.util.ArrayList;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

public class Reserva {
	
	private Inmueble inmueble;
	private EstadoReserva estado;
	private Usuario solicitante;

	public Reserva(Inmueble inmueble, Usuario solicitante, EstadoReserva estado) {
		this.setSolicitante(solicitante);
		this.setInmueble(inmueble);
		this.setEstado(estado);
	}

	private EstadoReserva getEstado() {
		return estado;
	}
	
	private void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}
	
	public Inmueble getInmueble() {
		return this.inmueble;
	}
	
	private void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}
	
	public Usuario getSolicitante() {
		return this.solicitante;
	}
	
	private void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
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

package ar.edu.unq.po2.sistemaDeAlquileres.reserva;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.rangoDeFecha.RangoDeFecha;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado.CambioDeEstadoError;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado.EstadoEquivocadoError;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado.EstadoReserva;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

public class Reserva {
	
	private RangoDeFecha dias;
	private Inmueble inmueble;
	private EstadoReserva estado;
	private Usuario solicitante;

	public Reserva(RangoDeFecha dias, Inmueble inmueble, Usuario solicitante, EstadoReserva estado) {
		this.setDias(dias);
		this.setSolicitante(solicitante);
		this.setInmueble(inmueble);
		this.setEstado(estado);
	}
	
	private void setDias(RangoDeFecha dias) {
		this.dias = dias;
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
	
	private RangoDeFecha getDias(){
		return this.dias;
	}

	public void cancelar() {
		try {
			this.setEstado(this.getEstado().cancelar(this));
		}
		catch (CambioDeEstadoError error) {
			
		}
	}
	
	public void aceptar() {
		try {
			this.setEstado(this.getEstado().aceptar(this));
		}
		catch (CambioDeEstadoError error) {}
	}
	
	public void finalizar() {
		try {
			this.setEstado(this.getEstado().finalizar(this));
		}
		catch (CambioDeEstadoError error) {}
	}
	
	public void setComentario(String comentario) throws EstadoEquivocadoError {
		this.estado.setComentario(this, comentario);
    }
    
    public void setPuntajeADuenho (String categoria, Integer puntaje) throws EstadoEquivocadoError {
		this.estado.setPuntajeADuenho(this, categoria, puntaje);
    }
    
    public void setPuntajeAInquilino (String categoria, Integer puntaje) throws EstadoEquivocadoError {
    	this.estado.setPuntajeAInquilino(this, categoria, puntaje);
    }
}

package ar.edu.unq.po2.sistemaDeAlquileres.reserva;

import ar.edu.unq.po2.sistemaDeAlquileres.inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.rangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado.CambioDeEstadoError;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado.EstadoEquivocadoError;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado.EstadoReserva;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

public class Reserva {
	
	private RangoDeFechas rangoDeFechas;
	private Inmueble inmueble;
	private EstadoReserva estado;
	private Usuario solicitante;
	private String formaDePago;
	private Float total;

	public Reserva(Inmueble inmueble, RangoDeFechas rangoDeFechas, Usuario solicitante, String formaDePago, EstadoReserva estado) {
		this.setRangoDeFechas(rangoDeFechas);
		this.setSolicitante(solicitante);
		this.setInmueble(inmueble);
		this.setEstado(estado);
		this.setFormaDePago(formaDePago);
	}

	public RangoDeFechas getRangoDeFechas() {
		return this.rangoDeFechas;
	}
	
	private void setRangoDeFechas(RangoDeFechas rangoDeFechas) {
		this.rangoDeFechas = rangoDeFechas;
	}

	public EstadoReserva getEstado() {
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

	public String getFormaDePago() {
		return this.formaDePago;
	}

	private void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
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
	
	public void comentarInmueble(String comentario) throws EstadoEquivocadoError {
		this.getEstado().comentarInmueble(this, comentario);
    }
    
    public void puntuarDuenho(String categoria, Integer puntaje) throws EstadoEquivocadoError {
		this.getEstado().puntuarDuenho(this, categoria, puntaje);
    }
    
    public void puntuarInquilino(String categoria, Integer puntaje) throws EstadoEquivocadoError {
    	this.getEstado().puntuarInquilino(this, categoria, puntaje);
    }

    public void  puntuarInmueble(String categoria, Integer puntaje) throws EstadoEquivocadoError {
    	this.getEstado().puntuarInmueble(this, categoria, puntaje);
    }
    
	public boolean estaPendienteDeAprobacion() {
		return this.getEstado().estaPendienteDeAprobacion();
	}

	public boolean estaConcretada() {
		return this.getEstado().estaConcretada();
	}
}

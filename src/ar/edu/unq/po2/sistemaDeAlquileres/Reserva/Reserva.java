package ar.edu.unq.po2.sistemaDeAlquileres.Reserva;

import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado.RangoDeFechaConPrecioDeterminado;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado.CambioDeEstadoError;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado.EstadoEquivocadoError;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;
import ar.edu.unq.po2.sistemaDeAlquileres.inmueble.Inmueble;

public class Reserva {
	
	private RangoDeFechaConPrecioDeterminado rangoDeFechas;
	private Inmueble inmueble;
	private EstadoReserva estado;
	private Usuario solicitante;
	private String formaDePago;
	private float total;

	public Reserva(Inmueble inmueble, RangoDeFechaConPrecioDeterminado rangoDeFechas, Usuario solicitante, String formaDePago, EstadoReserva estado) {
		this.setRangoDeFechas(rangoDeFechas);
		this.setSolicitante(solicitante);
		this.setInmueble(inmueble);
		this.setEstado(estado);
		this.setFormaDePago(formaDePago);
	}

	public RangoDeFechaConPrecioDeterminado getRangoDeFechas() {
		return this.rangoDeFechas;
	}
	
	private void setRangoDeFechas(RangoDeFechaConPrecioDeterminado rangoDeFechas) {
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
	
	public void setComentario(String comentario) throws EstadoEquivocadoError {
		this.getEstado().setComentario(this, comentario);
    }
    
    public void setPuntajeADuenho (String categoria, Integer puntaje) throws EstadoEquivocadoError {
		this.getEstado().setPuntajeADuenho(this, categoria, puntaje);
    }
    
    public void setPuntajeAInquilino (String categoria, Integer puntaje) throws EstadoEquivocadoError {
    	this.getEstado().setPuntajeAInquilino(this, categoria, puntaje);
    }

	public boolean estaPendienteDeAprobacion() {
		return this.getEstado().estaPendienteDeAprobacion();
	}

	public boolean estaConcretada() {
		return this.getEstado().estaConcretada();
	}

	public float getMontoTotal() {
		return this.total;
	}

	public LocalDate getFechaInicial() {
		return this.getRangoDeFechas().getFechaInicial();
	}
	
	public LocalDate getFechaFinal() {
		return this.getRangoDeFechas().getFechaFinal();
	}

	public void devolverMontoAInquilinoSegunCancelacion(float monto){
		this.getSolicitante().recibirPago(monto);
	}

	public void extraerMontoADueñoSegunCancelacion(float monto){
		this.getInmueble().getDueño().extraerMonto(monto);
	}

	public int cantidadDeDias() {
		return this.getRangoDeFechas().cantidadDeDias();
	}

	public void exigirMontoFaltanteAInquilino() {
		LocalDate diaInicial = this.getFechaInicial().plusDays(1);
		float monto= this.getRangoDeFechas().darPrecioSegunLaTemporada(diaInicial);
			this.getInmueble().getDueño().recibirPago(monto);
		}

	public float obtenerMontoSegunDias(int cantidadDeDias) {
		return this.getRangoDeFechas().obtenerMontoPorCantidadDeDias(cantidadDeDias);
	}
	
}

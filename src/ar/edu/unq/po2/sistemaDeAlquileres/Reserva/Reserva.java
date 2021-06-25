package ar.edu.unq.po2.sistemaDeAlquileres.Reserva;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado.CambioDeEstadoError;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado.EstadoEquivocadoError;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado.EstadoReserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

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
    
    public void cancelar() throws CambioDeEstadoError, Exception {
        this.setEstado(this.getEstado().cancelar(this));
    }
    
    public void aceptar() throws CambioDeEstadoError {
        this.setEstado(this.getEstado().aceptar(this));
    }
    
    public void finalizar() throws CambioDeEstadoError {
        this.setEstado(this.getEstado().finalizar(this));
    }
    
    public void comentarInmueble(String comentario) throws EstadoEquivocadoError {
        this.getEstado().comentarInmueble(this, comentario);
    }
    
    public void puntuarDuenho(String categoria, Integer puntaje) throws EstadoEquivocadoError, Exception {
        this.getEstado().puntuarDuenho(this, categoria, puntaje);
    }
    
    public void puntuarInquilino(String categoria, Integer puntaje) throws EstadoEquivocadoError, Exception {
        this.getEstado().puntuarInquilino(this, categoria, puntaje);
    }

    public void puntuarInmueble(String categoria, Integer puntaje) throws EstadoEquivocadoError, Exception {
        this.getEstado().puntuarInmueble(this, categoria, puntaje);
    }
    
    public boolean estaEnEstado(String codigoEstado) {
        return this.getEstado().esEstado(codigoEstado);
    }

	public LocalDate getFechaInicial() {
		return this.getRangoDeFechas().getFechaInicial();
	}
	
	public LocalDate getFechaFinal() {
		return this.getRangoDeFechas().getFechaFinal();
	}

	public Usuario getDuenho() {
		return this.getInmueble().getDuenho();
	}

	public float getMontoTotal() {
		return this.total;
	}

}
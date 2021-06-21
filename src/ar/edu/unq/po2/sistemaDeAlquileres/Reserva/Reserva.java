package ar.edu.unq.po2.sistemaDeAlquileres.Reserva;

import java.time.LocalDate;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado.RangoDeFechaConPrecioDeterminado;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

public class Reserva {
    
    private RangoDeFechaConPrecioDeterminado rango;
    private Inmueble inmueble;
    private EstadoReserva estado;
    private Usuario solicitante;

    public Reserva(RangoDeFechaConPrecioDeterminado rango, Inmueble inmueble, Usuario solicitante, EstadoReserva estado) {
        this.rango(rango);
        this.setSolicitante(solicitante);
        this.setInmueble(inmueble);
        this.setEstado(estado);
    }
    
    public LocalDate getFechaInicial() {
		return this.rango.getFechaInicial();
	}
	
	public LocalDate getFechaFinal() {
		return this.rango.getFechaFinal();
	}	
	
    public void devolverMontoAInquilinoSegunCancelacion(float monto){
		this.getSolicitante().recibirPago(monto);
	}


	public void extraerMontoADueñoSegunCancelacion(float monto){
		this.getInmueble().getDueño().extraerMonto(monto);
	}

	public float getMontoTotal() {
			return this.getRango().getMontoTotal();
		}

	public void exigirMontoFaltanteAInquilino() {
		LocalDate diaInicial = this.getFechaInicial().plusDays(1);
		float monto = this.getRango().getPrecioTemporada().getPrecio(diaInicial);
			this.getInmueble().getDueño().recibirPago(monto);
		}

	public int cantidadDeDias() {
		LocalDate diaAVerificar = this.getFechaInicial();
		int result= 0;
		while (!diaAVerificar.equals(this.getFechaFinal())) {
			result+= 1;
			diaAVerificar= diaAVerificar.plusDays(1);
		}
		return result;
	}
	
	public float obtenerMontoSegunDias(int cantidadDeDias) {
		float result= 0;
		int diaActual= 0;
		LocalDate diaInicial = this.rango.getFechaInicial();
		while(diaActual != cantidadDeDias){
			result+= this.rango.darPrecioSegunLaTemporada(diaInicial);
			diaActual++;
			diaInicial= diaInicial.plusDays(1);
		}
		return result;
	}
    
    private void rango(RangoDeFechaConPrecioDeterminado rango) {
        this.rango = rango;
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
    

    public RangoDeFechaConPrecioDeterminado getRango() {
        return this.rango;
    }
    
//    public int getCantidadDias() {
//        return this.getRango().get;
//    }

    public void cancelar() {
        this.setEstado(this.getEstado().cancelar(this));
    }
    
    public void aceptar() {
        this.setEstado(this.getEstado().aceptar(this));
    }
    
    public void finalizar() {
        this.setEstado(this.getEstado().finalizar(this));
    }

    

//    public void setPuntajeInquilino(String categoria, Integer puntaje) {
//        // TODO Auto-generated method stub
//        
//    }
//
//    public boolean SeRellenoElFormulario() {
//        // TODO Auto-generated method stub
//        
//    }

//    public boolean lasFechaEstanEnElRango(LocalDate fechaEntrada, LocalDate fechaSalida) {
//        return (this.getRango().lasFechasEstanEnElRango(fechaEntrada,fechaSalida));
//    }

    
//    public boolean elPrecioEstaEnElRangoDe(float precioMinimo, float precioMaximo) {
//        return (this.getRango().elPrecioEstaEntreElRangoDe(precioMinimo,precioMaximo));
//    }
    
    public void setComentario(String comentario) {
        
    }

    public void setPuntajeCategoriaAInmueble(String servicio, Integer puntaje) {
        // TODO Auto-generated method stub
        
    }

    public void setPuntajeAInquilino(String categoria, Integer puntaje) {
        // TODO Auto-generated method stub
        
    }

    public void setPuntajeADuenho(String categoria, Integer puntaje) {
        // TODO Auto-generated method stub
        
    }
    
    public boolean laFechaInicialDelRangoEstaDespuesDelDiaActual() {
        return (this.getRango().getFechaInicial().isAfter(LocalDate.now()));
    }
     
    public String getCiudad(){
        return (this.getInmueble().getCiudad());
    }

    public boolean laFechaActualEstaDentroDelRango() {
        return (this.getRango().lasFechasEstanEnElRango(LocalDate.now(), LocalDate.now()));
    }
}
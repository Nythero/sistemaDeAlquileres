package ar.edu.unq.po2.sistemaDeAlquileres.reserva;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.sistemaDeAlquileres.inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.rangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado.CambioDeEstadoError;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado.EstadoEquivocadoError;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado.EstadoReserva;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

class ReservaTestCase {

	Reserva reserva;
	Inmueble inmueble;
	RangoDeFechas rangoDeFechas;
	EstadoReserva estado;
	Usuario solicitante;
	String formaDePago;
	
	@BeforeEach
	void setup(){
		inmueble = mock(Inmueble.class);
		rangoDeFechas = mock(RangoDeFechas.class);
		estado = mock(EstadoReserva.class);
		solicitante = mock(Usuario.class);
		reserva = new Reserva(inmueble, rangoDeFechas, solicitante, formaDePago, estado);
	}
	
	@Test
	void Reserva_Aceptar_Success() throws CambioDeEstadoError {
		reserva.aceptar();
		
		verify(estado).aceptar(reserva);
	}
	
	@Test
	void Reserva_Cancelar_Success() throws Exception {
		reserva.cancelar();
		
		verify(estado).cancelar(reserva);
	}
	
	@Test
	void Reserva_Finalizar_Success() throws CambioDeEstadoError {
		inmueble = mock(Inmueble.class);
		rangoDeFechas = mock(RangoDeFechas.class);
		estado = mock(EstadoReserva.class);
		solicitante = mock(Usuario.class);
		reserva = new Reserva(inmueble, rangoDeFechas, solicitante, formaDePago, estado);
		
		reserva.finalizar();
		
		verify(estado).finalizar(reserva);
	}
	
	@Test
	void Reserva_ComentarInmueble_Success() throws EstadoEquivocadoError {
		String comentario = "";
		reserva.comentarInmueble(comentario);
		verify(estado).comentarInmueble(reserva, comentario);
    }
	
	@Test
	void Reserva_PuntuarDuenho_Success() throws EstadoEquivocadoError {
		String categoria = "";
		reserva.puntuarDuenho(categoria, 10);
		verify(estado).puntuarDuenho(reserva, categoria, 10);
    }
	
	@Test
	void Reserva_PuntuarInquilino_Success() throws EstadoEquivocadoError {
		String categoria = "";
		reserva.puntuarInquilino(categoria, 10);
		verify(estado).puntuarInquilino(reserva, categoria, 10);
    }
	
	@Test
	void Reserva_PuntuarInmueble_Success() throws EstadoEquivocadoError {
		String categoria = "";
		reserva.puntuarInmueble(categoria, 10);
		verify(estado).puntuarInmueble(reserva, categoria, 10);
    }
    
	@Test
	void Reserva_EstaEnEstado_Success() throws EstadoEquivocadoError {
		String estadoCodigo = "";
		reserva.estaEnEstado(estadoCodigo);
		verify(estado).esEstado(estadoCodigo);
    }
}

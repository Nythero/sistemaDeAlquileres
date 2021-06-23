package ar.edu.unq.po2.sistemaDeAlquileres.reserva;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.sistemaDeAlquileres.inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.rangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado.CambioDeEstadoError;
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
	void Reserva_Cancelar_Success() throws CambioDeEstadoError {
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
}

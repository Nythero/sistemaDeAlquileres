package ar.edu.unq.po2.sistemaDeAlquileres.reserva;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.sistemaDeAlquileres.*;
import ar.edu.unq.po2.sistemaDeAlquileres.rangoDeFecha.RangoDeFecha;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado.CambioDeEstadoError;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado.EstadoReserva;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

class ReservaTestCase {

	Reserva reserva;
	Inmueble inmueble;
	RangoDeFecha dias;
	EstadoReserva estado;
	Usuario solicitante;
	
	@Test
	void Reserva_Aceptar_Success() throws CambioDeEstadoError {
		inmueble = mock(Inmueble.class);
		dias = new RangoDeFecha();
		estado = mock(EstadoReserva.class);
		solicitante = mock(Usuario.class);
		reserva = new Reserva(dias, inmueble, solicitante, estado);
		
		reserva.aceptar();
		
		verify(estado).aceptar(reserva);
	}
	
	@Test
	void Reserva_Cancelar_Success() throws CambioDeEstadoError {
		inmueble = mock(Inmueble.class);
		dias = new RangoDeFecha();
		estado = mock(EstadoReserva.class);
		solicitante = mock(Usuario.class);
		reserva = new Reserva(dias, inmueble, solicitante, estado);
		
		reserva.cancelar();
		
		verify(estado).cancelar(reserva);
	}
	
	@Test
	void Reserva_Finalizar_Success() throws CambioDeEstadoError {
		inmueble = mock(Inmueble.class);
		dias = new RangoDeFecha();
		estado = mock(EstadoReserva.class);
		solicitante = mock(Usuario.class);
		reserva = new Reserva(dias, inmueble, solicitante, estado);
		
		reserva.finalizar();
		
		verify(estado).finalizar(reserva);
	}
}

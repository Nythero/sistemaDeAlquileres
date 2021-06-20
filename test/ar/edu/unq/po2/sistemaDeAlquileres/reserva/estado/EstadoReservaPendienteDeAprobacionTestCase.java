package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.sistemaDeAlquileres.*;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

class EstadoReservaPendienteDeAprobacionTestCase {

	Reserva reserva;
	Inmueble inmueble;
	EstadoReserva estado;
	Usuario solicitante;
	
	@Test
	void ReservaEstadoPendienteDeAprobacion_Aceptar_Success() throws CambioDeEstadoError {
		inmueble = mock(Inmueble.class);
		estado = new EstadoReservaPendienteDeAprobacion();
		solicitante = mock(Usuario.class);
		reserva = mock(Reserva.class);
		
		when(reserva.getInmueble()).thenReturn(inmueble);
		
		estado.aceptar(reserva);
		
		verify(inmueble).aceptarReserva(reserva);
	}
	
	@Test
	void ReservaEstadoPendienteDeAprobacion_Cancelar_Success() throws CambioDeEstadoError {
		inmueble = mock(Inmueble.class);
		estado = new EstadoReservaPendienteDeAprobacion();
		solicitante = mock(Usuario.class);
		reserva = mock(Reserva.class);
		
		when(reserva.getInmueble()).thenReturn(inmueble);
		
		estado.cancelar(reserva);
		
		verify(inmueble).cancelarReserva(reserva);
	}
	
	@Test
	void ReservaEstadoPendienteDeAprobacion_Finalizar_CambioDeEstadoError() throws CambioDeEstadoError {
		inmueble = mock(Inmueble.class);
		estado = new EstadoReservaPendienteDeAprobacion();
		solicitante = mock(Usuario.class);
		reserva = mock(Reserva.class);
		
		assertThrows(CambioDeEstadoError.class, () -> estado.finalizar(reserva));
	}
}
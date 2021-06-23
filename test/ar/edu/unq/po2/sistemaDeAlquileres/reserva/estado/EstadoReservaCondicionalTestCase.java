package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.sistemaDeAlquileres.*;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

class EstadoReservaCondicionalTestCase {

	Reserva reserva;
	Inmueble inmueble;
	EstadoReserva estado;
	Usuario solicitante;
	
	@Test
	void EstadoReservaAprobado_Aceptar_CambioEstadoError() throws CambioDeEstadoError {
		inmueble = mock(Inmueble.class);
		estado = new EstadoReservaC();
		solicitante = mock(Usuario.class);
		reserva = mock(Reserva.class);
		
		assertThrows(CambioDeEstadoError.class, () -> estado.aceptar(reserva));
	}
	
	@Test
	void EstadoReservaAprobado_Cancelar_Success() throws CambioDeEstadoError {
		inmueble = mock(Inmueble.class);
		estado = new EstadoReservaAprobado();
		solicitante = mock(Usuario.class);
		reserva = mock(Reserva.class);
		
		when(reserva.getInmueble()).thenReturn(inmueble);
		
		estado.cancelar(reserva);
		
		verify(inmueble).cancelarReserva(reserva);
	}
	
	@Test
	void ReservaEstadoPendienteDeAprobacion_Finalizar_Success() throws CambioDeEstadoError {
		inmueble = mock(Inmueble.class);
		estado = new EstadoReservaAprobado();
		solicitante = mock(Usuario.class);
		reserva = mock(Reserva.class);

		when(reserva.getInmueble()).thenReturn(inmueble);
		
		estado.finalizar(reserva);
		
		verify(inmueble).finalizarReserva(reserva);
	}
}
package ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Mail.MailSender;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;


class EstadoReservaConcretadoTestCase {

	Reserva reserva;
	Inmueble inmueble;
	EstadoReserva estado;
	static MockedStatic<MailSender> email;  
	
	@BeforeAll
	static void setupStatic() {
		email = mockStatic(MailSender.class);
	}
	
	@BeforeEach
	void setup(){
		estado = new EstadoReservaConcretado();
		reserva = mock(Reserva.class);
		inmueble = mock(Inmueble.class);
		
		when(reserva.getInmueble()).thenReturn(inmueble);
	}
	
	@Test
	void EstadoReservaConcretado_Aceptar_CambioEstadoError() throws CambioDeEstadoError {
		assertThrows(CambioDeEstadoError.class, () -> estado.aceptar(reserva));
	}
	
	@Test
	void EstadoReservaConcretado_Cancelar_Success() throws CambioDeEstadoError, Exception {
		EstadoReserva estadoDevuelto = estado.cancelar(reserva);
		
		assertTrue(estadoDevuelto.getClass().equals(EstadoReservaCancelado.class));
		email.verify(() -> MailSender.mandarMailDeCancelacion(reserva));
		verify(reserva).getInmueble();
		verify(inmueble).cancelarReserva(reserva);
	}
	
	@Test
	void EstadoReservaConcretado_Finalizar_Success() throws CambioDeEstadoError {
		EstadoReserva estadoDevuelto = estado.finalizar(reserva);
		
		assertTrue(estadoDevuelto.getClass().equals(EstadoReservaFinalizado.class));
	}
	
	@Test
	void EstadoReservaConcretado_ComentarInmueble_EstadoEquivocadoError() throws EstadoEquivocadoError {
		assertThrows(EstadoEquivocadoError.class, () -> estado.comentarInmueble(null, null));
	}

	@Test
	void EstadoReservaConcretado_PuntuarDuenho_EstadoEquivocadoError() throws EstadoEquivocadoError {
		assertThrows(EstadoEquivocadoError.class, () -> estado.puntuarDuenho(null, null, null));
	}

	@Test
	void EstadoReservaConcretado_PuntuarInquilino_EstadoEquivocadoError() throws EstadoEquivocadoError {
		assertThrows(EstadoEquivocadoError.class, () -> estado.puntuarInquilino(null, null, null));
	}

	@Test
	void EstadoReservaConcretado_PuntuarInmueble_EstadoEquivocadoError() throws EstadoEquivocadoError{
		assertThrows(EstadoEquivocadoError.class, () -> estado.puntuarInmueble(null, null, null));
	}
	
	@Test
	void EstadoReservaConcretado_Esta_Success() {
		assertTrue(estado.esEstado("Concretado"));
		assertFalse(estado.esEstado("Condicional"));
	}
	
	@AfterAll
	static void teardown() {
		email.close();
	}
}
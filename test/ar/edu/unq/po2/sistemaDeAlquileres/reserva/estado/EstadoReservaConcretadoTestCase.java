package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

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

import ar.edu.unq.po2.sistemaDeAlquileres.inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.mail.MailSender;
import ar.edu.unq.po2.sistemaDeAlquileres.ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

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
	void EstadoReservaConcretado_Cancelar_Success() throws CambioDeEstadoError {
		EstadoReserva estadoDevuelto = estado.cancelar(reserva);
		
		assertTrue(estadoDevuelto.getClass().equals(EstadoReservaCancelado.class));
		email.verify(() -> MailSender.mandarMailDeCancelacion(reserva));
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
	void EstadoReservaConcretado_EstaPendienteDeAprobacion_False() {
		assertFalse(estado.estaPendienteDeAprobacion());
	}

	@Test
	void EstadoReservaConcretado_EstaConcretada_True() {
		assertTrue(estado.estaConcretada());
	}
	
	@AfterAll
	static void teardown() {
		email.close();
	}
}
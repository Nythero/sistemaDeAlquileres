package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import ar.edu.unq.po2.sistemaDeAlquileres.inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.mail.MailSender;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

class EstadoReservaPendienteDeAprobacionTestCase {

	Reserva reserva;
	Inmueble inmueble;
	EstadoReservaPendienteDeAprobacion estado;
	Usuario solicitante;
	static MockedStatic<MailSender> email;
	
	@BeforeAll
	static void setupStatic() {
		email = mockStatic(MailSender.class);
	}
	
	@BeforeEach
	void setup() {
		estado = new EstadoReservaPendienteDeAprobacion();
		reserva = mock(Reserva.class);
	}
	
	@Test
	void EstadoReservaPendienteDeAprobacion_Aceptar_ReturnsEstadoReservaCondicional() throws CambioDeEstadoError {
		Inmueble inmueble = mock(Inmueble.class);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.yaEstaReservado(reserva.getRangoDeFechas())).thenReturn(true);
		
		EstadoReserva estadoDevuelto = estado.aceptar(reserva);
		
		email.verify(() -> MailSender.mandarMailDeConfirmacion(reserva));
		assertTrue(estadoDevuelto.getClass().equals(EstadoReservaCondicional.class));
	}
	
	@Test
	void EstadoReservaPendienteDeAprobacion_Aceptar_ReturnsEstadoReservaConcretado() throws CambioDeEstadoError {
		Inmueble inmueble = mock(Inmueble.class);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.yaEstaReservado(reserva.getRangoDeFechas())).thenReturn(false);
		
		EstadoReserva estadoDevuelto = estado.aceptar(reserva);
		
		email.verify(() -> MailSender.mandarMailDeConfirmacion(reserva));
		assertTrue(estadoDevuelto.getClass().equals(EstadoReservaConcretado.class));
	}
	@Test
	void EstadoReservaPendienteDeAprobacion_Cancelar_Success() throws CambioDeEstadoError {
		estado.cancelar(reserva);
		
		email.verify(() -> MailSender.mandarMailDeCancelacion(reserva));
	}
	
	@Test
	void EstadoReservaPendienteDeAprobacion_Finalizar_CambioDeEstadoError() throws CambioDeEstadoError {
		assertThrows(CambioDeEstadoError.class, () -> estado.finalizar(reserva));
	}

	@Test
	void EstadoReservaPendienteDeAprobacion_ComentarInmueble_EstadoEquivocadoError() {
		assertThrows(EstadoEquivocadoError.class, () -> estado.comentarInmueble(null, null));
	}

	@Test
	void EstadoReservaPendienteDeAprobacion_PuntuarDuenho_EstadoEquivocadoError() {
		assertThrows(EstadoEquivocadoError.class, () -> estado.puntuarDuenho(null, null, null));
	}

	@Test
	void EstadoReservaPendienteDeAprobacion_PuntuarInquilino_EstadoEquivocadoError() {
		assertThrows(EstadoEquivocadoError.class, () -> estado.puntuarInquilino(null, null, null));
	}

	@Test
	void EstadoReservaPendienteDeAprobacion_PuntuarInmueble_EstadoEquivocadoError() throws EstadoEquivocadoError{
		assertThrows(EstadoEquivocadoError.class, () -> estado.puntuarInmueble(null, null, null));
	}

	@Test
	void EstadoReservaPendienteDeAprobacion_EstaPendienteDeAprobacion_True() {
		assertTrue(estado.estaPendienteDeAprobacion());
	}

	@Test
	void EstadoReservaPendienteDeAprobacion_EstaConcretada_False() {
		assertFalse(estado.estaConcretada());
	}
	
	@AfterAll
	static void teardown() {
		email.close();
	}
}
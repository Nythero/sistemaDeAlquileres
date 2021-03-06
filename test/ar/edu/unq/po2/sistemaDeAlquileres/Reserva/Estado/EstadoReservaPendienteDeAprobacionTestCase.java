package ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado;

import static org.junit.Assert.assertTrue;
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

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Mail.MailSender;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;


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
	void EstadoReservaPendienteDeAprobacion_EsEstado_Success() {
		assertTrue(estado.esEstado("PendienteDeAprobacion"));
		assertFalse(estado.esEstado("Finalizado"));
	}
	
	@AfterAll
	static void teardown() {
		email.close();
	}
}
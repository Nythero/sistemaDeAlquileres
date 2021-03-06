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
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

class EstadoReservaCondicionalTestCase {

	Reserva reserva;
	Inmueble inmueble;
	EstadoReserva estado;
	Usuario solicitante;
	static MockedStatic<MailSender> email;  
	
	@BeforeAll
	static void setupStatic() {
		email = mockStatic(MailSender.class);
	}
	
	@BeforeEach
	void setup(){
		estado = new EstadoReservaCondicional();
		reserva = mock(Reserva.class);
		inmueble = mock(Inmueble.class);
		
		when(reserva.getInmueble()).thenReturn(inmueble);
	}
	@Test
	void EstadoReservaCondicional_Aceptar_CambioEstadoError() throws CambioDeEstadoError {
		assertThrows(CambioDeEstadoError.class, () -> estado.aceptar(reserva));
	}
	
	@Test
	void EstadoReservaCondicional_Cancelar_Success() throws Exception {
		EstadoReserva estadoDevuelto = estado.cancelar(reserva);
		
		assertTrue(estadoDevuelto.getClass().equals(EstadoReservaCancelado.class));
		email.verify(() -> MailSender.mandarMailDeCancelacion(reserva));
	}
	
	@Test
	void EstadoReservaCondicional_Finalizar_Success() throws CambioDeEstadoError {
		assertThrows(CambioDeEstadoError.class, () -> estado.finalizar(reserva));
	}
	
	@Test
	void EstadoReservaCondicional_Concretar_Success() throws Exception {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.yaEstaReservado(reserva.getRangoDeFechas())).thenReturn(false);
		EstadoReserva estadoDevuelto = estado.concretar(reserva);

		assertTrue(estadoDevuelto.getClass().equals(EstadoReservaConcretado.class));
	}

	@Test
	void EstadoReservaCondicional_Concretar_Exception() throws Exception {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.yaEstaReservado(reserva.getRangoDeFechas())).thenReturn(true);

		assertThrows(CambioDeEstadoError.class, () -> estado.concretar(reserva));
	}
	
	@Test
	void EstadoReservaCondicional_ComentarInmueble_EstadoEquivocadoError() throws EstadoEquivocadoError {
		assertThrows(EstadoEquivocadoError.class, () -> estado.comentarInmueble(null, null));
	}

	@Test
	void EstadoReservaCondicional_PuntuarDuenho_EstadoEquivocadoError() throws EstadoEquivocadoError {
		assertThrows(EstadoEquivocadoError.class, () -> estado.puntuarDuenho(null, null, null));
	}

	@Test
	void EstadoReservaCondicional_PuntuarInquilino_EstadoEquivocadoError() throws EstadoEquivocadoError {
		assertThrows(EstadoEquivocadoError.class, () -> estado.puntuarInquilino(null, null, null));
	}

	@Test
	void EstadoReservaCondicional_PuntuarInmueble_EstadoEquivocadoError() throws EstadoEquivocadoError{
		assertThrows(EstadoEquivocadoError.class, () -> estado.puntuarInmueble(null, null, null));
	}

	@Test
	void EstadoReservaCondicional_Esta_Success() {
		assertFalse(estado.esEstado("Concretado"));
		assertTrue(estado.esEstado("Condicional"));
	}
	
	@AfterAll
	static void teardown() {
		email.close();
	}
}
package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;

class EstadoReservaCanceladoTestCase {

	Reserva reserva;
	EstadoReserva estado;
	
	@BeforeEach
	void setup(){
		estado = new EstadoReservaCancelado();
		reserva = mock(Reserva.class);
	}
	
	@Test
	void EstadoReservaCancelado_Aceptar_CambioDeEstadoError() throws CambioDeEstadoError {
		assertThrows(CambioDeEstadoError.class, () -> estado.aceptar(reserva));
	}
	
	@Test
	void EstadoReservaCancelado_Cancelar_CambioDeEstadoError() throws CambioDeEstadoError {
		assertThrows(CambioDeEstadoError.class, () -> estado.cancelar(reserva));
	}
	
	@Test
	void EstadoReservaCancelado_Finalizar_CambioDeEstadoError() throws CambioDeEstadoError {
		assertThrows(CambioDeEstadoError.class, () -> estado.finalizar(reserva));
	}
	
	@Test
	void EstadoReservaCancelado_ComentarInmueble_EstadoEquivocadoError() {
		assertThrows(EstadoEquivocadoError.class, () -> estado.comentarInmueble(null, null));
	}

	@Test
	void EstadoReservaCancelado_PuntuarDuenho_EstadoEquivocadoError() {
		assertThrows(EstadoEquivocadoError.class, () -> estado.puntuarDuenho(null, null, null));
	}

	@Test
	void EstadoReservaCancelado_PuntuarInquilino_EstadoEquivocadoError() {
		assertThrows(EstadoEquivocadoError.class, () -> estado.puntuarInquilino(null, null, null));
	}

	@Test
	void EstadoReservaCancelado_PuntuarInmueble_EstadoEquivocadoError() throws EstadoEquivocadoError{
		assertThrows(EstadoEquivocadoError.class, () -> estado.puntuarInmueble(null, null, null));
	}

	@Test
	void EstadoReservaCancelado_EstaPendienteDeAprobacion_False() {
		assertFalse(estado.estaPendienteDeAprobacion());
	}

	@Test
	void EstadoReservaCancelado_EstaConcretada_False() {
		assertFalse(estado.estaConcretada());
	}
}
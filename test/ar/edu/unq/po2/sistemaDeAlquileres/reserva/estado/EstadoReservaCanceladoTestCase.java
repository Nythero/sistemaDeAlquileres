package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

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
	void ReservaEstadoPendienteDeAprobacion_Cancelar_CambioDeEstadoError() throws CambioDeEstadoError {
		assertThrows(CambioDeEstadoError.class, () -> estado.cancelar(reserva));
	}
	
	@Test
	void ReservaEstadoPendienteDeAprobacion_Finalizar_CambioDeEstadoError() throws CambioDeEstadoError {
		assertThrows(CambioDeEstadoError.class, () -> estado.finalizar(reserva));
	}
}
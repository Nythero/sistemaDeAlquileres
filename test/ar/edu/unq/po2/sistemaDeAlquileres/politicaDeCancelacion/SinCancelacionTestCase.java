package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion.PoliticaDeCancelacion;
import ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion.SinCancelacion;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

class SinCancelacionTestCase {
	private PoliticaDeCancelacion sinCancelacion;
	@Mock Reserva reserva;
	
	@BeforeEach
	void setUp() throws Exception {
		reserva= mock(Reserva.class);
		sinCancelacion= new SinCancelacion();
	}

	@Test
	void testSeCancelaLaReserva() {
		sinCancelacion.cancelarReserva(reserva);
		
		verifyZeroInteractions(reserva);
	}

}

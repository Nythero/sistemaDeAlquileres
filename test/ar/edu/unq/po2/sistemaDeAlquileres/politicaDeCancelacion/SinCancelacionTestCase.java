package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

class SinCancelacionTestCase {
	private PoliticaDeCancelacion sinCancelacion;
	@Mock private Reserva reserva;
	@Mock private LocalDate fechaActual;
	
	@BeforeEach
	void setUp() throws Exception {
		reserva= mock(Reserva.class);
		fechaActual= mock(LocalDate.class);
		sinCancelacion= new SinCancelacion();
	}

	@Test
	void testSeCancelaLaReserva() {
		sinCancelacion.cancelarReserva(fechaActual,reserva);
		
		verifyZeroInteractions(reserva);
	}

}

package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import junit.framework.AssertionFailedError;

class CancelacionIntermediaTestCase {
	private CancelacionIntermedia cancelacionIntermedia;
	@Mock private LocalDate fechaActual;
	@Mock private Reserva reserva;
	
	@BeforeEach
	void setUp() throws Exception {
		this.cancelacionIntermedia= new CancelacionIntermedia();
		this.fechaActual= mock(LocalDate.class);
		this.reserva= mock(Reserva.class);
	}

	@Test
	void testSeCancelaConMasDe20Dias() {
		when(fechaActual.isAfter(reserva.getFechaInicial())).thenReturn(false).thenReturn(false);
		when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(true).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(true).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).
		thenReturn(false).thenReturn(true);
		
		when(fechaActual.plusDays(1)).thenReturn(fechaActual).thenReturn(fechaActual).thenReturn(fechaActual);
		when(reserva.getMontoTotal()).thenReturn(500f).thenReturn(500f).thenReturn(500f);
		
		cancelacionIntermedia.cancelarReserva(fechaActual,reserva);
		verify(reserva,times(52)).getFechaInicial();
		verify(reserva).devolverMontoAInquilinoSegunCancelacion(500f);
		verify(reserva).extraerMontoADueñoSegunCancelacion(500f);
	}
	@Test
	void testSeCancelaEntre19Y10Dias() {
		when(fechaActual.isAfter(reserva.getFechaInicial())).thenReturn(false).thenReturn(false);
		when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(true).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(true);
		when(fechaActual.plusDays(1)).thenReturn(fechaActual).thenReturn(fechaActual);
		when(reserva.getMontoTotal()).thenReturn(500f).thenReturn(500f);
		cancelacionIntermedia.cancelarReserva(fechaActual,reserva);
		
		verify(reserva,times(28)).getFechaInicial();
		verify(reserva).devolverMontoAInquilinoSegunCancelacion(250f);
		verify(reserva).extraerMontoADueñoSegunCancelacion(250f);
	}
	
	@Test
	void testSeCancelaConMenosDe10Dias() {
		when(fechaActual.isAfter(reserva.getFechaInicial())).thenReturn(false).thenReturn(false).thenReturn(false);
		when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(true).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(true).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(true);
		when(fechaActual.plusDays(1)).thenReturn(fechaActual).thenReturn(fechaActual).thenReturn(fechaActual);
		cancelacionIntermedia.cancelarReserva(fechaActual,reserva);
		
		verify(reserva,times(32)).getFechaInicial();
	}
	
	@Test
	void testCuandoNoSePuedeCancelarLaReseva() {
		when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(true);
		
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			cancelacionIntermedia.cancelarReserva(fechaActual,reserva);
		  });
		
		verify(reserva,times(2)).getFechaInicial();
	}
}

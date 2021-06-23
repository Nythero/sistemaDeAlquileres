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

class CancelacionGratuitaTestCase {
	private CancelacionGratuita cancelacion;
	@Mock private LocalDate fechaActual;
	@Mock private Reserva reserva;

	@BeforeEach
	void setUp() throws Exception {
		this.cancelacion= new CancelacionGratuita();
		this.fechaActual= mock(LocalDate.class);
		this.reserva= mock(Reserva.class);		
	}
	
	@Test
	void testCuandoSeCancelaUnaReservaSiendoGratuita() {
		when(fechaActual.isAfter(reserva.getFechaInicial())).thenReturn(false);
		when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false)
		.thenReturn(true);
		when(fechaActual.plusDays(1)).thenReturn(fechaActual);
		when(reserva.getMontoTotal()).thenReturn(500f);
		cancelacion.cancelarReserva(fechaActual,reserva);
		
		verify(reserva,times(2)).getMontoTotal();
		verify(reserva,times(16)).getFechaInicial();
		verify(reserva).devolverMontoAInquilinoSegunCancelacion(500f);
		verify(reserva).extraerMontoADueñoSegunCancelacion(500f);
	}
	
	@Test
	void testCuandoSeCancelaUnaReservaDeUnDiaSiendoNoGratuita() {
		when(fechaActual.isAfter(reserva.getFechaInicial())).thenReturn(false);
		when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(true);
		when(fechaActual.plusDays(1)).thenReturn(fechaActual);
		when(reserva.getMontoTotal()).thenReturn(500f);
		when(reserva.cantidadDeDias()).thenReturn(1);
		cancelacion.cancelarReserva(fechaActual,reserva);
		
		verify(reserva,times(8)).getFechaInicial();
		verify(reserva).exigirMontoFaltanteAInquilino();
	}
	
	@Test
	void testCuandoSeCancelaUnaReservaDeMasDeUnDiaSiendoNoGratuita() {
		when(fechaActual.isAfter(reserva.getFechaInicial())).thenReturn(false);
		when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(true);
		when(fechaActual.plusDays(1)).thenReturn(fechaActual);
		when(reserva.cantidadDeDias()).thenReturn(3);
		when(reserva.getMontoTotal()).thenReturn(900f);
		when(reserva.obtenerMontoSegunDias(2)).thenReturn(600f);
		cancelacion.cancelarReserva(fechaActual,reserva);
		
		verify(reserva).getMontoTotal();
		verify(reserva,times(8)).getFechaInicial();
	 	verify(reserva).devolverMontoAInquilinoSegunCancelacion(300f);
		verify(reserva).extraerMontoADueñoSegunCancelacion(600f);
	}
	
	@Test
	void testCuandoNoSePuedeCancelarLaReseva() {
		when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(true);
		
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			cancelacion.cancelarReserva(fechaActual,reserva);
		  });
		
		verify(reserva,times(2)).getFechaInicial();
	}
	
	
}

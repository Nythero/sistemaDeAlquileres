package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado.RangoDeFechaConPrecioDeterminado;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

class CancelacionGratuitaTestCase {
	private CancelacionGratuita cancelacion;
	@Mock private LocalDate fechaActual;
	@Mock private Reserva reserva;
	@Mock private RangoDeFechaConPrecioDeterminado rangoDeFechas;

	@BeforeEach
	void setUp() throws Exception {
		this.cancelacion= new CancelacionGratuita();
		this.fechaActual= mock(LocalDate.class);
		this.reserva= mock(Reserva.class);
		this.rangoDeFechas= mock(RangoDeFechaConPrecioDeterminado.class);		
	}
	
	@Test
	void testCuandoSeCancelaUnaReservaSiendoGratuita() {
		/*LocalDate fechaActual = LocalDate.of(2021, 6, 19);
		when(reserva.getFechaInicial()).thenReturn(LocalDate.of(2021, 6, 30));
		when(reserva.getMontoTotal()).thenReturn(500f);
		cancelacion.cancelarReserva(fechaActual,reserva);
		
		verify(reserva,times(2)).getMontoTotal();
		verify(reserva,times(14)).getFechaInicial();
		verify(reserva).devolverMontoAInquilinoSegunCancelacion(500f);
		verify(reserva).extraerMontoADueñoSegunCancelacion(500f);*/
		//when(reserva.getFechaInicial()).thenReturn(LocalDate.of(2021, 6, 30));
		when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(false);
		when(fechaActual.isAfter(reserva.getFechaInicial())).thenReturn(false);
		when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false)
		.thenReturn(true);
		//when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(false);
		//thenReturn(true).thenReturn(true).thenReturn(true).
	//	thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).
		//thenReturn(true).thenReturn(true).thenReturn(false);*/
		
		when(reserva.getMontoTotal()).thenReturn(500f);
		cancelacion.cancelarReserva(fechaActual,reserva);
		
		verify(reserva,times(2)).getMontoTotal();
		verify(reserva,times(14)).getFechaInicial();
		verify(reserva).devolverMontoAInquilinoSegunCancelacion(500f);
		verify(reserva).extraerMontoADueñoSegunCancelacion(500f);
	}
	
	@Test
	void testCuandoSeCancelaUnaReservaDeUnDiaSiendoNoGratuita() {
		LocalDate fechaActual = LocalDate.of(2021, 6, 22);
		when(reserva.getFechaInicial()).thenReturn(LocalDate.of(2021, 6, 25));
		when(reserva.cantidadDeDias()).thenReturn(1);
		cancelacion.cancelarReserva(fechaActual,reserva);
		
		verify(reserva,times(6)).getFechaInicial();
		verify(reserva).exigirMontoFaltanteAInquilino();
	}
	
	@Test
	void testCuandoSeCancelaUnaReservaDeMasDeUnDiaSiendoNoGratuita() {
		/*
		LocalDate fechaActual = LocalDate.of(2021, 6, 22);
		when(reserva.getFechaInicial()).thenReturn(LocalDate.of(2021, 6, 25));
		when(reserva.cantidadDeDias()).thenReturn(3);
		when(reserva.getMontoTotal()).thenReturn(900f);
		when(reserva.obtenerMontoSegunDias(2)).thenReturn(600f);
		cancelacion.cancelarReserva(fechaActual,reserva);
		
		verify(reserva).getMontoTotal();
		verify(reserva,times(6)).getFechaInicial();
	 	verify(reserva).devolverMontoAInquilinoSegunCancelacion(300f);
		verify(reserva).extraerMontoADueñoSegunCancelacion(600f);
		*/
		when(fechaActual.isAfter(reserva.getFechaInicial())).thenReturn(false);
		when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(false)
		.thenReturn(true);
	}
}

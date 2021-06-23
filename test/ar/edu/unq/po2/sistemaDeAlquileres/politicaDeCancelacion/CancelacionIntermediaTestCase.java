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

class CancelacionIntermediaTestCase {
	private CancelacionIntermedia cancelacionIntermedia;
	@Mock private LocalDate fechaActual;
	@Mock private Reserva reserva;
	@Mock private RangoDeFechaConPrecioDeterminado rangoDeFechas;
	
	@BeforeEach
	void setUp() throws Exception {
		this.cancelacionIntermedia= new CancelacionIntermedia();
		this.fechaActual= mock(LocalDate.class);
		this.reserva= mock(Reserva.class);
		this.rangoDeFechas= mock(RangoDeFechaConPrecioDeterminado.class);	
	}

	@Test
	void testSeCancelaConMasDe20Dias() {
		/*LocalDate fechaActual = LocalDate.of(2021, 6, 19);
		when(reserva.getFechaInicial()).thenReturn(LocalDate.of(2021, 7, 20));
		when(reserva.getMontoTotal()).thenReturn(500f);
		cancelacionIntermedia.cancelarReserva(fechaActual,reserva);
		
		verify(reserva,times(68)).getFechaInicial();
		verify(reserva).devolverMontoAInquilinoSegunCancelacion(500f);
		verify(reserva).extraerMontoADueñoSegunCancelacion(500f);*/
		
		when(reserva.getFechaInicial()).thenReturn(fechaActual);
		when(reserva.getMontoTotal()).thenReturn(500f);
		cancelacionIntermedia.cancelarReserva(fechaActual,reserva);
		
		verify(reserva,times(68)).getFechaInicial();
		verify(reserva).devolverMontoAInquilinoSegunCancelacion(500f);
		verify(reserva).extraerMontoADueñoSegunCancelacion(500f);
		
	}
	
	@Test
	void testSeCancelaEntre19Y10Dias() {
		LocalDate fechaActual = LocalDate.of(2021, 6, 19);
		when(reserva.getFechaInicial()).thenReturn(LocalDate.of(2021, 7, 2));
		when(reserva.getMontoTotal()).thenReturn(500f);
		cancelacionIntermedia.cancelarReserva(fechaActual,reserva);
		
		verify(reserva,times(32)).getFechaInicial();
		verify(reserva).devolverMontoAInquilinoSegunCancelacion(250f);
		verify(reserva).extraerMontoADueñoSegunCancelacion(250f);
	}
	
	@Test
	void testSeCancelaConMenosDe10Dias() {
		LocalDate fechaActual = LocalDate.of(2021, 6, 19);
		when(reserva.getFechaInicial()).thenReturn(LocalDate.of(2021, 6, 22));
		cancelacionIntermedia.cancelarReserva(fechaActual,reserva);
		
		verify(reserva,times(18)).getFechaInicial();
	}

}

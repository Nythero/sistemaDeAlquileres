package ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;
import junit.framework.AssertionFailedError;

class CancelacionIntermediaTestCase {
	private CancelacionIntermedia cancelacionIntermedia;
	@Mock private LocalDate fechaActual;
	@Mock private Reserva reserva;
	@Mock private RangoDeFechas rango;
	@Mock private Usuario usuario;
	
	@BeforeEach
	void setUp() throws Exception {
		this.cancelacionIntermedia= new CancelacionIntermedia();
		this.fechaActual= mock(LocalDate.class);
		this.reserva= mock(Reserva.class);		
		this.rango= mock(RangoDeFechas.class);
		this.usuario= mock(Usuario.class);
	}

	
	@Test
	void testSeCancelaConMasDe20Dias() throws Exception {
		when(reserva.getRangoDeFechas()).thenReturn(rango);
		when(rango.darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(fechaActual,reserva))
		.thenReturn(22);
		when(reserva.getFechaInicial()).thenReturn(fechaActual);
		when(reserva.getMontoTotal()).thenReturn(500f);
		when(reserva.getSolicitante()).thenReturn(usuario);
		when(reserva.getDuenho()).thenReturn(usuario);
		cancelacionIntermedia.cancelarReserva(fechaActual, reserva);
		
		verify(usuario).recibirPago(500f);
		verify(usuario).extraerMonto(500f);
	}
	@Test
	void testSeCancelaEntre19Y10Dias() throws Exception {
		when(reserva.getRangoDeFechas()).thenReturn(rango);
		when(rango.darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(fechaActual,reserva))
		.thenReturn(15);
		when(reserva.getFechaInicial()).thenReturn(fechaActual);
		when(reserva.getMontoTotal()).thenReturn(500f);
		when(reserva.getSolicitante()).thenReturn(usuario);
		when(reserva.getDuenho()).thenReturn(usuario);
		cancelacionIntermedia.cancelarReserva(fechaActual, reserva);
		
		verify(usuario).recibirPago(250f);
		verify(usuario).extraerMonto(250f);
	}
	
	@Test
	void testSeCancelaConMenosDe10Dias() throws Exception {
		when(reserva.getRangoDeFechas()).thenReturn(rango);
		when(rango.darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(fechaActual,reserva))
		.thenReturn(8);
		when(reserva.getFechaInicial()).thenReturn(fechaActual);
		when(reserva.getMontoTotal()).thenReturn(500f);
		when(reserva.getSolicitante()).thenReturn(usuario);
		when(reserva.getDuenho()).thenReturn(usuario);
		cancelacionIntermedia.cancelarReserva(fechaActual, reserva);
		
		verifyZeroInteractions(usuario);
	}
	
	@Test
	void testCuandoNoSePuedeCancelarLaReseva() {
		when(reserva.getRangoDeFechas()).thenReturn(rango);
		when(reserva.getFechaInicial()).thenReturn(fechaActual);
		when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(true);
		when(rango.darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(fechaActual,reserva))
		.thenThrow(new AssertionFailedError("Ya paso la etapa de cancelacion"));
					
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			cancelacionIntermedia.cancelarReserva(fechaActual,reserva);
			  });
		}
}

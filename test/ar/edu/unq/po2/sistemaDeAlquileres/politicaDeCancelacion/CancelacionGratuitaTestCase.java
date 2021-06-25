package ar.edu.unq.po2.sistemaDeAlquileres.politicaDeCancelacion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;
import junit.framework.AssertionFailedError;

class CancelacionGratuitaTestCase {
	private CancelacionGratuita cancelacion;
	@Mock private LocalDate fechaActual;
	@Mock private Inmueble inmueble;
	@Mock private Reserva reserva;
	@Mock private RangoDeFechas rango;
	@Mock private Usuario usuario;
	@Mock private Temporada precio;

	@BeforeEach
	void setUp() throws Exception {
		this.cancelacion= new CancelacionGratuita();
		this.fechaActual= mock(LocalDate.class);
		this.inmueble= mock(Inmueble.class);
		this.reserva= mock(Reserva.class);		
		this.rango= mock(RangoDeFechas.class);
		this.usuario= mock(Usuario.class);
		this.precio= mock(Temporada.class);
	}
	
	@Test
	void testCuandoSeCancelaUnaReservaSiendoGratuita() throws Exception {
		when(reserva.getRangoDeFechas()).thenReturn(rango);
		when(rango.darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(fechaActual,reserva))
		.thenReturn(11);
		when(reserva.getSolicitante()).thenReturn(usuario);
		when(reserva.getDuenho()).thenReturn(usuario);
		when(reserva.getMontoTotal()).thenReturn(500f);
		cancelacion.cancelarReserva(fechaActual, reserva);
		
		verify(reserva,times(2)).getMontoTotal();
		verify(usuario).recibirPago(500f);
		verify(usuario).extraerMonto(500f);
	}
	
	@Test
	void testCuandoSeCancelaUnaReservaDeUnDiaSiendoNoGratuita() throws Exception {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getPrecio()).thenReturn(precio);
		when(reserva.getRangoDeFechas()).thenReturn(rango);
		when(rango.darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(fechaActual,reserva))
		.thenReturn(9);
		when(reserva.getFechaInicial()).thenReturn(fechaActual);
		when(rango.cantidadDeDias()).thenReturn(1);
		when(precio.getPrecio(reserva.getFechaInicial().plusDays(1))).thenReturn(500f);
		when(reserva.getSolicitante()).thenReturn(usuario);
		when(reserva.getDuenho()).thenReturn(usuario);
		cancelacion.cancelarReserva(fechaActual, reserva);
		
		verify(usuario).extraerMonto(500f);
	}
	
	@Test
	void testCuandoSeCancelaUnaReservaDeMasDeUnDiaSiendoNoGratuita() throws Exception {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getPrecio()).thenReturn(precio);
		when(reserva.getRangoDeFechas()).thenReturn(rango);
		when(rango.darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(fechaActual,reserva))
		.thenReturn(9);
		when(reserva.getFechaInicial()).thenReturn(fechaActual);
		when(rango.cantidadDeDias()).thenReturn(3);
		when(reserva.getMontoTotal()).thenReturn(500f);
		when(rango.obtenerMontoPorCantidadDeDias(precio,2)).thenReturn(200f);
		when(reserva.getSolicitante()).thenReturn(usuario);
		when(reserva.getDuenho()).thenReturn(usuario);
		cancelacion.cancelarReserva(fechaActual, reserva);
		
		verify(usuario).recibirPago(300f);
		verify(usuario).extraerMonto(200f);
	}
	
	
	@Test
	void testCuandoNoSePuedeCancelarLaReseva() {
		when(reserva.getRangoDeFechas()).thenReturn(rango);
		when(reserva.getFechaInicial()).thenReturn(fechaActual);
		when(fechaActual.isEqual(reserva.getFechaInicial())).thenReturn(true);
		when(rango.darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(fechaActual,reserva))
		.thenThrow(new AssertionFailedError("Ya paso la etapa de cancelacion"));
				
				Assertions.assertThrows(AssertionFailedError.class, () -> {
			cancelacion.cancelarReserva(fechaActual,reserva);
		  });
	}
}

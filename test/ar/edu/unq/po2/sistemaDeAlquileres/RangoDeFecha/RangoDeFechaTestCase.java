package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;
import junit.framework.AssertionFailedError;


class RangoDeFechaTestCase {
	private RangoDeFechas rangoDeFechas;
	private RangoDeFechas rangoAComparar;
	@Mock private LocalDate fechaInicial;
	@Mock private LocalDate fechaFinal;
	@Mock private LocalDate fechaInicialAComparar;
	@Mock private LocalDate fechaFinalAComparar;
	@Mock private Reserva reserva;
	@Mock private Temporada precio;
	private ArrayList<RangoDeFechas> rangos;
	
	
	@BeforeEach
	void setUp() throws Exception {
		fechaInicial= mock(LocalDate.class);
		fechaFinal= mock(LocalDate.class);
		fechaInicialAComparar= mock(LocalDate.class);
		fechaFinalAComparar= mock(LocalDate.class);
		reserva= mock(Reserva.class);
		precio= mock(Temporada.class);
		rangoDeFechas= new RangoDeFechas(fechaInicial,fechaFinal);
		rangoAComparar= new RangoDeFechas(fechaInicialAComparar, fechaFinalAComparar);
		this.rangos= new ArrayList<RangoDeFechas>();
	}
	
	@Test
	void testCantidadDeDias() {
		when(fechaInicial.isEqual(fechaFinal)).thenReturn(false).thenReturn(false).thenReturn(true);
		when(fechaInicial.plusDays(1)).thenReturn(fechaInicial);	
		
		int result = rangoDeFechas.cantidadDeDias();
		assertEquals(2,result);
	}
	
	@Test
	void testIntersectanLosRangos() {
		when(fechaInicial.isEqual(fechaInicialAComparar)).thenReturn(true);
		when(fechaInicial.isBefore(fechaInicialAComparar)).thenReturn(false);
		when(fechaFinal.isAfter(fechaInicialAComparar)).thenReturn(false);
		when(fechaInicial.isEqual(fechaInicialAComparar)).thenReturn(true);
		when(fechaInicial.plusDays(1)).thenReturn(fechaInicial);	

		boolean result = rangoDeFechas.intersectanLosRangos(rangoAComparar);
		assertTrue(result);
	}
	
	@Test
	void testNoSeIntersectanLosRangos() {
		when(fechaInicial.isEqual(fechaInicialAComparar)).thenReturn(true);
		when(fechaInicial.isBefore(fechaInicialAComparar)).thenReturn(false);
		when(fechaFinal.isAfter(fechaInicialAComparar)).thenReturn(false);
		when(fechaInicial.isEqual(fechaInicialAComparar)).thenReturn(false);
		when(fechaInicial.plusDays(1)).thenReturn(fechaInicial);	

		boolean result = rangoDeFechas.intersectanLosRangos(rangoAComparar);
		assertFalse(result);
	}
	
	@Test
	void testDarDiasFaltantesEntreFechaActualYFechaInicialDeReserva() {
		when(reserva.getFechaInicial()).thenReturn(fechaInicialAComparar);
		when(fechaInicial.isAfter(reserva.getFechaInicial())).thenReturn(false);
		when(fechaInicial.isEqual(reserva.getFechaInicial())).thenReturn(false).thenReturn(false)
		.thenReturn(false).thenReturn(false).thenReturn(true);
		when(fechaInicial.plusDays(1)).thenReturn(fechaInicial);
		int result = rangoDeFechas.darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(fechaInicial, reserva);
		
		verify(reserva,times(8)).getFechaInicial();
		assertEquals(3,result);
	}
	
	@Test
	void testNoSePuedeDarLosDiasFaltantesEntreFechaActualYFechaInicialDeReserva() {
		when(reserva.getFechaInicial()).thenReturn(fechaInicialAComparar);
		when(fechaInicial.isAfter(reserva.getFechaInicial())).thenReturn(false);
		when(fechaInicial.isEqual(reserva.getFechaInicial())).thenReturn(true);
		when(fechaInicial.plusDays(1)).thenReturn(fechaInicial);
		assertThrows(AssertionFailedError.class,()-> rangoDeFechas.darDiasFaltantesEntreFechaActualYFechaInicialDeReserva(fechaInicial, reserva));
	}
	
	@Test
	void testLasFechasEstanEnElRango() {
		when(fechaInicial.isEqual(fechaInicialAComparar)).thenReturn(false);
		when(fechaInicial.isBefore(fechaInicialAComparar)).thenReturn(true);
		when(fechaFinal.isEqual(fechaFinalAComparar)).thenReturn(false);
		when(fechaFinal.isAfter(fechaFinalAComparar)).thenReturn(true);
		
		boolean result = rangoDeFechas.lasFechasEstanEnElRango(fechaInicialAComparar, fechaFinalAComparar);
		
		assertEquals(true,result);
	}
	
	@Test
	void testLasFechasNoEstanEnElRango() {
		when(fechaInicial.isEqual(fechaInicialAComparar)).thenReturn(false);
		when(fechaInicial.isBefore(fechaInicialAComparar)).thenReturn(false);
		when(fechaFinal.isEqual(fechaFinalAComparar)).thenReturn(false);
		when(fechaFinal.isAfter(fechaFinalAComparar)).thenReturn(false);
		
		boolean result = rangoDeFechas.lasFechasEstanEnElRango(fechaInicialAComparar, fechaFinalAComparar);
		
		assertFalse(result);
	}
	
	@Test
	void testHayHayUnRangoCompatible() {
		rangos.add(rangoAComparar);
		when(fechaInicial.isEqual(fechaInicialAComparar)).thenReturn(true);
		when(fechaInicial.isBefore(fechaInicialAComparar)).thenReturn(false);
		when(fechaFinal.isEqual(fechaFinalAComparar)).thenReturn(true);
		when(fechaFinal.isAfter(fechaFinalAComparar)).thenReturn(false);
		
		boolean result = rangoDeFechas.lasFechasEstanEnElRango(fechaInicialAComparar, fechaFinalAComparar);
		
		assertTrue(result);
	}
	
	@Test
	void testHayNoHayUnRangoCompatible() {
		boolean result = rangoDeFechas.lasFechasEstanEnElRango(fechaInicialAComparar, fechaFinalAComparar);
		
		assertEquals(false,result);
	}
	
	@Test
	void testCalcularMontoTotalSegunElRango() {
		when(fechaInicial.isEqual(fechaFinal)).thenReturn(false).thenReturn(false).thenReturn(true);
		when(fechaInicial.plusDays(1)).thenReturn(fechaInicial);
		when(precio.getPrecio(fechaInicial)).thenReturn(500f).thenReturn(600f).thenReturn(500f);
		float result = rangoDeFechas.getMontoTotal(precio);
		
		verify(fechaInicial,times(3)).isEqual(fechaFinal);
		verify(precio,times(3)).getPrecio(fechaInicial);
		assertEquals(1600f, result);
	}
	
	
	@Test
	void testPrecioMaximoEntreElRangoDeFechas() {
		when(fechaInicial.isBefore(fechaFinal)).thenReturn(false);
		when(fechaInicial.isEqual(fechaFinal)).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
		when(fechaInicial.plusDays(1)).thenReturn(fechaInicial);
		when(precio.getPrecio(fechaInicial)).thenReturn(500f).thenReturn(600f).thenReturn(1000f);
		float result = rangoDeFechas.precioMaximoEntreElRangoDeFechas(precio,fechaInicial, fechaFinal);
		
		verify(fechaInicial,times(4)).isEqual(fechaFinal);
		verify(precio,times(5)).getPrecio(fechaInicial);
		assertEquals(1000f,result);
		
	}
	
	@Test
	void testObtenerMontoPorCantidadDeDias() {
		when(fechaInicial.plusDays(1)).thenReturn(fechaInicial);
		when(precio.getPrecio(fechaInicial)).thenReturn(500f).thenReturn(600f);
		float result = rangoDeFechas.obtenerMontoPorCantidadDeDias(precio,2);
		
		verify(precio,times(2)).getPrecio(fechaInicial);
		assertEquals(1100f, result);
	}
	
	@Test
	void testEstaIncluidaLaFecha() {
		when(fechaInicial.isBefore(fechaInicialAComparar)).thenReturn(true);
		when(fechaFinal.isAfter(fechaInicialAComparar)).thenReturn(true);
		when(fechaInicial.isEqual(fechaInicialAComparar)).thenReturn(false);
		when(fechaFinal.isEqual(fechaInicialAComparar)).thenReturn(false);
		boolean result = rangoDeFechas.estaIncluidaLaFecha(fechaInicialAComparar);
		
		assertTrue(result);
	}
	
	@Test
	void testNoEstaIncluidaLaFecha() {
		when(fechaInicial.isBefore(fechaInicialAComparar)).thenReturn(false);
		when(fechaFinal.isAfter(fechaInicialAComparar)).thenReturn(false);
		when(fechaInicial.isEqual(fechaInicialAComparar)).thenReturn(false);
		when(fechaFinal.isEqual(fechaInicialAComparar)).thenReturn(false);
		boolean result = rangoDeFechas.estaIncluidaLaFecha(fechaInicialAComparar);
		
		assertFalse(result);
	}
	
	@Test
	void testEstaIncluidoElRango() {
		when(fechaInicial.isBefore(fechaInicialAComparar)).thenReturn(true);
		when(fechaFinal.isAfter(fechaInicialAComparar)).thenReturn(true);
		when(fechaInicial.isEqual(fechaInicialAComparar)).thenReturn(false);
		when(fechaFinal.isEqual(fechaInicialAComparar)).thenReturn(false);
		when(fechaInicial.isBefore(fechaFinalAComparar)).thenReturn(true);
		when(fechaFinal.isAfter(fechaFinalAComparar)).thenReturn(false);
		when(fechaInicial.isEqual(fechaFinalAComparar)).thenReturn(false);
		when(fechaFinal.isEqual(fechaFinalAComparar)).thenReturn(true);
		boolean result = rangoDeFechas.estaIncluidoElRango(rangoAComparar);
		
		assertTrue(result);
	}

	@Test
	void testNoEstaIncluidoElRango() {
		when(fechaInicial.isBefore(fechaInicialAComparar)).thenReturn(false);
		when(fechaFinal.isAfter(fechaInicialAComparar)).thenReturn(false);
		when(fechaInicial.isEqual(fechaInicialAComparar)).thenReturn(false);
		when(fechaFinal.isEqual(fechaInicialAComparar)).thenReturn(false);
		boolean result = rangoDeFechas.estaIncluidoElRango(rangoAComparar);
		
		assertFalse(result);
	}
}

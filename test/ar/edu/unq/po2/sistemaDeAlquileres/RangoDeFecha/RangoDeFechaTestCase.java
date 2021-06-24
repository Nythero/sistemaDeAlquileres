package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado.RangoDeFechaConPrecioDeterminado;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;




class RangoDeFechaTestCase {
	private RangoDeFechas rangoDeFechas;
	private RangoDeFechas rangoAComparar;
	private RangoDeFechaConPrecioDeterminado rangoConPrecio;
	@Mock private LocalDate fechaInicial;
	@Mock private LocalDate fechaFinal;
	@Mock private LocalDate fechaInicialAComparar;
	@Mock private LocalDate fechaFinalAComparar;
	@Mock private Reserva reserva;
	@Mock private Temporada precio;
	private ArrayList<RangoDeFechaConPrecioDeterminado> rangos;
	
	
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
		rangoConPrecio = new RangoDeFechaConPrecioDeterminado(fechaInicialAComparar, fechaFinalAComparar,precio);
		this.rangos= new ArrayList<RangoDeFechaConPrecioDeterminado>();
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
		
		assertEquals(false,result);
	}
	
	@Test
	void testHayHayUnRangoCompatible() {
		rangos.add(rangoConPrecio);
		when(fechaInicial.isEqual(fechaInicialAComparar)).thenReturn(true);
		when(fechaInicial.isBefore(fechaInicialAComparar)).thenReturn(false);
		when(fechaFinal.isEqual(fechaFinalAComparar)).thenReturn(true);
		when(fechaFinal.isAfter(fechaFinalAComparar)).thenReturn(false);
		
		boolean result = rangoDeFechas.lasFechasEstanEnElRango(fechaInicialAComparar, fechaFinalAComparar);
		
		assertEquals(true,result);
	}
	
	@Test
	void testHayNoHayUnRangoCompatible() {
		boolean result = rangoDeFechas.lasFechasEstanEnElRango(fechaInicialAComparar, fechaFinalAComparar);
		
		assertEquals(false,result);
	}
	
	
	
}

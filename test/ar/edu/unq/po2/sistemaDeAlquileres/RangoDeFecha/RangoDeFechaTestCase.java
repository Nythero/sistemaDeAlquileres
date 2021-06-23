package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;



class RangoDeFechaTestCase {
	private RangoDeFechas rangoDeFechas;
	private RangoDeFechas rangoAComparar;
	@Mock private LocalDate fechaInicial;
	@Mock private LocalDate fechaFinal;
	@Mock private LocalDate fechaInicialAComparar;
	@Mock private LocalDate fechaFinalAComparar;
	
	
	@BeforeEach
	void setUp() throws Exception {
		fechaInicial= mock(LocalDate.class);
		fechaFinal= mock(LocalDate.class);
		fechaInicialAComparar= mock(LocalDate.class);
		fechaFinalAComparar= mock(LocalDate.class);
		rangoDeFechas= new RangoDeFechas(fechaInicial,fechaFinal);
		rangoAComparar= new RangoDeFechas(fechaInicialAComparar, fechaFinalAComparar);
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
}

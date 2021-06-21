package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Fijo;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;

class RangoDeFechaConPrecioDeterminadoTestCase {
	private RangoDeFechaConPrecioDeterminado rangoDeFechas;
	private LocalDate fechaInicial;
	private LocalDate fechaFinal;
	private Temporada precio;
	@Mock private Temporada precio2;
	
	@BeforeEach
	void setUp() throws Exception {
		fechaInicial= LocalDate.of(2021, 6, 18);
		fechaFinal= LocalDate.of(2021, 6, 25);
		precio= new Fijo(500f);
		precio2= mock(Temporada.class);
		rangoDeFechas= new RangoDeFechaConPrecioDeterminado(fechaInicial,fechaFinal,precio);
	}
	
	@Test
	void testCalcularMontoTotalSegunElRango() {
		float result = rangoDeFechas.getMontoTotal();
		assertEquals(4000f, result);
	}
	
	
	@Test
	void testCalcularMontoSegunElDia() {
		float result = rangoDeFechas.darPrecioSegunLaTemporada(LocalDate.now());
		assertEquals(500f, result);
	}
	
	
	@Test
	void testLaFechaInicialEstaEnElRango() {
		LocalDate fechaInicialAReservar = LocalDate.of(2021, 6, 18);
		LocalDate fechaFinalAReservar = LocalDate.of(2021, 6, 18);
		
		boolean result = rangoDeFechas.lasFechasEstanEnElRango(fechaInicialAReservar, fechaFinalAReservar);
		assertTrue(result);
	}
	
	@Test
	void testLaFechaEstaEnElRango() {
		LocalDate fechaInicialAReservar = LocalDate.of(2021, 6, 19);
		LocalDate fechaFinalAReservar = LocalDate.of(2021, 6, 23);
		
		boolean result = rangoDeFechas.lasFechasEstanEnElRango(fechaInicialAReservar, fechaFinalAReservar);
		assertTrue(result);
	}
	
	@Test
	void testLaFechaInicialNoEstaEnElRango() {
		LocalDate fechaInicialAReservar = LocalDate.of(2021, 6, 17);
		LocalDate fechaFinalAReservar = LocalDate.of(2021, 6, 23);
		
		boolean result = rangoDeFechas.lasFechasEstanEnElRango(fechaInicialAReservar, fechaFinalAReservar);
		assertFalse(result);
	}
	
	@Test
	void testLaFechaFinalNoEstaEnElRango() {
		LocalDate fechaInicialAReservar = LocalDate.of(2021, 6, 19);
		LocalDate fechaFinalAReservar = LocalDate.of(2021, 6, 27);
		
		boolean result = rangoDeFechas.lasFechasEstanEnElRango(fechaInicialAReservar, fechaFinalAReservar);
		assertFalse(result);
	}
	
	@Test
	void testPrecioMaximoEntreElRangoDeFechas() {
		float result = rangoDeFechas.precioMaximoEntreElRangoDeFechas(fechaInicial, fechaFinal);
		assertEquals(500f,result);
	}
	
	@Test
	void testDarPrecioSegunLaTemporada() {
		when(precio2.getPrecio(fechaInicial)).thenReturn(500f);
		float result = rangoDeFechas.darPrecioSegunLaTemporada(fechaInicial);
		assertEquals(500f,result);
	}
}

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
	@Mock private LocalDate fechaInicial;
	@Mock private LocalDate fechaFinal;
	private Temporada precio;
	@Mock private Temporada precio2;
	
	@BeforeEach
	void setUp() throws Exception {
		//fechaInicial= LocalDate.of(2021, 6, 18);
		//fechaFinal= LocalDate.of(2021, 6, 19);
		fechaInicial= mock(LocalDate.class);
		fechaFinal= mock(LocalDate.class);
		precio= new Fijo(500f);
		precio2= mock(Temporada.class);
		rangoDeFechas= new RangoDeFechaConPrecioDeterminado(fechaInicial,fechaFinal,precio2);
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
		RangoDeFechaConPrecioDeterminado rango = new RangoDeFechaConPrecioDeterminado(LocalDate.of(2021, 6, 18), LocalDate.of(2021, 6, 18), precio);	
		boolean result = rango.intersectanLosRangos(rangoDeFechas);
		assertTrue(result);
	}
	
	@Test
	void testLaFechaEstaEnElRango() {
		RangoDeFechaConPrecioDeterminado rango = new RangoDeFechaConPrecioDeterminado(LocalDate.of(2021, 6, 19), LocalDate.of(2021, 6, 21), precio);	
		boolean result = rango.intersectanLosRangos(rangoDeFechas);
		assertTrue(result);
	}
	
	@Test
	void testLaFechaInicialNoEstaEnElRango() {
		RangoDeFechaConPrecioDeterminado rango = new RangoDeFechaConPrecioDeterminado(LocalDate.of(2021, 6, 14), LocalDate.of(2021, 6, 16), precio);	
		boolean result = rango.intersectanLosRangos(rangoDeFechas);
		
		assertFalse(result);
	}
	
	@Test
	void testLaFechaFinalNoEstaEnElRango() {
		RangoDeFechaConPrecioDeterminado rango = new RangoDeFechaConPrecioDeterminado(LocalDate.of(2021, 6, 26), LocalDate.of(2021, 6, 27), precio);	
		boolean result = rango.intersectanLosRangos(rangoDeFechas);
		
		assertFalse(result);
	}
	
	@Test
	void testPrecioMaximoEntreElRangoDeFechas() {
		when(fechaInicial.isBefore(fechaFinal)).thenReturn(false);
		when(fechaInicial.isEqual(fechaFinal)).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
		when(fechaInicial.plusDays(1)).thenReturn(fechaInicial);
		when(precio2.getPrecio(fechaInicial)).thenReturn(500f).thenReturn(600f).thenReturn(1000f);
		float result = rangoDeFechas.precioMaximoEntreElRangoDeFechas(fechaInicial, fechaFinal);
		for(int i = 0; i<5;i++) {
			System.out.print(fechaInicial.isEqual(fechaFinal));
		}
		assertEquals(1000f,result);
		
	}
	
	@Test
	void testDarPrecioSegunLaTemporada() {
		when(precio2.getPrecio(fechaInicial)).thenReturn(500f);
		float result = rangoDeFechas.darPrecioSegunLaTemporada(fechaInicial);
		assertEquals(500f,result);
	}
}

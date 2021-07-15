package ar.edu.unq.po2.sistemaDeAlquileres.Temporada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import junit.framework.AssertionFailedError;

class TemporadaAltaTestCase {
	private TemporadaAlta temporadaAlta;
	@Mock private LocalDate fecha;
	
	@BeforeEach
	void setUp() throws Exception {
		temporadaAlta= new TemporadaAlta(200f,500f);
		fecha= mock(LocalDate.class);
	}
	
	@Test
	void testElPrecioCotidianoNoEsCorrecto() {
		assertThrows(Exception.class, ()-> new TemporadaAlta(-200f,500f));
	}
	
	@Test
	void testElPrecioEspecialNoEsCorrecto() {
		assertThrows(Exception.class, ()-> new TemporadaAlta(200f,-500f));
	}
	
	@Test
	void testGetPrecioSiendoMesDeTemporadaAlta() {
		when(fecha.getMonthValue()).thenReturn(1);
		float result= temporadaAlta.getPrecio(fecha);
		
		verify(fecha,times(2)).getMonthValue();
		assertEquals(500f, result);
	}
	@Test
	void testGetPrecioNoSiendoTemporadaAlta() {
		when(fecha.getMonthValue()).thenReturn(3);
		float result= temporadaAlta.getPrecio(fecha);
		
		verify(fecha,times(3)).getMonthValue();
		assertEquals(200, result);
	}
	

	@Test
	void testSePuedeBajarDePrecioDeTemporadaAlta() {
		temporadaAlta.bajarPrecioEspecial(100f);
		when(fecha.getMonthValue()).thenReturn(2);
		float result= temporadaAlta.getPrecio(fecha);
		
		verify(fecha,times(3)).getMonthValue();
		assertEquals(100f, result);
	}
	
	
	@Test
	void testSePuedeBajarDePrecioALaNoTemporadaAlta() {
		temporadaAlta.bajarAlPrecioCotidiano(60f);
		when(fecha.getMonthValue()).thenReturn(7);
		float result= temporadaAlta.getPrecio(fecha);
		
		verify(fecha,times(3)).getMonthValue();
		assertEquals(60f, result);
	}
	
	
	@Test
	void testNoSePuedeBajarDePrecioDeTemporadaAltaYaQueSuperaAlPrecioOriginal() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			temporadaAlta.bajarPrecioEspecial(600f);
		  });
	}
	
	@Test
	void testNoSePuedeBajarDePrecioDeTemporadaAltaYaQueEsMenorA0() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			temporadaAlta.bajarPrecioEspecial(-1f);
		  });
	}
	
	@Test
	void testNoSePuedeBajarDePrecioCotidianoYaQueSuperaAlPrecioOriginal() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			temporadaAlta.bajarAlPrecioCotidiano(600f);
		  });
	}
	
	@Test
	void testNoSePuedeBajarDePrecioCotidianoYaQueEsMenorA0() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			temporadaAlta.bajarAlPrecioCotidiano(-1f);
		  });
	}
}

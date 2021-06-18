package ar.edu.unq.po2.sistemaDeAlquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.temporada.TemporadaAlta;
import junit.framework.AssertionFailedError;

class TemporadaAltaTestCase {
	private TemporadaAlta temporadaAlta;
	@Mock private LocalDate fecha;
	
	@BeforeEach
	void setUp() throws Exception {
		temporadaAlta= new TemporadaAlta(500f,200f);
		fecha= mock(LocalDate.class);
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
		temporadaAlta.bajarPrecio(100f);
		when(fecha.getMonthValue()).thenReturn(2);
		float result= temporadaAlta.getPrecio(fecha);
		
		verify(fecha,times(3)).getMonthValue();
		assertEquals(400f, result);
	}
	
	
	@Test
	void testSePuedeBajarDePrecioALaNoTemporadaAlta() {
		temporadaAlta.bajarPrecio(60);
		when(fecha.getMonthValue()).thenReturn(7);
		float result= temporadaAlta.getPrecio(fecha);
		
		verify(fecha,times(3)).getMonthValue();
		assertEquals(140f, result);
	}
	
	
	@Test
	void testNoSePuedeBajarDePrecioYaQueSuperaAlPrecioOriginal() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			temporadaAlta.bajarPrecio(600f);
		  });
	}
	
	@Test
	void testNoSePuedeBajarDePrecioYaQueEsMenorA0() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			temporadaAlta.bajarPrecio(-1f);
		  });
	}
	
}

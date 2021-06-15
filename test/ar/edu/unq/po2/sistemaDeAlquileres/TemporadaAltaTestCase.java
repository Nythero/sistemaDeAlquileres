package ar.edu.unq.po2.sistemaDeAlquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.TemporadaAlta;
import junit.framework.AssertionFailedError;

class TemporadaAltaTestCase {
	private TemporadaAlta temporadaAlta;
	@Mock private Date dia;
	
	@BeforeEach
	void setUp() throws Exception {
		temporadaAlta= new TemporadaAlta(500f,200f);
		dia= mock(Date.class);
	}
	
	
	@Test
	void testGetPrecioSiendoMesDeTemporadaAlta() {
		when(dia.getMonth()).thenReturn(1);
		float result= temporadaAlta.getPrecio(dia);
		
		verify(dia,times(2)).getMonth();
		assertEquals(500f, result);
	}
	
	@Test
	void testGetPrecioNoSiendoTemporadaAlta() {
		when(dia.getMonth()).thenReturn(3);
		float result= temporadaAlta.getPrecio(dia);
		
		verify(dia,times(3)).getMonth();
		assertEquals(200, result);
	}
	

	@Test
	void testSePuedeBajarDePrecioDeTemporadaAlta() {
		temporadaAlta.bajarPrecio(100f);
		when(dia.getMonth()).thenReturn(2);
		float result= temporadaAlta.getPrecio(dia);
		
		assertEquals(400f, result);
	}
	
	
	@Test
	void testSePuedeBajarDePrecioALaNoTemporadaAlta() {
		temporadaAlta.bajarPrecio(60);
		when(dia.getMonth()).thenReturn(7);
		float result= temporadaAlta.getPrecio(dia);
		
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

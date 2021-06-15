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

import ar.edu.unq.po2.sistemaDeAlquileres.FeriadosInamovibles;
import junit.framework.AssertionFailedError;


class FeriadosInamoviblesTestCase {
	private FeriadosInamovibles feriadosInamovibles;
	@Mock private Date dia;
	
	@BeforeEach
	void setUp() throws Exception {
		feriadosInamovibles= new FeriadosInamovibles(500f,200f);
		dia= mock(Date.class);
	}
	

	@Test
	void testGetPrecioSiendoFeriado() {
		when(dia.getMonth()).thenReturn(12);
		when(dia.getDay()).thenReturn(8);
		float result= feriadosInamovibles.getPrecio(dia);
		
		verify(dia).getMonth();
		verify(dia).getDay();
		assertEquals(500f, result);
	}
	
	@Test
	void testGetPrecioNoSiendoFeriado() {
		when(dia.getMonth()).thenReturn(12);
		when(dia.getDay()).thenReturn(14);
		float result= feriadosInamovibles.getPrecio(dia);
		
		verify(dia).getMonth();
		verify(dia).getDay();
		assertEquals(200f, result);
	}
	
	@Test
	void testSePuedeBajarDePrecioDeFeriado() {
		feriadosInamovibles.bajarPrecio(100f);
		when(dia.getMonth()).thenReturn(5);
		when(dia.getDay()).thenReturn(25);
		float result= feriadosInamovibles.getPrecio(dia);
		
		verify(dia).getMonth();
		verify(dia).getDay();
		assertEquals(400f, result);
	}
	
	@Test
	void testSePuedeBajarDePrecioDeDiaCotidiano() {
		feriadosInamovibles.bajarPrecio(100f);
		when(dia.getMonth()).thenReturn(5);
		when(dia.getDay()).thenReturn(28);
		float result= feriadosInamovibles.getPrecio(dia);
		
		verify(dia).getMonth();
		verify(dia).getDay();
		assertEquals(100f, result);
	}

	
	
	@Test
	void testNoSePuedeBajarDePrecioYaQueSuperaAlPrecioOriginal() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			feriadosInamovibles.bajarPrecio(600f);
		  });
	}
	
	@Test
	void testNoSePuedeBajarDePrecioYaQueEsMenorA0() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			feriadosInamovibles.bajarPrecio(-1f);
		  });
	}
}

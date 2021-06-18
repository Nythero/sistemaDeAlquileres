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

import ar.edu.unq.po2.sistemaDeAlquileres.temporada.FeriadosInamovibles;
import junit.framework.AssertionFailedError;


class FeriadosInamoviblesTestCase {
	private FeriadosInamovibles feriadosInamovibles;
	@Mock private LocalDate fecha;
	
	@BeforeEach
	void setUp() throws Exception {
		feriadosInamovibles= new FeriadosInamovibles(500f,200f);
		fecha= mock(LocalDate.class);
	}
	

	@Test
	void testGetPrecioSiendoFeriado() {
		when(fecha.getMonthValue()).thenReturn(12);
		when(fecha.getDayOfMonth()).thenReturn(8);
		float result= feriadosInamovibles.getPrecio(fecha);
		
		verify(fecha).getMonthValue();
		verify(fecha).getDayOfMonth();
		assertEquals(500f, result);
	}
	
	@Test
	void testGetPrecioNoSiendoFeriado() {
		when(fecha.getMonthValue()).thenReturn(12);
		when(fecha.getDayOfMonth()).thenReturn(14);
		float result= feriadosInamovibles.getPrecio(fecha);
		
		verify(fecha).getMonthValue();
		verify(fecha).getDayOfMonth();
		assertEquals(200f, result);
	}
	
	@Test
	void testSePuedeBajarDePrecioDeFeriado() {
		feriadosInamovibles.bajarPrecio(100f);
		when(fecha.getMonthValue()).thenReturn(5);
		when(fecha.getDayOfMonth()).thenReturn(25);
		float result= feriadosInamovibles.getPrecio(fecha);
		
		verify(fecha).getMonthValue();
		verify(fecha).getDayOfMonth();
		assertEquals(400f, result);
	}
	
	@Test
	void testSePuedeBajarDePrecioDefechaCotifechano() {
		feriadosInamovibles.bajarPrecio(100f);
		when(fecha.getMonthValue()).thenReturn(5);
		when(fecha.getDayOfMonth()).thenReturn(28);
		float result= feriadosInamovibles.getPrecio(fecha);
		
		verify(fecha).getMonthValue();
		verify(fecha).getDayOfMonth();
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

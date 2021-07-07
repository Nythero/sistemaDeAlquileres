package ar.edu.unq.po2.sistemaDeAlquileres.Temporada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import junit.framework.AssertionFailedError;

class FeriadosInamoviblesTestCase {
	private FeriadosInamovibles feriadosInamovibles;
	@Mock private LocalDate fecha;
	
	@BeforeEach
	void setUp() throws Exception {
		feriadosInamovibles= new FeriadosInamovibles(200f,500f);
		fecha= mock(LocalDate.class);
	}
	
	@Test
	void testElPrecioCotidianoNoEsCorrecto() {
		assertThrows(Exception.class, ()-> new FeriadosInamovibles(-200f,500f));
	}
	
	@Test
	void testElPrecioEspecialNoEsCorrecto() {
		assertThrows(Exception.class, ()-> new FeriadosInamovibles(200f,-500f));
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
		feriadosInamovibles.bajarPrecioEspecial(100f);
		when(fecha.getMonthValue()).thenReturn(5);
		when(fecha.getDayOfMonth()).thenReturn(25);
		float result= feriadosInamovibles.getPrecio(fecha);
		
		verify(fecha).getMonthValue();
		verify(fecha).getDayOfMonth();
		assertEquals(100f, result);
	}
	
	@Test
	void testSePuedeBajarDePrecioDefechaNoFeriado() {
		feriadosInamovibles.bajarAlPrecioCotidiano(60f);
		when(fecha.getMonthValue()).thenReturn(5);
		when(fecha.getDayOfMonth()).thenReturn(28);
		float result= feriadosInamovibles.getPrecio(fecha);
		
		verify(fecha).getMonthValue();
		verify(fecha).getDayOfMonth();
		assertEquals(60f, result);
	}

	
	
	@Test
	void testNoSePuedeBajarDePrecioDeFeriadoYaQueSuperaAlPrecioOriginal() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			feriadosInamovibles.bajarPrecioEspecial(600f);
		  });
	}
	
	@Test
	void testNoSePuedeBajarDePrecioDeFeriadoYaQueEsMenorA0() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			feriadosInamovibles.bajarPrecioEspecial(-1f);
		  });
	}
	
	@Test
	void testNoSePuedeBajarDePrecioNoFeriadoYaQueSuperaAlPrecioOriginal() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			feriadosInamovibles.bajarPrecioEspecial(600f);
		  });
	}
	
	@Test
	void testNoSePuedeBajarDePrecioNoFeriadoYaQueEsMenorA0() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			feriadosInamovibles.bajarPrecioEspecial(-1f);
		  });
	}
}

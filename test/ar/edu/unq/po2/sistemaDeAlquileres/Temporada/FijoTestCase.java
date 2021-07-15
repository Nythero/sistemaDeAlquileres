package ar.edu.unq.po2.sistemaDeAlquileres.Temporada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import junit.framework.AssertionFailedError;

class FijoTestCase {
	private Fijo fijo;
	@Mock private LocalDate fecha;
	
	@BeforeEach
	void setUp() throws Exception {
		fijo= new Fijo(500f);
		fecha= mock(LocalDate.class);
	}
	
	@Test
	void testElPrecioCotidianoNoEsCorrecto() {
		assertThrows(Exception.class, ()-> new Fijo(-200f));
	}
	
	@Test
	void testGetPrecioSiendoDiaDeSemana() {
		float result= fijo.getPrecio(fecha);
		
		assertEquals(500f, result);
	}
	
	@Test
	void testGetPrecioSiendoFinDeSemana() {
		float result= fijo.getPrecio(fecha);
		
		assertEquals(500f, result);
	}
	@Test
	void testSePuedeBajarDePrecio() {
		fijo.bajarAlPrecioCotidiano(200f);
		float result= fijo.getPrecio(fecha);
		
		assertEquals(200f, result);
	}
	
	@Test
	void testSePuedeBajarDeEspecial() {
		fijo.bajarPrecioEspecial(200f);
		float result= fijo.getPrecio(fecha);
		
		assertEquals(500f, result);
	}
	
	@Test
	void testNoSePuedeBajarDePrecioYaQueSuperaAlPrecioOriginal() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			fijo.bajarAlPrecioCotidiano(600f);
		  });
	}
	
	@Test
	void testNoSePuedeBajarDePrecioYaQueEsMenorA0() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			fijo.bajarAlPrecioCotidiano(-1f);
		  });
	}
	
	@Test
	void testNoSePuedeBajarDePrecioEspecialYaQueEsMenorA0() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			fijo.bajarAlPrecioCotidiano(-1f);
		  });
	}
}

package ar.edu.unq.po2.sistemaDeAlquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.temporada.Fijo;
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
		fijo.bajarPrecio(200f);
		float result= fijo.getPrecio(fecha);
		
		assertEquals(300f, result);
	}
	
	@Test
	void testNoSePuedeBajarDePrecioYaQueSuperaAlPrecioOriginal() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			fijo.bajarPrecio(-1f);
		  });
	}
	
	@Test
	void testNoSePuedeBajarDePrecioYaQueEsMenorA0() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			fijo.bajarPrecio(-1f);
		  });
	}
	
}

package ar.edu.unq.po2.sistemaDeAlquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.util.Calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Fijo;
import junit.framework.AssertionFailedError;

class FijoTestCase {
	private Fijo fijo;
	@Mock private Date dia;
	
	@BeforeEach
	void setUp() throws Exception {
		fijo= new Fijo(500f);
		dia= mock(Date.class);
	}

	@Test
	void testGetPrecioSiendoDiaDeSemana() {
		float result= fijo.getPrecio(dia);
		
		assertEquals(500f, result);
	}
	
	@Test
	void testGetPrecioSiendoFinDeSemana() {
		float result= fijo.getPrecio(dia);
		
		assertEquals(500f, result);
	}
	
	@Test
	void testSePuedeBajarDePrecio() {
		fijo.bajarPrecio(200f);
		float result= fijo.getPrecio(dia);
		
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

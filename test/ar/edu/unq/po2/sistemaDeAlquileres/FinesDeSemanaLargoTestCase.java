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

import ar.edu.unq.po2.sistemaDeAlquileres.FinesDeSemanaLargo;
import junit.framework.AssertionFailedError;

class FinesDeSemanaLargoTestCase {
	private FinesDeSemanaLargo findeLargo;
	@Mock private Date dia;
	
	@BeforeEach
	void setUp() throws Exception {
		findeLargo= new FinesDeSemanaLargo(500f,250f);
		dia= mock(Date.class);
	}

	@Test
	void testGetPrecioSiendoDiaDeSemana() {
		when(dia.getDay()).thenReturn(3);
		float result= findeLargo.getPrecio(dia);
		
		verify(dia,times(3)).getDay();
		assertEquals(500f, result);
	}
	
	@Test
	void testGetPrecioSiendoFinDeSemanaLargo() {
		when(dia.getDay()).thenReturn(1);
		float result= findeLargo.getPrecio(dia);
		
		verify(dia,times(1)).getDay();
		assertEquals(250f, result);
	}

	@Test
	void testSePuedeBajarDePrecioDiaDeSemana() {
		findeLargo.bajarPrecio(200f);
		when(dia.getDay()).thenReturn(5);
		float result= findeLargo.getPrecio(dia);
		
		assertEquals(300f, result);
	}
	
	
	@Test
	void testSePuedeBajarDePrecioFinDeSemanalaLargo() {
		findeLargo.bajarPrecio(200f);
		when(dia.getDay()).thenReturn(2);
		float result= findeLargo.getPrecio(dia);
		
		assertEquals(50f, result);
	}
	
	
	@Test
	void testNoSePuedeBajarDePrecioYaQueSuperaAlPrecioOriginal() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {findeLargo.bajarPrecio(600f);});
	}
	
	@Test
	void testNoSePuedeBajarDePrecioYaQueEsMenorA0() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {findeLargo.bajarPrecio(-1f);});
	}
	
}

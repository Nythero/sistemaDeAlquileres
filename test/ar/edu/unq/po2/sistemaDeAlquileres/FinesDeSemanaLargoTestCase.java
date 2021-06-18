package ar.edu.unq.po2.sistemaDeAlquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.temporada.FinesDeSemanaLargo;
import junit.framework.AssertionFailedError;

class FinesDeSemanaLargoTestCase {
	private FinesDeSemanaLargo findeLargo;
	@Mock private LocalDate fecha;
	
	@BeforeEach
	void setUp() throws Exception {
		findeLargo= new FinesDeSemanaLargo(500f,250f);
		fecha= mock(LocalDate.class);
	}

	@Test
	void testGetPrecioSiendoDiaDeSemana() {
		when(fecha.getDayOfWeek()).thenReturn(DayOfWeek.TUESDAY);
		float result= findeLargo.getPrecio(fecha);
		
		verify(fecha,times(3)).getDayOfWeek();
		assertEquals(500f, result);
	}
	
	@Test
	void testGetPrecioSiendoFinDeSemanaLargo() {
		when(fecha.getDayOfWeek()).thenReturn(DayOfWeek.SATURDAY);
		float result= findeLargo.getPrecio(fecha);
		
		verify(fecha,times(2)).getDayOfWeek();
		assertEquals(250f, result);
	}

	@Test
	void testSePuedeBajarDePrecioDiaDeSemana() {
		findeLargo.bajarPrecio(200f);
		when(fecha.getDayOfWeek()).thenReturn(DayOfWeek.WEDNESDAY);
		float result= findeLargo.getPrecio(fecha);
		
		verify(fecha,times(3)).getDayOfWeek();
		assertEquals(300f, result);
	}
	
	
	@Test
	void testSePuedeBajarDePrecioFinDeSemanalaLargo() {
		findeLargo.bajarPrecio(200f);
		when(fecha.getDayOfWeek()).thenReturn(DayOfWeek.SUNDAY);
		float result= findeLargo.getPrecio(fecha);
		
		verify(fecha,times(3)).getDayOfWeek();
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

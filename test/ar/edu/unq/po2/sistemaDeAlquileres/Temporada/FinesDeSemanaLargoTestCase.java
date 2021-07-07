package ar.edu.unq.po2.sistemaDeAlquileres.Temporada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
	void testElPrecioCotidianoNoEsCorrecto() {
		assertThrows(Exception.class, ()-> new FinesDeSemanaLargo(-200f,500f));
	}
	
	@Test
	void testElPrecioEspecialNoEsCorrecto() {
		assertThrows(Exception.class, ()-> new FinesDeSemanaLargo(200f,-500f));
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
		findeLargo.bajarAlPrecioCotidiano(200f);
		when(fecha.getDayOfWeek()).thenReturn(DayOfWeek.WEDNESDAY);
		float result= findeLargo.getPrecio(fecha);
		
		verify(fecha,times(3)).getDayOfWeek();
		assertEquals(200f, result);
	}
	
	
	@Test
	void testSePuedeBajarDePrecioFinDeSemanalaLargo() {
		findeLargo.bajarPrecioEspecial(200f);
		when(fecha.getDayOfWeek()).thenReturn(DayOfWeek.SUNDAY);
		float result= findeLargo.getPrecio(fecha);
		
		verify(fecha,times(3)).getDayOfWeek();
		assertEquals(200f, result);
	}
	
	
	@Test
	void testNoSePuedeBajarDePrecioAFinDeSemanaLargoYaQueSuperaAlPrecioOriginal() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {findeLargo.bajarAlPrecioCotidiano(600f);});
	}
	
	@Test
	void testNoSePuedeBajarDePrecioADiaDeSemanaoYaQueSuperaAlPrecioOriginal() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {findeLargo.bajarAlPrecioCotidiano(600f);});
	}
	
	@Test
	void testNoSePuedeBajarDePrecioDiaDeSemanaYaQueEsMenorA0() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {findeLargo.bajarAlPrecioCotidiano(-1f);});
	}
	
	@Test
	void testNoSePuedeBajarDePrecioDeFinDeSemanaLargoYaQueEsMenorA0() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {findeLargo.bajarPrecioEspecial(-1f);});
	}
	
}

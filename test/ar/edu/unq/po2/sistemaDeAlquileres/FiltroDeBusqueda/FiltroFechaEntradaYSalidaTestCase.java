package ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

class FiltroFechaEntradaYSalidaTestCase {
	private IFiltroDeBusqueda filtro;
	@Mock private LocalDate fechaEntrada;
	@Mock private LocalDate fechaSalida;
	@Mock Inmueble inmueble;
	
	@BeforeEach
	void setUp() throws Exception {
		inmueble= mock(Inmueble.class);
		fechaEntrada= mock(LocalDate.class);
		fechaSalida= mock(LocalDate.class);
	}

	@Test
	void testErrorAlDarUnaEntradaNull() throws Exception {
		assertThrows(Exception.class, () -> new FiltroFechaEntradaYSalida(null,fechaSalida));
	}
	
	@Test
	void testErrorAlDarUnaSalidaNull() throws Exception {
		assertThrows(Exception.class, () -> new FiltroFechaEntradaYSalida(fechaEntrada,null));
	}
	
	@Test
	void testConstructor() throws Exception {
		assertDoesNotThrow(()-> new FiltroFechaEntradaYSalida(fechaEntrada,fechaSalida));
	}
	
	
	@Test
	void testCumpleConElFiltro() throws Exception {
		filtro= new FiltroFechaEntradaYSalida(fechaEntrada,fechaSalida);
		when(inmueble.hayAlgunRangoDeFechasQuePoseaLasFecha(fechaEntrada,fechaSalida)).thenReturn(true);
		boolean result = filtro.cumpleConElFiltro(inmueble);
		
		assertTrue(result);
	}
	
	@Test
	void testNoCumpleConElFiltro() throws Exception {
		filtro= new FiltroFechaEntradaYSalida(fechaEntrada,fechaSalida);
		when(inmueble.hayAlgunRangoDeFechasQuePoseaLasFecha(fechaEntrada,fechaSalida)).thenReturn(false);
		boolean result = filtro.cumpleConElFiltro(inmueble);
		
		assertFalse(result);
	}
	

	@Test
	void testNoSePuedenAgregarFiltros() throws Exception {
		filtro= new FiltroFechaEntradaYSalida(fechaEntrada,fechaSalida);
		assertThrows(Exception.class, () -> filtro.agregarFiltro(filtro));
	}
	
	@Test
	void testNoSePuedenRemoverFiltros() throws Exception {
		filtro= new FiltroFechaEntradaYSalida(fechaEntrada,fechaSalida);
		assertThrows(Exception.class, () -> filtro.removerFiltro(filtro));
	}
}

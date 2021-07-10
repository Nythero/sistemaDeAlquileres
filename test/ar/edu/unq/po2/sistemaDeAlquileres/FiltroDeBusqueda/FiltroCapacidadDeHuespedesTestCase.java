package ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

class FiltroCapacidadDeHuespedesTestCase {
	private IFiltroDeBusqueda filtro;
	@Mock Inmueble inmueble;
	
	@BeforeEach
	void setUp() throws Exception {
		inmueble= mock(Inmueble.class);
	}
	
	@Test
	void testConstructor() throws Exception {
		assertDoesNotThrow(()-> new FiltroCapacidadDeHuespedes(5));
	}
	
	
	@Test
	void testCumpleConElFiltro() throws Exception {
		filtro= new FiltroCapacidadDeHuespedes(5);
		when(inmueble.getCapacidad()).thenReturn(5);
		boolean result = filtro.cumpleConElFiltro(inmueble);
		
		assertTrue(result);
	}
	
	@Test
	void testCumpleConElFiltroPorCapacidadNull() throws Exception {
		filtro= new FiltroCapacidadDeHuespedes(null);
		when(inmueble.getCapacidad()).thenReturn(5);
		boolean result = filtro.cumpleConElFiltro(inmueble);
		
		assertTrue(result);
	}
	
	@Test
	void testNoCumpleConElFiltroPorCapacidadIncorrecta() throws Exception {
		filtro= new FiltroCapacidadDeHuespedes(5);
		when(inmueble.getCapacidad()).thenReturn(3);
		boolean result = filtro.cumpleConElFiltro(inmueble);
		
		assertFalse(result);
	}
	/*
	@Test
	void testNoSePuedenAgregarFiltros() throws Exception {
		filtro= new FiltroCapacidadDeHuespedes(3);
		assertThrows(Exception.class, () -> filtro.agregarFiltro(filtro));
	}
	
	@Test
	void testNoSePuedenRemoverFiltros() throws Exception {
		filtro= new FiltroCapacidadDeHuespedes(3);
		assertThrows(Exception.class, () -> filtro.removerFiltro(filtro));
	}*/
}


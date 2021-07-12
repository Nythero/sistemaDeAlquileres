package ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

class FiltroCiudadTestCase {
	private IFiltroDeBusqueda filtro;
	@Mock Inmueble inmueble;
	
	@BeforeEach
	void setUp() throws Exception {
		inmueble= mock(Inmueble.class);
	}

	@Test
	void testErrorAlDarNull() throws Exception {
		assertThrows(Exception.class, () -> new FiltroCiudad(null));
	}
	
	@Test
	void testConstructor() throws Exception {
		assertDoesNotThrow(()-> new FiltroCiudad("Quilmes"));
	}
	
	
	@Test
	void testCumpleConElFiltro() throws Exception {
		filtro= new FiltroCiudad("Quilmes");
		when(inmueble.getCiudad()).thenReturn("Quilmes");
		boolean result = filtro.cumpleConElFiltro(inmueble);
		
		assertTrue(result);
	}
	
	@Test
	void testNoCumpleConElFiltro() throws Exception {
		filtro= new FiltroCiudad("Quilmes");
		when(inmueble.getCiudad()).thenReturn("Lanus");
		boolean result = filtro.cumpleConElFiltro(inmueble);
		
		assertFalse(result);
	}

	
	@Test
	void testNoSePuedenAgregarFiltros() throws Exception {
		filtro= new FiltroCiudad("Quilmes");
		assertThrows(Exception.class, () -> filtro.agregarFiltro(filtro));
	}
	
	@Test
	void testNoSePuedenRemoverFiltros() throws Exception {
		filtro= new FiltroCiudad("Quilmes");
		assertThrows(Exception.class, () -> filtro.removerFiltro(filtro));
	}
}

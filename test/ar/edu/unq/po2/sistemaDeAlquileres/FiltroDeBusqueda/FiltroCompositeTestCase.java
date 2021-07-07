package ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

class FiltroCompositeTestCase {
	@Mock private IFiltroDeBusqueda filtroCiudad;
	private FiltroComposite filtroComposite;
	@Mock private IFiltroDeBusqueda filtroCapacidadDeHuespedes;
	@Mock private Inmueble inmueble;

	@BeforeEach
	void setUp() throws Exception {
		filtroComposite= new FiltroComposite();
		inmueble= mock(Inmueble.class);
		filtroCiudad= mock(IFiltroDeBusqueda.class);
		filtroCapacidadDeHuespedes= mock(IFiltroDeBusqueda.class);
	}

	@Test
	void testAddFiltros() {
		filtroComposite.agregarFiltro(filtroCiudad);
		filtroComposite.agregarFiltro(filtroCapacidadDeHuespedes);
		
		assertEquals(2, filtroComposite.getFiltros().size());
	}
	
	@Test
	void testRemoveFiltros() {
		filtroComposite.agregarFiltro(filtroCiudad);
		filtroComposite.agregarFiltro(filtroCapacidadDeHuespedes);
		filtroComposite.removerFiltro(filtroCiudad);
		
		assertEquals(1, filtroComposite.getFiltros().size());
	}
	
	@Test
	void testCumpleElFiltro() {
		filtroComposite.agregarFiltro(filtroCiudad);
		filtroComposite.agregarFiltro(filtroCapacidadDeHuespedes);
		filtroComposite.removerFiltro(filtroCiudad);
		when(filtroCiudad.cumpleConElFiltro(inmueble)).thenReturn(true);
		when(filtroCapacidadDeHuespedes.cumpleConElFiltro(inmueble)).thenReturn(true);
		boolean result = filtroComposite.cumpleConElFiltro(inmueble);
		
		assertTrue(result);
	}
	
	@Test
	void testNoCumpleElFiltro() {
		filtroComposite.agregarFiltro(filtroCiudad);
		filtroComposite.agregarFiltro(filtroCapacidadDeHuespedes);
		filtroComposite.removerFiltro(filtroCiudad);
		when(filtroCiudad.cumpleConElFiltro(inmueble)).thenReturn(true);
		when(filtroCapacidadDeHuespedes.cumpleConElFiltro(inmueble)).thenReturn(false);
		boolean result = filtroComposite.cumpleConElFiltro(inmueble);
		
		assertFalse(result);
	}
	
	

}

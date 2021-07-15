package ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

class FiltroPrecioMinimoYMaximoTestCase {
	private IFiltroDeBusqueda filtro;
	@Mock Inmueble inmueble;
	@Mock private LocalDate fechaEntrada;
	@Mock private LocalDate fechaSalida;
	
	
	@BeforeEach
	void setUp() throws Exception {
		inmueble= mock(Inmueble.class);
		fechaEntrada= mock(LocalDate.class);
		fechaSalida= mock(LocalDate.class);
	}
	
	@Test
	void testCumpleConElFiltro() throws Exception {
		filtro= new FiltroPrecioMinimoYMaximo(200f,500f,fechaEntrada,fechaSalida);
		when(inmueble.precioMaximoDelRangoDeFechasEntre(fechaEntrada,fechaSalida)).thenReturn(300f).thenReturn(300f);
		boolean result = filtro.cumpleConElFiltro(inmueble);
		
		assertTrue(result);
	}
	
	@Test
	void testCumpleConElFiltroDadoUnPrecioMinimoNull() throws Exception {
		filtro= new FiltroPrecioMinimoYMaximo(null,500f,fechaEntrada,fechaSalida);
		when(inmueble.precioMaximoDelRangoDeFechasEntre(fechaEntrada,fechaSalida)).thenReturn(300f).thenReturn(300f);
		boolean result = filtro.cumpleConElFiltro(inmueble);
		
		assertTrue(result);
	}
	
	@Test
	void testCumpleConElFiltroDadoUnPrecioMaximoNull() throws Exception {
		filtro= new FiltroPrecioMinimoYMaximo(200f,null,fechaEntrada,fechaSalida);
		when(inmueble.precioMaximoDelRangoDeFechasEntre(fechaEntrada,fechaSalida)).thenReturn(300f).thenReturn(300f);
		boolean result = filtro.cumpleConElFiltro(inmueble);
		
		assertTrue(result);
	}
	
	@Test
	void testNoCumpleConElFiltro() throws Exception {
		filtro= new FiltroPrecioMinimoYMaximo(200f,500f,fechaEntrada,fechaSalida);
		when(inmueble.precioMaximoDelRangoDeFechasEntre(fechaEntrada,fechaSalida)).thenReturn(100f).thenReturn(100f);
		boolean result = filtro.cumpleConElFiltro(inmueble);
		
		assertFalse(result);
	}
	
	
	@Test
	void testNoSePuedenAgregarFiltros() throws Exception {
		filtro= new FiltroPrecioMinimoYMaximo(200f,500f,fechaEntrada,fechaSalida);
		assertThrows(Exception.class, () -> filtro.agregarFiltro(filtro));
	}
	
	@Test
	void testNoSePuedenRemoverFiltros() throws Exception {
		filtro= new FiltroPrecioMinimoYMaximo(200f,500f,fechaEntrada,fechaSalida);
		assertThrows(Exception.class, () -> filtro.removerFiltro(filtro));
	}
}
package ar.edu.unq.po2.sistemaDeAlquileres.Administrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Sitio.Sitio;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

public class AdministradorTestCase {
	private Administrador administrador;
	private Sitio sitio;
	private Inmueble inmueble;
	private Inmueble inmueble2;
	private Usuario usuario1;
	private Usuario usuario2;
	private MockedStatic<Inmueble> inmueble1; 
	
	@BeforeEach
	void setUp() throws Exception {
		administrador = new Administrador();
		sitio = mock(Sitio.class);
		inmueble = mock(Inmueble.class);
		inmueble = mock(Inmueble.class);
		usuario1 = mock (Usuario.class);
		usuario2 = mock (Usuario.class);
	}

	@Test
	void testAddCategoriaAInquilinos() throws Exception {
		administrador.addCategoriaAInquilinos(sitio,"Amabilidad");
		verify(sitio).addCategoriaAInquilino("Amabilidad");
		
	}
	
	@Test
	void testAddCategoriaADuenho() throws Exception {
		administrador.addCategoriaADuenho(sitio,"Puntualidad");
		verify(sitio).addCategoriaADuenho("Puntualidad");
	}
	
	@Test
	void testAddCategoriaAInmueble() throws Exception {
		administrador.addCategoriaAInmueble(sitio,"Seguridad");
		verify(sitio).addCategoriaAInmueble("Seguridad");
	}
	
//	@Test
//	void testDarDeAltaAlTipoDeInmueble() {
//		administrador.addTipoDeInmueble("Choza");
//		verify(inmueble).darDeAltaAlTipoDeInmueble("Choza");	
//	}
//	
//	
//	@Test
//	void testVoidaddAltaServicio() {
//		inmueble1 = mockStatic(Inmueble.class);
//		administrador.addAltaServicio("Gas");
//		inmueble1.verify(()->Inmueble.darDeAltaElServicio("Gas"));	
//	}
	
//	@Test
//	void testVoidaddAltaServicio() {
//		try {
//			inmueble1 = mockStatic(Inmueble.class);
//			administrador.addAltaServicio("Gas");
//			inmueble1.verify(()->Inmueble.darDeAltaElServicio("Gas"));
//		}
//		finally {}
//	}
//	
	@Test
	void testGetTopTen() {
		ArrayList<Usuario> usuariosTopTen = new ArrayList<Usuario>();
		usuariosTopTen.add(usuario1);
		usuariosTopTen.add(usuario2);
		when(sitio.obtenerElTopTenDeInquilinos()).thenReturn(usuariosTopTen);
		assertEquals(usuariosTopTen,administrador.getTopTen(sitio));
		verify(sitio).obtenerElTopTenDeInquilinos();
	}
	
	@Test
	void testGetCantidadDeInmueblesLibres() {
		when(sitio.getCantidadDeInmueblesLibres()).thenReturn(2);
		assertEquals(2,administrador.getCantidadDeInmueblesLibres(sitio));
		verify(sitio).getCantidadDeInmueblesLibres();
	}
	
	@Test
	void testGetTasaOcupacional() {
		when(sitio.getTasaOcupacional()).thenReturn(10);
		assertEquals(10,administrador.getTasaOcupacional(sitio));
		verify(sitio).getTasaOcupacional(); 
	}
}

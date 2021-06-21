package ar.edu.unq.po2.sistemaDeAlquileres.Administrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.Sitio.Sitio;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

public class AdministradorTestCase {
	private Administrador administrador;
	private Sitio sitio;
	private Inmueble inmueble;
	private Usuario usuario1;
	private Usuario usuario2;
	private MockedStatic<Inmueble> inmueble1;
	
	@BeforeEach
	void setUp() throws Exception {
		administrador = new Administrador();
		sitio = mock(Sitio.class);
		inmueble = mock(Inmueble.class);
		usuario1 = mock (Usuario.class);
		usuario2 = mock (Usuario.class);
	}

	@Test
	void testAddCategoriaAInquilinos() {
		administrador.addCategoriaAInquilinos(sitio,"Amabilidad");
		verify(sitio).addCategoriaAInquilino("Amabilidad");
		
	}
	
	@Test
	void testAddCategoriaADuenho() {
		administrador.addCategoriaADuenho(sitio,"Puntualidad");
		verify(sitio).addCategoriaADuenho("Puntualidad");
	}
	
	@Test
	void testAddCategoriaAInmueble() {
		administrador.addCategoriaAInmueble(sitio,"Seguridad");
		verify(sitio).addCategoriaAInmueble("Seguridad");
	}
	
	@Test
	void testDarDeAltaAlTipoDeInmueble() {
		administrador.addTipoDeInmueble("Choza");
		verify(inmueble).darDeAltaAlTipoDeInmueble("Choza");	
	}
	
	
	@Test
	void testVoidaddAltaServicio() {
		inmueble1 = mockStatic(Inmueble.class);
		administrador.addAltaServicio("Gas");
		inmueble1.verify(()->Inmueble.darDeAltaElServicio("Gas"));	
	}
	
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
//	@Test
//	void testGetTopTen() {
//		ArrayList<Usuario> usuariosTopTen = new ArrayList<Usuario>();
//		usuariosTopTen.add(usuario1);
//		usuariosTopTen.add(usuario2);
//		verify(sitio).obtenerElTopTenDeInquilinos();
//		when(sitio.obtenerElTopTenDeInquilinos()).thenReturn(usuariosTopTen);
//		assertEquals(usuariosTopTen,administrador.getTopTen(sitio));
//	}
	


}

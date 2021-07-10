package ar.edu.unq.po2.sistemaDeAlquileres.Sitio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;

import ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda.IFiltroDeBusqueda;
import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.Ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;


class SitioTestCase {

	private Sitio sitio;
	private Inmueble inmueble1;
	private Inmueble inmueble2;
	private Usuario usuario1;
	private Usuario usuario2;
	private Usuario usuario3;
	private Ranking ranking1;
	private Ranking ranking2;
	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva3;
	private Reserva reserva4;
	private Reserva reserva5;
	@Mock private RangoDeFechas rango;
	private Reserva reserva6;
	@Mock private IFiltroDeBusqueda filtroComposite;
	@Mock private IFiltroDeBusqueda filtroHoja;
	
	@BeforeEach
	void setUp() throws Exception {
		filtroComposite = mock(IFiltroDeBusqueda.class);
		filtroHoja = mock(IFiltroDeBusqueda.class);
		sitio = new Sitio();
		inmueble1 = mock(Inmueble.class);
		inmueble2 = mock(Inmueble.class);
		usuario1 = mock (Usuario.class);
		usuario2 = mock (Usuario.class);
		usuario3 = mock (Usuario.class);
		ranking1 = mock (Ranking.class);
		ranking2 = mock (Ranking.class);
		reserva1 = mock (Reserva.class);
		reserva2 = mock (Reserva.class);
		reserva3 = mock (Reserva.class);
		reserva4 = mock (Reserva.class);
		reserva5 = mock (Reserva.class);
		reserva6 = mock (Reserva.class);
		rango= mock(RangoDeFechas.class);
	}

	
	@Test
	void testRegistrarUsuario() throws Exception {
		sitio.registrarUsuario(usuario1);
		assertTrue(sitio.esUsuario(usuario1));
	}
	
	@Test
	void testRegistrarUsuarioLanzaUnaExcepcion() throws Exception {
		sitio.registrarUsuario(usuario1);
		assertThrows(Exception.class, () -> sitio.registrarUsuario(usuario1));
	}
	
	@Test
	void testAgregarInmuebleLanzaUnaExcepcionYaQueElInmuebleEsInvalido() {
		Assertions.assertThrows(Exception.class, () -> {
			sitio.agregarInmueble(inmueble1);
		});
	}
	
	@Test
	void testAgregarInmueble() {
		sitio.agregarTipoDeInmuebleValido("choza");
		sitio.agregarServicioValido("gas");
		when(inmueble1.getTipoDeInmueble()).thenReturn("choza");
		//when(inmueble1.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		
		assertDoesNotThrow(() -> sitio.agregarInmueble(inmueble1));
	} 
	
	@Test
	void testAddCategoriaAInquilino() throws Exception {
		sitio.registrarUsuario(usuario1);
		sitio.registrarUsuario(usuario2);
		when(usuario1.getRankingComoInquilino()).thenReturn(ranking1);
		when(usuario2.getRankingComoInquilino()).thenReturn(ranking2);
		
		sitio.addCategoriaAInquilino("Amabilidad");
		verify(ranking1).addCategoria("Amabilidad");
		verify(ranking2).addCategoria("Amabilidad");
		verify(usuario1).getRankingComoInquilino();
		verify(usuario2).getRankingComoInquilino();
		
	}
	
	
	@Test
	void testAddCategoriaADuenho() throws Exception {
		sitio.registrarUsuario(usuario1);
		sitio.registrarUsuario(usuario2);
		when(usuario1.getRankingComoDuenho()).thenReturn(ranking1);
		when(usuario2.getRankingComoDuenho()).thenReturn(ranking2);
		
		sitio.addCategoriaADuenho("Puntualidad");
		verify(ranking1).addCategoria("Puntualidad");
		verify(ranking2).addCategoria("Puntualidad");
		verify(usuario1).getRankingComoDuenho();
		verify(usuario2).getRankingComoDuenho();	
	}
	
	@Test
	void testAddCategoriaAInmueble() throws Exception {
		sitio.agregarTipoDeInmuebleValido("choza");
		sitio.agregarServicioValido("gas");
		when(inmueble1.getTipoDeInmueble()).thenReturn("choza");
		//when(inmueble1.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		when(inmueble2.getTipoDeInmueble()).thenReturn("choza");
		//when(inmueble2.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		sitio.agregarInmueble(inmueble1);
		sitio.agregarInmueble(inmueble2);
		when(inmueble1.getRanking()).thenReturn(ranking1);
		when(inmueble2.getRanking()).thenReturn(ranking2);
		
		sitio.addCategoriaAInmueble("Seguridad");
		verify(ranking1).addCategoria("Seguridad");
		verify(ranking2).addCategoria("Seguridad");
		verify(inmueble1).getRanking();
		verify(inmueble2).getRanking();	
	}

	   @Test
	    void testFiltrarInmueblesQuePertenezcanALasFechasDadas() throws Exception {
	        sitio.agregarTipoDeInmuebleValido("casa");
	        when(inmueble1.getTipoDeInmueble()).thenReturn("casa");
	        //when(inmueble1.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
	        when(inmueble2.getTipoDeInmueble()).thenReturn("casa");
	        //when(inmueble2.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
	        sitio.agregarInmueble(inmueble1);
	        sitio.agregarInmueble(inmueble2);
	        LocalDate fecha1 = LocalDate.of(2020, 5,10);
	        LocalDate fecha2 = LocalDate.of(2020, 5,10);
	        when(inmueble1.hayAlgunRangoDeFechasQuePoseaLasFecha(fecha1,fecha2)).thenReturn(true);
	        when(inmueble2.hayAlgunRangoDeFechasQuePoseaLasFecha(fecha1,fecha2)).thenReturn(false);
	        
	        ArrayList<Inmueble>inmueblesFiltados = sitio.filtrarInmueblesQuePertenezcanALasFechas(fecha1, fecha2);
	         
	        assertTrue(inmueblesFiltados.contains(inmueble1));
	        assertFalse(inmueblesFiltados.contains(inmueble2));
	    }
//	
//	
	@Test 
	void testBuscarInmuebles() throws Exception {
		sitio.agregarTipoDeInmuebleValido("casa");
		when(inmueble1.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble1.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		when(inmueble2.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble2.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		LocalDate fecha3 = LocalDate.of(2020, 5,10);
		LocalDate fecha4 = LocalDate.of(2020, 5,20);
		sitio.agregarInmueble(inmueble1); 
		sitio.agregarInmueble(inmueble2);
		
		when(filtroComposite.cumpleConElFiltro(inmueble1)).thenReturn(true);
		when(filtroComposite.cumpleConElFiltro(inmueble2)).thenReturn(false);
		ArrayList<Inmueble> inmuebles = sitio.buscarInmuebles(filtroComposite);

		assertTrue(inmuebles.contains(inmueble1));
		assertFalse(inmuebles.contains(inmueble2));	
	}
//	
	@Test
	void obtenerElTopTenDeInquilinos () throws Exception {
		sitio.registrarUsuario(usuario1);
		sitio.registrarUsuario(usuario2);
		sitio.registrarUsuario(usuario3);

		when(usuario1.cantidadReservas()).thenReturn(3);
 		when(usuario2.cantidadReservas()).thenReturn(5);
		when(usuario3.cantidadReservas()).thenReturn(7);
		
		ArrayList<Usuario>usuariosTopEsperados = new ArrayList<Usuario>();
		usuariosTopEsperados.add(usuario3);//3
		usuariosTopEsperados.add(usuario2);//3,2
		usuariosTopEsperados.add(usuario1);//3,2,1
		
		ArrayList<Usuario>usuariosTop = sitio.obtenerElTopTenDeInquilinos();
		
		assertEquals(usuariosTopEsperados, usuariosTop);
	}

	@Test
	void testGetCantidadDeInmueblesLibres() throws Exception {
		sitio.agregarTipoDeInmuebleValido("casa");
		when(inmueble1.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble1.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		when(inmueble2.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble2.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		sitio.agregarInmueble(inmueble1);
		sitio.agregarInmueble(inmueble2);
		when(inmueble1.estaAlquiladoActualmente(any(LocalDate.class))).thenReturn(false);
		when(inmueble2.estaAlquiladoActualmente(any(LocalDate.class))).thenReturn(false);
		
		assertEquals(2,sitio.getCantidadDeInmueblesLibres());
	} 
	
	@Test
	void testGetCantidadDeInmueblesAlquilados() throws Exception {
		sitio.agregarTipoDeInmuebleValido("casa");
		when(inmueble1.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble1.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		when(inmueble2.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble2.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		sitio.agregarInmueble(inmueble1);
		sitio.agregarInmueble(inmueble2);
		when(inmueble1.estaAlquiladoActualmente(any(LocalDate.class))).thenReturn(true);
		when(inmueble2.estaAlquiladoActualmente(any(LocalDate.class))).thenReturn(false);
		
		assertEquals(1,sitio.getCantidadDeInmueblesAlquilados());
	} 
	
	@Test
	void testGetTasaOcupacional() throws Exception {
		sitio.agregarTipoDeInmuebleValido("casa");
		when(inmueble1.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble1.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		when(inmueble2.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble2.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		sitio.agregarInmueble(inmueble1);
		sitio.agregarInmueble(inmueble2);
		when(inmueble1.estaAlquiladoActualmente(any(LocalDate.class))).thenReturn(true);
		when(inmueble2.estaAlquiladoActualmente(any(LocalDate.class))).thenReturn(true);
		
		assertEquals(1,sitio.getTasaOcupacional());
	}
}

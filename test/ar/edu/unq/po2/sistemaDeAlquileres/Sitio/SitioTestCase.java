package ar.edu.unq.po2.sistemaDeAlquileres.Sitio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Administrador.Administrador;
import ar.edu.unq.po2.sistemaDeAlquileres.Ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;
import junit.framework.AssertionFailedError;

class SitioTestCase {

	private Administrador administrador;
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
	private Reserva reserva6;
	
	@BeforeEach
	void setUp() throws Exception {
		administrador = new Administrador();
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
	}

	
	@Test
	void testRegistrarUsuario() {
		sitio.registrarUsuario(usuario1);
		assertTrue(sitio.esUsuario(usuario1));
	}
	
	
	@Test
	void testDesdeCuandoElPerfilFueCreadoLanzaUnaExcepcionAlNoTenerElUsuario() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			sitio.desdeCuandoElPerfilFueCreado(usuario1);
		}); 
	}
	
	@Test
	void testDesdeCuandoElPerfilFueCreado() {
		Date fechaEntrada = new Date(2020, 5,10);
		sitio.registrarUsuario(usuario1);
		when(usuario1.getFechaDeCreacion()).thenReturn(fechaEntrada);
		assertEquals(fechaEntrada, sitio.desdeCuandoElPerfilFueCreado(usuario1));
	}
	
	@Test
	void testAgregarInmuebleLanzaUnaExcepcionYaQueElInmuebleEsInvalido() {
		//when(inmueble1.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(false);
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			sitio.agregarInmueble(inmueble1);
		});
	}
	
	@Test
	void testAgregarInmueble() {
		sitio.agregarTipoDeInmuebleValido("choza");
		sitio.agregarServicioValido("gas");
		when(inmueble1.getTipoDeInmueble()).thenReturn("choza");
		//when(inmueble1.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		
		sitio.agregarInmueble(inmueble1);
		
		assertTrue(sitio.getInmuebles().contains(inmueble1));
	} 
	
	@Test
	void testAddCategoriaAInquilino() {
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
	void testAddCategoriaADuenho() {
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
	void testAddCategoriaAInmueble() {
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
	void testCuantosInmueblesAlquilo() {
		
		when(usuario1.cantidadDeVecesQueAlquilo()).thenReturn(2);
		assertEquals(2, sitio.cuantosInmueblesAlquilo(usuario1));
	}

	@Test
	void testFiltrarInmueblesQuePertenezcanALasFechasDadas() {
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
	void testBuscarInmuebles() {
		sitio.agregarTipoDeInmuebleValido("casa");
		when(inmueble1.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble1.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		when(inmueble2.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble2.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		sitio.agregarInmueble(inmueble1); 
		sitio.agregarInmueble(inmueble2);
		LocalDate fecha3 = LocalDate.of(2020, 5,10);
		LocalDate fecha4 = LocalDate.of(2020, 5,20);
		when(inmueble1.getCiudad()).thenReturn("Quilmes");
		when(inmueble2.getCiudad()).thenReturn("Quilmes");
		when(inmueble1.getCapacidad()).thenReturn(5);
		when(inmueble2.getCapacidad()).thenReturn(4);
		when(inmueble1.precioMaximoDelRangoDeFechasEntre(fecha3,fecha4)).thenReturn(200f);
		when(inmueble2.precioMaximoDelRangoDeFechasEntre(fecha3,fecha4)).thenReturn(500f);
		when(inmueble1.hayAlgunRangoDeFechasQuePoseaLasFecha(fecha3,fecha4)).thenReturn(true);
		when(inmueble2.hayAlgunRangoDeFechasQuePoseaLasFecha(fecha3,fecha4)).thenReturn(false);
		
		ArrayList<Inmueble> inmuebles = sitio.buscarInmuebles("Quilmes",fecha3,fecha4,3,100f,300f);
		
		assertTrue(inmuebles.contains(inmueble1));
		assertFalse(inmuebles.contains(inmueble2));

	}
	
	
	@Test
	void testUsuariosOrdenadosPorReservasRealizadas() {
		sitio.registrarUsuario(usuario1);
		sitio.registrarUsuario(usuario2);
		sitio.registrarUsuario(usuario3);
		ArrayList <Reserva> reservas1 = new ArrayList<Reserva>();
		ArrayList <Reserva> reservas2 = new ArrayList<Reserva>();
		ArrayList <Reserva> reservas3 = new ArrayList<Reserva>();
		reservas1.add(reserva1);
		reservas1.add(reserva2);
		reservas1.add(reserva3);
		reservas2.add(reserva4);
		reservas2.add(reserva5);
		reservas3.add(reserva6);
		when(usuario1.getReservasRealizadas()).thenReturn(reservas1);
 		when(usuario2.getReservasRealizadas()).thenReturn(reservas3);
		when(usuario3.getReservasRealizadas()).thenReturn(reservas2);
		
		assertEquals(usuario3,sitio.usuariosOrdenadosPorReservasRealizadas().get(1));
		assertEquals(usuario2,sitio.usuariosOrdenadosPorReservasRealizadas().get(2));
		assertEquals(usuario1,sitio.usuariosOrdenadosPorReservasRealizadas().get(0));
	}
//	
	@Test
	void obtenerElTopTenDeInquilinos () {
		sitio.registrarUsuario(usuario1);
		sitio.registrarUsuario(usuario2);
		sitio.registrarUsuario(usuario3);
		ArrayList <Reserva> reservas1 = new ArrayList<Reserva>();
		ArrayList <Reserva> reservas2 = new ArrayList<Reserva>();
		ArrayList <Reserva> reservas3 = new ArrayList<Reserva>();
		reservas1.add(reserva1);
		reservas1.add(reserva2);
		reservas1.add(reserva3);
		reservas2.add(reserva4);
		reservas2.add(reserva5);
		reservas3.add(reserva6);
		when(usuario1.getReservasRealizadas()).thenReturn(reservas1);
 		when(usuario2.getReservasRealizadas()).thenReturn(reservas3);
		when(usuario3.getReservasRealizadas()).thenReturn(reservas2);
		
		ArrayList<Usuario>usuariosTop = sitio.obtenerElTopTenDeInquilinos();
	}
//	
	@Test
	void testGetCantidadDeInmueblesLibres() {
		sitio.agregarTipoDeInmuebleValido("casa");
		when(inmueble1.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble1.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		when(inmueble2.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble2.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		sitio.agregarInmueble(inmueble1);
		sitio.agregarInmueble(inmueble2);
		when(inmueble1.estaAlquiladoActualmente()).thenReturn(false);
		when(inmueble2.estaAlquiladoActualmente()).thenReturn(false);
		
		assertEquals(2,sitio.getCantidadDeInmueblesLibres());
	} 
	
	@Test
	void testGetCantidadDeInmueblesAlquilados() {
		sitio.agregarTipoDeInmuebleValido("casa");
		when(inmueble1.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble1.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		when(inmueble2.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble2.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		sitio.agregarInmueble(inmueble1);
		sitio.agregarInmueble(inmueble2);
		when(inmueble1.estaAlquiladoActualmente()).thenReturn(true);
		when(inmueble2.estaAlquiladoActualmente()).thenReturn(false);
		
		assertEquals(1,sitio.getCantidadDeInmueblesAlquilados());
	} 
	
	@Test
	void testGetTasaOcupacional() {
		sitio.agregarTipoDeInmuebleValido("casa");
		when(inmueble1.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble1.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		when(inmueble2.getTipoDeInmueble()).thenReturn("casa");
		//when(inmueble2.poseeTodosLosServiciosValidosDelSitio(sitio)).thenReturn(true);
		sitio.agregarInmueble(inmueble1);
		sitio.agregarInmueble(inmueble2);
		when(inmueble1.estaAlquiladoActualmente()).thenReturn(true);
		when(inmueble2.estaAlquiladoActualmente()).thenReturn(true);
		
		assertEquals(1,sitio.getTasaOcupacional());
	}
}

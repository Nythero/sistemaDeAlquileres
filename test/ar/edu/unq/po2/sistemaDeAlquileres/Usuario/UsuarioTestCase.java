package ar.edu.unq.po2.sistemaDeAlquileres.Usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Sitio.Sitio;
import junit.framework.AssertionFailedError;

public class UsuarioTestCase {
 
//	private String nombre;
//	private String mail;
//	private String telefono; no se usan 
	private Ranking rankingComoDuenho;
	private Ranking rankingComoInquilino;
//	private Date fechaDeCreacion;  
	private Sitio sitio;
	private Usuario usuario1;
	private Reserva reserva1; 
	private Reserva reserva2; 
	private Inmueble inmueble1;
	private Inmueble inmueble2;
//	private RangoDeFechaConPrecioDeterminado rango1;
//	private RangoDeFechaConPrecioDeterminado rango2;
	
	
	@BeforeEach
	void setUp() throws Exception {
		inmueble1 = mock(Inmueble.class);
		inmueble2 = mock(Inmueble.class);
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class); 
		rankingComoDuenho = mock(Ranking.class);
		rankingComoInquilino = mock(Ranking.class);
		sitio = mock(Sitio.class);
		usuario1 = new Usuario("Miguel", "mail@gmail.com","12345",rankingComoDuenho,
							    rankingComoInquilino);
//		rango1 = mock(RangoDeFechaConPrecioDeterminado.class);
//		rango2 = mock(RangoDeFechaConPrecioDeterminado.class);
	}
	
	@Test
	void testGetSaldo() {
		usuario1.setSaldo(200);
		assertEquals(200, usuario1.getSaldo());
	}
	
	@Test
	void testSetComentarioAReserva() {
		usuario1.setComentarioAReserva(reserva1,"Comentario");
		verify(reserva1).setComentario("Comentario");
	}
	
	@Test
	void testSetPuntajeADuenho() {
		usuario1.setPuntajeADuenho(reserva1,"amabilidad", 5);
		verify(reserva1).setPuntajeADuenho("amabilidad",5);
	}
	
	@Test
	void testSetPuntajeAInquilino() {
		usuario1.setPuntajeAInquilino(reserva1,"honestidad", 5);
		verify(reserva1).setPuntajeAInquilino("honestidad",5);
	}
	
	@Test
	void testSetPuntajeCategoriaAInmueble() {
		usuario1.setPuntajeCategoriaAInmueble(reserva1,"seguridad", 5);
		verify(reserva1).setPuntajeCategoriaAInmueble("seguridad",5);
	}
	
	@Test //preguntar si dejarlo asi, o hacer la forma en la que el sitio tire errores
	     // con un test que pase que el usuario no esta registrado, otro que le pases parametros invalidos
	void testBuscarInmuebles() {
		LocalDate fechaEntrada = LocalDate.of(2020, 5,10);
		LocalDate fechaSalida = LocalDate.of(2020, 5,20);
		usuario1.buscarInmuebles(sitio,"Quilmes", fechaEntrada,fechaSalida, 4, 400f,600f);
		verify(sitio).buscarInmuebles("Quilmes", fechaEntrada,fechaSalida, 4, 400f,600f);
	}
	
	@Test
	void testRegistrarseEnSitio() {
		usuario1.registrarseEnSitio(sitio);
		verify(sitio).registrarUsuario(usuario1);
	}
	
	@Test
	void testVisualizarDatosDelInmuebleQueEsteEnLaLista() throws Exception{
		ArrayList<Inmueble> listaDeInmuebles = new ArrayList<Inmueble>();
		listaDeInmuebles.add(inmueble1);
		listaDeInmuebles.add(inmueble2);
		Inmueble inmuebleResultante = usuario1.visualizarDatosDelInmueble(listaDeInmuebles, 1);
		assertEquals(inmueble2, inmuebleResultante);
	}
	
	@Test
	void testVisualizarDatosDelInmuebleQueNoEsteEnLaListaYSalteUnError() throws Exception{
		ArrayList<Inmueble> listaDeInmuebles = new ArrayList<Inmueble>();
		listaDeInmuebles.add(inmueble1);
		listaDeInmuebles.add(inmueble2);

		Assertions.assertThrows(AssertionFailedError.class, () -> {
			usuario1.visualizarDatosDelInmueble(listaDeInmuebles, 3);
		});
	}
	
	@Test
	void testPublicarInmueble() {
		usuario1.publicarInmueble(inmueble1,sitio);
		verify(sitio).agregarInmueble(inmueble1);
		assertEquals(1,usuario1.getInmuebles().size());
	}
	
	@Test
	void testExtraerMonto() {
		usuario1.recibirPago(200);
		usuario1.extraerMonto(100);
		assertEquals(100,usuario1.getSaldo());
	}
	
	
	@Test
	void testAgregarInmueble() {
		usuario1.agregarInmueble(inmueble1);
		assertEquals(1,usuario1.getInmuebles().size());
	}
	
	@Test
	void testAgregarInmuebleRepetido() {
		usuario1.agregarInmueble(inmueble1);
		usuario1.agregarInmueble(inmueble1);
		assertEquals(1,usuario1.getInmuebles().size());
	}
	
	
	@Test
	void testExtraerMontoMayorAlDisponible() {
		usuario1.recibirPago(200);
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			usuario1.extraerMonto(300);
		});
		assertEquals(200,usuario1.getSaldo());
	}
	
	@Test
	void testAgregarReserva(){
		usuario1.agregarReserva(reserva1);
		assertEquals(1,usuario1.getReservas().size());
	}
	
	@Test
	void testGetReservasFuturas(){
		usuario1.agregarReserva(reserva1);
		usuario1.agregarReserva(reserva2);
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reserva1);
		reservas.add(reserva2);
		when(reserva1.laFechaInicialDelRangoEstaDespuesDelDiaActual()).thenReturn(true);
		when(reserva2.laFechaInicialDelRangoEstaDespuesDelDiaActual()).thenReturn(true);
		
		assertEquals(reservas,usuario1.getTodasLasReservasFuturas());
	}
	
	@Test
	void testGetReservasEnCiudadEnParticular(){
		usuario1.agregarReserva(reserva1);
		usuario1.agregarReserva(reserva2);
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reserva1);
		when(reserva1.getCiudad()).thenReturn("Quilmes");
		when(reserva2.getCiudad()).thenReturn("La Plata");
		
		assertEquals(reservas,usuario1.getReservasEnCiudadParticular("Quilmes"));
	}
	
	
	@Test
	void testGetCiudadesEnLasQueTieneReserva(){
		usuario1.agregarReserva(reserva1);
		usuario1.agregarReserva(reserva2);
		Set<String> ciudades = new HashSet<String>();
		ciudades.add("La Plata");
		ciudades.add("Quilmes");		
		when(reserva1.getCiudad()).thenReturn("Quilmes");
		when(reserva2.getCiudad()).thenReturn("La Plata");
		
		assertEquals(ciudades,usuario1.getCiudadesEnLasQueTieneReservas());
	}
	
	@Test
	void testGetCiudadesEnLasQueTieneReservaSinCiudadesRepetidas(){
		usuario1.agregarReserva(reserva1);
		usuario1.agregarReserva(reserva2);
		Set<String> ciudades = new HashSet<String>();
		ciudades.add("Quilmes");		
		when(reserva1.getCiudad()).thenReturn("Quilmes");
		when(reserva2.getCiudad()).thenReturn("Quilmes");
		
		assertEquals(ciudades,usuario1.getCiudadesEnLasQueTieneReservas());
	}
	
	
	@Test 
	void testObtenerLaPosicionDelInmuebleEnListaDeInmuebles(){
		usuario1.agregarInmueble(inmueble1);	
		assertEquals(0,usuario1.obtenerLaPosicionDelInmueble(inmueble1));
	}
	
	@Test 
	void testObtenerLaPosicionDelInmuebleUnInmuebleQueNoEstaEnLaListaDevuelveMenosUno(){
		usuario1.agregarInmueble(inmueble2);	
		assertEquals(-1,usuario1.obtenerLaPosicionDelInmueble(inmueble1));
	}
	
	@Test
	void cuantasVecesAlquiloElInmuebleDado(){
		usuario1.agregarInmueble(inmueble1);	
		when(inmueble1.getCantidadDeVecesAlquilado()).thenReturn(5);
		
		assertEquals(5,usuario1.cantidadDeVecesQueAlquilo());
	}
	
	@Test
	void cuantasVecesAlquiloElInmuebleDadoSinTenerloEnInmuebles(){	
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			usuario1.cuantasVecesAlquiloElInmueble(inmueble1);
		});
	} 
	
	@Test
	void testCantidadDeVecesQueAlquilo(){
		usuario1.agregarInmueble(inmueble1);
		usuario1.agregarInmueble(inmueble2);		
		when(inmueble1.getCantidadDeVecesAlquilado()).thenReturn(5);
		when(inmueble2.getCantidadDeVecesAlquilado()).thenReturn(15);
		
		assertEquals(20,usuario1.cantidadDeVecesQueAlquilo());
	}
	
	@Test
	void testGetInmueblesQueHanSidoAlquilados(){
		usuario1.agregarInmueble(inmueble1);
		usuario1.agregarInmueble(inmueble2);
		ArrayList<Inmueble> inmueblesQueHanSidoAlquilados = new ArrayList<Inmueble>();
		inmueblesQueHanSidoAlquilados.add(inmueble1);
		
		when(inmueble1.getCantidadDeVecesAlquilado()).thenReturn(5);
		when(inmueble2.getCantidadDeVecesAlquilado()).thenReturn(0);
	    	
		assertEquals(inmueblesQueHanSidoAlquilados,usuario1.getInmueblesQueHanSidoAlquilados());
	}
	
	
}
	

// los de realizarReserva,las futuras y las de las ciudades, faltan resolver como armar la reserva
// falta lo de reserva condicional






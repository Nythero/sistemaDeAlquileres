package ar.edu.unq.po2.sistemaDeAlquileres.Usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda.IFiltroDeBusqueda;
import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado.CambioDeEstadoError;
import ar.edu.unq.po2.sistemaDeAlquileres.Sitio.Sitio;


public class UsuarioTestCase {
	private Ranking rankingComoDuenho;
	private Ranking rankingComoInquilino;  
	private Sitio sitio;
	private Usuario usuario1;
	private Reserva reserva1; 
	private Reserva reserva2; 
	private Inmueble inmueble1;
	private Inmueble inmueble2;
	
	@BeforeEach
	void setUp() throws Exception {
		inmueble1 = mock(Inmueble.class);
		inmueble2 = mock(Inmueble.class);
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		rankingComoDuenho = mock(Ranking.class);
		rankingComoInquilino = mock(Ranking.class);
		sitio = mock(Sitio.class);
		usuario1 = new Usuario("Miguel", "mail@gmail.com","12345");

		when(reserva1.getInmueble()).thenReturn(inmueble1);
		when(reserva2.getInmueble()).thenReturn(inmueble2);
		when(sitio.esUsuario(usuario1)).thenReturn(true);
	}
	
	@Test
	void testRealizarReservaValida() throws Exception {
		when(reserva1.getInmueble()).thenReturn(inmueble1);
		usuario1.realizarReserva(reserva1,sitio);
		
		verify(reserva1).getInmueble();
		verify(inmueble1).agregarReserva(reserva1);
	}
	
	@Test
	void testRealizarReservaInvalida() throws Exception {
		when(reserva1.getInmueble()).thenReturn(inmueble1);
		when(sitio.esUsuario(usuario1)).thenReturn(false);
		assertThrows(Exception.class,()->usuario1.realizarReserva(reserva1,sitio));
	}
	
	@Test
	void testSetComentarioAReserva() throws Exception {
		usuario1.realizarReserva(reserva1,sitio);
		usuario1.comentarInmueble(reserva1,"Comentario");
		verify(reserva1).comentarInmueble("Comentario");
	}
	
	@Test
	void testSetPuntajeADuenho() throws Exception {
		usuario1.realizarReserva(reserva1,sitio);
		usuario1.puntuarDuenho(reserva1,"amabilidad", 5);
		verify(reserva1).puntuarDuenho("amabilidad",5);
	}
	
	@Test
	void testSetPuntajeAInquilino() throws Exception {
		usuario1.realizarReserva(reserva1,sitio);
		usuario1.puntuarInquilino(reserva1,"honestidad", 5);
		verify(reserva1).puntuarInquilino("honestidad",5);
	}
	
	@Test
	void testSetPuntajeCategoriaAInmueble() throws Exception {
		usuario1.realizarReserva(reserva1,sitio);
		usuario1.puntuarInmueble(reserva1,"seguridad", 5);
		verify(reserva1).puntuarInmueble("seguridad",5);
	}
	
	@Test
	void testBuscarInmuebles() throws Exception {
		IFiltroDeBusqueda filtro = mock(IFiltroDeBusqueda.class);
		LocalDate fechaEntrada = LocalDate.of(2020, 5,10);
		LocalDate fechaSalida = LocalDate.of(2020, 5,20);
		usuario1.buscarInmuebles(sitio,"Quilmes", fechaEntrada,fechaSalida, 4, 400f,600f);
		verify(sitio).buscarInmuebles(any(IFiltroDeBusqueda.class));
	}
	
	@Test
	void testRegistrarseEnSitio() throws Exception {
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

		Assertions.assertThrows(Exception.class, () -> {
			usuario1.visualizarDatosDelInmueble(listaDeInmuebles, 3);
		});
	}
	
	@Test
	void testPublicarInmueble() throws Exception {
		usuario1.publicarInmueble(inmueble1,sitio);
		verify(sitio).agregarInmueble(inmueble1);
	}
	
	@Test
	void testExtraerMonto() throws Exception {
		usuario1.recibirPago(200);
		usuario1.extraerMonto(100);
		assertEquals(100,usuario1.getSaldo());
	}
	
	@Test
	void testPublicarInmuebleRepetido() throws Exception {
		usuario1.publicarInmueble(inmueble1, sitio);
		assertThrows(Exception.class,() -> usuario1.publicarInmueble(inmueble1, sitio));
	}
	
	
	@Test
	void testExtraerMontoMayorAlDisponible() {
		usuario1.recibirPago(200);
		assertThrows(Exception.class, () -> {
			usuario1.extraerMonto(300);
		});
		assertEquals(200,usuario1.getSaldo());
	}
	
	@Test
	void testAgregarReserva() throws Exception{
		usuario1.realizarReserva(reserva1,sitio);
		assertEquals(1,usuario1.cantidadReservas());
	}
	
	@Test
	void testGetReservasFuturas() throws Exception{
		LocalDate fechaActual = mock(LocalDate.class);
		LocalDate fecha = mock(LocalDate.class);
		when(fechaActual.isBefore(fecha)).thenReturn(true);
		when(reserva1.getFechaInicial()).thenReturn(fecha);
		when(reserva2.getFechaInicial()).thenReturn(fecha);
		when(reserva1.estaEnEstado("Condicional")).thenReturn(true);
		when(reserva2.estaEnEstado("Condicional")).thenReturn(true);
		
		usuario1.realizarReserva(reserva1,sitio);
		usuario1.realizarReserva(reserva2,sitio);
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reserva1);
		reservas.add(reserva2);
		
		assertEquals(reservas,usuario1.getTodasLasReservasFuturas(fechaActual));
	}
	
	@Test
	void testGetReservasEnCiudadEnParticular() throws Exception{
		usuario1.realizarReserva(reserva1,sitio);
		usuario1.realizarReserva(reserva2,sitio);
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reserva1);
		
		when(inmueble1.getCiudad()).thenReturn("Quilmes");
		when(inmueble2.getCiudad()).thenReturn("La Plata");
		assertEquals(reservas,usuario1.getReservasEnCiudadParticular("Quilmes"));
	}
	
	
	@Test
	void testGetCiudadesEnLasQueTieneReserva() throws Exception{
		usuario1.realizarReserva(reserva1,sitio);
		usuario1.realizarReserva(reserva2,sitio);
		Set<String> ciudades = new HashSet<String>();
		ciudades.add("La Plata");
		ciudades.add("Quilmes");		
		when(inmueble1.getCiudad()).thenReturn("Quilmes");
		when(inmueble2.getCiudad()).thenReturn("La Plata");
		
		assertEquals(ciudades,usuario1.getCiudadesEnLasQueTieneReservas());
	}
	
	@Test
	void testGetCiudadesEnLasQueTieneReservaSinCiudadesRepetidas() throws Exception{
		usuario1.realizarReserva(reserva1,sitio);
		usuario1.realizarReserva(reserva2,sitio);
		Set<String> ciudades = new HashSet<String>();
		ciudades.add("Quilmes");		
		when(inmueble1.getCiudad()).thenReturn("Quilmes");
		when(inmueble2.getCiudad()).thenReturn("Quilmes");
		
		assertEquals(ciudades,usuario1.getCiudadesEnLasQueTieneReservas());
	}
	
	@Test
	void testCuantasVecesAlquiloElInmuebleDadoSinTenerloEnInmuebles(){	
		assertThrows(Exception.class, () -> {
			usuario1.cuantasVecesAlquiloElInmueble(inmueble1);
		});
	} 
	
	@Test
    void cuantasVecesAlquiloElInmuebleDado() throws Exception{
        usuario1.publicarInmueble(inmueble1, sitio);
        when(inmueble1.getCantidadDeVecesAlquilado()).thenReturn(5);

        assertEquals(5,usuario1.cuantasVecesAlquiloElInmueble(inmueble1));
    }
	
	@Test
	void testCantidadDeVecesQueAlquilo() throws Exception{
		usuario1.publicarInmueble(inmueble1, sitio);
		usuario1.publicarInmueble(inmueble2, sitio);		
		when(inmueble1.getCantidadDeVecesAlquilado()).thenReturn(5);
		when(inmueble2.getCantidadDeVecesAlquilado()).thenReturn(15);
		
		assertEquals(20,usuario1.cantidadDeVecesQueAlquilo());
	}
	
	@Test
	void testGetInmueblesQueHanSidoAlquilados() throws Exception{
		usuario1.publicarInmueble(inmueble1, sitio);
		usuario1.publicarInmueble(inmueble2, sitio);
		ArrayList<Inmueble> inmueblesQueHanSidoAlquilados = new ArrayList<Inmueble>();
		inmueblesQueHanSidoAlquilados.add(inmueble1);
		
		when(inmueble1.getCantidadDeVecesAlquilado()).thenReturn(5);
		when(inmueble2.getCantidadDeVecesAlquilado()).thenReturn(0);
	    	
		assertEquals(inmueblesQueHanSidoAlquilados,usuario1.getInmueblesQueHanSidoAlquilados());
	}
	
	@Test
	void testCancelarReservaTiraError() throws CambioDeEstadoError, Exception {
		assertThrows(Exception.class, () -> usuario1.cancelarReserva(reserva1));
	}
	
	@Test
	void testCancelarReservaSucces() throws CambioDeEstadoError, Exception {
		usuario1.realizarReserva(reserva1,sitio);
		usuario1.cancelarReserva(reserva1);
		
		verify(reserva1).cancelar();
	}
	

}
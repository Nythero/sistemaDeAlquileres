package ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Foto.Foto;
import ar.edu.unq.po2.sistemaDeAlquileres.IObsevers.IObserver;
import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;
import ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion.PoliticaDeCancelacion;


class InmuebleTestCase {
	private Inmueble inmueble;
	private LocalTime horaDeCheckIn;
	private LocalTime horaDeCheckOut;
	private Temporada precio;
	private Usuario usuario;
	private RangoDeFechas rango;
	private PoliticaDeCancelacion politica;
	@Mock private Foto foto;
	
	@BeforeEach
	void setUp() throws Exception {
		horaDeCheckIn= mock(LocalTime.class);
		horaDeCheckOut= mock(LocalTime.class);
		precio= mock(Temporada.class);
		usuario = mock(Usuario.class);
		rango = mock(RangoDeFechas.class);
		politica = mock(PoliticaDeCancelacion.class);
		
		inmueble = new Inmueble(usuario, "apartamento", 200, "Argentina", "Quilmes", "Calle falsa 123",
				3, horaDeCheckIn, horaDeCheckOut, precio, politica);
		inmueble.agregarRangoDeFechas(rango);
		when(rango.estaIncluidoElRango(rango)).thenReturn(true);
		
		inmueble.agregarFormaDePago("Efectivo");
		foto= mock(Foto.class);
	}
	
	private void validarReserva(Reserva reserva) {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(reserva.getRangoDeFechas()).thenReturn(rango);
		when(reserva.getFormaDePago()).thenReturn("Efectivo");
		when(reserva.estaEnEstado("PendienteDeAprobacion")).thenReturn(true);
	}
	
	@Test
	void Inmueble_AgregarReserva_Success() {
		Reserva reserva = mock(Reserva.class);
		when(reserva.getMontoTotal()).thenReturn(200f);
		this.validarReserva(reserva);
		
		assertDoesNotThrow(() -> inmueble.agregarReserva(reserva));
		
		verify(reserva).getInmueble();
		verify(reserva).getRangoDeFechas();
		verify(reserva).getFormaDePago();
		verify(reserva).estaEnEstado("PendienteDeAprobacion");
		verify(usuario).recibirPago(200f);
	}
	
	@Test
	void Inmueble_AgregarReserva_ReservaInvalida() {
		Reserva reserva = mock(Reserva.class);
		
		when(reserva.getRangoDeFechas()).thenReturn(rango);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(reserva.getFormaDePago()).thenReturn("Tarjeta");
		when(reserva.estaEnEstado("PendienteDeAprobacion")).thenReturn(true);
		
		assertThrows(Exception.class, () -> inmueble.agregarReserva(reserva));
	}
	
	@Test
	void Inmueble_YaEstaReservado_True() throws Exception {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		
		for (int i = 0; i < 3; i++) {
			reservas.add(mock(Reserva.class));
			this.validarReserva(reservas.get(i));
			inmueble.agregarReserva(reservas.get(i));
		}
		
		when(reservas.get(0).estaEnEstado("Concretada")).thenReturn(true);
		when(reservas.get(1).estaEnEstado("Concretada")).thenReturn(false);
		when(reservas.get(2).estaEnEstado("Concretada")).thenReturn(true);
		when(rango.intersectanLosRangos(reservas.get(0).getRangoDeFechas())).thenReturn(false);
		when(rango.intersectanLosRangos(reservas.get(2).getRangoDeFechas())).thenReturn(true);
		
		assertTrue(inmueble.yaEstaReservado(rango));
	}
	
	@Test
	void Inmueble_YaEstaReservado_False() throws Exception {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		
		for (int i = 0; i < 3; i++) {
			reservas.add(mock(Reserva.class));
			this.validarReserva(reservas.get(i));
			inmueble.agregarReserva(reservas.get(i));
		}
		
		when(reservas.get(0).estaEnEstado("Concretado")).thenReturn(false);
		when(reservas.get(1).estaEnEstado("Concretado")).thenReturn(false);
		when(reservas.get(2).estaEnEstado("Concretado")).thenReturn(false);
		
		assertFalse(inmueble.yaEstaReservado(rango));
	}
	
	@Test
	void Inmueble_AgregarComentario_Success() {
		assertDoesNotThrow(() -> inmueble.agregarComentario("Comentario"));
	}
	
	@Test
	void Inmueble_CancelarReserva_Success() throws Exception {
		Reserva reservaConcretada = mock(Reserva.class);
		this.validarReserva(reservaConcretada);
		
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		for(int i = 0; i < 3; i++) {
			reservas.add(mock(Reserva.class));
			this.validarReserva(reservas.get(i));
			inmueble.agregarReserva(reservas.get(i));
		}

		when(rango.intersectanLosRangos(rango)).thenReturn(true).thenReturn(true).thenReturn(false);
		
		IObserver observador1 = mock(IObserver.class);
		IObserver observador2 = mock(IObserver.class);
		inmueble.agregarReserva(reservaConcretada);
		inmueble.add("Cancelado", observador1);
		inmueble.add("Cancelado", observador2);
		
		inmueble.cancelarReserva(reservaConcretada);
		
		verify(politica).cancelarReserva(LocalDate.now(), reservaConcretada);
		verify(observador1).update(inmueble, null);
		verify(observador2).update(inmueble, null);
		verify(reservas.get(0)).aceptar();
		verify(reservas.get(1)).aceptar();
	}
	
	@Test
	void Inmueble_AgregarFoto_Success() {
		assertDoesNotThrow(() -> inmueble.agregarFoto(foto));
		assertDoesNotThrow(() -> inmueble.agregarFoto(foto));
		assertDoesNotThrow(() -> inmueble.agregarFoto(foto));
		assertDoesNotThrow(() -> inmueble.agregarFoto(foto));
		assertDoesNotThrow(() -> inmueble.agregarFoto(foto));
		assertThrows(Exception.class, () -> inmueble.agregarFoto(foto));
	}
	
	@Test
	void Inmueble_AgregarCategoria_Success() {
		assertDoesNotThrow(() -> inmueble.agregarCategoria("1"));
		assertDoesNotThrow(() -> inmueble.agregarCategoria("2"));
		assertDoesNotThrow(() -> inmueble.agregarCategoria("3"));
	}
	
	@Test
	void Inmueble_AgregarServicio_Success() {
		assertDoesNotThrow(() -> inmueble.agregarServicio("1"));
		assertDoesNotThrow(() -> inmueble.agregarServicio("2"));
		assertDoesNotThrow(() -> inmueble.agregarServicio("3"));
	}
	
	@Test
	void Inmueble_CantidadDeVecesAlquilado_Success() throws Exception {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		
		for (int i = 0; i < 3; i++) {
			reservas.add(mock(Reserva.class));
			this.validarReserva(reservas.get(i));
			inmueble.agregarReserva(reservas.get(i));
			when(reservas.get(i).estaEnEstado("Finalizado")).thenReturn(true);
		}
		
		assertEquals(3, inmueble.getCantidadDeVecesAlquilado());
	}
	
	@Test
	void Inmueble_HayAlgunRangoDeFechasQuePoseaElRango_Succes() {
		when(rango.estaIncluidoElRango(rango)).thenReturn(false).thenReturn(false).thenReturn(true).thenReturn(false).thenReturn(false);
		for(int i = 1; i < 5; i++) {
			inmueble.agregarRangoDeFechas(rango);
		}
		
		assertTrue(inmueble.hayAlgunRangoDeFechasQuePoseaElRango(rango));
	}
	
	@Test void Inmueble_EstaAlquiladoActualmente_Success() throws Exception{
		Reserva reserva1 = mock(Reserva.class);
		Reserva reserva2 = mock(Reserva.class);
		this.validarReserva(reserva1);
		this.validarReserva(reserva2);
		when(reserva1.estaEnEstado("EnCurso")).thenReturn(false);
		when(reserva2.estaEnEstado("EnCurso")).thenReturn(true);
		inmueble.agregarReserva(reserva1);
		inmueble.agregarReserva(reserva2);
		
		assertTrue(inmueble.estaAlquiladoActualmente());
	}
	
	@Test
	void testBajarPrecioCotidiano() {
		inmueble.bajarPrecioCotidiano(200f);
		
		verify(precio).bajarAlPrecioCotidiano(200f);
	}
	
	@Test
	void testBajarPrecioEspecial() {
		inmueble.bajarPrecioEspecial(200f);
		
		verify(precio).bajarPrecioEspecial(200f);
	}
	
	@Test void testHayAlgunRangoDeFechasQuePoseaLasFecha() throws Exception{
		LocalDate fecha = mock(LocalDate.class);
		inmueble.agregarRangoDeFechas(rango);
		inmueble.agregarRangoDeFechas(rango);
		when(rango.lasFechasEstanEnElRango(fecha, fecha)).thenReturn(false).thenReturn(true);
		boolean result = inmueble.hayAlgunRangoDeFechasQuePoseaLasFecha(fecha,fecha);
		
		assertTrue(result);
	}
	
	@Test void testNoHayAlgunRangoDeFechasQuePoseaLasFecha() throws Exception{
		LocalDate fecha = mock(LocalDate.class);
		inmueble.agregarRangoDeFechas(rango);
		inmueble.agregarRangoDeFechas(rango);
		when(rango.estaIncluidoElRango(any(RangoDeFechas.class))).thenReturn(true).thenReturn(false);
		when(rango.precioMaximoEntreElRangoDeFechas(precio, fecha, fecha)).thenReturn(200f);
		float result = inmueble.precioMaximoDelRangoDeFechasEntre(fecha,fecha);
		
		assertEquals(200f,result);
	}
	
	
	@Test void testPrecioMaximoAlEstarVacio() throws Exception{
		LocalDate fecha = mock(LocalDate.class);
		float result = inmueble.precioMaximoDelRangoDeFechasEntre(fecha,fecha);
		assertEquals(0f,result);
	}	
}
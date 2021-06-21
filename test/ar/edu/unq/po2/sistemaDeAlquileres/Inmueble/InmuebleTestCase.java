package ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Foto.Foto;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Fijo;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;
import junit.framework.AssertionFailedError;

class InmuebleTestCase {
	private Inmueble inmueble;
	private ArrayList<String> servicios;
	@Mock private ArrayList<String> fotos;
	@Mock private ArrayList<String> formasDePago;
	@Mock private LocalTime horaDeCheckIn;
	@Mock private LocalTime horaDeCheckOut;
	@Mock private LocalDate fechaPublicaciondesde;
	@Mock private LocalDate fechaPublicacionhasta;
	@Mock private LocalDate desde;
	@Mock private LocalDate hasta;
	@Mock private Temporada precio;
	@Mock private Usuario usuario;
	@Mock private Reserva reserva;
	@Mock private Foto foto;
	
	@BeforeEach
	void setUp() throws Exception {
		servicios=new ArrayList<String>();
		horaDeCheckIn= mock(LocalTime.class);
		horaDeCheckOut= mock(LocalTime.class);
		fechaPublicaciondesde= mock(LocalDate.class);
		fechaPublicacionhasta= mock(LocalDate.class);
		formasDePago= mock(ArrayList.class);
		//precio= mock(Temporada.class);
		precio= new Fijo(200f);
		foto= mock(Foto.class);
		usuario = mock(Usuario.class);
		reserva= mock(Reserva.class);
	}
	
	@Test
	void testSeAgregaUnTipoDeInmueble() {
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		
		assertEquals(1,Inmueble.cantidadDeTiposDeInmueblesValidos());
	}
	
	@Test
	void testNoSeAgregaTiposDeInmueblesRepetidos() {
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		
		assertEquals(1,Inmueble.cantidadDeTiposDeInmueblesValidos());
	}
	
	@Test
	void testSeAgregaUnServicioAlInmueble() {
		Inmueble.darDeAltaElServicio("Gas");  
		
		assertEquals(1,Inmueble.cantidadDeServiciosDeInmueblesValidos());
	}
	
	@Test
	void testNoSeAgregaServiciosRepetidos() {
		Inmueble.darDeAltaElServicio("Gas");  
		Inmueble.darDeAltaElServicio("Gas");  
		
		assertEquals(1,Inmueble.cantidadDeServiciosDeInmueblesValidos());
	}
	
	@Test
	void testNoSePuedeCrearInmuebleCuandoNoSeEncuentraElTipo() {
		Inmueble.darDeAltaAlTipoDeInmueble("Casa");  
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			new Inmueble(usuario,"Apartamento", 200, "Argentina", "Quilmes", 
					"Calle falsa 123",servicios,3,horaDeCheckIn, horaDeCheckOut, formasDePago, precio);
		  });
	}
	
	@Test
	void testNoSePuedeCrearInmuebleCuandoNoSeEncuentraElServicio() {
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		Inmueble.darDeAltaElServicio("Gas"); 
		servicios.add("Electricidad");
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			new Inmueble(usuario,"Apartamento", 200, "Argentina", "Quilmes", 
					"Calle falsa 123",servicios,3,horaDeCheckIn, horaDeCheckOut, formasDePago, precio);
		  });
	}
	
	@Test
	void testSeCreaUnInmuebleApropiado() {
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		Inmueble.darDeAltaElServicio("Gas"); 
		servicios.add("Gas");
		new Inmueble(usuario,"Apartamento", 200, "Argentina", "Quilmes", 
					"Calle falsa 123",servicios,3,horaDeCheckIn, horaDeCheckOut,
					formasDePago, precio);
	}
	
	
	@Test
	void testSeAñadeUnRangoDeFechaConPrecio() {
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		Inmueble.darDeAltaElServicio("Gas"); 
		servicios.add("Gas");
		inmueble= new Inmueble(usuario,"Apartamento", 200, "Argentina", "Quilmes", 
					"Calle falsa 123",servicios,3,horaDeCheckIn, horaDeCheckOut,
					formasDePago, precio);
		
		inmueble.addRangoDeFechaConPrecio(fechaPublicaciondesde, fechaPublicacionhasta, precio);
		
		assertEquals(1,inmueble.getRangos().size());
	}
	
	@Test
	void testHayRangosQuePoseaLaFecha() {
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		Inmueble.darDeAltaElServicio("Gas"); 
		servicios.add("Gas");
		inmueble= new Inmueble(usuario,"Apartamento", 200, "Argentina", "Quilmes", 
					"Calle falsa 123",servicios,3,horaDeCheckIn, horaDeCheckOut,
					formasDePago, precio);
		inmueble.addRangoDeFechaConPrecio(LocalDate.of(2021, 6, 21), LocalDate.of(2021, 6, 27), precio);
		
		assertTrue(inmueble.hayAlgunRangoDeFechasQuePoseaLasFecha(LocalDate.of(2021, 6, 22), LocalDate.of(2021, 6, 25)));
	}
	
	@Test
	void testNoHayRangosQuePoseaLaFecha() {
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		Inmueble.darDeAltaElServicio("Gas"); 
		servicios.add("Gas");
		inmueble= new Inmueble(usuario,"Apartamento", 200, "Argentina", "Quilmes", 
					"Calle falsa 123",servicios,3,horaDeCheckIn, horaDeCheckOut,
					formasDePago, precio);
		inmueble.addRangoDeFechaConPrecio(LocalDate.of(2021, 6, 21), LocalDate.of(2021, 6, 27), precio);
		
		assertTrue(inmueble.hayAlgunRangoDeFechasQuePoseaLasFecha(LocalDate.of(2021, 6, 14), LocalDate.of(2021, 6, 25)));
	}
	
	@Test
	void testPrecioMaximoEntreRangoDeFecha() {
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		Inmueble.darDeAltaElServicio("Gas"); 
		servicios.add("Gas");
		inmueble= new Inmueble(usuario,"Apartamento", 200, "Argentina", "Quilmes", 
					"Calle falsa 123",servicios,3,horaDeCheckIn, horaDeCheckOut,
					formasDePago, precio);
		inmueble.addRangoDeFechaConPrecio(LocalDate.of(2021, 6, 21), LocalDate.of(2021, 6, 27), precio);
		
		assertEquals(200f,inmueble.precioMaximoDelRangoDeFechasEntre(LocalDate.of(2021, 6, 22), LocalDate.of(2021, 6, 24)));
	}
	
	@Test
	void testSeAñadeUnaReserva() {
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		Inmueble.darDeAltaElServicio("Gas"); 
		servicios.add("Gas");
		inmueble= new Inmueble(usuario,"Apartamento", 200, "Argentina", "Quilmes", 
					"Calle falsa 123",servicios,3,horaDeCheckIn, horaDeCheckOut,
					formasDePago, precio);
		inmueble.addReserva(reserva);
		
		assertEquals(1,inmueble.getReservas().size());
	}
	
	@Test
	void testSeEncuentraAlquiladoActualmente() {
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		Inmueble.darDeAltaElServicio("Gas"); 
		servicios.add("Gas");
		inmueble= new Inmueble(usuario,"Apartamento", 200, "Argentina", "Quilmes", 
					"Calle falsa 123",servicios,3,horaDeCheckIn, horaDeCheckOut,
					formasDePago, precio);
		inmueble.addReserva(reserva);
		when(reserva.laFechaActualEstaDentroDelRango()).thenReturn(true);
		
		assertTrue(inmueble.estaAlquiladoActualmente());
	}
	
	@Test
	void testNoSeEncuentraAlquiladoActualmente() {
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		Inmueble.darDeAltaElServicio("Gas"); 
		servicios.add("Gas");
		inmueble= new Inmueble(usuario,"Apartamento", 200, "Argentina", "Quilmes", 
					"Calle falsa 123",servicios,3,horaDeCheckIn, horaDeCheckOut,
					formasDePago, precio);
		
		
		assertFalse(inmueble.estaAlquiladoActualmente());
	}
	
	@Test
	void testSePuedeAñadirFotos() {
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		Inmueble.darDeAltaElServicio("Gas"); 
		servicios.add("Gas");
		inmueble= new Inmueble(usuario,"Apartamento", 200, "Argentina", "Quilmes", 
					"Calle falsa 123",servicios,3,horaDeCheckIn, horaDeCheckOut,
					formasDePago, precio);
		inmueble.addFoto(foto);
		
		assertEquals(1,inmueble.getFotos().size());
	}
	
	@Test
	void testNoSePuedenAgregarMasFotos() {
		Inmueble.darDeAltaAlTipoDeInmueble("Apartamento");  
		Inmueble.darDeAltaElServicio("Gas"); 
		servicios.add("Gas");
		inmueble= new Inmueble(usuario,"Apartamento", 200, "Argentina", "Quilmes", 
					"Calle falsa 123",servicios,3,horaDeCheckIn, horaDeCheckOut,
					formasDePago, precio);
		inmueble.addFoto(foto);
		inmueble.addFoto(foto);
		inmueble.addFoto(foto);
		inmueble.addFoto(foto);
		inmueble.addFoto(foto);
		inmueble.addFoto(foto);
		
		assertEquals(5,inmueble.getFotos().size());
	}
	
	
	
	
	
}

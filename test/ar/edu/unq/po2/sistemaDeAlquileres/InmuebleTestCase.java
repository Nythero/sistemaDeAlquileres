package ar.edu.unq.po2.sistemaDeAlquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

class InmuebleTestCase {
	private Inmueble inmueble;
	@Mock private ArrayList<String> servicios;
	@Mock private ArrayList<String> fotos;
	@Mock private ArrayList<String> formasDePago;
	@Mock private Date horaDeCheckIn;
	@Mock private Date horaDeCheckOut;
	@Mock private Temporada precio;
	@Mock private Usuario usuario;
	
	@BeforeEach
	void setUp() throws Exception {
		/*ArrayList<String> servicios = new ArrayList<String>();
		servicios.add("Gas");
		ArrayList<String> fotos = new ArrayList<String>();
		servicios.add("Foto1");
		
		Calendar horaDeCheckIn = Calendar.getInstance();
		horaDeCheckIn.set(Calendar.HOUR_OF_DAY, 12);
		horaDeCheckIn.set(Calendar.MINUTE, 30);
		horaDeCheckIn.set(Calendar.SECOND, 00);
		Date horaDeChecIN = horaDeCheckIn.getTime();
		
		Calendar horaDeCheckOut = Calendar.getInstance();
		horaDeCheckIn.set(Calendar.HOUR_OF_DAY, 12);
		horaDeCheckIn.set(Calendar.MINUTE, 30);
		horaDeCheckIn.set(Calendar.SECOND, 00);
		Date horaDeChecIN = horaDeCheckIn.getTime();
		
		*/
		servicios= mock(ArrayList.class);
		fotos= mock(ArrayList.class);
		horaDeCheckIn= mock(Date.class);
		horaDeCheckOut= mock(Date.class);
		formasDePago= mock(ArrayList.class);
		precio= mock(Temporada.class);
		usuario = mock(Usuario.class);
		inmueble= new Inmueble(usuario,"apartamento", 200, "Argentina", "Quilmes", "Calle falsa 123", 
				               servicios, 3, fotos,horaDeCheckIn, horaDeCheckOut, formasDePago, precio);
	}

	
	@Test
	void testGetTipoDeInmueble1() {
		String result= inmueble.getTipoDeInmueble();
		
		assertEquals("apartamento", result);
	}
	
	@Test
	void testGetSuperficie() {
		int result= inmueble.getSuperficie();
		
		assertEquals(200, result);
	}
	
	@Test
	void testGetCiudad() {
		String result= inmueble.getTipoDeInmueble();
		
		assertEquals("apartamento", result);
	}
	
	@Test
	void testGetTipoDeInmueble() {
		String result= inmueble.getTipoDeInmueble();
		
		assertEquals("apartamento", result);
	}

}

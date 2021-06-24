package ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Foto.Foto;
import ar.edu.unq.po2.sistemaDeAlquileres.IObsevers.IObserver;
import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado.RangoDeFechaConPrecioDeterminado;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

class InmuebleTestcase {
	private Inmueble inmueble;
	@Mock IObserver observer;
	@Mock Temporada precio;
	private ArrayList<String> servicios;
	@Mock private ArrayList<Foto> fotos;
	@Mock private ArrayList<String> formasDePago;
	@Mock private LocalTime horaDeCheckIn;
	@Mock private LocalTime horaDeCheckOut;
	@Mock private Usuario usuario;
	@Mock private Foto foto;
	@Mock private RangoDeFechaConPrecioDeterminado rango;
	
	@BeforeEach
	void setUp() throws Exception {
		observer= mock(IObserver.class);
		precio=mock(Temporada.class);
		servicios=new ArrayList<String>();
		horaDeCheckIn= mock(LocalTime.class);
		rango= mock(RangoDeFechaConPrecioDeterminado.class);
		formasDePago= mock(ArrayList.class);
		inmueble=new Inmueble(usuario,"Apartamento", 200, "Argentina", "Quilmes", 
				"Calle falsa 123",servicios,3,horaDeCheckIn, horaDeCheckOut, formasDePago,precio);
	}

	@Test
	void testSeNotificaLaBajaDePrecioCotidiano() {
		inmueble.add("Baja de precio", observer);
		when(rango.getPrecioTemporada()).thenReturn(precio);
		inmueble.bajarPrecioCotidiano(rango, 200f);
		
		verify(precio).bajarAlPrecioCotidiano(200f);
		verify(observer).update(inmueble, 200f);
	}
	
	@Test
	void testSeNotificaLaBajaDePrecioEspecial() {
		inmueble.add("Baja de precio", observer);
		when(rango.getPrecioTemporada()).thenReturn(precio);
		inmueble.bajarPrecioEspecial(rango, 200f);
		
		verify(precio).bajarPrecioEspecial(200f);
		verify(observer).update(inmueble, 200f);
	}

}

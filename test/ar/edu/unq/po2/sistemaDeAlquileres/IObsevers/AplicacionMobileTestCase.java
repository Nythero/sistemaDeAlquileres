package ar.edu.unq.po2.sistemaDeAlquileres.IObsevers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;

class AplicacionMobileTestCase {
	private AplicacionMobile aplicacionMobile;
	@Mock private ar.edu.unq.po2.sistemaDeAlquileres.Inmueble inmueble;
	
	@BeforeEach
	void setUp() throws Exception {
		aplicacionMobile= new AplicacionMobile();
		inmueble= mock(Inmueble.class);
	}

	@Test
	void testSeActualizaALosUsuarios() {
		when(inmueble.getTipoDeInmueble()).thenReturn("Apartamento");
		aplicacionMobile.update(inmueble, null);
		
		verify(inmueble).getTipoDeInmueble();
	}

}

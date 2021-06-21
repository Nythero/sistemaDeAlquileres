package ar.edu.unq.po2.sistemaDeAlquileres.IObsevers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

class TrivagoTestCase {
	private Trivago trivago;
	@Mock private Inmueble inmueble;
	
	@BeforeEach
	void setUp() throws Exception {
		trivago= new Trivago();
		inmueble= mock(Inmueble.class);
	}

	@Test
	void testSeActualizaLaPaginaPrincipal() {
		when(inmueble.getTipoDeInmueble()).thenReturn("Apartamento");
		trivago.update(inmueble, 300f);
		
		verify(inmueble).getTipoDeInmueble();
	}

}

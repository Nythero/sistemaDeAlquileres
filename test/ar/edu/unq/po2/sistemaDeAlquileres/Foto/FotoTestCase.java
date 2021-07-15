package ar.edu.unq.po2.sistemaDeAlquileres.Foto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FotoTestCase {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testAnchoNoValido() throws Exception {
		assertThrows(Exception.class, ()-> new Foto("Foto1",0,20));
	}
	
	@Test
	void tesAlturaNoValida() {
		assertThrows(Exception.class, ()-> new Foto("Foto1",5,-5));
	}
	
	@Test
	void testFotoValida() {
		assertDoesNotThrow(()-> new Foto("Foto1",1920,1080));
	}

}

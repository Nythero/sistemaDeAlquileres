package ar.edu.unq.po2.sistemaDeAlquileres;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.sistemaDeAlquileres.Feriado;
import junit.framework.AssertionFailedError;

class FeriadoTestCase {
	private Feriado feriado;
	
	@BeforeEach
	void setUp() throws Exception {
		feriado= new Feriado();
	}

	@Test
	void testAddFeriadoSiNoExisteLaClave() {
		feriado.addFeriado(8,26);
		int result = feriado.feriadosDelMes(8).size();
		
		assertEquals(1, result);
	}
	
	@Test
	void testAddFeriadoSiExisteLaClave() {
		feriado.addFeriado(5,26);
		int result = feriado.feriadosDelMes(5).size();
		
		assertEquals(3, result);
	}
	
	@Test
	void testNoAñadeSiExisteElFeriadoEnElMes() {
		feriado.addFeriado(5,25);
		int result = feriado.feriadosDelMes(5).size();
		
		assertEquals(2, result);
	}
	
	
	@Test
	void testRemoveFeriadoSiExiste() {
		feriado.removeFeriado(5,25);
		int result = feriado.feriadosDelMes(5).size();
		
		assertEquals(1, result);
	}
	
	@Test
	void testErrorCuandoRemoveFeriadoNoExistente() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			feriado.removeFeriado(5,28);
		  });
	}
	
	@Test
	void testDarLosDiasFeriadosDelMesDado() {
		int result = feriado.feriadosDelMes(5).size();
		
		assertEquals(2, result);
	}
	
	@Test
	void testDarLosDiasFeriadosDelMesDadoYaQueNoExisteDichoMes() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			feriado.feriadosDelMes(8);
		  });
	}

}

package ar.edu.unq.po2.sistemaDeAlquileres.Ranking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.AssertionFailedError;

class RankingTestCase {
	private Ranking ranking;
	
	@BeforeEach
	void setUp() throws Exception {
		ranking= new Ranking();
	}
	
	@Test
	void testAddCategoria() {
		ranking.addCategoria("Gas");
		int result= ranking.cantidadDePuntajesSegunCategoria("Gas");
		
		assertEquals(0, result);
	}
	
	@Test
	void testAddPuntajeQueNoSeEncuentraYaCargado() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			ranking.addPuntajePorCategoria("Gas", 2);
		  });
	}
	
	@Test
	void testAddPuntajeQueSeEncuentraYaCargado() {
		ranking.addCategoria("Gas");
		ranking.addPuntajePorCategoria("Gas", 2);
		ranking.addPuntajePorCategoria("Gas", 5);
		int result= ranking.cantidadDePuntajesSegunCategoria("Gas");
		
		assertEquals(2, result);
	}
	
	@Test
	void testAddPuntajeNoValido() {
		ranking.addCategoria("Gas");
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			ranking.addPuntajePorCategoria("Gas", 8);
		  });
	}
	
	@Test
	void testCantidadDePuntosCuandoNoSeEnuentraCargadoLaCategoria() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			ranking.cantidadDePuntajesSegunCategoria("Gas");
		  });
	}
	
	
	@Test
	void testPuntajePromedioPorCategoriaValido() {
		ranking.addCategoria("Gas");
		ranking.addPuntajePorCategoria("Gas", 2);
		ranking.addPuntajePorCategoria("Gas", 5);
		int result= ranking.puntajePromedioPorCategoria("Gas");
		
		assertEquals(3, result);
	}

	@Test
	void testPuntajePromedioTotalCuandoHaySoloPuntajesPorCategorias() {
		ranking.addCategoria("Electricidad");
		ranking.addCategoria("Gas");
		ranking.addPuntajePorCategoria("Electricidad", 2);
		ranking.addPuntajePorCategoria("Gas", 4);
		ranking.addPuntajePorCategoria("Gas", 5);
		int result= ranking.puntajeTotalPromedio();

		assertEquals(3, result);
	}
	
	@Test
	void testPuntajePromedioTotalCuandoNoRgistradosPuntos() {
		int result= ranking.puntajeTotalPromedio();

		assertEquals(0, result);
	}
}

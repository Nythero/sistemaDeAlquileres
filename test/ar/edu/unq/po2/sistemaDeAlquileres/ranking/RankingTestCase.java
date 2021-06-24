package ar.edu.unq.po2.sistemaDeAlquileres.ranking;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RankingTestCase {
	private Ranking ranking;
	
	@BeforeEach
	void setUp() throws Exception {
		ranking= new Ranking();
	}
	
	@Test
	void testAddCategoria() throws Exception {
		ranking.addCategoria("Gas");
		int result = ranking.cantidadDePuntajesSegunCategoria("Gas");
		
		assertEquals(0, result);
	}

	@Test
	void testAddCategoriaRepetida() throws Exception {
		ranking.addCategoria("Gas");
		assertThrows(Exception.class, () -> ranking.addCategoria("Gas"));
	}
	
	@Test
	void testAddPuntajeQueNoSeEncuentraCargado() {
		Assertions.assertThrows(Exception.class, () -> {
			ranking.addPuntajePorCategoria("Gas", 2);
		  });
	}
	
	@Test
	void testAddPuntajeQueSeEncuentraYaCargado() throws Exception {
		ranking.addCategoria("Gas");
		ranking.addPuntajePorCategoria("Gas", 2);
		ranking.addPuntajePorCategoria("Gas", 5);
		int result = ranking.cantidadDePuntajesSegunCategoria("Gas");
		
		assertEquals(2, result);
	}
	
	@Test
	void testAddPuntajeNoValido() throws Exception {
		ranking.addCategoria("Gas");
		assertThrows(Exception.class, () -> {
			ranking.addPuntajePorCategoria("Gas", 8);
		  });
		assertThrows(Exception.class, () -> {
			ranking.addPuntajePorCategoria("Gas", -1);
		  });
	}
	
	@Test
	void testCantidadDePuntosCuandoNoSeEnuentraCargadoLaCategoria() {
		Assertions.assertThrows(Exception.class, () -> {
			ranking.cantidadDePuntajesSegunCategoria("Gas");
		  });
	}
	
	
	@Test
	void testPuntajePromedioPorCategoriaValido() throws Exception {
		ranking.addCategoria("Gas");
		ranking.addCategoria("Pas");
		
		ranking.addPuntajePorCategoria("Gas", 2);
		ranking.addPuntajePorCategoria("Gas", 5);
		
		int resultGas = ranking.puntajePromedioPorCategoria("Gas");
		int resultPas = ranking.puntajePromedioPorCategoria("Pas");
		
		assertEquals(3, resultGas);
		assertEquals(0, resultPas);
	}

	@Test
	void testPuntajePromedioTotalCuandoHaySoloPuntajesPorCategorias() throws Exception {
		ranking.addCategoria("Electricidad");
		ranking.addCategoria("Gas");
		ranking.addPuntajePorCategoria("Electricidad", 2);
		ranking.addPuntajePorCategoria("Gas", 4);
		ranking.addPuntajePorCategoria("Gas", 5);
		int result= ranking.puntajeTotalPromedio();

		assertEquals(3, result);
	}
	
	@Test
	void testPuntajePromedioTotalCuandoNoRgistradosPuntos() throws Exception {
		int result= ranking.puntajeTotalPromedio();

		assertEquals(0, result);
	}
	
	@Test
	void testAgregarComentario() {
		assertDoesNotThrow(() -> ranking.agregarComentario(""));
	}
}

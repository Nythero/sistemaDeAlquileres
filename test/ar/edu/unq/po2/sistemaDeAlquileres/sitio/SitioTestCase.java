package ar.edu.unq.po2.sistemaDeAlquileres.sitio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

class SitioTestCase {

	Sitio sitio;
	Usuario usuario1;
	Usuario usuario2;
	Inmueble inmueble1;
	Inmueble inmueble2;
	
	@BeforeEach
	void setup() {
		sitio = new Sitio();
		usuario1 = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		inmueble1 = mock(Inmueble.class);
		inmueble2 = mock(Inmueble.class);
	}
	
	@Test
	void Sitio_EsUsuario_Success() throws Exception {
		sitio.registrarUsuario(usuario1);
		
		assertTrue(sitio.esUsuario(usuario1));
		assertFalse(sitio.esUsuario(usuario2));
	}
	
	@Test
	void Sitio_RegistrarUsuario_Success() throws Exception{
		assertDoesNotThrow(() -> sitio.registrarUsuario(usuario1));
		assertDoesNotThrow(() -> sitio.registrarUsuario(usuario2));
	}
	
	@Test
	void Sitio_RegistrarUsuario_Exception() throws Exception{
		sitio.registrarUsuario(usuario1);
		
		assertThrows(Exception.class, () -> sitio.registrarUsuario(usuario1));
	}

	@Test
	void Sitio_AgregarInmueble_Success() throws Exception{
		assertDoesNotThrow(() -> sitio.agregarInmueble(inmueble1));
		assertDoesNotThrow(() -> sitio.agregarInmueble(inmueble2));
	}
	
	@Test
	void Sitio_AgregarInmueble_Exception() throws Exception{
		sitio.agregarInmueble(inmueble1);

		assertThrows(Exception.class, () -> sitio.agregarInmueble(inmueble1));
	}
	
	@Test
	void Sitio_AgregarCategoriaAInquilino_Success() throws Exception {
		Ranking ranking = mock(Ranking.class);
		sitio.registrarUsuario(usuario1);
		sitio.registrarUsuario(usuario2);
		
		when(usuario1.getRankingComoInquilino()).thenReturn(ranking);
		when(usuario2.getRankingComoInquilino()).thenReturn(ranking);
		
		sitio.addCategoriaAInquilino("cat");
		
		verify(usuario1).getRankingComoInquilino();
		verify(usuario2).getRankingComoInquilino();
	}
	
	@Test
	void Sitio_AgregarCategoriaADuenho_Success() throws Exception {
		Ranking ranking = mock(Ranking.class);
		sitio.registrarUsuario(usuario1);
		sitio.registrarUsuario(usuario2);
		
		when(usuario1.getRankingComoDuenho()).thenReturn(ranking);
		when(usuario2.getRankingComoDuenho()).thenReturn(ranking);
		
		sitio.addCategoriaADuenho("cat");
		
		verify(usuario1).getRankingComoDuenho();
		verify(usuario2).getRankingComoDuenho();
	}
	
	@Test
	void Sitio_AgregarCategoriaAInmueble_Success() throws Exception {
		Ranking ranking = mock(Ranking.class);
		sitio.agregarInmueble(inmueble1);
		sitio.agregarInmueble(inmueble2);
		
		when(inmueble1.getRanking()).thenReturn(ranking);
		when(inmueble2.getRanking()).thenReturn(ranking);
		
		sitio.addCategoriaAInmueble("cat");
		
		verify(inmueble1).getRanking();
		verify(inmueble2).getRanking();
	}
}

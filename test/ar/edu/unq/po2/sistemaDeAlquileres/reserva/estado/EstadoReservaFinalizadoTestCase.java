package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.sistemaDeAlquileres.inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

class EstadoReservaFinalizadoTestCase {

	Reserva reserva;
	EstadoReserva estado;
	
	@BeforeEach
	void setup(){
		estado = new EstadoReservaFinalizado();
		reserva = mock(Reserva.class);
	}
	
	@Test
	void EstadoReservaFinalizado_Aceptar_CambioDeEstadoError() throws CambioDeEstadoError {
		assertThrows(CambioDeEstadoError.class, () -> estado.aceptar(reserva));
	}
	
	@Test
	void EstadoReservaFinalizado_Cancelar_CambioDeEstadoError() throws CambioDeEstadoError {
		assertThrows(CambioDeEstadoError.class, () -> estado.cancelar(reserva));
	}
	
	@Test
	void EstadoReservaFinalizado_Finalizar_CambioDeEstadoError() throws CambioDeEstadoError {
		assertThrows(CambioDeEstadoError.class, () -> estado.finalizar(reserva));
	}
	
	@Test
	void EstadoReservaFinalizado_ComentarInmueble_Success() throws EstadoEquivocadoError {
		Inmueble inmueble = mock(Inmueble.class);
		when(reserva.getInmueble()).thenReturn(inmueble);
		
		estado.comentarInmueble(reserva, "");
		
		verify(inmueble).agregarComentario("");
	}

	@Test
	void EstadoReservaFinalizado_PuntuarDuenho_Success() throws EstadoEquivocadoError {
		Inmueble inmueble = mock(Inmueble.class);
		Usuario duenho = mock(Usuario.class);
		Ranking ranking = mock(Ranking.class);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getDueño()).thenReturn(duenho);
		when(duenho.getRankingComoDuenho()).thenReturn(ranking);
		
		estado.puntuarDuenho(reserva, "Facherocidad", 10);
		
		verify(ranking).addPuntajePorCategoria("Facherocidad", 10);
	}

	@Test
	void EstadoReservaFinalizado_PuntuarInquilino_Success() throws EstadoEquivocadoError {
		Usuario inquilino = mock(Usuario.class);
		Ranking ranking = mock(Ranking.class);
		when(reserva.getSolicitante()).thenReturn(inquilino);
		when(inquilino.getRankingComoInquilino()).thenReturn(ranking);
		
		estado.puntuarInquilino(reserva, "Facherocidad", 10);
		
		verify(ranking).addPuntajePorCategoria("Facherocidad", 10);
	}

	@Test
	void EstadoReservaFinalizado_PuntuarInmueble_Success() throws EstadoEquivocadoError{
		Inmueble inmueble = mock(Inmueble.class);
		Ranking ranking = mock(Ranking.class);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getRanking()).thenReturn(ranking);
		
		estado.puntuarInmueble(reserva, "Facherocidad", 10);
		
		verify(ranking).addPuntajePorCategoria("Facherocidad", 10);
	}

	@Test
	void EstadoReservaFinalizado_EstaPendienteDeAprobacion_False() {
		assertFalse(estado.estaPendienteDeAprobacion());
	}

	@Test
	void EstadoReservaFinalizado_EstaConcretada_False() {
		assertFalse(estado.estaConcretada());
	}
}
package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;

public class EstadoReservaFinalizado extends EstadoReserva {

	@Override
	public void comentarInmueble(Reserva reserva, String comentario) {
		reserva.getInmueble().agregarComentario(comentario);
	}

	@Override
	public void puntuarDuenho(Reserva reserva, String categoria, Integer puntaje) throws Exception {
		reserva.getInmueble().getDuenho().getRankingComoDuenho().addPuntajePorCategoria(categoria, puntaje);
	}

	@Override
	public void puntuarInquilino(Reserva reserva, String categoria, Integer puntaje) throws Exception {
		reserva.getSolicitante().getRankingComoInquilino().addPuntajePorCategoria(categoria, puntaje);
	}
	
	@Override
	public void puntuarInmueble(Reserva reserva, String categoria, Integer puntaje) throws Exception {
		reserva.getInmueble().getRanking().addPuntajePorCategoria(categoria, puntaje);
	}

	@Override
	String codigo() {
		return "Finalizado";
	}
}

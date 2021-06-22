package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;

public class EstadoReservaFinalizado implements EstadoReserva {

	@Override
	public EstadoReserva cancelar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError("Finalizado", "cancelar");
	}

	@Override
	public EstadoReserva aceptar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError("Finalizado", "aceptar");
	}

	@Override
	public EstadoReserva finalizar(Reserva reserva) throws CambioDeEstadoError {
		throw new CambioDeEstadoError("Finalizado", "finalizar");
	}

	@Override
	public void setComentario(Reserva reserva, String comentario) {
		reserva.getInmueble().agregarComentario(comentario);
	}

	@Override
	public void setPuntajeADuenho(Reserva reserva, String categoria, Integer puntaje) {
		reserva.getInmueble().getDueño().getRankingComoDuenho().addPuntajePorCategoria(categoria, puntaje);
	}

	@Override
	public void setPuntajeAInquilino(Reserva reserva, String categoria, Integer puntaje) {
		reserva.getSolicitante().getRankingComoInquilino().addPuntajePorCategoria(categoria, puntaje);
	}
	
	@Override
	public void setPuntajeAInmueble(Reserva reserva, String categoria, Integer puntaje) {
		reserva.getInmueble().getRanking().addPuntajePorCategoria(categoria, puntaje);
	}

	@Override
	public boolean estaPendienteDeAprobacion() {
		return false;
	}

	@Override
	public boolean estaConcretada() {
		return false;
	}
}

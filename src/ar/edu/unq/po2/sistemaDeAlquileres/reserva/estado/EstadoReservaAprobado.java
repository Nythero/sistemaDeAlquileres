package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;

public class EstadoReservaAprobado implements EstadoReserva {

	@Override
	public EstadoReserva cancelar(Reserva reserva) {
		reserva.getInmueble().cancelarReserva(reserva);
		return new EstadoReservaCancelado();
	}

	@Override
	public EstadoReserva aceptar(Reserva reserva) throws CambioDeEstadoError{
		throw new CambioDeEstadoError("Aprobado", "aceptar");
	}

	@Override
	public EstadoReserva finalizar(Reserva reserva) {
		reserva.getInmueble().finalizarReserva(reserva);
		return new EstadoReservaFinalizado();
	}

	@Override
	public void setComentario(Reserva reserva, String comentario) throws EstadoEquivocadoError{
		throw new EstadoEquivocadoError("Aprobado", " publicar un comentario");
	}

	@Override
	public void setPuntajeADuenho(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Aprobado", " puntuar al dueño");
	}

	@Override
	public void setPuntajeAInquilino(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError {
		throw new EstadoEquivocadoError("Aprobado", " puntuar al inquilino");
	}

	@Override
	public void setPuntajeAInmueble(Reserva reserva, String categoria, Integer puntaje) throws EstadoEquivocadoError{
		throw new EstadoEquivocadoError("Aprobado", " puntuar al inmueble");
	}
}

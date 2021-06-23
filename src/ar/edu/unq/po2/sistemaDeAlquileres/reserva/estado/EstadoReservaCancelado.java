package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

public class EstadoReservaCancelado extends EstadoReserva {
	@Override
	String codigo() {
		return "Cancelado";
	}
}
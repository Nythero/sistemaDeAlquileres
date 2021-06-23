package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

class EstadoReservaCancelado extends EstadoReserva {
	@Override
	public String codigo() {
		return "Cancelado";
	}
}
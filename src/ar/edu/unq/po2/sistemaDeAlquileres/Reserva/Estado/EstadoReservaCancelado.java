package ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado;

class EstadoReservaCancelado extends EstadoReserva {
	@Override
	public String codigo() {
		return "Cancelado";
	}
}
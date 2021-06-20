package ar.edu.unq.po2.sistemaDeAlquileres.reserva;

public interface EstadoReserva {

	EstadoReserva cancelar(Reserva reserva);

	EstadoReserva aceptar(Reserva reserva);

	EstadoReserva finalizar(Reserva reserva);

}

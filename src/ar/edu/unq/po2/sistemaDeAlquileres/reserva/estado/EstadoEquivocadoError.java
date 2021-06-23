package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

public class EstadoEquivocadoError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EstadoEquivocadoError(String estado, String accion) {
		super("En el estado " + estado + " no se puede " + accion);
	}

}

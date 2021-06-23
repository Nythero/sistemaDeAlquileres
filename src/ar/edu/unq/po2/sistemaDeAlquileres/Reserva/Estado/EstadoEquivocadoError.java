package ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado;

public class EstadoEquivocadoError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public EstadoEquivocadoError(String estado, String accion) {
		message = "En el estado " + estado + " no se puede " + accion;
	}

}

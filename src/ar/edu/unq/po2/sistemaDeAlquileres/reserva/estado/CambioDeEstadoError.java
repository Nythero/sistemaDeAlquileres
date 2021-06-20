package ar.edu.unq.po2.sistemaDeAlquileres.reserva.estado;

public class CambioDeEstadoError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public CambioDeEstadoError(String estado, String accion) {
		message = "El estado " + estado + " no se puede " + accion;
	}

}

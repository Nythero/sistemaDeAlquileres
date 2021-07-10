package ar.edu.unq.po2.sistemaDeAlquileres.Mail;

public class Mail {
	String destinatario;
	String remitente;
	String asunto;
	String mensaje;
	
	public Mail(String destinatario, String remitente, String asunto, String mensaje) {
		this.destinatario = destinatario;
		this.remitente    = remitente;
		this.asunto       = asunto;
		this.mensaje      = mensaje;
	}
}

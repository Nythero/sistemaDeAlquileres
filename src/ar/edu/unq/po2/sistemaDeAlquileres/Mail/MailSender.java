package ar.edu.unq.po2.sistemaDeAlquileres.Mail;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public class MailSender {

	static ServidorMail servidor;
	
	public static void setServidor(ServidorMail servidorNuevo) {
		servidor = servidorNuevo;
	}
	
	/**
	 * Manda un mail notificando que la reserva fue cancelada al duenho del inmueble y a la persona que reservó el inmueble
	 * 
	 * @param reserva
	 */
	public static void mandarMailDeCancelacion(Reserva reserva) {
		MailSender.mandarMail(reserva.getDuenho().getDireccionDeEmail(), 
							  "soporte@sistemaAlquiler.com.ar", 
							  "Reserva de inmueble", 
							  "La reserva fue cancelada");
		MailSender.mandarMail(reserva.getSolicitante().getDireccionDeEmail(), 
				  "soporte@sistemaAlquiler.com.ar", 
				  "Reserva de inmueble", 
				  "La reserva fue cancelada");
	}

	/**
	 * Manda un mail notificando que la reserva fue aceptada al duenho del inmueble y a la persona que reservó el inmueble
	 * 
	 * @param reserva
	 */
	public static void mandarMailDeConfirmacion(Reserva reserva) {
		MailSender.mandarMail(reserva.getDuenho().getDireccionDeEmail(), 
				  "soporte@sistemaAlquiler.com.ar", 
				  "Reserva de inmueble", 
				  "La reserva fue confirmada");
		MailSender.mandarMail(reserva.getSolicitante().getDireccionDeEmail(), 
				  "soporte@sistemaAlquiler.com.ar", 
				  "Reserva de inmueble", 
				  "La reserva fue confirmada");
	}
	
	/**
	 * Manda un mail al destinatario, de parte del remitente con el asunto y el mensaje dados
	 * @param emailDestinatario
	 * @param emailRemitente
	 * @param asunto
	 * @param message
	 */
	public static void mandarMail(String emailDestinatario, String emailRemitente, String asunto, String message) {
		Mail mail = new Mail(emailDestinatario, emailRemitente, asunto, message);
		servidor.enviar(mail);
	}
}

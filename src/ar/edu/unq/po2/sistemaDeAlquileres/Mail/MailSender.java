package ar.edu.unq.po2.sistemaDeAlquileres.Mail;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;

public class MailSender {

	static ServidorMail servidor;
	
	public static void setServidor(ServidorMail servidorNuevo) {
		servidor = servidorNuevo;
	}
	
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
	
	public static void mandarMail(String emailDestinatario, String emailRemitente, String asunto, String message) {
		Mail mail = new Mail(emailDestinatario, emailRemitente, asunto, message);
		servidor.enviar(mail);
	}
}

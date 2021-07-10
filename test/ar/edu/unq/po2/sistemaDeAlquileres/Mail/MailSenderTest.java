package ar.edu.unq.po2.sistemaDeAlquileres.Mail;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

class MailSenderTest {
	ServidorMail servidor;
	Usuario inquilino;
	Usuario duenho;
	Reserva reserva;
	
	@BeforeEach
	void setup() {
		servidor = mock(ServidorMail.class);
		MailSender.setServidor(servidor);
		inquilino = mock(Usuario.class);
		duenho = mock(Usuario.class);
		reserva = mock(Reserva.class);
		when(inquilino.getDireccionDeEmail()).thenReturn("inquilino@email.com");
		when(duenho.getDireccionDeEmail()).thenReturn("duenho@email.com");
		when(reserva.getDuenho()).thenReturn(duenho);
		when(reserva.getSolicitante()).thenReturn(inquilino);
	}
	
	@Test
	void MailSender_MandarMailDeCancelacion_Success() {
		MailSender.mandarMailDeCancelacion(reserva);
		
		verify(reserva).getDuenho();
		verify(reserva).getSolicitante();
		verify(servidor, times(2)).enviar(any(Mail.class));
	}
	
	@Test
	void MailSender_MandarMailDeConfirmacion_Success() {
		MailSender.mandarMailDeConfirmacion(reserva);
		
		verify(reserva).getDuenho();
		verify(reserva).getSolicitante();
		verify(servidor, times(2)).enviar(any(Mail.class));
	}
	
	@Test
	void MailSender_MandarMail_Success() {
		MailSender.mandarMail("destinatario@algo.com", "remitente@algo.com", "asunto", "mensaje");
		verify(servidor).enviar(any(Mail.class));
	}
}

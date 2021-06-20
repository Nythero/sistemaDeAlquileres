package ar.edu.unq.po2.sistemaDeAlquileres.usuario;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.sistemaDeAlquileres.*;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.EstadoReserva;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.sitio.Sitio;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

public class UsuarioTestCase {

	private String nombre;
	private String mail;
	private String telefono;
	private Ranking rankingComoDuenho;
	private Ranking rankingComoInquilino;
	private Date fechaDeCreacion;  
	private Sitio sitio;
	private Usuario usuario1;
	
	
	//nos falta que el usuario pueda agregar inmuebles a su lista preguntandole al sitio 
	
	@BeforeEach
	void setUp() throws Exception {
		Inmueble inmueble1 = mock(Inmueble.class);
		Inmueble inmueble2 = mock(Inmueble.class);
		Reserva reserva1 = mock(Reserva.class);
		Reserva reserva2 = mock(Reserva.class); 
		Ranking rakingComoDuenho = mock(Ranking.class);
		Date fechaDeCreacion = new Date(2000,06,07);
		Sitio sitio = mock(Sitio.class);
		Usuario usuario1 = new Usuario("Miguel", "mail@gmail.com","12345",rankingComoDuenho,
										rankingComoInquilino);
	}
	
	
}







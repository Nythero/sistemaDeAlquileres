package ar.edu.unq.po2.sistemaDeAlquileres.Reserva;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.sistemaDeAlquileres.*;
import ar.edu.unq.po2.sistemaDeAlquileres.Dia.Dia;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

class ReservaTestCase {

	Reserva reserva;
	Inmueble inmueble;
	ArrayList<Dia> dias;
	EstadoReserva estado;
	Usuario solicitante;
	
	@Test
	void Reserva_Aceptar_Success() {
		inmueble = mock(Inmueble.class);
		dias = new ArrayList<Dia>();
		estado = mock(EstadoReserva.class);
		solicitante = mock(Usuario.class);
		reserva = new Reserva(dias, inmueble, solicitante, estado);
		
		reserva.aceptar();
		
		verify(estado).aceptar(reserva);
	}
	
	@Test
	void Reserva_Cancelar_Success() {
		inmueble = mock(Inmueble.class);
		dias = new ArrayList<Dia>();
		estado = mock(EstadoReserva.class);
		solicitante = mock(Usuario.class);
		reserva = new Reserva(dias, inmueble, solicitante, estado);
		
		reserva.cancelar();
		
		verify(estado).cancelar(reserva);
	}
	
	@Test
	void Reserva_Finalizar_Success() {
		inmueble = mock(Inmueble.class);
		dias = new ArrayList<Dia>();
		estado = mock(EstadoReserva.class);
		solicitante = mock(Usuario.class);
		reserva = new Reserva(dias, inmueble, solicitante, estado);
		
		reserva.finalizar();
		
		verify(estado).finalizar(reserva);
	}
	
	@Test
	void Reserva_GetDia_ReturnDia() {
		inmueble = mock(Inmueble.class);
		dias = new ArrayList<Dia>();
		Dia dia = mock(Dia.class);
		estado = mock(EstadoReserva.class);
		solicitante = mock(Usuario.class);
		
		dias.add(dia);
		
		reserva = new Reserva(dias, inmueble, solicitante, estado);
		
		Assertions.assertEquals(dia, reserva.getDia(0));
	}

}

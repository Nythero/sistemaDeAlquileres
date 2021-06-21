package ar.edu.unq.po2.sistemaDeAlquileres.Reserva;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.sistemaDeAlquileres.*;
<<<<<<< HEAD
=======
import ar.edu.unq.po2.sistemaDeAlquileres.Dia.Dia;
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
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

	@Test
	void Reserva_GetCantidadDias_Get3() {
		inmueble = mock(Inmueble.class);
		dias = new ArrayList<Dia>();
		Dia dia1 = mock(Dia.class);
		Dia dia2 = mock(Dia.class);
		Dia dia3 = mock(Dia.class);
		estado = mock(EstadoReserva.class);
		solicitante = mock(Usuario.class);
		
		dias.add(dia1);
		dias.add(dia2);
		dias.add(dia3);
		
		reserva = new Reserva(dias, inmueble, solicitante, estado);
		
		Assertions.assertEquals(3, reserva.getCantidadDias());
	}
}

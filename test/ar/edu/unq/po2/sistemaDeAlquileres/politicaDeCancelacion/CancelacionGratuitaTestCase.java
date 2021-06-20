package ar.edu.unq.po2.sistemaDeAlquileres.politicaDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado.RangoDeFechaConPrecioDeterminado;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.EstadoReserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

class CancelacionGratuitaTestCase {
	private CancelacionGratuita cancelacion;
	@Mock private Reserva reserva;
	@Mock private RangoDeFechaConPrecioDeterminado dia;
	@Mock private RangoDeFechaConPrecioDeterminado dia2;
	@Mock private Usuario inquilino;
	@Mock private Inmueble inmueble;
	@Mock private EstadoReserva estado;

	@BeforeEach
	void setUp() throws Exception {
		cancelacion= new CancelacionGratuita();
		reserva= mock(Reserva.class);
		dia= mock(RangoDeFechaConPrecioDeterminado.class);
		dia2= mock(RangoDeFechaConPrecioDeterminado.class);
		inquilino= mock(Usuario.class);
		inmueble = mock(Inmueble.class);
		estado= mock(EstadoReserva.class);
		
	}
	//QUE PASA CUANDO SE RESERVA UN DIA Y QUE PASA CUANDO SE SUPERA LOS 10 DIAS
	@Test
	void testCuandoSeCancelaCon1DiaDeReserva() {
		when (reserva.getCantidadDias()).thenReturn(1);
		when (reserva.getDia(0)).thenReturn(dia);
		when (dia.getFecha()).thenReturn(new Date(2021, 6, 19));
		when (dia.getPrecio()).thenReturn(200);
		cancelacion.cancelarReserva(reserva);
	}
	
	@Test
	void testCuandoSeCancelaCon2Dias() {
		when (reserva.getCantidadDias()).thenReturn(2);
		when (reserva.getDia(0)).thenReturn(dia);
		when (reserva.getDia(1)).thenReturn(dia2);
		when (dia.getFecha()).thenReturn(new Date(2021, 6, 19));
		when (dia2.getFecha()).thenReturn(new Date(2021, 6, 20));
		when (dia.getPrecio()).thenReturn(200);
		when (dia2.getPrecio()).thenReturn(300);
		cancelacion.cancelarReserva(reserva);
	}
	
	@Test
	void testCuandoSeCancelaConMasDeDosDias() {
		when (reserva.getCantidadDias()).thenReturn(3);
		when (reserva.getDia(0)).thenReturn(dia);
		when (reserva.getDia(1)).thenReturn(dia2);
		when (dia.getFecha()).thenReturn(new Date(2021, 6, 19));
		when (dia2.getFecha()).thenReturn(new Date(2021, 6, 20));
		when (dia.getPrecio()).thenReturn(200);
		when (dia2.getPrecio()).thenReturn(300);
		cancelacion.cancelarReserva(reserva);
	}
	
	@Test
	void testCuandoSeCancelaConMasDe10Dias() {
		when (reserva.getCantidadDias()).thenReturn(3);
		when (reserva.getDia(0)).thenReturn(dia);
		when (reserva.getDia(1)).thenReturn(dia2);
		when (dia.getFecha()).thenReturn(new Date(2021, 6, 28));
		when (dia2.getFecha()).thenReturn(new Date(2021, 6, 29));
		when (dia.getPrecio()).thenReturn(200);
		when (dia2.getPrecio()).thenReturn(300);
		cancelacion.cancelarReserva(reserva);
	}
	
	@Test
	void testCuandoSeCancelaConMasDe10Dsias() {
		cancelacion.a(reserva);
	}

	
	

}

package ar.edu.unq.po2.sistemaDeAlquileres.inmueble;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.temporada.Temporada;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;
import ar.edu.unq.po2.sistemaDeAlquileres.rangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.rangoDeFechas.RangosDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.ranking.Ranking;


class InmuebleTestCase {
	private Inmueble inmueble;
	private Date horaDeCheckIn;
	private Date horaDeCheckOut;
	private Temporada precio;
	private Usuario usuario;
	private Ranking ranking;
	private RangosDeFechas rangos;
	
	@BeforeEach
	void setUp() throws Exception {
		horaDeCheckIn= mock(Date.class);
		horaDeCheckOut= mock(Date.class);
		precio= mock(Temporada.class);
		usuario = mock(Usuario.class);
		ranking = mock(Ranking.class);
		rangos = mock(RangosDeFechas.class);
		
		inmueble = new Inmueble(usuario, "apartamento", 200, "Argentina", "Quilmes", "Calle falsa 123",
				3, horaDeCheckIn, horaDeCheckOut, precio, ranking, rangos);
	}
	
	@Test
	void Inmueble_AgregarReserva_Success() {
		Reserva reserva = mock(Reserva.class);
		RangoDeFechas rango = mock(RangoDeFechas.class);
		
		inmueble.agregarFormaDePago("Efectivo");
		
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(reserva.getRangoDeFechas()).thenReturn(rango);
		when(reserva.getFormaDePago()).thenReturn("Efectivo");
		when(reserva.estaPendienteDeAprobacion()).thenReturn(true);
		when(rangos.contains(rango)).thenReturn(true);
		
		assertDoesNotThrow(() -> inmueble.agregarReserva(reserva));
		
		verify(reserva).getInmueble();
		verify(reserva).getRangoDeFechas();
		verify(reserva).getFormaDePago();
		verify(reserva).estaPendienteDeAprobacion();
	}
	
	@Test
	void Inmueble_AgregarReserva_ReservaInvalida() {
		Reserva reserva = mock(Reserva.class);
		RangoDeFechas rango = mock(RangoDeFechas.class);
		when(reserva.getRangoDeFechas()).thenReturn(rango);
		
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(reserva.getFormaDePago()).thenReturn("Efectivo");
		when(reserva.estaPendienteDeAprobacion()).thenReturn(true);
		when(rangos.contains(rango)).thenReturn(true);
		
		assertThrows(Exception.class, () -> inmueble.agregarReserva(reserva));
	}
	
	@Test
	void Inmueble_YaEstaReservado_True() throws Exception {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		RangoDeFechas rango = mock(RangoDeFechas.class);
		inmueble.agregarFormaDePago("Efectivo");
		
		for (int i = 0; i < 3; i++) {
			reservas.add(mock(Reserva.class));
			when(reservas.get(i).getInmueble()).thenReturn(inmueble);
			when(reservas.get(i).getRangoDeFechas()).thenReturn(rango);
			when(rangos.contains(rango)).thenReturn(true);
			when(reservas.get(i).getFormaDePago()).thenReturn("Efectivo");
			when(reservas.get(i).estaPendienteDeAprobacion()).thenReturn(true);
			inmueble.agregarReserva(reservas.get(i));
		}
		
		when(reservas.get(0).estaConcretada()).thenReturn(true);
		when(reservas.get(1).estaConcretada()).thenReturn(false);
		when(reservas.get(2).estaConcretada()).thenReturn(true);
		when(rango.intersectanLosRangos(reservas.get(0).getRangoDeFechas())).thenReturn(false);
		when(rango.intersectanLosRangos(reservas.get(2).getRangoDeFechas())).thenReturn(true);
		
		assertTrue(inmueble.yaEstaReservado(rango));
	}
	
	@Test
	void Inmueble_YaEstaReservado_False() throws Exception {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		RangoDeFechas rango = mock(RangoDeFechas.class);
		inmueble.agregarFormaDePago("Efectivo");
		
		for (int i = 0; i < 3; i++) {
			reservas.add(mock(Reserva.class));
			when(reservas.get(i).getInmueble()).thenReturn(inmueble);
			when(reservas.get(i).getRangoDeFechas()).thenReturn(rango);
			when(rangos.contains(rango)).thenReturn(true);
			when(reservas.get(i).getFormaDePago()).thenReturn("Efectivo");
			when(reservas.get(i).estaPendienteDeAprobacion()).thenReturn(true);
			inmueble.agregarReserva(reservas.get(i));
		}
		
		when(reservas.get(0).estaConcretada()).thenReturn(false);
		when(reservas.get(1).estaConcretada()).thenReturn(false);
		when(reservas.get(2).estaConcretada()).thenReturn(false);
		
		assertFalse(inmueble.yaEstaReservado(rango));
	}
	
}

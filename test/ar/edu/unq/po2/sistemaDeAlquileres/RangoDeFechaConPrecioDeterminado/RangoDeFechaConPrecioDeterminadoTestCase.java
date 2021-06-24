package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;

class RangoDeFechaConPrecioDeterminadoTestCase {
	private RangoDeFechaConPrecioDeterminado rangoDeFechas;
	@Mock private LocalDate fechaInicial;
	@Mock private LocalDate fechaFinal;
	@Mock private Temporada precio;
	
	@BeforeEach
	void setUp() throws Exception {
		fechaInicial= mock(LocalDate.class);
		fechaFinal= mock(LocalDate.class);
		precio= mock(Temporada.class);
		rangoDeFechas= new RangoDeFechaConPrecioDeterminado(fechaInicial,fechaFinal,precio);
	}
	
	@Test
	void testDarPrecioSegunLaTemporada() {
		when(precio.getPrecio(fechaInicial)).thenReturn(500f);
		float result = rangoDeFechas.darPrecioSegunLaTemporada(fechaInicial);
		
		verify(precio).getPrecio(fechaInicial);
		assertEquals(500f,result);
	}
	
	
	@Test
	void testCalcularMontoTotalSegunElRango() {
		when(fechaInicial.isEqual(fechaFinal)).thenReturn(false).thenReturn(false).thenReturn(true);
		when(fechaInicial.plusDays(1)).thenReturn(fechaInicial);
		when(precio.getPrecio(fechaInicial)).thenReturn(500f).thenReturn(600f).thenReturn(500f);
		float result = rangoDeFechas.getMontoTotal();
		
		verify(fechaInicial,times(3)).isEqual(fechaFinal);
		verify(precio,times(3)).getPrecio(fechaInicial);
		assertEquals(1600f, result);
	}
	
	
	@Test
	void testCalcularMontoSegunElDia() {
		when(precio.getPrecio(fechaInicial)).thenReturn(500f);
		float result = rangoDeFechas.darPrecioSegunLaTemporada(fechaInicial);
		
		verify(precio,times(1)).getPrecio(fechaInicial);
		assertEquals(500f, result);
	}
	
	
	@Test
	void testPrecioMaximoEntreElRangoDeFechas() {
		when(fechaInicial.isBefore(fechaFinal)).thenReturn(false);
		when(fechaInicial.isEqual(fechaFinal)).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
		when(fechaInicial.plusDays(1)).thenReturn(fechaInicial);
		when(precio.getPrecio(fechaInicial)).thenReturn(500f).thenReturn(600f).thenReturn(1000f);
		float result = rangoDeFechas.precioMaximoEntreElRangoDeFechas(fechaInicial, fechaFinal);
		
		verify(fechaInicial,times(4)).isEqual(fechaFinal);
		verify(precio,times(5)).getPrecio(fechaInicial);
		assertEquals(1000f,result);
		
	}
	
	@Test
	void testObtenerMontoPorCantidadDeDias() {
		when(fechaInicial.plusDays(1)).thenReturn(fechaInicial);
		when(precio.getPrecio(fechaInicial)).thenReturn(500f).thenReturn(600f);
		float result = rangoDeFechas.obtenerMontoPorCantidadDeDias(2);
		
		verify(precio,times(2)).getPrecio(fechaInicial);
		assertEquals(1100f, result);
	}
	
}

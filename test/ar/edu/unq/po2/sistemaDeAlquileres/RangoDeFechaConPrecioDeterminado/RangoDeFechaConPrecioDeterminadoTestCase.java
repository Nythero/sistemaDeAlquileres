package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Fijo;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;

class RangoDeFechaConPrecioDeterminadoTestCase {
	private RangoDeFechaConPrecioDeterminado rangoDeFechas;
	private LocalDate fechaInicial;
	private LocalDate fechaFinal;
	private Temporada precio;
	
	@BeforeEach
	void setUp() throws Exception {
		fechaInicial= LocalDate.of(2021, 6, 18);
		fechaFinal= LocalDate.of(2021, 6, 21);
		precio= new Fijo(500f);
		rangoDeFechas= new RangoDeFechaConPrecioDeterminado(fechaInicial,fechaFinal,precio);
	}
	
	
	@Test
	void testCalcularMontoSegunElDia() {
		float result = rangoDeFechas.darPrecioSegunLaTemporada(LocalDate.now());
		assertEquals(500f, result);
	}
	
	
	@Test
	void testCalcularMontoTotalSegunElRango() {
		float result = rangoDeFechas.getMontoTotal();
		assertEquals(2000f, result);
	}
}

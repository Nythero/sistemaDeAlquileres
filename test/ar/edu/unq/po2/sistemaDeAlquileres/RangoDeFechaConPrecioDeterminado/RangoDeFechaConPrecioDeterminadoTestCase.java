package ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Fijo;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;

class RangoDeFechaConPrecioDeterminadoTestCase {
	private RangoDeFechaConPrecioDeterminado rangoDeFechas;
	@Mock private LocalDate fechaInicial;
	@Mock private LocalDate fechaFinal;
	@Mock private Temporada precio;
	
	@BeforeEach
	void setUp() throws Exception {
		fechaInicial= LocalDate.of(2021, 6, 18);
		fechaFinal= LocalDate.of(2021, 6, 21);
		precio= new Fijo(500f);
		rangoDeFechas= new RangoDeFechaConPrecioDeterminado(fechaInicial,fechaFinal,precio);
	}

	@Test
	void testCalcularMontoTotalSegunElRango() {
		float result = rangoDeFechas.getMontoTotal();
		assertEquals(2000f, result);
	}

}

package ar.edu.unq.po2.sistemaDeAlquileres.Observable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.sistemaDeAlquileres.IObsevers.IObserver;
import ar.edu.unq.po2.sistemaDeAlquileres.inmueble.Inmueble;
import junit.framework.AssertionFailedError;

class ObservableTestCase {
	private Observable observable;
	@Mock private IObserver observer1;
	@Mock private IObserver observer2;
	@Mock private Inmueble inmueble;
	
	@BeforeEach
	void setUp() throws Exception {
		observable = new Observable();
		observer1= mock(IObserver.class);
		observer2= mock(IObserver.class);
		inmueble= mock(Inmueble.class);
		
	}

	@Test
	void testCantidadDeSuscripcionesAlIniciar() {
		int result = observable.getCantidadDeSuscripciones();
		
		assertEquals(0, result);
	}
	
	@Test
	void testCantidadDeSuscripcionesAlAgregarObserverCuandoEstandoVacio() {
		observable.add("Baja de precio", observer1);
		int result = observable.getCantidadDeSuscripciones();
		
		assertEquals(1, result);
	}
	
	@Test
	void testCantidadDeSuscripcionesAlAgregarObserverCuandoYaEstaLaSuscripcion() {
		observable.add("Baja de precio", observer1);
		observable.add("Baja de precio", observer2);
		int result = observable.getCantidadDeSuscritosAUnaSuscripcion("Baja de precio");
		
		assertEquals(2, result);
	}
	
	@Test
	void testNoSeAgreganObseverRepetidos() {
		observable.add("Baja de precio", observer1);
		observable.add("Baja de precio", observer1);
		int result = observable.getCantidadDeSuscritosAUnaSuscripcion("Baja de precio");
		
		assertEquals(1, result);
	}
	
	@Test
	void testSeDaErrorCuandoSeQuitaObserverCuandoNoExisteLaCategoria() {
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			observable.remove("Baja de precio", observer1);
		  });
	}
	
	@Test
	void testSeDaErrorCuandoSeQuitaObserverCuandoNoEstaSuscriptoACategoria() {
		observable.add("Baja de precio", observer2);
		Assertions.assertThrows(AssertionFailedError.class, () -> {
			observable.remove("Baja de precio", observer1);
		  });
	}
	
	@Test
	void testSePuedeEliminarAlObserverEnLaCategoriaDada() {
		observable.add("Baja de precio", observer1);
		observable.add("Baja de precio", observer2);
		observable.remove("Baja de precio", observer1);
		int result = observable.getCantidadDeSuscritosAUnaSuscripcion("Baja de precio");
		
		assertEquals(1, result);
	}
	
	@Test
	void testNoSeNotificaANadieYAQueEstaVacio() {
		observable.notify("Baja de Precio", inmueble, null);
		
		verifyZeroInteractions(inmueble);
	}
	
	@Test
	void testSeNotificanALosSuscriptores() {
		observable.add("Baja de precio", observer1);
		observable.add("Baja de precio", observer2);
		observable.notify("Baja de precio", inmueble,400f);
		
		verify(observer1,times(1)).update(inmueble,400f);
		verify(observer2,times(1)).update(inmueble, 400f);
	}
}

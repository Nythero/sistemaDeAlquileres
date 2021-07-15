package ar.edu.unq.po2.sistemaDeAlquileres.IObsevers;

public class HomePagePublisher {
	
	/**
	 * Dada una informacion la publica en la pagina
	 * @param message
	 */
	public void publish(String message) {
		System.out.println(message); 
	}
}

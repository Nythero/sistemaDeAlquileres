package ar.edu.unq.po2.sistemaDeAlquileres.IObsevers;

public class HomePagePublisher {
	
	/**
	 * Publica en la pagina la informacion dada
	 * @param message
	 */
	public void publish(String message) {
		System.out.println(message); 
	}
}

package ar.edu.unq.po2.sistemaDeAlquileres.IObsevers;

import ar.edu.unq.po2.sistemaDeAlquileres.inmueble.Inmueble;

public class Trivago implements IObserver{
	private HomePagePublisher homePagePublisher;
	
	public Trivago() {
		this.homePagePublisher= new HomePagePublisher();
	}

	@Override
	public void update(Inmueble inmueble, Object arg) {
		float precio = (float)arg;
		this.homePagePublisher.publish("Un inmueble <" + inmueble.getTipoDeInmueble() +
				                        "> a tan sólo " + precio + " pesos");
	}

}

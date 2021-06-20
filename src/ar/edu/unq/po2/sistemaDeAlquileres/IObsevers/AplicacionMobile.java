package ar.edu.unq.po2.sistemaDeAlquileres.IObsevers;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

public class AplicacionMobile implements IObserver{
	private PopUpWindow popUpWindow;
	
	public AplicacionMobile() {
		this.popUpWindow= new PopUpWindow();
	}

	/**
	 * Actualiza el la pagina cuando le llega la notificacion
	 */
	@Override
	public void update(Inmueble inmueble, Object arg) {
		this.popUpWindow.popUp("El/la <" + inmueble.getTipoDeInmueble() +
				                 "> que te interesa se ha liberado! Corre a reservarlo!"
				                ,"Rojo"
				                ,14);
	}	
}

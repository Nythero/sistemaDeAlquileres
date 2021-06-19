package ar.edu.unq.po2.sistemaDeAlquileres.IObsevers;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;

public class AplicacionMobile implements IObserver{
	private PopUpWindow popUpWindow;
	
	public AplicacionMobile() {
		this.popUpWindow= new PopUpWindow();
	}

	@Override
	public void update(Inmueble inmueble, Integer precio) {
		this.popUpWindow.popUp("El/la <" + inmueble.getTipoDeInmueble() +
				                 "> que te interesa se ha liberado! Corre a reservarlo!"
				                ,"Rojo"
				                ,14);
	}	
}

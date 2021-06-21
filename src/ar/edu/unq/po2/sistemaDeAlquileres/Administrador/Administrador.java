package ar.edu.unq.po2.sistemaDeAlquileres.Administrador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Sitio.Sitio;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

public class Administrador {
	private ArrayList<String> tiposDeInmueble;
	private ArrayList<String> tiposDeServicios;
	
	public void addCategoriaAInquilinos (Sitio sitio,String categoria) {
		sitio.addCategoriaAInquilino(categoria);
	}
	
	public void addCategoriaADuenho (Sitio sitio, String categoria) {
		sitio.addCategoriaADuenho(categoria);
<<<<<<< HEAD
	} 
=======
	}
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
	
	
	public void addCategoriaAInmueble (Sitio sitio, String categoria) {
		sitio.addCategoriaAInmueble(categoria);
<<<<<<< HEAD
	}
	
	public void addTipoDeInmueble(String tipoDeInmueble) {
=======
	}// preguntar si hacer lo mismo pero directamente con ranking 
	//Ranking.class. addCategoriaAInmueble porque asi, solo le agrega la categoria a los inmuebles que estan
	//lo mismo en los metodos de abajo
	
/*	public void addTipoDeInmueble(String tipoDeInmueble) {
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
		Inmueble.darDeAltaAlTipoDeInmueble(tipoDeInmueble);
	}

	public void addAltaServicio(String servicio) {
		Inmueble.darDeAltaElServicio(servicio);
	}
<<<<<<< HEAD
=======
	*/
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
	
	
	//obtiene el topten de los inquilinos, es decir los usuarios que mas veces alquilaron
	public ArrayList<Usuario> getTopTen(Sitio sitio) {
		return (sitio.obtenerElTopTenDeInquilinos());
	}		
<<<<<<< HEAD
		 
=======
		
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
	public Integer getCantidadDeInmueblesLibres(Sitio sitio) {
		return (sitio.getCantidadDeInmueblesLibres());
	}
	
	public Integer getTasaOcupacional(Sitio sitio) {
		return (sitio.getTasaOcupacional());
	}
}

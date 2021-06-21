package ar.edu.unq.po2.sistemaDeAlquileres.Administrador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;
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
	} 
	
	
	public void addCategoriaAInmueble (Sitio sitio, String categoria) {
		sitio.addCategoriaAInmueble(categoria);
	}
	
	public void addTipoDeInmueble(String tipoDeInmueble) {
		Inmueble.darDeAltaAlTipoDeInmueble(tipoDeInmueble);
	}

	public void addAltaServicio(String servicio) {
		Inmueble.darDeAltaElServicio(servicio);
	}
	
	
	//obtiene el topten de los inquilinos, es decir los usuarios que mas veces alquilaron
	public ArrayList<Usuario> getTopTen(Sitio sitio) {
		return (sitio.obtenerElTopTenDeInquilinos());
	}		
		 
	public Integer getCantidadDeInmueblesLibres(Sitio sitio) {
		return (sitio.getCantidadDeInmueblesLibres());
	}
	
	public Integer getTasaOcupacional(Sitio sitio) {
		return (sitio.getTasaOcupacional());
	}
}

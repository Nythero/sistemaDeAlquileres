package ar.edu.unq.po2.sistemaDeAlquileres.Administrador;

import java.util.ArrayList;

import ar.edu.unq.po2.sistemaDeAlquileres.Sitio.Sitio;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

public class Administrador {
	
	public void addCategoriaAInquilinos (Sitio sitio,String categoria) throws Exception {
		sitio.addCategoriaAInquilino(categoria);
	}
	
	public void addCategoriaADuenho (Sitio sitio, String categoria) throws Exception {
		sitio.addCategoriaADuenho(categoria);
	}
	
	
	public void addCategoriaAInmueble (Sitio sitio, String categoria) throws Exception {
		sitio.addCategoriaAInmueble(categoria);
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

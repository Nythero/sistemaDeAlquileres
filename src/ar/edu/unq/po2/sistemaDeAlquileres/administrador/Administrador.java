package ar.edu.unq.po2.sistemaDeAlquileres.administrador;

import java.util.ArrayList;

import ar.edu.unq.po2.sistemaDeAlquileres.sitio.Sitio;

public class Administrador {
	private ArrayList<String> tiposDeInmueble;
	private ArrayList<String> tiposDeServicios;
	
	public void addCategoriaAInquilinos (Sitio sitio,String categoria) {
		
	}
	
	public void addCategoriaADuenho (Sitio sitio, String categoria) {
		
	}
	
	public void addCategoriaAInmueble (Sitio sitio, String categoria) {
		
	}
	
	public void addTipoDeInmueble(String tipoDeInmueble) {
		
	}
	
	public void addAltaServicio(String servicio) {
		
	}
	
	public void getTopTen(Sitio sitio) {
		
	}
	
	public Integer getCantidadDeInmueblesLibres(Sitio sitio) {
		return 2; //to do	
	}
	
	public Integer getTasaOcupacional(Sitio sitio) {
		return 2;//to do
	}
}

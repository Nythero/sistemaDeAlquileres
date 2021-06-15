package ar.edu.unq.po2.sistemaDeAlquileres.Sitio;

import java.util.ArrayList;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

public class Sitio {
	private ArrayList<Usuario> usuarios; 
	private ArrayList<Inmueble> inmuebles;
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public ArrayList<Inmueble> getInmuebles() {
		return inmuebles;
	}
	
	public boolean esUsuario(Usuario usuario) {
		return this.getUsuarios().contains(usuario);
	}
	
	public void registrarUsuario(Usuario usuario) {
		if (!this.esUsuario(usuario)) {
			this.getUsuarios().add(usuario);
		}
	}
	
	
	public void agregarInmueble (Inmueble inmueble) {
		//checkear si es valido 
		this.getInmuebles().add(inmueble);
	}
	
	public void addCategoriaAInquilino(String categoria) {
		
	}
	
	public void addCategoriaAInmueble(String categoria) {
			
	}
	
	public void addCategoriaADuenho(String categoria) {
		
	}
}

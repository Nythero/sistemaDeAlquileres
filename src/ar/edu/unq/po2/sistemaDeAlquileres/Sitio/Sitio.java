package ar.edu.unq.po2.sistemaDeAlquileres.sitio;

import java.util.ArrayList;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

public class Sitio {
	private ArrayList<Usuario> usuarios; 
	private ArrayList<Inmueble> inmuebles;
	
	public Sitio() {
		usuarios  = new ArrayList<Usuario>();
		inmuebles = new ArrayList<Inmueble>();
	}
	
	private ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
	private ArrayList<Inmueble> getInmuebles() {
		return inmuebles;
	}
	
	public boolean esUsuario(Usuario usuario) {
		return this.getUsuarios().contains(usuario);
	}
	
	public void registrarUsuario(Usuario usuario) throws Exception {
		if (this.esUsuario(usuario)) {
			throw new Exception("El usuario ya existe");
		}
		this.getUsuarios().add(usuario);
	}
	
	public void agregarInmueble (Inmueble inmueble) throws Exception {
		if (this.getInmuebles().contains(inmueble)) {
			throw new Exception("El inmueble ya existe");
		}
		this.getInmuebles().add(inmueble);
	}
	
	public void addCategoriaAInquilino(String categoria) {
		this.usuarios.forEach(usuario -> usuario.getRankingComoInquilino().addCategoria(categoria));
	}
	
	public void addCategoriaAInmueble(String categoria) {
		this.inmuebles.forEach(inmueble -> inmueble.getRanking().addCategoria(categoria));
	}
	
	public void addCategoriaADuenho(String categoria) {
		this.usuarios.forEach(usuario -> usuario.getRankingComoDuenho().addCategoria(categoria));
	}
}

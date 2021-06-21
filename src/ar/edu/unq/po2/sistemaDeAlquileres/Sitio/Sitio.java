package ar.edu.unq.po2.sistemaDeAlquileres.Sitio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;
import junit.framework.AssertionFailedError;

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
	
	public Date desdeCuandoElPerfilFueCreado(Usuario usuario) {
		if(!this.esUsuario(usuario)) {
			throw new AssertionFailedError("El usuario no esta registrado");
		}
		else {
			return usuario.getFechaDeCreacion();
		}
	}
	
	public void agregarInmueble (Inmueble inmueble) {
		//checkear si es valido 
		this.getInmuebles().add(inmueble);
	}
	
	public void addCategoriaAInquilino(String categoria) {
		for(Usuario usuario : this.getUsuarios()) {
			usuario.getRankingComoInquilino().addCategoria(categoria);
		}
	}
	
	
	public void addCategoriaADuenho(String categoria) {
		for(Usuario usuario : this.getUsuarios()) {
			usuario.getRankingComoDuenho().addCategoria(categoria);;
		}
	}
	
	
	public void addCategoriaAInmueble(String categoria) {
		for(Inmueble inmueble : this.getInmuebles()) {
			inmueble.getRanking().addCategoria(categoria);  
		} // preguntar si en vez de un for TIENE QUE SER UN RANKING.CLASS Y AHI HACER COSAS
	}
	

	public Integer cuantosInmueblesAlquilo(Usuario usuario) {
		return (usuario.cantidadDeVecesQueAlquilo());
	}

	public ArrayList<Inmueble> buscarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida, Integer huespedes,
												float precioMinimo, float precioMaximo) {
		ArrayList<Inmueble> inmuebles = new ArrayList<Inmueble>();
		for (Inmueble inmueble : this.filtrarInmueblesQuePertenezcanALasFechas(fechaEntrada, fechaSalida)) {
			if (inmueble.getCiudad() == ciudad 
				&& inmueble.getCapacidad() >= huespedes
				&& inmueble.precioMaximoDelRangoDeFechasEntre(fechaEntrada,fechaSalida) > precioMinimo
				&& inmueble.precioMaximoDelRangoDeFechasEntre(fechaEntrada,fechaSalida) < precioMaximo) {
				
				inmuebles.add(inmueble);
			}
		}
		return(inmuebles);
	}
	
	//devuelve los inmuebles que al menos uno de sus rangos este entre las fechas dadas 
	public ArrayList<Inmueble> filtrarInmueblesQuePertenezcanALasFechas(LocalDate fechaEntrada, LocalDate fechaSalida){												
		ArrayList<Inmueble>inmueblesFiltrados = new ArrayList<Inmueble>();
		for (Inmueble inmueble : this.getInmuebles()) {
			if (inmueble.hayAlgunRangoDeFechasQuePoseaLasFecha(fechaEntrada,fechaSalida)) {
				inmueblesFiltrados.add(inmueble);
			}		
		}
		return inmueblesFiltrados;
	}
	
	
	public ArrayList<Usuario> obtenerElTopTenDeInquilinos() {
		ArrayList<Usuario> listaARecorrer = this.usuariosOrdenadosPorReservasRealizadas();
		ArrayList<Usuario> usuariosTop = new ArrayList<Usuario>();
		
		for(int i = 0; i < 10 || i != (usuarios.size() - 1); i++) {
			usuariosTop.add(listaARecorrer.get(i));
		}
		return usuariosTop;
	}  

	//ordena a los usuarios de mayor a menor, en base a la cantidad de reservas que hayan hecho
	public ArrayList<Usuario> usuariosOrdenadosPorReservasRealizadas() {
		ArrayList<Usuario> usuarios = this.getUsuarios();
//		usuarios.sort(usuarios, Comparator.comparing(Usuario -> 
//												Usuario.getReservasRealizadas().size()));
		usuarios.sort(Comparator.comparing(usuario -> usuario.getReservasRealizadas().size()));
		return usuarios;
	}

	
	public Integer getCantidadDeInmueblesLibres() {
		Integer cantidadDeInmueblesLibres = 0;
		for (Inmueble inmueble : this.getInmuebles()) {
			if (!inmueble.estaAlquiladoActualmente()) {
				cantidadDeInmueblesLibres =+ 1;
			} 
		}
		return cantidadDeInmueblesLibres;
	}
	
	public Integer getCantidadDeInmueblesAlquilados() {
		Integer cantidadDeInmueblesAlquilados = 0;
		for (Inmueble inmueble : this.getInmuebles()) {
			if (inmueble.estaAlquiladoActualmente()) {
				cantidadDeInmueblesAlquilados =+ 1;
			} 
		}
		return cantidadDeInmueblesAlquilados;
	}
	
	public Integer getTasaOcupacional() {
		return (this.getCantidadDeInmueblesAlquilados() / this.getInmuebles().size());
	}
}




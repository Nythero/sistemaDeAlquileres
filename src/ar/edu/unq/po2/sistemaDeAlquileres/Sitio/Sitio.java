package ar.edu.unq.po2.sistemaDeAlquileres.Sitio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;

import java.util.Comparator;


public class Sitio {
	private final ArrayList<Usuario> usuarios; 
	private final ArrayList<Inmueble> inmuebles;
	private final Set<String> tiposDeServicios;
	private final Set<String> tiposDeInmueblesValidos;
	
	public Sitio() {
		this.usuarios = new ArrayList<Usuario>();
		this.inmuebles = new ArrayList<Inmueble>();
		this.tiposDeServicios = new HashSet<String>();
		this.tiposDeInmueblesValidos = new HashSet<String>();
	}
	
	private ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
	private ArrayList<Inmueble> getInmuebles() {
		return inmuebles;
	}
	
	private Set<String> getTiposDeServicios() {
		return this.tiposDeServicios;
	} 
	
	private Set<String> getTipoDeInmueblesValidos() {
		return this.tiposDeInmueblesValidos;
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
		if (this.esInmuebleValido(inmueble)) {
			this.getInmuebles().add(inmueble);
		}
		else {
			throw new Exception();
		}
	}
	
	private boolean esInmuebleValido(Inmueble inmueble) {
		return (this.getTipoDeInmueblesValidos().contains(inmueble.getTipoDeInmueble())
				&& this.getTiposDeServicios().containsAll(inmueble.getServicios()));
	}
	
	public void addCategoriaAInquilino(String categoria) throws Exception {
		for (int i = 0; i < this.getUsuarios().size(); i++) {
			this.getUsuarios().get(i).getRankingComoInquilino().addCategoria(categoria);
		}
	}
	
	public void addCategoriaAInmueble(String categoria) throws Exception {
		for (int i = 0; i < this.getInmuebles().size(); i++) {
			this.getInmuebles().get(i).getRanking().addCategoria(categoria);
		}
		//this.inmuebles.forEach(inmueble -> inmueble.getRanking().addCategoria(categoria));
	}
	
	public void addCategoriaADuenho(String categoria) throws Exception {
		for (int i = 0; i < this.getUsuarios().size(); i++) {
			this.getUsuarios().get(i).getRankingComoDuenho().addCategoria(categoria);
		}
//		this.usuarios.forEach(usuario -> usuario.getRankingComoDuenho().addCategoria(categoria))
	}
	
	public void agregarServicioValido(String servicio) {
		this.getTiposDeServicios().add(servicio);	
	} 
	
	public void agregarTipoDeInmuebleValido(String tipoDeInmueble) {
		this.getTipoDeInmueblesValidos().add(tipoDeInmueble);
	} 

	public ArrayList<Inmueble> buscarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida, Integer huespedes,
												float precioMinimo, float precioMaximo) {
		ArrayList<Inmueble> inmuebles = new ArrayList<Inmueble>();
		for (Inmueble inmueble : this.filtrarInmueblesQuePertenezcanALasFechas(fechaEntrada, fechaSalida)) {
			if (inmueble.getCiudad() == ciudad 
				&& (null == inmueble.getCapacidad()  || inmueble.getCapacidad() >= huespedes)
				&& (null == (Float) precioMaximo || inmueble.precioMaximoDelRangoDeFechasEntre(fechaEntrada,fechaSalida) > precioMinimo )
				&& (null == (Float) precioMaximo || inmueble.precioMaximoDelRangoDeFechasEntre(fechaEntrada,fechaSalida) < precioMaximo)) {
				
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
		
		for(int i = 0; i < 10 && i < usuarios.size(); i++) {
			usuariosTop.add(listaARecorrer.get(i));
		}
		return usuariosTop;
	} 
	//ordena a los usuarios de mayor a menor, en base a la cantidad de reservas que hayan hecho
	private ArrayList<Usuario> usuariosOrdenadosPorReservasRealizadas() {
		ArrayList<Usuario> usuarios = this.getUsuarios();
		usuarios.sort(Comparator.comparingInt(usuario -> ((Usuario) usuario).cantidadReservas()).reversed());
		return usuarios;
	}

	public Integer getCantidadDeInmueblesLibres() {
		Integer cantidadDeInmueblesLibres = 0;
		for (Inmueble inmueble : this.getInmuebles()) {
			if (!inmueble.estaAlquiladoActualmente()) {
				cantidadDeInmueblesLibres += 1;
			} 
		}
		return cantidadDeInmueblesLibres;
	}
	
	public Integer getCantidadDeInmueblesAlquilados() {
		Integer cantidadDeInmueblesAlquilados = 0;
		for (Inmueble inmueble : this.getInmuebles()) {
			if (inmueble.estaAlquiladoActualmente()) {
				cantidadDeInmueblesAlquilados += 1;
			} 
		}
		return cantidadDeInmueblesAlquilados;
	}
	 
	public Integer getTasaOcupacional() {
		return (this.getCantidadDeInmueblesAlquilados() / this.getInmuebles().size());
	}
}

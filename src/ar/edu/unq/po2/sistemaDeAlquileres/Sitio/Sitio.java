package ar.edu.unq.po2.sistemaDeAlquileres.Sitio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda.IFiltroDeBusqueda;
import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
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

	/**
	 * Agrega la categoria de puntuacion a todos los inquilinos del sitio
	 * @param categoria
	 * @throws Exception
	 */
	public void addCategoriaAInquilino(String categoria) throws Exception {
		for (int i = 0; i < this.getUsuarios().size(); i++) {
			this.getUsuarios().get(i).getRankingComoInquilino().addCategoria(categoria);
		}
	}
	
	/**
	 * Agrega la categoria de puntuacion a todos los inmuebles del sitio
	 * @param categoria
	 * @throws Exception
	 */
	public void addCategoriaAInmueble(String categoria) throws Exception {
		for (int i = 0; i < this.getInmuebles().size(); i++) {
			this.getInmuebles().get(i).getRanking().addCategoria(categoria);
		}
	}
	
	/**
	 * Agrega la categoria de puntuacion a todos los duenhos del sitio
	 * @param categoria
	 * @throws Exception
	 */
	public void addCategoriaADuenho(String categoria) throws Exception {
		for (int i = 0; i < this.getUsuarios().size(); i++) {
			this.getUsuarios().get(i).getRankingComoDuenho().addCategoria(categoria);
		}
	}
	
	/**
	 * Agrega el servicio dado como servicio valido
	 * @param servicio
	 */
	public void agregarServicioValido(String servicio) {
		this.getTiposDeServicios().add(servicio);	
	} 
	
	/**
	 * Agrega el tipo de inmueble dado como servicio valido
	 * @param tipoDeInmueble
	 */
	public void agregarTipoDeInmuebleValido(String tipoDeInmueble) {
		this.getTipoDeInmueblesValidos().add(tipoDeInmueble);
	} 

	/**
	 * Devuelve los inmuebles que cumplan con el filtro dado
	 * @param filtroComposite
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Inmueble> buscarInmuebles(IFiltroDeBusqueda filtroComposite) throws Exception {
			ArrayList<Inmueble> inmuebles = new ArrayList<Inmueble>();
			for (Inmueble inmueble : this.getInmuebles()) {  
				if (filtroComposite.cumpleConElFiltro(inmueble)) {
					inmuebles.add(inmueble);
				}
			}
			return(inmuebles);
	}
	
	/**
	 * Dada una fechaEntrada y una fechaSalida devuelve los inmuebles que al menos uno de sus rangos este entre las fechas dadas
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return
	 */
	public ArrayList<Inmueble> filtrarInmueblesQuePertenezcanALasFechas(LocalDate fechaEntrada, LocalDate fechaSalida){                                                
	        ArrayList<Inmueble>inmueblesFiltrados = new ArrayList<Inmueble>();
	        for (Inmueble inmueble : this.getInmuebles()) {
	            if (inmueble.hayAlgunRangoDeFechasQuePoseaLasFecha(fechaEntrada,fechaSalida)) {
	                inmueblesFiltrados.add(inmueble);
	            }        
	        }  
	        return inmueblesFiltrados;
	    }
	
	/**
	 * Devuelve los 10 usuarios con mayor cantidad de reservas realizadas
	 * @return
	 */
	public ArrayList<Usuario> obtenerElTopTenDeInquilinos() {
		ArrayList<Usuario> listaARecorrer = this.usuariosOrdenadosPorReservasRealizadas();
		ArrayList<Usuario> usuariosTop = new ArrayList<Usuario>();
		
		for(int i = 0; i < 10 && i < usuarios.size(); i++) {
			usuariosTop.add(listaARecorrer.get(i));
		}
		return usuariosTop;
	} 
	/**
	 * Ordena a los usuarios de mayor a menor, en base a la cantidad de reservas que hayan hecho
	 * @return
	 */
	private ArrayList<Usuario> usuariosOrdenadosPorReservasRealizadas() {
		ArrayList<Usuario> usuarios = this.getUsuarios();
		usuarios.sort(Comparator.comparingInt(usuario -> ((Usuario) usuario).cantidadReservas()).reversed());
		return usuarios;
	}

	/**
	 * Devuelve la cantidad de inmuebles sin alquilar actualmente
	 * @return
	 */
	public Integer getCantidadDeInmueblesLibres() {
		Integer cantidadDeInmueblesLibres = 0;
		for (Inmueble inmueble : this.getInmuebles()) {
			if (!inmueble.estaAlquiladoActualmente(LocalDate.now())) {
				cantidadDeInmueblesLibres += 1;
			} 
		}
		return cantidadDeInmueblesLibres;
	}

	/**
	 * Devuelve la cantidad de inmuebles alquilados actualmente
	 * @return
	 */
	public Integer getCantidadDeInmueblesAlquilados() {
		Integer cantidadDeInmueblesAlquilados = 0;
		for (Inmueble inmueble : this.getInmuebles()) {
			if (inmueble.estaAlquiladoActualmente(LocalDate.now())) {
				cantidadDeInmueblesAlquilados += 1;
			} 
		}
		return cantidadDeInmueblesAlquilados;
	}
	 
	public Integer getTasaOcupacional() {
		return (this.getCantidadDeInmueblesAlquilados() / this.getInmuebles().size());
	}
}

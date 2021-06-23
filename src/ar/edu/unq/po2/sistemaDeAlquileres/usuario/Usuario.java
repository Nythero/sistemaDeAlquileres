package ar.edu.unq.po2.sistemaDeAlquileres.usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.unq.po2.sistemaDeAlquileres.inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.sitio.Sitio;

public class Usuario {
	private ArrayList<Inmueble> inmuebles;
	private String nombreCompleto;
	private String direccionDeEmail;
	private String telefono;
	private ArrayList<Reserva> reservasRealizadas;
	private ArrayList<Reserva> reservasCondicionales; //preguntar si es necesario
	private Ranking rankingComoDuenho;
	private Ranking rankingComoInquilino;
	private Date fechaDeCreacion;  
	private ArrayList<String> comentarios;
	private Integer saldo;
	
	
	public Usuario (String nombreCompleto, String direccionDeEmail,
					String telefono,
					Ranking rankingComoDuenho, Ranking rankingComoInquilino) {
		
		this.inmuebles = new ArrayList<Inmueble>();
		this.setNombreCompleto(nombreCompleto);
		this.setDireccionDeEmail(direccionDeEmail);
		this.setTelefono(telefono);
		this.reservasRealizadas = new ArrayList<Reserva>();  
		this.reservasCondicionales = new ArrayList<Reserva>(); 
		this.rankingComoDuenho = new Ranking();
		this.rankingComoInquilino = new Ranking();
		this.fechaDeCreacion = new Date();
		this.comentarios = new ArrayList<String>();
		this.saldo = 0;
	}
	
	public ArrayList<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getDireccionDeEmail() {
		return direccionDeEmail;
	}

	public void setDireccionDeEmail(String direccionDeEmail) {
		this.direccionDeEmail = direccionDeEmail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public ArrayList<Reserva> getReservasRealizadas() {
		return reservasRealizadas;
	}


	public ArrayList<Reserva> getReservasCondicionales() {
		return reservasCondicionales;
	}


	public ArrayList<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ArrayList<String> comentarios) {
		this.comentarios = comentarios;
	}

	
	public ArrayList<Reserva> getReservas() {
		return this.reservasRealizadas;
	}
	
	public Ranking getRankingComoDuenho() {
		return this.rankingComoDuenho;
	}
	
	public Ranking getRankingComoInquilino() {
		return this.rankingComoInquilino;
	}
	
	public void setSaldo (Integer cantidad) {
	}
	
	// checkear si esta en horario de cheking o no? lo haria la reserva
	public void setComentario(Inmueble inmueble, String comentario) {
		inmueble.getComentariosGenerales().add(comentario);
	}
	
	// checkear si esta en horario de cheking o no?  lo haria la reserva
	public void setPuntajeADuenho (Inmueble inmueble, Integer puntaje) {
		inmueble.getDueño().getRankingComoDuenho().addPuntajeGeneral(puntaje);
	}
	
	
	// nos falta la reserva para ver quien es el inquilino
	public void setPuntajeAInquilino (Reserva reserva, Integer puntaje) {
		
	}
	
	
	
	
	
	// faltaria lo del check in? o tambien preguntar si es valido ese servicio? 
//	public void setPuntajeCategoriaAInmueble (Inmueble inmueble, String servicio, Integer puntaje) {
//		reserva.SeRellenoElFormulario(){
//			error 
//		}
//		
//	}
//	
	
//	public ArrayList<Inmueble> buscarInmuebles(String ciudad,Date fechaEntrada,
//												Date fechaSalida,Integer huespedes,float precioMinimo,
//												float precioMaximo){
//		return //inmuebles
//	}
	
	
	public void registrarseEnSitio(Sitio sitio) {
		try {
			sitio.registrarUsuario(this);
		}
		catch (Exception e) {}
	}
	
	public void visualizarDatosDelInmueble(ArrayList<Inmueble> inmuebles, Integer posicionInmueble) throws Exception {
		if ((posicionInmueble - 1) > inmuebles.size()) {
			throw new Exception("No hay tantos inmuebles"); 
		}
		else {
			inmuebles.get(posicionInmueble);
		}
	}
	
	
	public void publicarInmueble(Inmueble inmueble,Sitio sitio) {
		try {
			sitio.agregarInmueble(inmueble);
		}
		catch (Exception e) {}
	}
	
	
//	//nos falta averiguar bien como armar la reserva o mejor dicho, los dias
	public ArrayList<Reserva> getTodasLasReservasFuturas(){
		ArrayList<Reserva> reservasFuturas = new ArrayList<Reserva>();
		return reservasFuturas;
	}
//	

	public ArrayList<Reserva> getReservasEnCiudadParticular(String ciudad) {
		ArrayList<Reserva> reservasEnCiudadDada = new ArrayList<Reserva>();
		for (Reserva reserva : this.getReservasRealizadas()) {
			if (ciudad == reserva.getInmueble().getCiudad()) {
				reservasEnCiudadDada.add(reserva);
			}
		}
		return reservasEnCiudadDada;
	}


	public Set<String> getCiudadesEnLasQueTieneReservas(){
//		ArrayList<String> ciudadesConReserva = new ArrayList<String>();
		Set<String> ciudadesConReserva = new HashSet<String>();	
		for (Reserva reserva : this.getReservasRealizadas()) {
			ciudadesConReserva.add(reserva.getInmueble().getCiudad());
		}
		
		return ciudadesConReserva;
	}

	
	//tenemos que averiguar sobre reservas
//	public void realizarReserva (Inmueble inmueble, Sitio sitio, Date fechaInicio, 
//								Date fechaFinal, String formaDePago) {
//		
//	}
//	
//	public void cancelarReserva(Reserva reserva,Sitio sitio) {
//		
//	}
	
	public Integer getCantidadDeVecesQueAlquilo() {
		Integer cantidadDeVecesAlquilado = 0;
		for (Inmueble inmueble : this.getInmuebles()) {
			cantidadDeVecesAlquilado += inmueble.getCantidadDeVecesAlquilado();
		}
		return cantidadDeVecesAlquilado;
	}
}




    


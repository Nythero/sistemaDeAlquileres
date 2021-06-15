package ar.edu.unq.po2.sistemaDeAlquileres.Usuario;

import java.util.ArrayList;
import java.util.Date;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Sitio.Sitio;

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
	
	public Usuario (String nombreCompleto, String direccionDeEmail,
					String telefono, ArrayList<Reserva> reservasCondicionales,
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
		sitio.registrarUsuario(this);
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
		sitio.agregarInmueble(inmueble);
	}
	
	
	
//	//nos falta averiguar bien como armar la reserva
//	public ArrayList<Reserva> getTodasLasReservasFuturas(){
//		
//	}
//	
//	//mismo que arriba
//	public ArrayList<Reserva> getReservasEnCiudadParticular(String ciudad) {
//		
//	}
//	
//	//mismo que arriba
//	public ArrayList<String> getCiudadesEnLasQueTieneReservas(){
//		
//	}
//	
	//tenemos que averiguar sobre reservas
//	public void realizarReserva (Inmueble inmueble, Sitio sitio, Date fechaInicio, 
//								Date fechaFinal, String formaDePago) {
//		
//	}
//	
//	public void cancelarReserva(Reserva reserva,Sitio sitio) {
//		
//	}
	// esto es el array list de que cosa? 
//	public Integer getCantidadDeVecesQueAlquilo() {
//		
//	}
	
}
	
}

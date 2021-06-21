package ar.edu.unq.po2.sistemaDeAlquileres.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Sitio.Sitio;
import junit.framework.AssertionFailedError;

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
	private float saldo;
	
	
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
	
	public Date getFechaDeCreacion() {
		return (this.fechaDeCreacion);
	}
	
<<<<<<< HEAD
	public float getSaldo() {
		return this.saldo;
	}
	
=======
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
	public void setSaldo (Integer cantidad) {
		this.saldo = this.saldo + cantidad;
	}
	
<<<<<<< HEAD
	
	public void setComentarioAReserva(Reserva reserva, String comentario) {
=======

	public void setComentario(Reserva reserva, String comentario) {
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
		reserva.setComentario(comentario);
	}
	
	
	public void setPuntajeADuenho (Reserva reserva,String categoria, Integer puntaje) {
		reserva.setPuntajeADuenho(categoria,puntaje);
	}
	
	public void setPuntajeAInquilino (Reserva reserva,String categoria, Integer puntaje) {
		reserva.setPuntajeAInquilino(categoria,puntaje);
	}
	
	public void setPuntajeCategoriaAInmueble (Reserva reserva, String servicio, Integer puntaje) {
		reserva.setPuntajeCategoriaAInmueble(servicio,puntaje);
	}
	
<<<<<<< HEAD
	 
=======
	
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
	public ArrayList<Inmueble> buscarInmuebles(Sitio sitio,String ciudad,LocalDate fechaEntrada,
												LocalDate fechaSalida,Integer huespedes,float precioMinimo,
												float precioMaximo){

		return (sitio.buscarInmuebles(ciudad, fechaEntrada,fechaSalida, huespedes,precioMinimo,precioMaximo));
	}
	
	
	public void registrarseEnSitio(Sitio sitio) {
		sitio.registrarUsuario(this);
	}
	
<<<<<<< HEAD

	
	public Inmueble visualizarDatosDelInmueble(ArrayList<Inmueble> inmuebles, Integer posicionInmueble){
		if (posicionInmueble > inmuebles.size()) {
			throw new AssertionFailedError(); //"No hay tantos inmuebles"
		}
		else {
			return (inmuebles.get(posicionInmueble));
		}
	}
	
	public void agregarInmueble(Inmueble inmueble) {
		if (!this.getInmuebles().contains(inmueble)) {
			this.getInmuebles().add(inmueble);
		}
	}
	
	public void publicarInmueble(Inmueble inmueble,Sitio sitio) {
		sitio.agregarInmueble(inmueble);
		this.agregarInmueble(inmueble);
=======
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
		this.getInmuebles().add(inmueble);
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
	}
	
	public void recibirPago(float monto){
		this.saldo += monto;
	}

	public void extraerMonto(float monto){
<<<<<<< HEAD
		if (monto > this.getSaldo()) {
			throw new AssertionFailedError(); //"No hay dinero suficiente para extraer"
		}
		else {
			this.saldo-= monto;
		}	
	}
	
	public void agregarReserva(Reserva reserva) {
		this.getReservas().add(reserva);
	}
	
//	public void realizarReserva (Inmueble inmueble, Sitio sitio, Date fechaInicio, 
//								 Date fechaFinal, String formaDePago) {
//
//		throw new Exception("TO DO");
//	}
//	
//	//terminar aca 
//	public void cancelarReserva(Reserva reserva,Sitio sitio) {
//		throw new Exception("TO DO");
//	}
//	
	
	
=======
		this.saldo-= monto;
	}
	
//	public void realizarReserva (Inmueble inmueble, Sitio sitio, Date fechaInicio, 
//	Date fechaFinal, String formaDePago) {
//
//}

//public void cancelarReserva(Reserva reserva,Sitio sitio) {
//
//}
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
	
	public ArrayList<Reserva> getTodasLasReservasFuturas(){
		ArrayList<Reserva> reservasFuturas = new ArrayList<Reserva>();
		for (Reserva reserva : this.getReservasRealizadas()) {
<<<<<<< HEAD
			if (reserva.laFechaInicialDelRangoEstaDespuesDelDiaActual()){
=======
			if (reserva.getRango().getFechaInicial().isAfter(LocalDate.now())){
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
				reservasFuturas.add(reserva);
			}
		}
		return reservasFuturas;
	}
	
<<<<<<< HEAD
	
	public ArrayList<Reserva> getReservasEnCiudadParticular(String ciudad) {
		ArrayList<Reserva> reservasEnCiudadDada = new ArrayList<Reserva>();
		for (Reserva reserva : this.getReservasRealizadas()) {
			if (ciudad == reserva.getCiudad()) {
=======

	public ArrayList<Reserva> getReservasEnCiudadParticular(String ciudad) {
		ArrayList<Reserva> reservasEnCiudadDada = new ArrayList<Reserva>();
		for (Reserva reserva : this.getReservasRealizadas()) {
			if (ciudad == reserva.getInmueble().getCiudad()) {
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
				reservasEnCiudadDada.add(reserva);
			}
		}
		return reservasEnCiudadDada;
	}

<<<<<<< HEAD
	
	public Set<String> getCiudadesEnLasQueTieneReservas(){
		Set<String> ciudadesConReserva = new HashSet<String>();	
		for (Reserva reserva : this.getReservasRealizadas()) {
			ciudadesConReserva.add(reserva.getCiudad());
=======

	public Set<String> getCiudadesEnLasQueTieneReservas(){
		Set<String> ciudadesConReserva = new HashSet<String>();	
		for (Reserva reserva : this.getReservasRealizadas()) {
			ciudadesConReserva.add(reserva.getInmueble().getCiudad());
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
		}
		
		return ciudadesConReserva;
	}

	
<<<<<<< HEAD
	public Integer cantidadDeVecesQueAlquilo() { //devuelve la cantidad de veces que fueron alquilados todos los inmuebles
=======
	public Integer cantidadDeVecesQueAlquilo() {
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
		Integer cantidadDeVecesAlquilado = 0;
		for (Inmueble inmueble : this.getInmuebles()) {
			cantidadDeVecesAlquilado += inmueble.getCantidadDeVecesAlquilado();
		}
		return cantidadDeVecesAlquilado;
	}

<<<<<<< HEAD
	public Integer obtenerLaPosicionDelInmueble(Inmueble inmueble) {
		return (this.getInmuebles().indexOf(inmueble));
	}
	
	public Integer cuantasVecesAlquiloElInmueble(Inmueble inmueble) {
		Integer posicionDelInmueble = 0;
		if (this.getInmuebles().contains(inmueble)) {
			posicionDelInmueble = this.obtenerLaPosicionDelInmueble(inmueble);
			return this.getInmuebles().get(posicionDelInmueble).getCantidadDeVecesAlquilado();
		} 
		else { 
			throw new AssertionFailedError();
		}
		
	}
	 
=======
	
	public Integer cuantasVecesAlquiloElInmueble(Inmueble inmueble) {
		
		Integer posicionDelInmueble = 0;
		if (this.getInmuebles().contains(inmueble)) {
			posicionDelInmueble = this.getInmuebles().indexOf(inmueble);
		}
		else {
			throw new AssertionFailedError();
		}
		return this.getInmuebles().get(posicionDelInmueble).getCantidadDeVecesAlquilado();
	}
	
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
	
	public ArrayList<Inmueble> getInmueblesQueHanSidoAlquilados(){
		ArrayList<Inmueble> inmueblesAlquilados = new ArrayList<Inmueble>();
		for (Inmueble inmueble : this.getInmuebles()) {
			if (inmueble.getCantidadDeVecesAlquilado() > 0 ){ 
				inmueblesAlquilados.add(inmueble);
			}
		}
		return inmueblesAlquilados;
	}
	
<<<<<<< HEAD
=======
	//es valido hacerlo asi? o tendria que ser Ranking.class ? 
	public void agregarCategoriaComoDuenho(String categoria) {
		this.getRankingComoDuenho().addCategoria(categoria);
	}
	
	//es valido hacerlo asi? o tendria que ser Ranking.class ? 
	public void agregarCategoriaComoInquilino(String categoria) {
		this.getRankingComoInquilino().addCategoria(categoria);
	}
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
}




    


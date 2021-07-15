package ar.edu.unq.po2.sistemaDeAlquileres.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda.FiltroCapacidadDeHuespedes;
import ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda.FiltroCiudad;
import ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda.FiltroComposite;
import ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda.FiltroFechaEntradaYSalida;
import ar.edu.unq.po2.sistemaDeAlquileres.FiltroDeBusqueda.FiltroPrecioMinimoYMaximo;
import ar.edu.unq.po2.sistemaDeAlquileres.Inmueble.Inmueble;
import ar.edu.unq.po2.sistemaDeAlquileres.Ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Estado.CambioDeEstadoError;
import ar.edu.unq.po2.sistemaDeAlquileres.Sitio.Sitio;



public class Usuario {
	private String nombreCompleto;
	private String direccionDeEmail;
	private String telefono;
	private float saldo;
	private final LocalDate fechaDeCreacion;
	private final Ranking rankingComoDuenho;
	private final Ranking rankingComoInquilino;
	private final ArrayList<Inmueble> inmuebles;
	private final ArrayList<Reserva> reservas;  
	
	
	public Usuario (String nombreCompleto, String direccionDeEmail,
					String telefono) {
		this.setNombreCompleto(nombreCompleto);
		this.setDireccionDeEmail(direccionDeEmail);
		this.setTelefono(telefono);
		this.saldo = 0f;
		this.fechaDeCreacion = LocalDate.now();
		this.rankingComoDuenho = new Ranking();
		this.rankingComoInquilino = new Ranking();
		this.inmuebles = new ArrayList<Inmueble>();
		this.reservas = new ArrayList<Reserva>();  
	}


	private void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}


	private void setDireccionDeEmail(String direccionDeEmail) {
		this.direccionDeEmail = direccionDeEmail;	
	}
	
	public String getDireccionDeEmail() {
		return this.direccionDeEmail;
	}


	private void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public float getSaldo() {
		return this.saldo;
	}
	
	private void setSaldo(Float saldo) {
		this.saldo = saldo;
	}
	
	public Ranking getRankingComoDuenho() {
		return this.rankingComoDuenho;
	}
	
	public Ranking getRankingComoInquilino() {
		return this.rankingComoInquilino;
	}
	
	private ArrayList<Inmueble> getInmuebles() {
		return inmuebles;
	}

	private ArrayList<Reserva> getReservas() {
		return reservas;
	}
	
	public void comentarInmueble(Reserva reserva, String comentario) throws Exception {
		if(this.getReservas().contains(reserva)) {
			reserva.comentarInmueble(comentario);
		}
	}
	
	public void puntuarDuenho(Reserva reserva,String categoria, Integer puntaje) throws Exception {
		if(this.getReservas().contains(reserva)) {
			reserva.puntuarDuenho(categoria,puntaje);
		}	
	}
	
	public void puntuarInquilino(Reserva reserva,String categoria, Integer puntaje) throws Exception {
		if(this.getReservas().contains(reserva)) {
			reserva.puntuarInquilino(categoria,puntaje);
		}
		
	}
	
	public void puntuarInmueble(Reserva reserva, String servicio, Integer puntaje) throws Exception {
		if(this.getReservas().contains(reserva)) {
			reserva.puntuarInmueble(servicio,puntaje);
		}	
	}
	
	public ArrayList<Inmueble> buscarInmuebles(Sitio sitio,String ciudad,LocalDate fechaEntrada,
												LocalDate fechaSalida,Integer huespedes,float precioMinimo,
												float precioMaximo) throws Exception {
		FiltroComposite filtroComposite = new FiltroComposite();
		filtroComposite.agregarFiltro(new FiltroCiudad(ciudad));
		filtroComposite.agregarFiltro(new FiltroFechaEntradaYSalida(fechaEntrada,fechaSalida));
		filtroComposite.agregarFiltro(new FiltroCapacidadDeHuespedes(huespedes));
		filtroComposite.agregarFiltro(new FiltroPrecioMinimoYMaximo(precioMinimo,precioMaximo,fechaEntrada,fechaSalida));
		return sitio.buscarInmuebles(filtroComposite);
	}
	
	public void registrarseEnSitio(Sitio sitio) throws Exception {
		sitio.registrarUsuario(this);
	}

	public Inmueble visualizarDatosDelInmueble(ArrayList<Inmueble> inmuebles, Integer posicionInmueble) throws Exception {
		if (posicionInmueble > inmuebles.size()) {
			throw new Exception("No hay tantos inmuebles");
		}
		else {
			return (inmuebles.get(posicionInmueble));
		}
	}
	
	private void agregarInmueble(Inmueble inmueble) throws Exception {
		if (!this.getInmuebles().contains(inmueble)) {
			this.getInmuebles().add(inmueble);
		}
		else {
			throw new Exception("El inmueble ya esta agregado en el usuario");
		}
	}
	
	public void publicarInmueble(Inmueble inmueble, Sitio sitio) throws Exception {
		sitio.agregarInmueble(inmueble);
		this.agregarInmueble(inmueble);
	}
	
	public void recibirPago(float monto){
		this.setSaldo(this.getSaldo() + monto);
	}

	public void extraerMonto(float monto) throws Exception{
		if (monto > this.getSaldo()) {
			throw new Exception("No hay dinero suficiente para extraer");
		}
		else {
			this.setSaldo(this.getSaldo() - monto);
		}	
	}
	
	public void realizarReserva(Reserva reserva,Sitio sitio) throws Exception {
		if(sitio.esUsuario(this)) {
			reserva.getInmueble().agregarReserva(reserva);
			this.getReservas().add(reserva);
		}
		else {
			throw new Exception("El usuario no esta registrado en el sitio");
		}
		
	}
	
	public void cancelarReserva(Reserva reserva) throws CambioDeEstadoError, Exception {
		if (this.getReservas().contains(reserva)) {
			reserva.cancelar();
		}
		else {
			throw new Exception("La reserva no pertenece al usuario");
		}
	}
	
	public ArrayList<Reserva> getTodasLasReservasFuturas(){
		ArrayList<Reserva> reservasFuturas = new ArrayList<Reserva>();
		for (Reserva reserva : this.getReservas()) {
			if (reserva.estaEnEstado("Condicional") || reserva.estaEnEstado("Concretado")){
				reservasFuturas.add(reserva);
			}
		}
		return reservasFuturas;
	}
	
	public ArrayList<Reserva> getReservasEnCiudadParticular(String ciudad) {
		ArrayList<Reserva> reservasEnCiudadDada = new ArrayList<Reserva>();
		for (Reserva reserva : this.getReservas()) {
			if (ciudad == reserva.getInmueble().getCiudad()) {
				reservasEnCiudadDada.add(reserva);
			}
		}
		return reservasEnCiudadDada;
	}
	
	public Set<String> getCiudadesEnLasQueTieneReservas(){
		Set<String> ciudadesConReserva = new HashSet<String>();	
		for (Reserva reserva : this.getReservas()) {
			ciudadesConReserva.add(reserva.getInmueble().getCiudad());
		}
		return ciudadesConReserva;
	}
	
	public Integer cantidadDeVecesQueAlquilo() { //devuelve la cantidad de veces que fueron alquilados todos los inmuebles
		Integer cantidadDeVecesAlquilado = 0;
		for (Inmueble inmueble : this.getInmuebles()) {
			cantidadDeVecesAlquilado += inmueble.getCantidadDeVecesAlquilado();
		}
		return cantidadDeVecesAlquilado;
	}
	
	public Integer cuantasVecesAlquiloElInmueble(Inmueble inmueble) throws Exception {
		if (this.getInmuebles().contains(inmueble)) {
			return inmueble.getCantidadDeVecesAlquilado();
		} 
		else { 
			throw new Exception("El inmueble no le pertenece al usuario");
		}
	}
	
	public ArrayList<Inmueble> getInmueblesQueHanSidoAlquilados(){
		ArrayList<Inmueble> inmueblesAlquilados = new ArrayList<Inmueble>();
		for (Inmueble inmueble : this.getInmuebles()) {
			if (inmueble.getCantidadDeVecesAlquilado() > 0 ){ 
				inmueblesAlquilados.add(inmueble);
			}
		}
		return inmueblesAlquilados;
	}
	
	public int cantidadReservas() {
		return this.getReservas().size();
	}
}
package ar.edu.unq.po2.sistemaDeAlquileres.inmueble;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ar.edu.unq.po2.sistemaDeAlquileres.ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.temporada.Temporada;
import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;
import ar.edu.unq.po2.sistemaDeAlquileres.rangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.rangoDeFechaConPrecioDeterminado.RangoDeFechaConPrecioDeterminado;
import ar.edu.unq.po2.sistemaDeAlquileres.rangoDeFechas.RangosDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.politicaDeCancelacion.PoliticaDeCancelacion;

public class Inmueble {
	private Usuario duenho;
	private String tipoDeInmueble;
	private Integer superficie;
	private Integer capacidad;
	private LocalTime horaDeCheckIn;
	private LocalTime horaDeCheckOut;
	private Temporada precio;
	private final Ranking ranking;
	private final String pais;
	private final String ciudad;
	private final String direccion;
	private final ArrayList<String> servicios;
	private final ArrayList<String> fotos;
	private final ArrayList<String> formasDePago;
	private final ArrayList<String> comentarios;
	private final ArrayList<RangoDeFechas> rangosDeFechas;
	private final ArrayList<Reserva> reservas;
	private final PoliticaDeCancelacion politicaDeCancelacion;
	 
	
	public Inmueble(Usuario duenho, String tipoDeInmueble, int superficie, String pais, String ciudad, String direccion,
			int capacidad, LocalTime horaDeCheckIn, LocalTime horaDeCheckOut, Temporada precio) {

		this.setDuenho(duenho);
		this.setTipoDeInmueble(tipoDeInmueble);
		this.setSuperficie(superficie);
		this.setCapacidad(capacidad);
		this.setHoraDeCheckin(horaDeCheckIn);
		this.setHoraDeCheckout(horaDeCheckOut);
		this.setPrecio(precio);
		
		this.ranking = new Ranking();
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.servicios = new ArrayList<String>();
		this.fotos = new ArrayList<String>();
		this.formasDePago = new ArrayList<String>();
		this.comentarios = new ArrayList<String>();
		this.rangosDeFechas = new ArrayList<RangoDeFechas>();
		this.reservas = new ArrayList<Reserva>();
	}
	
	//Setters y Getters de atributos no finales
	
	public Usuario getDuenho() {
		return this.duenho;
	}
	
	private void setDuenho(Usuario usuario) {
		this.duenho = usuario;
	}
	
	public String getTipoDeInmueble() {
		return this.tipoDeInmueble;
	}
	
	private void setTipoDeInmueble(String tipo) {
		this.tipoDeInmueble = tipo;
	}

	public Integer getSuperficie() {
		return this.superficie;
	}
	
	private void setSuperficie(Integer superficie) {
		this.superficie = superficie;
	}

	public Integer getCapacidad() {
		return this.capacidad;
	}
	
	private void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public LocalTime getHoraDeCheckIn() {
		return this.horaDeCheckIn;
	}
	
	private void setHoraDeCheckin(LocalTime hora) {
		this.horaDeCheckIn = hora;
	}

	public LocalTime getHoraDeCheckOut() {
		return this.horaDeCheckOut;
	}
	
	private void setHoraDeCheckout(LocalTime hora) {
		this.horaDeCheckOut = hora;
	}

	public Temporada getPrecio() {
		return this.precio;
	}
	
	public void setPrecio(Temporada precio) {
		this.precio = precio;
	}

	//Getters de atributos finales
	
	public Ranking getRanking() {
		return this.ranking;
	}

	public String getPais() {
		return this.pais;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public ArrayList<String> getServicios() {
		return new ArrayList<String>(this.servicios);
	}

	private ArrayList<String> getFotos() {
		return fotos;
	}

	private ArrayList<String> getFormasDePago() {
		return this.formasDePago;
	}

	private ArrayList<String> getComentarios() {
		return this.comentarios;
	}
	
	private ArrayList<RangoDeFechas> getRangos() {
		return this.rangosDeFechas;
	}
	
	private ArrayList<Reserva> getReservas() {
		return reservas;
	}
	
	private PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return this.politicaDeCancelacion;
	}

	//Manejo de elementos de las colecciones
	
	public void agregarServicio(String servicio) {
		this.getServicios().add(servicio);
	}
	
	public void agregarFotos(String foto) throws Exception {
		if (this.getFotos().size() < 5) {
			this.getFotos().add(foto);
		}
		else {
			throw new Exception("El inmueble solo puede tener 5 fotos");
		}
	}

	public void agregarFormaDePago(String string) {
		this.getFormasDePago().add(string);
	}

	public void agregarComentario(String comentario) {
		this.getComentarios().add(comentario);
	}

	public void agregarRangoDeFechas(RangoDeFechas rango) {
		this.getRangos().add(rango);
	}
	
	public void agregarReserva(Reserva reserva) throws Exception{
		this.verificarReserva(reserva);
		this.reservas.add(reserva);
	}
	
	public void agregarCategoria(String categoria) {
		this.getRanking().addCategoria(categoria);
	}
	
	//
	
	public Integer getCantidadDeVecesAlquilado() {
		int cantidad = 0;
		for (Reserva reserva : this.getReservas()) {
			cantidad += (reserva.estaFinalizada())? 1 : 0;
		}
		return cantidad;
	}
	
	//indica si las fechas dadas, estan dentro del rango
	public boolean hayAlgunRangoDeFechasQuePoseaLasFecha(LocalDate fechaEntrada, LocalDate fechaSalida) {
		boolean elRangoEstaEntreLaFecha = true;//!(this.getRangos().isEmpty()) ;
		for(RangoDeFechaConPrecioDeterminado rango : this.getRangos()) {
			elRangoEstaEntreLaFecha |= rango.lasFechasEstanEnElRango(fechaEntrada,fechaSalida);
		}
		return elRangoEstaEntreLaFecha;
	}

	//devuelve el precio maximo del rango de fechas dadas
	public float precioMaximoDelRangoDeFechasEntre(LocalDate fechaEntrada, LocalDate fechaSalida) {
		float precioMaximoDelRango = 0;
		for (RangoDeFechaConPrecioDeterminado rango : this.getRangos()) {
			if (rango.lasFechasEstanEnElRango(fechaEntrada, fechaSalida)) {
				precioMaximoDelRango = rango.precioMaximoEntreElRangoDeFechas(fechaEntrada,fechaSalida);
			    break;
			} 
		}
		return precioMaximoDelRango;
	}
	
	public boolean estaAlquiladoActualmente() {
		boolean estaAlquilado = false;
		int i = 0;
		for(Reserva reserva : this.getReservas()) {
			estaAlquilado |= reserva.estaEnCurso();
		}
//		while (!this.getReservas().isEmpty() && i < this.getReservas().size()) {
//			estaAlquilado = this.getReservas().get(i).laFechaActualEstaDentroDelRango();
//			i++;
//		}
		return estaAlquilado;
	}

	private void verificarReserva(Reserva reserva) throws Exception {
		if (reserva.getInmueble() != this ||
				!this.estaIncluidoEnUnRango(reserva.getRangoDeFechas()) ||
				!this.getFormasDePago().contains(reserva.getFormaDePago()) ||
				!reserva.estaEnEstado("PendienteDeAprobacion")) {
			throw new Exception("Reserva Invalida");
		}
	}
	
	private boolean estaIncluidoEnUnRango(RangoDeFechas rango) {
        boolean result = false;
        int i=0;
        while(!this.getRangos().isEmpty() && i < this.getRangos().size()) {
            result |= this.getRangos().get(i).estaIncluidoElRango(rango);
            i++;
        }
        return result;
    }

	public boolean yaEstaReservado(RangoDeFechas rangoDeFechas) {
		boolean yaEstaReservado = false;
		for(Reserva reserva : reservas) {
			if (reserva.estaEnEstado("Concretada")) {
				yaEstaReservado = yaEstaReservado || rangoDeFechas.intersectanLosRangos(reserva.getRangoDeFechas());
			}
		}
		return yaEstaReservado;
	}

	public void cancelarReserva(Reserva reserva) throws Exception {
		this.getPoliticaDeCancelacion().cancelarReserva(LocalDate.now(), reserva));
		this.notify("Cancelado", this, null);
		for(Reserva reservaN : this.getReservas()) {
			if(reservaN.getRangoDeFechas().intersectanLosRangos(reserva.getRangoDeFechas()))){
				try {
					reservaN.aceptar();
				}
				catch(Exception e) {}
			}
		}
	}
}
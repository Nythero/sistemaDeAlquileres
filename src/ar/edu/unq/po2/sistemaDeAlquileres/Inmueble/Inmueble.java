package ar.edu.unq.po2.sistemaDeAlquileres.inmueble;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.Ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;


public class Inmueble {
	private Usuario dueño;
	private String tipoDeInmueble;
	private Ranking ranking;
	private Integer superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private Integer capacidad;
	private Date horaDeCheckIn;
	private Date horaDeCheckOut;
	private Temporada precio;
	private final Map<String,ArrayList<String>> comentariosPorCategorias = new HashMap<String,ArrayList<String>>();
	private final ArrayList<String> comentariosGenerales = new ArrayList<String>();
	private final ArrayList<String> servicios = new ArrayList<String>();
	private final ArrayList<String> formasDePago = new ArrayList<String>();
	private final ArrayList<String> fotos = new ArrayList<String>();
	private final ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	private RangoDeFechas rangosDeFechas;

	public Inmueble(Usuario dueño, String tipoDeInmueble, int superficie, String pais, String ciudad, String direccion,
			int capacidad, Date horaDeCheckIn, Date horaDeCheckOut, Temporada precio, Ranking ranking, RangosDeFechas rangosDeFechas) {

		this.dueño = dueño;
		this.tipoDeInmueble = tipoDeInmueble;
		this.superficie = superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.capacidad = capacidad;
		this.horaDeCheckIn = horaDeCheckIn;
		this.horaDeCheckOut = horaDeCheckOut;
		this.precio = precio;
		this.ranking = ranking;
		this.setRangosDeFechas(rangosDeFechas);
	}

	private void setRangosDeFechas(RangoDeFechas rangosDeFechas) {
		this.rangosDeFechas = rangosDeFechas;
	}

	public Usuario getDueño() {
		return this.dueño;
	}
	
	public String getTipoDeInmueble() {
		return this.tipoDeInmueble;
	}

	public Ranking getRanking() {
		return this.ranking;
	}

	public Integer getSuperficie() {
		return this.superficie;
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
		return this.servicios;
	}

	public Integer getCapacidad() {
		return this.capacidad;
	}

	public ArrayList<String> getFotos() {
		return fotos;
	}

	public Date getHoraDeCheckIn() {
		return this.horaDeCheckIn;
	}

	public Date getHoraDeCheckOut() {
		return this.horaDeCheckOut;
	}

	public ArrayList<String> getFormasDePago() {
		return this.formasDePago;
	}

	public Temporada getPrecio() {
		return this.precio;
	}
	
	private RangoDeFechas getRangoDeFechas() {
		return this.rangosDeFechas;
	}

	public ArrayList<String> getComentariosGenerales() {
		return this.comentariosGenerales;
	}

	public Map<String, ArrayList<String>> getComentariosPorCategorias() {
		return this.comentariosPorCategorias;
	}

	public void agregarComentario(String comentario) {
		this.getComentariosGenerales().add(comentario);
	}
	
	public void agregarReserva(Reserva reserva) throws Exception{
		this.verificarReserva(reserva);
		this.reservas.add(reserva);
	}
	
	private void verificarReserva(Reserva reserva) throws Exception {
		if (reserva.getInmueble() != this ||
			!this.getRangoDeFechas().contains(reserva.getRangoDeFechas()) ||
			!this.getFormasDePago().contains(reserva.getFormaDePago()) ||
			!reserva.estaPendienteDeAprobacion()) {
			throw new Exception("Reserva Invalida");
		}
	}

	public void agregarFormaDePago(String string) {
		this.getFormasDePago().add(string);
	}

	public boolean yaEstaReservado(RangoDeFechas rangoDeFechas) {
		boolean yaEstaReservado = false;
		for(Reserva reserva : reservas) {
			if (reserva.estaConcretada()) {
				yaEstaReservado = yaEstaReservado || rangoDeFechas.intersectanLosRangos(reserva.getRangoDeFechas());
			}
		}
		return yaEstaReservado;
	}
}

package ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ar.edu.unq.po2.sistemaDeAlquileres.Foto.Foto;
import ar.edu.unq.po2.sistemaDeAlquileres.Observable.Observable;
import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado.RangoDeFechaConPrecioDeterminado;
import ar.edu.unq.po2.sistemaDeAlquileres.Ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Sitio.Sitio;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;
import junit.framework.AssertionFailedError;

public class Inmueble extends Observable{
	private Usuario due�o;
	private String tipoDeInmueble;
	private String pais;
	private String ciudad;
	private String direccion;
	private Integer superficie;
	private Ranking ranking;
	private Integer capacidad;
	private LocalTime horaDeCheckIn;
	private LocalTime horaDeCheckOut;
	private ArrayList<String> servicios;
	private ArrayList<Reserva> reservas;
	private ArrayList<Foto> fotos;
	private ArrayList<String> formasDePago;
	private ArrayList<String> comentarios;
	private Map<String,ArrayList<String>> comentariosPorCategorias;
	private Integer cantidadDeVecesAlquilado; 
	private Temporada precio;
	private ArrayList<RangoDeFechaConPrecioDeterminado> rangosDeFechas;
	
	 
	
	public Inmueble(Usuario due�o,String tipoDeInmueble,int superficie,String pais,String ciudad,String direccion,
			ArrayList<String> servicios,int capacidad,LocalTime horaDeCheckIn,
			LocalTime horaDeCheckOut,ArrayList<String> formasDePago, Temporada precio2) {
		
		this.due�o = due�o;
		this.tipoDeInmueble= tipoDeInmueble;
		this.ranking= new Ranking();
		this.superficie= superficie;
		this.pais= pais;
		this.ciudad= ciudad;
		this.direccion= direccion;
		this.servicios= servicios;
		this.capacidad= capacidad;
		this.fotos= new ArrayList<Foto>();
		this.horaDeCheckIn= horaDeCheckIn;
		this.horaDeCheckOut= horaDeCheckOut;
		this.formasDePago= formasDePago;
		this.rangosDeFechas = new ArrayList<RangoDeFechaConPrecioDeterminado>();
		this.comentarios= new ArrayList<String>();
		this.comentariosPorCategorias= new HashMap<String,ArrayList<String>>();
		this.cantidadDeVecesAlquilado= 0;
		this.precio= precio2;
		
		
	}
	
//	public HashSet<String> tiposDeInmueblesValidos(){
//		return (HashSet<String>) (serviciosValidos);
//	}
	
	public Usuario getDue�o() {
		return this.due�o;
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

	public LocalTime getHoraDeCheckIn() {
		return this.horaDeCheckIn;
	}

	public LocalTime getHoraDeCheckOut() {
		return this.horaDeCheckOut;
	}

	public ArrayList<String> getFormasDePago() {
		return this.formasDePago;
	}

	public Temporada getPrecio() {
		return this.precio;
	}

	public ArrayList<String> getComentariosGenerales() {
		return this.comentariosGenerales;
	}

	public Map<String, ArrayList<String>> getComentariosPorCategorias() {
		return this.comentariosPorCategorias;
	}

	public Integer getCantidadDeVecesAlquilado() {
		return this.cantidadDeVecesAlquilado;
	}

	
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	
	public ArrayList<RangoDeFechaConPrecioDeterminado>getRangos() {
		return (this.rangosDeFechas);
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
	 
//	 elInmuebleFueAlquilado()
//	public boolean estaLibre() {
//		return (this.getCantidadDeVecesAlquilado() == 0);
//	}
	
	//lo cambiamos por estaAlquiladoActualmente()
//	public boolean estaAlquilado() {
//		return (this.getCantidadDeVecesAlquilado() > 0);
//	}
	
	public boolean estaAlquiladoActualmente() {
		boolean estaAlquilado = false;
		int i = 0; 
		while (!this.getReservas().isEmpty() && i < this.getReservas().size()) {
			estaAlquilado = this.getReservas().get(i).getRangoDeFechas().laFechaActualEstaDentroDelRango();
			i++;
		}
		return estaAlquilado;
	}
	
	public void bajarPrecioCotidiano(RangoDeFechaConPrecioDeterminado rango,float monto) {
		this.precio.bajarAlPrecioCotidiano(monto);
		this.notify("Baja de precio", this, monto);
	}
	
	public void bajarPrecioEspecial(RangoDeFechaConPrecioDeterminado rango,float monto) {
		this.precio.bajarPrecioEspecial(monto);
		this.notify("Baja de precio", this, monto);
	}

	
	
	public void agregarCategoria(String categoria) {
		this.getRanking().addCategoria(categoria);
	}

	public static void darDeAltaElServicio(String servicio) {
		serviciosValidos.add(servicio);
	}

	public static void darDeAltaAlTipoDeInmueble(String tipoDeInmueble) {
		tiposDeInmueblesValidos.add(tipoDeInmueble);
	}
	
	
//	public boolean poseeTodosLosServiciosValidosDelSitio(Sitio sitio) {
//		ArrayList<String> serviciosValidos = sitio.getTiposDeServiciosValidos();
//		boolean losServiciosSonValidos = true;
//		int i = 0;
//		while (serviciosValidos.size() > i) {
//			losServiciosSonValidos &= this.getServicios().contains(serviciosValidos.get(i));
//			i++;
//		}
//		return losServiciosSonValidos;
//	}	  
}
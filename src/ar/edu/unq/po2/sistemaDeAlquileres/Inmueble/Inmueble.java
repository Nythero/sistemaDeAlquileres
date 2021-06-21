package ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;

<<<<<<< HEAD
=======
import java.sql.Date;
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
<<<<<<< HEAD
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ar.edu.unq.po2.sistemaDeAlquileres.Foto.Foto;
=======
import java.util.Map;

>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFechaConPrecioDeterminado.RangoDeFechaConPrecioDeterminado;
import ar.edu.unq.po2.sistemaDeAlquileres.Ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;
import junit.framework.AssertionFailedError;

public class Inmueble {
	private Usuario dueño;
	private String tipoDeInmueble;
	private Ranking ranking;
	private Integer superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private ArrayList<String> servicios;
	private Integer capacidad;
<<<<<<< HEAD
	private ArrayList<Foto> fotos;
=======
	private ArrayList<String> fotos;
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
	private LocalTime horaDeCheckIn;
	private LocalTime horaDeCheckOut;
	private ArrayList<String> formasDePago;
	private Temporada precio;
	private ArrayList<String> comentariosGenerales;
	private Map<String,ArrayList<String>> comentariosPorCategorias;
	private Integer cantidadDeVecesAlquilado; 
	private ArrayList<RangoDeFechaConPrecioDeterminado> rangosDeFechas;
<<<<<<< HEAD
	private static Set<String> tiposDeInmueblesValidos = new HashSet<String>();
	private static Set<String> serviciosValidos = new HashSet<String>();
	private ArrayList<Reserva> reservas;
	 
	
	public Inmueble(Usuario dueño,String tipoDeInmueble,int superficie,String pais,
			String ciudad,String direccion,ArrayList<String> servicios,int capacidad, LocalTime horaDeCheckIn,
=======
	private static ArrayList<String> tiposDeInmueblesValidos = new ArrayList<String>();
	private static ArrayList<String> serviciosValidos = new ArrayList<String>();
	private ArrayList<Reserva> reservas;
	 
	
	public Inmueble(Usuario dueño,String tipoDeInmueble,int superficie,String pais,String ciudad,String direccion,
			ArrayList<String> servicios,int capacidad,ArrayList<String> fotos, LocalTime horaDeCheckIn,
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
			LocalTime horaDeCheckOut,ArrayList<String> formasDePago,Temporada precio) {
		
		if(!tiposDeInmueblesValidos.contains(tipoDeInmueble)) {
			throw new AssertionFailedError("El tipo de inmueble es invalido");
		}
		
		if(!serviciosValidos.containsAll(servicios)) {
			throw new AssertionFailedError("El tipo de servicio es invalido");
		}
		
		this.tipoDeInmueble= tipoDeInmueble;
		this.ranking= new Ranking();
		this.superficie= superficie;
		this.pais= pais;
		this.ciudad= ciudad;
		this.direccion= direccion;
		this.servicios= servicios;
		this.capacidad= capacidad;
<<<<<<< HEAD
		this.fotos= new ArrayList<Foto>();
=======
		this.fotos= fotos;
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
		this.horaDeCheckIn= horaDeCheckIn;
		this.horaDeCheckOut= horaDeCheckOut;
		this.formasDePago= formasDePago;
		this.rangosDeFechas = new ArrayList<RangoDeFechaConPrecioDeterminado>();
		this.comentariosGenerales= new ArrayList<String>();
		this.comentariosPorCategorias= new HashMap<String,ArrayList<String>>();
		this.cantidadDeVecesAlquilado= 0;
		this.dueño = dueño;
<<<<<<< HEAD
		this.reservas= new ArrayList<Reserva>();
		
	}
	
	public void addReserva(Reserva reserva) {
		this.reservas.add(reserva);
	}
	
	
	public HashSet<String> tiposDeInmueblesValidos(){
		return (HashSet<String>) (serviciosValidos);
	}

	
=======
		
	}
	
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
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

<<<<<<< HEAD
	public ArrayList<Foto> getFotos() {
=======
	public ArrayList<String> getFotos() {
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e
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
		boolean elRangoEstaEntreLaFecha = !(this.getRangos().isEmpty()) ;
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
	
<<<<<<< HEAD
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
			estaAlquilado = this.getReservas().get(i).laFechaActualEstaDentroDelRango();
			i++;
		}
		return estaAlquilado;
	}
	
	public void addRangoDeFechaConPrecio(LocalDate desde,LocalDate hasta, Temporada precio) {
		RangoDeFechaConPrecioDeterminado rangoDeFecha = new RangoDeFechaConPrecioDeterminado(desde, hasta, precio);
		this.rangosDeFechas.add(rangoDeFecha);
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
	
	public static int cantidadDeTiposDeInmueblesValidos() {
		return tiposDeInmueblesValidos.size();
	}
	
	public static int cantidadDeServiciosDeInmueblesValidos() {
		return serviciosValidos.size();
	}
	
	public void addFoto(Foto foto) {
		if(this.fotos.size()<5) {
			this.fotos.add(foto);
		}
	}
	
}

=======
	
	public boolean estaLibre() {
		return (this.getCantidadDeVecesAlquilado() == 0);
	}
	
	public boolean estaAlquilado() {
		return (this.getCantidadDeVecesAlquilado() > 0);
	}
	
	//esto no tiene que ser Ranking.class y ahi darle la categoria? para que la tengan todas las clases futuras? 
	public void agregarCategoria(String categoria) {
		this.getRanking().addCategoria(categoria);
	}	

}
>>>>>>> 8938c7b794f1514cf824f73231e2107f7799309e

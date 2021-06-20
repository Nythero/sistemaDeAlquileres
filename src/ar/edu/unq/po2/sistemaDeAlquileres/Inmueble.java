package ar.edu.unq.po2.sistemaDeAlquileres;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ar.edu.unq.po2.sistemaDeAlquileres.usuario.Usuario;

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
	private ArrayList<String> fotos;
	private java.util.Date horaDeCheckIn;
	private java.util.Date horaDeCheckOut;
	private ArrayList<String> formasDePago;
	private Temporada precio;
	private ArrayList<String> comentariosGenerales;
	private Map<String,ArrayList<String>> comentariosPorCategorias;
	private Integer cantidadDeVecesAlquilado;

	public Inmueble(Usuario dueño,String tipoDeInmueble,int superficie,String pais,String ciudad,String direccion,
			ArrayList<String> servicios,int capacidad,ArrayList<String> fotos, java.util.Date horaDeCheckIn,
			 java.util.Date horaDeCheckOut,ArrayList<String> formasDePago,Temporada precio) {
		
		this.tipoDeInmueble= tipoDeInmueble;
		this.ranking= new Ranking();
		this.superficie= superficie;
		this.pais= pais;
		this.ciudad= ciudad;
		this.direccion= direccion;
		this.servicios= servicios;
		this.capacidad= capacidad;
		this.fotos= fotos;
		this.horaDeCheckIn= horaDeCheckIn;
		this.horaDeCheckOut= horaDeCheckOut;
		this.formasDePago= formasDePago;
		this.precio= precio;
		this.comentariosGenerales= new ArrayList<String>();
		this.comentariosPorCategorias= new HashMap<String,ArrayList<String>>();
		this.cantidadDeVecesAlquilado= 0;
		this.dueño = dueño;
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

	public java.util.Date getHoraDeCheckIn() {
		return this.horaDeCheckIn;
	}

	public java.util.Date getHoraDeCheckOut() {
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
	
	

}

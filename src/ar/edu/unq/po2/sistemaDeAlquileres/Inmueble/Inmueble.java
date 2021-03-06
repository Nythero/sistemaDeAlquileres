package ar.edu.unq.po2.sistemaDeAlquileres.Inmueble;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


import ar.edu.unq.po2.sistemaDeAlquileres.Foto.Foto;
import ar.edu.unq.po2.sistemaDeAlquileres.Observable.Observable;
import ar.edu.unq.po2.sistemaDeAlquileres.RangoDeFecha.RangoDeFechas;
import ar.edu.unq.po2.sistemaDeAlquileres.Ranking.Ranking;
import ar.edu.unq.po2.sistemaDeAlquileres.Reserva.Reserva;
import ar.edu.unq.po2.sistemaDeAlquileres.Temporada.Temporada;
import ar.edu.unq.po2.sistemaDeAlquileres.Usuario.Usuario;
import ar.edu.unq.po2.sistemaDeAlquileres.PoliticaDeCancelacion.PoliticaDeCancelacion;


public class Inmueble extends Observable{
	private Usuario duenho;
	private String tipoDeInmueble;
	private Integer superficie;
	private Integer capacidad;
	private LocalTime horaDeCheckIn;
	private LocalTime horaDeCheckOut;
	private Temporada precio;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private final Ranking ranking;
	private final String pais;
	private final String ciudad;
	private final String direccion;
	private final ArrayList<String> servicios;
	private final ArrayList<Foto> fotos;
	private final ArrayList<String> formasDePago;
	private final ArrayList<String> comentarios;
	private final ArrayList<RangoDeFechas> rangosDeFechas;
	private final ArrayList<Reserva> reservas;
	
	public Inmueble(Usuario duenho, String tipoDeInmueble, int superficie, String pais, String ciudad, String direccion,
			int capacidad, LocalTime horaDeCheckIn, LocalTime horaDeCheckOut, Temporada precio,
			PoliticaDeCancelacion politicaDeCancelacion) {

		this.setDuenho(duenho);
		this.setTipoDeInmueble(tipoDeInmueble);
		this.setSuperficie(superficie);
		this.setCapacidad(capacidad);
		this.setHoraDeCheckin(horaDeCheckIn);
		this.setHoraDeCheckout(horaDeCheckOut);
		this.setPrecio(precio);
		this.setPoliticaDeCancelacion(politicaDeCancelacion);
		
		this.ranking = new Ranking();
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.servicios = new ArrayList<String>();
		this.fotos = new ArrayList<Foto>();
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

	
	private void setSuperficie(Integer superficie) {
		this.superficie = superficie;
	}

	public Integer getCapacidad() {
		return this.capacidad;
	}
	
	private void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	
	private void setHoraDeCheckin(LocalTime hora) {
		this.horaDeCheckIn = hora;
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
	
	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return this.politicaDeCancelacion;
	}
	
	private void setPoliticaDeCancelacion(PoliticaDeCancelacion politicaDeCancelacion) {
		this.politicaDeCancelacion = politicaDeCancelacion;
	}

	//Getters de atributos finales
	
	public Ranking getRanking() {
		return this.ranking;
	}


	public String getCiudad() {
		return this.ciudad;
	}


	public ArrayList<String> getServicios() {
		return new ArrayList<String>(this.servicios);
	}

	private ArrayList<Foto> getFotos() {
		return this.fotos;
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

	//Manejo de elementos de las colecciones
	
	public void agregarServicio(String servicio) {
		this.getServicios().add(servicio);
	}
	
	public void agregarFoto(Foto foto) throws Exception {
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
		this.verificarReservaParaAgregar(reserva);
		this.reservas.add(reserva);
		this.getDuenho().recibirPago(reserva.getMontoTotal());
	}
	
	public void agregarCategoria(String categoria) throws Exception {
		this.getRanking().addCategoria(categoria);
	}
	
	public Integer getCantidadDeVecesAlquilado() {
		int cantidad = 0;
		for (Reserva reserva : this.getReservas()) {
			cantidad += (reserva.estaEnEstado("Finalizado"))? 1 : 0;
		}
		return cantidad;
	}
	
	public boolean hayAlgunRangoDeFechasQuePoseaLasFecha(LocalDate fechaEntrada, LocalDate fechaSalida) {
        boolean elRangoEstaEntreLaFecha = false;
        for(RangoDeFechas rango : this.getRangos()) {
            elRangoEstaEntreLaFecha |= rango.lasFechasEstanEnElRango(fechaEntrada,fechaSalida);
        }
        return elRangoEstaEntreLaFecha;
    }

	/**
	 * Dado una fechaEntrada y una fechaSalida devuelve el precio maximo
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return
	 */
	public float precioMaximoDelRangoDeFechasEntre(LocalDate fechaEntrada, LocalDate fechaSalida) {
		float precioMaximoDelRango = 0f;
		RangoDeFechas rangoP = new RangoDeFechas(fechaEntrada, fechaSalida);
		for (RangoDeFechas rango : this.getRangos()) {
			if (rango.estaIncluidoElRango(rangoP)) {
				precioMaximoDelRango = rango.precioMaximoEntreElRangoDeFechas(this.getPrecio(), fechaEntrada,fechaSalida);
			} 
		}
		return precioMaximoDelRango;
	}
	
	public boolean estaAlquiladoActualmente(LocalDate diaActual) {
		boolean estaAlquilado = false;
		for(Reserva reserva : this.getReservas()) {
			estaAlquilado |= reserva.getRangoDeFechas().estaIncluidaLaFecha(diaActual);
		}
		return estaAlquilado;
	}

	private void verificarReservaParaAgregar(Reserva reserva) throws Exception {
		if (reserva.getInmueble() != this ||
			this.getReservas().contains(reserva) ||
			!this.estaIncluidoEnUnRango(reserva.getRangoDeFechas()) ||
			!this.getFormasDePago().contains(reserva.getFormaDePago()) ||
			!reserva.estaEnEstado("PendienteDeAprobacion")) {
			throw new Exception("Reserva Invalida para agregar");
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
		for(Reserva reserva : this.getReservas()) {
			yaEstaReservado |= (reserva.estaEnEstado("Concretado") && rangoDeFechas.intersectanLosRangos(reserva.getRangoDeFechas()));
		}
		return yaEstaReservado;
	}

	public boolean hayAlgunRangoDeFechasQuePoseaElRango(RangoDeFechas rangoP) {
        boolean elRangoEstaEntreLaFecha = false;
        for(RangoDeFechas rango : this.getRangos()) {
            elRangoEstaEntreLaFecha |= rango.estaIncluidoElRango(rangoP);
        }
        return elRangoEstaEntreLaFecha;
    }
	
	public void bajarPrecioCotidiano(float montoNuevo) {
		this.precio.bajarAlPrecioCotidiano(montoNuevo);
	}
	
	public void bajarPrecioEspecial(float montoNuevo) {
		this.precio.bajarPrecioEspecial(montoNuevo);
	}

	public void cancelarReserva(Reserva reserva) throws Exception {
		this.verificarReservaParaCancelar(reserva);
		this.getPoliticaDeCancelacion().cancelarReserva(LocalDate.now(), reserva);
		this.notify("Cancelado", this, null);
		actualizarReservasPorCancelacion();
	}

	private void actualizarReservasPorCancelacion() throws Exception {
		for(Reserva reservaN : this.getReservas()) {
			try {
				reservaN.concretar();
			}
			catch(Exception e) {}
		}
	}

	private void verificarReservaParaCancelar(Reserva reserva) throws Exception {
		if (!this.getReservas().contains(reserva) ||
			!reserva.estaEnEstado("Concretado")) {
				throw new Exception("Reserva Invalida para cancelar");
			}
		}
}
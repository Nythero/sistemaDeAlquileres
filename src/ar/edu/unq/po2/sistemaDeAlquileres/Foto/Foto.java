package ar.edu.unq.po2.sistemaDeAlquileres.Foto;


public class Foto {
	private String nombre;
	private Integer ancho;
	private Integer alto;
	
	Foto(String nombre, Integer ancho, Integer alto) throws Exception{
		this.nombre= nombre;
		this.setAncho(ancho);
		this.setAlto(alto);
	}
	/**
	 * Dado un ancho setea la foto. En caso de no ser un valor positivo devuelve error
	 * @param ancho
	 * @throws Exception
	 */
	public void setAncho(Integer ancho) throws Exception {
		if(ancho <=0) {
			throw new Exception("El numero dado no puede ser negativo");
		}
		else{
			this.ancho = ancho;
		}
	}
	
	/**
	 * Dado un alto setea la foto. En caso de no ser un valor positivo devuelve error
	 * @param alto
	 * @throws Exception
	 */
	public void setAlto(Integer alto) throws Exception {
		if(alto <=0) {
			throw new Exception("El numero dado no puede ser negativo");
		}
		else{
			this.alto = alto;
		}
	}
	
}

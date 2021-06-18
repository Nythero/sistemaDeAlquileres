package ar.edu.unq.po2.sistemaDeAlquileres.temporada;

import java.time.LocalDate;

public interface Temporada {
	float getPrecio(LocalDate fecha);
	void bajarPrecio(float precioADescontar);
}

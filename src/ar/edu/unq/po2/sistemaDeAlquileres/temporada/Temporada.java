package ar.edu.unq.po2.sistemaDeAlquileres.temporada;

import java.sql.Date;

public interface Temporada {
	float getPrecio(Date dia);
	void bajarPrecio(float precioADescontar);
}

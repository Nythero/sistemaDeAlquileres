package ar.edu.unq.po2.sistemaDeAlquileres;

import java.sql.Date;

public interface Temporada {
	float getPrecio(Date dia);
	void bajarPrecio(float precioADescontar);
}

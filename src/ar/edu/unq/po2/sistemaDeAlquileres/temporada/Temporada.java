package ar.edu.unq.po2.sistemaDeAlquileres.temporada;

import java.sql.Date;

import ar.edu.unq.po2.sistemaDeAlquileres.rangoDeFecha.RangoDeFechas;

public interface Temporada {
	float getPrecio(Date dia);
	void bajarPrecio(float precioADescontar);
	float precioMaximoEntreElRangoDeFechas(RangoDeFechas rango);
}

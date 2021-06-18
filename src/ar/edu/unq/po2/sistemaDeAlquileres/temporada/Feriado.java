package ar.edu.unq.po2.sistemaDeAlquileres.temporada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import junit.framework.AssertionFailedError;

public class Feriado {
	private Map<Integer,ArrayList<Integer>> feriadosInamovibles;
	
	public Feriado() {
		this.feriadosInamovibles= this.feriadosInamovibles();
	}
	
	/**
	 * Crea un map con los feriados inamovibles
	 * @return
	 */
	private Map<Integer, ArrayList<Integer>> feriadosInamovibles() {
		int [] eneroArray= {1};
		ArrayList enero = new ArrayList<>();
		this.convertirAArrayList(eneroArray,enero);
		
		ArrayList febrero = new ArrayList<>();
		int [] febreroArray= {15,16};
		this.convertirAArrayList(febreroArray,febrero);
		
		ArrayList marzo = new ArrayList<>();
		int [] marzoArray= {24};
		this.convertirAArrayList(marzoArray,marzo);
		
		ArrayList abril = new ArrayList<>();
		int [] abrilArray= {2};
		this.convertirAArrayList(abrilArray,abril);
		
		ArrayList mayo = new ArrayList<>();
		int [] mayoArray= {1,25};
		this.convertirAArrayList(mayoArray,mayo);
		
		ArrayList junio = new ArrayList<>();
		int [] junioArray= {20};
		this.convertirAArrayList(junioArray,junio);
		
		ArrayList julio = new ArrayList<>();
		int [] julioArray= {9};
		this.convertirAArrayList(julioArray,julio);
		
		ArrayList diciembre = new ArrayList<>();
		int [] diciembreArray= {8,25};
		this.convertirAArrayList(diciembreArray,diciembre);
		HashMap map = new HashMap<>();
		
		map.put(1, enero);
		map.put(2, febrero);
		map.put(3, marzo);
		map.put(4, abril);
		map.put(5, mayo);
		map.put(6, junio);
		map.put(7, julio);
		map.put(12, diciembre);
		return map;
	}

	/**
	 * Convierte un Array dado en un ArrayList
	 * @param array
	 * @param lista
	 */
	private void convertirAArrayList(int[] array, ArrayList lista) {
	      for(int i=0;i<array.length;i++)
	      {
	    	  lista.add(array[i]);        
	      }
	}

	/**
	 * Retorna los feriados inamovibles
	 * @return
	 */
	public Map<Integer, ArrayList<Integer>> getFeriadosInamovibles() {
		return this.feriadosInamovibles;
	}

	/**
	 * Retorna un ArayList con los dias de feriados del mes dado.
	 * En caso de no existir devuelve error
	 * @param mes
	 * @return
	 */
	public ArrayList<Integer> feriadosDelMes(int mes) {
		if(feriadosInamovibles.containsKey(mes)) {
			return this.getFeriadosInamovibles().get(mes);
		}
		else {
			throw new AssertionFailedError();
		}
	}
	
	/**
	 * Añade el feriado al mes dado.
	 * @param mes
	 * @param dia
	 */
	public void addFeriado(int mes, int dia) {
		if(!feriadosInamovibles.containsKey(mes)) {
			ArrayList<Integer> lista = new ArrayList<Integer>();
			lista.add(dia);
			this.getFeriadosInamovibles().put(mes, lista);
		}
		else {
			ArrayList<Integer> feriados = this.feriadosDelMes(mes);
			this.añadirSiCorresponde(feriados,dia);
			this.getFeriadosInamovibles().put(mes, this.añadirSiCorresponde(feriados,dia));
		}
	}

	/**
	 * Añade al ArrayList el dia dado si no se encuentra en dicha lista
	 * Caso contrario devuelve el ArrayList original
	 * @param feriados
	 * @param dia
	 * @return
	 */
	private ArrayList<Integer> añadirSiCorresponde(ArrayList<Integer> feriados, int dia) {
		if(!feriados.contains(dia)) {
			feriados.add(dia);
		}
		return feriados;
	}

	/**
	 * Remueve el feriado dado. Si no se encuentra devuelve error
	 * @param mes
	 * @param dia
	 */
	public void removeFeriado(int mes, int dia) {
		if(feriadosInamovibles.containsKey(mes) && this.feriadosDelMes(mes).contains(dia)) {
			ArrayList<Integer> feriados = this.feriadosDelMes(mes);
			feriados.remove(feriados.indexOf(dia));
			this.getFeriadosInamovibles().put(mes, feriados);
		}
		else {
			throw new AssertionFailedError();
		}
	}
	
	
}

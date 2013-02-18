package ar.uba.fi.celdas7568.db;

import java.util.Random;

import ar.uba.fi.celdas7568.ciudad.Agente;
import ar.uba.fi.celdas7568.ciudad.AtributoPersonalidad;
import ar.uba.fi.celdas7568.ciudad.Personalidad;

public class OrigenAgenteRandom implements OrigenAgentes {
	
	private int contador = 1;
	private Random random = new Random();

	@Override
	public Agente obtenerAgente() {
		Personalidad nuevaPersonalidad = new Personalidad();
			
		for(AtributoPersonalidad a : AtributoPersonalidad.values()){
			nuevaPersonalidad.put(a, random.nextDouble());
		}
		
		Agente nuevoAgente = new Agente(nuevaPersonalidad, "agente_random_" + contador++);

		return nuevoAgente;
	}
	
	@Override
	public String toString(){
		return "Random";
	}

}

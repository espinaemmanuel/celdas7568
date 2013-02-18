package ar.uba.fi.celdas7568.ciudad.heuristicas;

import ar.uba.fi.celdas7568.ciudad.Personalidad;
import ar.uba.fi.celdas7568.ciudad.ElementoCiudad;

public interface Heuristica {
	
	public Personalidad habitanteInicial(ElementoCiudad zona);

	public int getDimensionOpinion();

	public double[] vectorizar(Personalidad p);

	public Personalidad obtenerPersonalidad(double[] vector);

}

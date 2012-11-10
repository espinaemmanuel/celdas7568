package ar.uba.fi.celdas7568.ciudad.heuristicas;

import java.util.List;

import ar.uba.fi.celdas7568.ciudad.Opinion;
import ar.uba.fi.celdas7568.ciudad.Personalidad;
import ar.uba.fi.celdas7568.ciudad.Zona;

public interface HeuristicaDeDesicion {

	/**
	 * En base al estado actual de la heuristica y la personalidad pasada como parámetro, genera una opinión 
	 * sobre la zona pasada como parámetro. Este método no puede modificar el estado de la heuristica
	 * 
	 * @param zona Zona a evaluar
	 * @param personalidad Personalidad del agente
	 * @return Opinion sobre la zona
	 */
	public Opinion evaluarZona(Zona zona, Personalidad personalidad);

	/**
	 * Evalúa todas las opiniones obtenidas pasadas como parámetro y selecciona la zona de una de ellas o ninguna
	 * si ninguna es adecuada. Este método no puede modificar el estado de la heuristica
	 * 
	 * @param opiniones opiniones generadas previamente
	 * @param personalidad la personalidad del agente que realiza la evaluación
	 * @return
	 */
	public Zona evaluarOpiniones(List<Opinion> opiniones, Personalidad personalidad);

	/**
	 * En base al estado actual de la heurística, y una personalidad de agente, toma una lista de opiniones y modifica
	 * su estado interno con el fin de poder decidir en una evaluación posterior una zona.
	 * 
	 * @param opiniones
	 * @param personalidad
	 */
	public void reconsiderarOpiniones(List<Opinion> opiniones, Personalidad personalidad);

}

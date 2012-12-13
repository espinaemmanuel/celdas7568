package ar.uba.fi.celdas7568.ciudad.heuristicas;

import java.util.List;

import ar.uba.fi.celdas7568.ciudad.Opinion;
import ar.uba.fi.celdas7568.ciudad.Personalidad;
import ar.uba.fi.celdas7568.ciudad.Zona;

public interface HeuristicaDeDecision<O extends Opinion> {

	/**
	 * En base al estado actual de la heuristica y la personalidad pasada como par�metro, genera una opini�n 
	 * sobre la zona pasada como par�metro. Este m�todo no puede modificar el estado de la heuristica
	 * 
	 * @param zona Zona a evaluar
	 * @param personalidad Personalidad del agente
	 * @return Opinion sobre la zona
	 */
	public O evaluarZona(Zona zona, Personalidad personalidad);

	/**
	 * Eval�a todas las opiniones obtenidas pasadas como par�metro y selecciona la zona de una de ellas o ninguna
	 * si ninguna es adecuada. Este m�todo no puede modificar el estado de la heuristica
	 * 
	 * @param opiniones opiniones generadas previamente
	 * @param personalidad la personalidad del agente que realiza la evaluaci�n
	 * @return
	 */
	public Zona evaluarOpiniones(List<O> opiniones, Personalidad personalidad);

	/**
	 * En base al estado actual de la heur�stica, y una personalidad de agente, toma una lista de opiniones y modifica
	 * su estado interno con el fin de poder decidir en una evaluaci�n posterior una zona.
	 * 
	 * @param opiniones
	 * @param personalidad
	 */
	public void reconsiderarOpiniones(List<O> opiniones, Personalidad personalidad);

}

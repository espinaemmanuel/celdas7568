package ar.uba.fi.celdas7568.ciudad;

import java.util.List;

import ar.uba.fi.celdas7568.ciudad.heuristicas.HeuristicaDeDecision;

import com.google.common.collect.Lists;

public class Agente {
	
	private Personalidad personalidad;
	private HeuristicaDeDecision heuristicaDeDesicion;
	private String nombre;
	
	public Agente(String nombre, Personalidad personalidad, HeuristicaDeDecision heuristica) {
		this.nombre = nombre;
		this.personalidad = personalidad;
		this.heuristicaDeDesicion = heuristica;
	}
	
	public Zona elegirZona(Ciudad ciudad){
		
		Zona zonaElegida = null;
		
		while(zonaElegida == null){
			List<Opinion> opiniones = Lists.newArrayList();
			
			for(Zona zona : ciudad){
				Opinion opinionSobreZona = heuristicaDeDesicion.evaluarZona(zona, personalidad);
				opiniones.add(opinionSobreZona);
			}
			
			zonaElegida = heuristicaDeDesicion.evaluarOpiniones(opiniones, personalidad);
			
			if(zonaElegida == null){
				heuristicaDeDesicion.reconsiderarOpiniones(opiniones, personalidad);
			}
		}	
		
		return zonaElegida;		
	}
	
	@Override
	public String toString() {
		
		Personalidad personalidad = getPersonalidad();
		
		return "Personalidad del agente: " + getNombre() + "\n\n" + personalidad.toString();
	}
	
	public Personalidad getPersonalidad() {
		return personalidad;
	}

	public void setPersonalidad(Personalidad personalidad) {
		this.personalidad = personalidad;
	}

	public HeuristicaDeDecision getHeuristicaDeDesicion() {
		return heuristicaDeDesicion;
	}

	public void setHeuristicaDeDesicion(HeuristicaDeDecision heuristicaDeDesicion) {
		this.heuristicaDeDesicion = heuristicaDeDesicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

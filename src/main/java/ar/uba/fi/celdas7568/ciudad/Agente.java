package ar.uba.fi.celdas7568.ciudad;

import java.util.List;

import ar.uba.fi.celdas7568.ciudad.heuristicas.HeuristicaDeDesicion;

import com.google.common.collect.Lists;

public class Agente {
	
	private Personalidad personalidad;
	private HeuristicaDeDesicion heuristicaDeDesicion;
	
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
	
	public Personalidad getPersonalidad() {
		return personalidad;
	}

	public void setPersonalidad(Personalidad personalidad) {
		this.personalidad = personalidad;
	}

	public HeuristicaDeDesicion getHeuristicaDeDesicion() {
		return heuristicaDeDesicion;
	}

	public void setHeuristicaDeDesicion(HeuristicaDeDesicion heuristicaDeDesicion) {
		this.heuristicaDeDesicion = heuristicaDeDesicion;
	}
}

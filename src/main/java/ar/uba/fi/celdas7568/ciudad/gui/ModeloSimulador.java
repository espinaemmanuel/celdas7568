package ar.uba.fi.celdas7568.ciudad.gui;

import java.util.List;

import com.google.common.collect.Lists;

import ar.uba.fi.celdas7568.ciudad.Agente;
import ar.uba.fi.celdas7568.ciudad.Ciudad;
import ar.uba.fi.celdas7568.ciudad.VisualizacionZona;
import ar.uba.fi.celdas7568.ciudad.heuristicas.Heuristica;
import ar.uba.fi.celdas7568.db.OrigenAgenteJson;
import ar.uba.fi.celdas7568.db.OrigenAgentes;

public class ModeloSimulador {
	
	private List<OrigenAgentes> origenesAgentes = Lists.newArrayList();
	private OrigenAgentes origenAgenteActual;
	private Agente agenteActual;
	private Ciudad ciudad;
	private Heuristica heuristica;
	private VisualizacionZona visualizacion;
	private IconosCiudad iconos;

	public ModeloSimulador(Heuristica heuristica, VisualizacionZona visualizacionZona, IconosCiudad iconos){
		this.heuristica = heuristica;
		this.visualizacion = visualizacionZona;
		this.iconos = iconos;
	}
	
	
	public OrigenAgentes[] getOrigenesAgentes() {
		return origenesAgentes.toArray(new OrigenAgentes[origenesAgentes.size()]);
	}
	
	public void setOrigenAgenteActual(OrigenAgentes origen){
		this.origenAgenteActual = origen;
	}
	
	public Agente getAgenteActual(){
		return this.agenteActual;
	}
	
	public void avanzarAgente(){
		this.agenteActual = this.origenAgenteActual.obtenerAgente();
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
		
		this.ciudad.setHeuristica(this.heuristica);
		this.ciudad.setVisualizacionZona(this.visualizacion);
		this.ciudad.setIconos(this.iconos);
	}

	public void addAgenteOrigen(OrigenAgentes origen) {
		this.origenesAgentes.add(origen);
	}


	public Heuristica getHeuristica() {
		return heuristica;
	}


	public void setHeuristica(Heuristica heuristica) {
		this.heuristica = heuristica;
	}


	public VisualizacionZona getVisualizacion() {
		return visualizacion;
	}


	public void setVisualizacion(VisualizacionZona visualizacion) {
		this.visualizacion = visualizacion;
	}

}

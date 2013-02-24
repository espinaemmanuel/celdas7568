package ar.uba.fi.celdas7568.ciudad;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ar.uba.fi.celdas.som.NodoSOM;
import ar.uba.fi.celdas.som.SOM;
import ar.uba.fi.celdas7568.ciudad.gui.IconoGrilla;
import ar.uba.fi.celdas7568.ciudad.gui.IconosCiudad;
import ar.uba.fi.celdas7568.ciudad.gui.ItemCiudad;
import ar.uba.fi.celdas7568.ciudad.gui.ModeloGrilla;
import ar.uba.fi.celdas7568.ciudad.heuristicas.Heuristica;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Ciudad implements ModeloGrilla {
	
	private Set<ElementoCiudad> elementosCiudad = Sets.newHashSet();
	private Heuristica heuristica;
	private VisualizacionZona visualizacionZona;
	private SOM som;
	private IconosCiudad iconos;
	
	private List<ObservadorModeloGrilla> observadores = Lists.newArrayList();
	
	private NodoSOM ultimoColocado = null;
	
	private int ancho;
	private int alto;
	private String nombre;
	private boolean inicializada = false;
	
	public Ciudad(String nombre, int ancho, int alto){
		this.nombre = nombre;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	@Override
	public String toString(){
		return this.nombre;
	}
	
	protected void inicializarRed(){
		this.som = new SOM(this.ancho, this.alto, heuristica.getDimensionOpinion());
		
		for(ElementoCiudad zona : elementosCiudad){
			Personalidad p = heuristica.habitanteInicial(zona);
			
			this.som.forzarEstado(zona.x, zona.y, heuristica.vectorizar(p));
		}
		
		this.som.setDecaidaDistancia(ConfiguracionSimulador.getDouble(ConfiguracionSimulador.DECAIDA_DISTANCIA));
		this.som.setDecaimientoLearningRate(ConfiguracionSimulador.getDouble(ConfiguracionSimulador.DECAIDA_LEARNING_RATE));
		this.som.setDecaimientoRadio(ConfiguracionSimulador.getDouble(ConfiguracionSimulador.DECAIDA_RADIO));
		this.som.setIndesicion(ConfiguracionSimulador.getDouble(ConfiguracionSimulador.INDESICION));
		this.som.setLearningRateInicial(ConfiguracionSimulador.getDouble(ConfiguracionSimulador.LEARNING_RATE_INICIAL));
		this.som.setRadioInicial(ConfiguracionSimulador.getDouble(ConfiguracionSimulador.RADIO_INICIAL));
		
		inicializada = true;
	}
	
	public void agregarAgente(Agente agente){
		if(!inicializada){
			inicializarRed();
		}
		this.ultimoColocado = this.som.agregarVector(heuristica.vectorizar(agente.getPersonalidad()));
		this.notificarObservadores();
	}
	
	public void addElementoCiudad(ElementoCiudad elementoCiudad){
		this.elementosCiudad.add(elementoCiudad);
	}

	@Override
	public int getAncho() {
		return this.ancho;
	}

	@Override
	public int getAlto() {
		return this.alto;
	}

	@Override
	public double getIntensidad(int x, int y) {
		if(!inicializada){
			inicializarRed();
		}
		
		Personalidad p = heuristica.obtenerPersonalidad(this.som.getNodo(x, y).getVector());
		return visualizacionZona.getIntensidadColor(p);
	}

	@Override
	public List<IconoGrilla> getIconos() {
		
		List<IconoGrilla> listaIconos = Lists.newArrayList();
		
		for(ElementoCiudad zona : elementosCiudad){
			for(ItemCiudad item : zona.itemsCiudad){
				listaIconos.add(new IconoGrilla(zona.x, zona.y, iconos.getIcono(item)));				
			}
		}
		
		if(ultimoColocado != null){
			listaIconos.add(new IconoGrilla(ultimoColocado.getPunto().x, ultimoColocado.getPunto().y, iconos.getIcono(ItemCiudad.AGENTE)));
		}
		
		return listaIconos;
	}

	public String getNombre() {
		return nombre;
	}

	public Heuristica getHeuristica() {
		return heuristica;
	}

	public void setHeuristica(Heuristica heuristica) {
		this.heuristica = heuristica;
	}

	public VisualizacionZona getVisualizacionZona() {
		return visualizacionZona;
	}

	public void setVisualizacionZona(VisualizacionZona visualizacionZona) {
		this.visualizacionZona = visualizacionZona;
	}

	public void setIconos(IconosCiudad iconos) {
		this.iconos = iconos;
	}
	
	@Override
	public void agregarObservador(ObservadorModeloGrilla observador){
		this.observadores.add(observador);
	}
	
	public void notificarObservadores(){
		for(ObservadorModeloGrilla observador : observadores){
			observador.notificarCambio();
		}
	}

	@Override
	public Color getColor() {
		return this.visualizacionZona.getColorBase();
	}

}

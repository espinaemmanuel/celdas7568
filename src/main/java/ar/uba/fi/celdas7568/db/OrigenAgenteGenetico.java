package ar.uba.fi.celdas7568.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import ar.uba.fi.celdas7568.ciudad.Agente;
import ar.uba.fi.celdas7568.ciudad.AtributoPersonalidad;
import ar.uba.fi.celdas7568.ciudad.Personalidad;
import ar.uba.fi.celdas7568.ciudad.gui.VentanaPrincipal;

public class OrigenAgenteGenetico implements OrigenAgentes {
	
	private double tolerancia;
	private int sizeGeneracion;
	private ArrayList<Agente> pool = Lists.newArrayList();
	
	private final static Logger logger = Logger
			.getLogger(OrigenAgenteGenetico.class.getName());
	
	protected class AgenteGenerado extends Agente{
		
		private Map<Agente, Double> origenes = Maps.newHashMap();

		public AgenteGenerado(Personalidad personalidad, String nombre, Map<Agente, Double> origenes) {
			super(personalidad, nombre);
			this.origenes = origenes;
		}
		
		@Override
		public String toString(){
			String texto = "Agente generado: ";
			for(Map.Entry<Agente, Double> origen : origenes.entrySet()){
				texto += String.format("%s  (%f %%)- ", origen.getKey().getNombre(), origen.getValue());
			}
			
			return texto;
		}
	}
	
	public OrigenAgenteGenetico(Collection<Agente> poolInicial, double tolerancia, int sizeGeneracion){
		this.tolerancia = tolerancia;
		this.sizeGeneracion = sizeGeneracion;
		this.pool.addAll(poolInicial);
	}
	
	public boolean agenteValido(AgenteGenerado agente){
		double[] doubleArray = new double[agente.getPersonalidad().values().size()];
		int i = 0;
		for(Double val : agente.getPersonalidad().values()){
			doubleArray[i++] = val;
		}
		DescriptiveStatistics stats = new DescriptiveStatistics(doubleArray);
		double desvio = stats.getStandardDeviation();
		
		boolean aceptado = desvio >  tolerancia;
		if(aceptado){
			logger.info("agente genetico generado, desviacion de atributos: " + desvio);
		}
		
		return aceptado;
	}
	
	public AgenteGenerado generarAgente(){
		int numOrigenes = Math.min(this.sizeGeneracion, pool.size());
		
	    Collections.shuffle(this.pool);
	    
	    Random rand = new Random();
	    double[] pesos = new double[numOrigenes];
	    double acumulador = 0;
	    for(int i=0; i<pesos.length; i++){
	    	pesos[i] = rand.nextDouble();
	    	acumulador += pesos[i];
	    }
	    
	    //normalizar
	    for(int i=0; i<pesos.length; i++){
	    	pesos[i] = pesos[i] / acumulador;
	    }
	    
	    Personalidad personalidad = new Personalidad();
	    for(AtributoPersonalidad atributo : AtributoPersonalidad.values()){
	    	double valorFinal = 0;
	    	for(int i=0; i<numOrigenes; i++){
	    		Agente origen = this.pool.get(i);
	    		double valorAtributo  = origen.getPersonalidad().containsKey(atributo) ? origen.getPersonalidad().get(atributo) : 0;	    		
	    		valorFinal += valorAtributo*pesos[i];
	    	}
	    	personalidad.put(atributo, valorFinal);
	    }
		
	    Map<Agente, Double> origenesValores = Maps.newHashMap();
    	for(int i=0; i<numOrigenes; i++){
    		origenesValores.put(this.pool.get(i), pesos[i]);
    	}

		return new AgenteGenerado(personalidad, "Generado", origenesValores);
	}

	@Override
	public Agente obtenerAgente() {
		AgenteGenerado generado = generarAgente();
		
		while(!agenteValido(generado)){
			generado = generarAgente();
		}
		
		pool.add(generado);
		
		logger.info("Agente generado: " + generado);
		
		return generado;
	}
	
	@Override
	public String toString(){
		return "Genetico";
	}

}

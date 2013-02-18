package ar.uba.fi.celdas.som;

import java.util.Map;

import static com.google.common.base.Preconditions.*;

import com.google.common.collect.Maps;

public class SOM {
	
	private Map<Punto, NodoSOM> nodos = Maps.newHashMap();
	private int dimension;
	
	private double radioInicial = 10;
	private double decaimientoRadio = 100;
	
	private double lrInicial = 1;
	private double decaimientoLr = 100;
	
	private double radioVecindad;
	private double learningRate;
	
	private int contador = 0;
	
	public SOM(int ancho, int alto, int dimension){
		this.dimension = dimension;
		
		for(int i=0; i < ancho*alto; i++){
			Punto posicion = new Punto(i % ancho, i / ancho);
			
			nodos.put(posicion, new NodoSOM(posicion, new double[dimension]));	
		}
	}
	
	public NodoSOM agregarVector(double[] vector){
		checkArgument(vector.length == dimension, "el vector no es del tama�o requerido");
		
		this.contador++;
		
		//Buscar el Best Matching unit
		double minimoScore = 10000;
		NodoSOM nodoSeleccionado = null;
		for(NodoSOM nodo : this.nodos.values()){
			
			double score = calcularScore(nodo.getVector(), vector);
			
			if(score < minimoScore){
				minimoScore = score;
				nodoSeleccionado = nodo;
			}
		}
		
		this.radioVecindad = calcularNuevoRadio();
		this.learningRate = calcularNuevoLearningRate();
		
		//Actualizar la red
		for(NodoSOM nodo : this.nodos.values()){
			double distancia = calcularDistancia(nodoSeleccionado, nodo);
			
			if(distancia > radioVecindad){
				continue;
			}
			
			double[] w0 = nodo.getVector();
			double influenciaVecindario = calcularInfluenciaVecindario(distancia, this.radioVecindad);
			
			assert influenciaVecindario <= 1.0;
			assert this.learningRate <= 1.0; 
			
			double[] w1 = VectorUtil.suma(w0, VectorUtil.producto(influenciaVecindario*this.learningRate, VectorUtil.differencia(vector, w0)));
			
			nodo.setVector(w1);
		}
		
		return nodoSeleccionado;
	}

	private double calcularNuevoLearningRate() {
		return this.lrInicial*Math.exp(-1.0*((double)this.contador)/this.decaimientoLr);
	}

	private double calcularNuevoRadio() {
		return this.radioInicial*Math.exp(-1.0*((double)this.contador)/this.decaimientoRadio);
	}

	private double calcularInfluenciaVecindario(double d,
			double r) {
		if(r == 0) return 0;
		
		return Math.exp(-1.0*d*d/(2*r*r));
	}

	private double calcularDistancia(NodoSOM n1, NodoSOM n2) {
		return VectorUtil.distancia(new double[]{n1.getPunto().x, n1.getPunto().y}, new double[]{n2.getPunto().x, n2.getPunto().y});
	}

	private double calcularScore(double[] v1, double[] v2) {
		return VectorUtil.distancia(v1, v2);
	}

	public NodoSOM getNodo(int x, int y) {
		return this.nodos.get(new Punto(x, y));
	}

	public void forzarEstado(int x, int y, double[] vectorizar) {
		Punto posicion = new Punto(x, y);
		nodos.put(posicion, new NodoSOM(posicion, vectorizar));			
	}
}

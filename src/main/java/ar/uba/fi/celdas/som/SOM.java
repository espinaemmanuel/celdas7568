package ar.uba.fi.celdas.som;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.*;

import ar.uba.fi.celdas7568.db.OrigenAgenteGenetico;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SOM {

	private final static Logger logger = Logger.getLogger(SOM.class.getName());

	private Map<Punto, NodoSOM> nodos = Maps.newHashMap();
	private int dimension;

	private double radioInicial = 15;
	private double decaidaDistancia = 5;
	private double decaimientoRadio = 200;
	private double learningRateInicial = 1;
	private double decaimientoLearningRate = 200;
	private double indesicion = 0.01;

	private double radioVecindad;
	private double learningRate;

	private class Score implements Comparable<Score> {
		NodoSOM nodo;
		double valor;

		public Score(NodoSOM nodo, double score) {
			super();
			this.nodo = nodo;
			this.valor = score;
		}

		@Override
		public int compareTo(Score o) {
			return Double.compare(this.valor, o.valor);
		}
	}

	private int contador = 0;

	public SOM(int ancho, int alto, int dimension) {
		this.dimension = dimension;

		for (int i = 0; i < ancho * alto; i++) {
			Punto posicion = new Punto(i % ancho, i / ancho);

			nodos.put(posicion, new NodoSOM(posicion, new double[dimension]));
		}

	}

	public NodoSOM agregarVector(double[] vector) {
		checkArgument(vector.length == dimension,
				"el vector no es del tama�o requerido");

		this.contador++;

		// Buscar el Best Matching unit
		double minimoScore = Double.POSITIVE_INFINITY;
		double maximoScore = 0;

		List<Score> scores = Lists.newArrayList();

		for (NodoSOM nodo : this.nodos.values()) {

			Score score = new Score(nodo, calcularScore(nodo, vector));
			scores.add(score);

			if (score.valor < minimoScore) {
				minimoScore = score.valor;
			}

			if (score.valor > maximoScore) {
				maximoScore = score.valor;
			}
		}

		// extraer los más cercanos como candidatos
		double maximoScorePermitido = minimoScore + (maximoScore - minimoScore)
				* indesicion;
		List<Score> scoresCandidatos = Lists.newArrayList();
		for (Score score : scores) {
			if (score.valor <= maximoScorePermitido) {
				scoresCandidatos.add(score);
			}
		}

		logger.info("Cantidad de nodos candidatos: " + scoresCandidatos.size());

		// Elegir al azar uno de los candidatos
		Random random = new Random();
		int indiceElegido = random.nextInt(scoresCandidatos.size());
		NodoSOM nodoSeleccionado = scoresCandidatos.get(indiceElegido).nodo;
		double scoreSeleccionado = scoresCandidatos.get(indiceElegido).valor;

		// Afectar una vecindad
		this.radioVecindad = calcularNuevoRadio();
		this.learningRate = calcularNuevoLearningRate();

		// Actualizar la red
		double indesicionProducida = (maximoScorePermitido - scoreSeleccionado)
				/ (maximoScorePermitido - minimoScore);
		logger.info("Indesicion: " + indesicionProducida);
		logger.info("Radio: " + this.radioVecindad);
		logger.info("Learning rate: " + this.learningRate);

		for (NodoSOM nodo : this.nodos.values()) {
			double distancia = calcularDistancia(nodoSeleccionado, nodo);

			if (distancia > radioVecindad) {
				continue;
			}

			double[] w0 = nodo.getVector();
			double influenciaVecindario = influenciaProximidad(distancia,
					this.radioVecindad);

			assert influenciaVecindario <= 1.0;
			assert this.learningRate <= 1.0;

			double[] w1 = VectorUtil.suma(w0, VectorUtil.producto(
					indesicionProducida * influenciaVecindario
							* this.learningRate,
					VectorUtil.differencia(vector, w0)));

			nodo.setVector(w1);
		}

		return nodoSeleccionado;
	}

	private double calcularNuevoLearningRate() {
		return this.learningRateInicial
				* Math.exp(-1.0 * ((double) this.contador)
						/ this.decaimientoLearningRate);
	}

	private double calcularNuevoRadio() {
		return this.radioInicial
				* Math.exp(-1.0 * ((double) this.contador)
						/ this.decaimientoRadio);
	}

	private double influenciaProximidad(double d, double r) {
		if (r == 0)
			return 0;

		return Math.exp(-1.0 * this.decaidaDistancia * d * d / (r * r));
	}

	private double calcularDistancia(NodoSOM n1, NodoSOM n2) {
		return VectorUtil.distancia(
				new double[] { n1.getPunto().x, n1.getPunto().y },
				new double[] { n2.getPunto().x, n2.getPunto().y });
	}

	private double calcularScore(NodoSOM nodo, double[] v2) {
		return VectorUtil.distancia(nodo.getVector(), v2);
	}

	public NodoSOM getNodo(int x, int y) {
		return this.nodos.get(new Punto(x, y));
	}

	public void forzarEstado(int x, int y, double[] vectorizar) {
		Punto posicion = new Punto(x, y);
		nodos.put(posicion, new NodoSOM(posicion, vectorizar));
	}

	public double getRadioInicial() {
		return radioInicial;
	}

	public void setRadioInicial(double radioInicial) {
		this.radioInicial = radioInicial;
	}

	public double getDecaidaDistancia() {
		return decaidaDistancia;
	}

	public void setDecaidaDistancia(double decaidaDistancia) {
		this.decaidaDistancia = decaidaDistancia;
	}

	public double getDecaimientoRadio() {
		return decaimientoRadio;
	}

	public void setDecaimientoRadio(double decaimientoRadio) {
		this.decaimientoRadio = decaimientoRadio;
	}

	public double getLearningRateInicial() {
		return learningRateInicial;
	}

	public void setLearningRateInicial(double learningRateInicial) {
		this.learningRateInicial = learningRateInicial;
	}

	public double getDecaimientoLearningRate() {
		return decaimientoLearningRate;
	}

	public void setDecaimientoLearningRate(double decaimientoLearningRate) {
		this.decaimientoLearningRate = decaimientoLearningRate;
	}

	public double getIndesicion() {
		return indesicion;
	}

	public void setIndesicion(double indesicion) {
		this.indesicion = indesicion;
	}
}

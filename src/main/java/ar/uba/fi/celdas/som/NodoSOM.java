package ar.uba.fi.celdas.som;

import java.util.Set;

import com.google.common.collect.Sets;

public class NodoSOM {
	
	private Punto punto;
	private double[] vector;
	
	public NodoSOM(Punto punto, double[] vector) {
		super();
		this.punto = punto;
		this.vector = vector;
	}

	public Punto getPunto() {
		return punto;
	}

	public void setPunto(Punto punto) {
		this.punto = punto;
	}

	public double[] getVector() {
		return vector;
	}

	public void setVector(double[] vector) {
		this.vector = vector;
	}
	
	@Override
	public String toString(){
		return String.format("(%d, %d)", this.punto.x, this.punto.y);
	}

}

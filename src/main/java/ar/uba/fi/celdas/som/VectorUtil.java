package ar.uba.fi.celdas.som;

import static com.google.common.base.Preconditions.*;

public class VectorUtil {
	
	/**
	 * Calcula v1 - v2;
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double[] differencia(double[] v1, double[] v2){
		checkArgument(v1.length == v2.length);
		
		double[] res = new double[v1.length];
		
		for(int i=0; i < v1.length; i++){
			res[i] = v1[i] - v2[i];
		}
		
		return res;
	}
	
	public static double[] producto(double n, double[] v){
		double[] res = new double[v.length];
		
		for(int i=0 ; i < v.length ; i++){
			res[i] = n*v[i];
		}
		
		return res;
	}
	
	public static double[] suma(double[] v1, double[] v2){
		checkArgument(v1.length == v2.length);
		
		double[] res = new double[v1.length];
		
		for(int i=0; i < v1.length; i++){
			res[i] = v1[i] + v2[i];
		}
		
		return res;
	}
	
	public static double norma(double[] v){
		
		double acomulador = 0;

		for(int i=0; i < v.length; i++){
			acomulador += v[i]*v[i];
		}
		
		return Math.sqrt(acomulador);
	}
	
	public static double distancia(double[] v1, double[] v2){
		checkArgument(v1.length == v2.length);
		
		return norma(differencia(v1, v2));
	}

}

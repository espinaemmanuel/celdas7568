package ar.uba.fi.celdas7568.ciudad.heuristicas;

import ar.uba.fi.celdas7568.ciudad.AtributoPersonalidad;
import ar.uba.fi.celdas7568.ciudad.Personalidad;
import ar.uba.fi.celdas7568.ciudad.ElementoCiudad;
import ar.uba.fi.celdas7568.ciudad.gui.ItemCiudad;


public class HeuristicaSimple implements Heuristica{

	@Override
	public Personalidad habitanteInicial(ElementoCiudad zona) {
		
		Personalidad personalidad = new Personalidad();
	
		double cultura = 0;
		cultura += zona.itemsCiudad.contains(ItemCiudad.TEATRO) ? 1 : 0;
		cultura += zona.itemsCiudad.contains(ItemCiudad.UNIVERSIDAD) ? 1 : 0;
		personalidad.put(AtributoPersonalidad.CULTURA, cultura);

		double diversion = 0;
		diversion += zona.itemsCiudad.contains(ItemCiudad.TEATRO) ? 1 : 0;
		diversion += zona.itemsCiudad.contains(ItemCiudad.BAR) ? 1 : 0;
		diversion += zona.itemsCiudad.contains(ItemCiudad.PARQUE) ? 1 : 0;
		personalidad.put(AtributoPersonalidad.DIVERSION, diversion);

		double seguridad = 0;
		seguridad += zona.itemsCiudad.contains(ItemCiudad.COMISARIA) ? 1 : 0;
		seguridad += zona.itemsCiudad.contains(ItemCiudad.BOMBEROS) ? 1 : 0;
		seguridad += zona.itemsCiudad.contains(ItemCiudad.HOSPITAL) ? 1 : 0;
		seguridad += zona.itemsCiudad.contains(ItemCiudad.PELIGRO) ? -1 : 0;
		personalidad.put(AtributoPersonalidad.SEGURIDAD, seguridad);
		
		double educacion = 0;
		educacion += zona.itemsCiudad.contains(ItemCiudad.UNIVERSIDAD) ? 1 : 0;
		personalidad.put(AtributoPersonalidad.EDUCACION, educacion);

		double naturaleza = 0;
		naturaleza += zona.itemsCiudad.contains(ItemCiudad.PARQUE) ? 1 : 0;
		personalidad.put(AtributoPersonalidad.NATURALEZA, naturaleza);

		double familiar = 0;
		familiar += zona.itemsCiudad.contains(ItemCiudad.PARQUE) ? 1 : 0;
		familiar += zona.itemsCiudad.contains(ItemCiudad.UNIVERSIDAD) ? 1 : 0;
		familiar += zona.itemsCiudad.contains(ItemCiudad.BAR) ? -1 : 0;
		familiar += zona.itemsCiudad.contains(ItemCiudad.HOSPITAL) ? 1 : 0;
		familiar += zona.itemsCiudad.contains(ItemCiudad.SUCIO) ? 1 : 0;
		familiar += zona.itemsCiudad.contains(ItemCiudad.IGLESIA) ? 1 : 0;
		personalidad.put(AtributoPersonalidad.FAMILIAR, familiar);

		double tranquilo = 0;
		tranquilo += zona.itemsCiudad.contains(ItemCiudad.BAR) ? -1 : 0;
		tranquilo += zona.itemsCiudad.contains(ItemCiudad.EXCLUSIVA) ? 1 : 0;
		tranquilo += zona.itemsCiudad.contains(ItemCiudad.PARQUE) ? 1 : 0;
		tranquilo += zona.itemsCiudad.contains(ItemCiudad.TRANSITO) ? -1 : 0;
		personalidad.put(AtributoPersonalidad.TRANQUILO, tranquilo);

		double transporte = 0;
		transporte += zona.itemsCiudad.contains(ItemCiudad.COLECTIVO) ? 1 : 0;
		transporte += zona.itemsCiudad.contains(ItemCiudad.SUBTE) ? 1 : 0;
		transporte += zona.itemsCiudad.contains(ItemCiudad.TRANSITO) ? -1 : 0;
		personalidad.put(AtributoPersonalidad.TRANSPORTE, transporte);

		double barrioExclusivo = 0;
		barrioExclusivo += zona.itemsCiudad.contains(ItemCiudad.EXCLUSIVA) ? 1 : 0;
		barrioExclusivo += zona.itemsCiudad.contains(ItemCiudad.CARA) ? 1 : 0;
		personalidad.put(AtributoPersonalidad.EXCLUSIVIDAD, barrioExclusivo);

		double costo = 0;
		costo += zona.itemsCiudad.contains(ItemCiudad.CARA) ? -1 : 0;
		personalidad.put(AtributoPersonalidad.COSTO, costo);

		return personalidad;
	}

	@Override
	public int getDimensionOpinion() {
		return AtributoPersonalidad.values().length;
	}

	@Override
	public double[] vectorizar(Personalidad p) {
		
		double[] vector = new double[AtributoPersonalidad.values().length];
		
		int i=0;
		for(AtributoPersonalidad atributo : AtributoPersonalidad.values()){
			vector[i] = p.containsKey(atributo) ? p.get(atributo) : 0;
			i++;
		}
		return vector;
	}

	@Override
	public Personalidad obtenerPersonalidad(double[] vector) {
		
		Personalidad p = new Personalidad();
		int i=0;
		for(AtributoPersonalidad atributo : AtributoPersonalidad.values()){
			p.put(atributo, vector[i]);
			i++;
		}
		return p;
	}


}

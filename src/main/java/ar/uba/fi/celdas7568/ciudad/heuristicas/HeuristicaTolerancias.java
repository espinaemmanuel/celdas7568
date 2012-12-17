package ar.uba.fi.celdas7568.ciudad.heuristicas;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import ar.uba.fi.celdas7568.ciudad.OpinionSimple;
import ar.uba.fi.celdas7568.ciudad.Personalidad;
import ar.uba.fi.celdas7568.ciudad.Zona;

public class HeuristicaTolerancias implements HeuristicaDeDecision<OpinionSimple> {
	
	public static final double MIN_OPINION = 10;
	
	private Map<String, ValorTolerancia> tolerancias = Maps.newHashMap();
	
	private class ValorTolerancia{
		
		public double maximo;
		public double minimo;
		public double actual;
		
		public ValorTolerancia(double maximo, double minimo, double actual) {
			super();
			this.maximo = maximo;
			this.minimo = minimo;
			this.actual = actual;
		}
	}
	
	public HeuristicaTolerancias(){		
		tolerancias.put("cultura", new ValorTolerancia(1, 0.01, 0.5));
		tolerancias.put("diversion", new ValorTolerancia(1, 0.01, 0.5));
		tolerancias.put("seguridad", new ValorTolerancia(1, 0.01, 0.5));
		tolerancias.put("educacion", new ValorTolerancia(1, 0.01, 0.5));
		tolerancias.put("naturaleza", new ValorTolerancia(1, 0.01, 0.5));
		tolerancias.put("populoso", new ValorTolerancia(1, 0.01, 0.5));
		tolerancias.put("familiar", new ValorTolerancia(1, 0.01, 0.5));
		tolerancias.put("tranquilo", new ValorTolerancia(1, 0.01, 0.5));
		tolerancias.put("transporte", new ValorTolerancia(1, 0.01, 0.5));
		tolerancias.put("barrioExclusivo", new ValorTolerancia(1, 0.01, 0.5));
		tolerancias.put("costo", new ValorTolerancia(1, 0.01, 0.5));
	}	

	@Override
	public OpinionSimple evaluarZona(Zona zona, Personalidad personalidad) {
	
		double cultura = (double)(zona.teatros + zona.bibliotecas + zona.cines + zona.museos);
		double diversion = (double)(zona.casinos + zona.teatros + zona.cines + zona.bares + zona.parques);
		double seguridad = (double)(zona.comisarias + zona.carceles);
		double educacion = (double)(zona.universidades + zona.bibliotecas + zona.museos + zona.escuelas);
		double naturaleza = (double)(zona.plazas + zona.parques);
		double populoso = (double)(zona.densidadPoblacional + zona.transito + zona.ruido + zona.suciedad);
		double familiar = (double)(zona.escuelas + zona.universidades + zona.parques + zona.plazas);
		double tranquilo = (double)(1.0/zona.densidadPoblacional + 1.0/zona.ruido + 1.0/zona.ruido);
		double transporte = (double)(zona.subtes + zona.colectivos) + (double)(1.0/zona.transito);
		double barrioExclusivo = (double)(1.0/zona.densidadPoblacional + zona.valorMedioTierra + zona.poderAdquisitivo);
		double costo = (double)(1.0/zona.valorMedioTierra + 1.0/zona.valorMedioTierra);
		
		cultura *= personalidad.cultura/getTolerancia("cultura");
		diversion *= personalidad.diversion/getTolerancia("diversion");
		seguridad *= personalidad.seguridad/getTolerancia("seguridad");
		educacion *= personalidad.educacion/getTolerancia("educacion");
		naturaleza *= personalidad.naturaleza/getTolerancia("naturaleza");
		populoso *= personalidad.populoso/getTolerancia("populoso");
		familiar *= personalidad.familiar/getTolerancia("familiar");
		tranquilo *= personalidad.tranquilo/getTolerancia("tranquilo");
		transporte *= personalidad.transporte/getTolerancia("transporte");
		barrioExclusivo *= personalidad.barrioExclusivo/getTolerancia("barrioExclusivo");
		costo *= personalidad.costo/getTolerancia("costo");
		
		double opinion = cultura + diversion + seguridad + educacion + naturaleza + populoso +
		familiar + tranquilo + transporte + barrioExclusivo + costo;
		
		return new OpinionSimple(opinion, zona) ;
	}

	public double getTolerancia(String string) {
		return tolerancias.get(string).actual;
	}
	
	public void setToleranciaMinima(String string, double min) {
		tolerancias.get(string).minimo = min;
	}

	@Override
	public Zona evaluarOpiniones(List<OpinionSimple> opiniones,
			Personalidad personalidad) {
		
		OpinionSimple max = null;
		
		
		for(OpinionSimple o : opiniones){
			if(o.getValorOpinion() < MIN_OPINION) continue;
			
			if(max == null){
				max = o;
			} else if(max.getValorOpinion() < o.getValorOpinion()){
				max = o;
			}
		}
		
		return max != null ? max.getZona() : null;
	}

	@Override
	public void reconsiderarOpiniones(List<OpinionSimple> opiniones,
			Personalidad personalidad) {
		
		boolean zonaEncontrada = evaluarOpiniones(opiniones, personalidad) != null;
		
		for(Map.Entry<String, ValorTolerancia> t : tolerancias.entrySet()){			
			if(zonaEncontrada){
				t.getValue().minimo = t.getValue().actual;
			} else {
				t.getValue().maximo = t.getValue().actual;
			}
			
			t.getValue().actual = (t.getValue().maximo + t.getValue().minimo)/2;
			
		}
		
	}

	
}

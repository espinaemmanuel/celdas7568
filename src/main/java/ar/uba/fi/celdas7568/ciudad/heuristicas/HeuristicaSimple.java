package ar.uba.fi.celdas7568.ciudad.heuristicas;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ar.uba.fi.celdas7568.ciudad.OpinionSimple;
import ar.uba.fi.celdas7568.ciudad.Personalidad;
import ar.uba.fi.celdas7568.ciudad.Zona;

public class HeuristicaSimple implements HeuristicaDeDecision<OpinionSimple> {

	private static final double VALOR_MINIMO_OPINION = 30f;
	
	@Override
	public OpinionSimple evaluarZona(Zona zona, Personalidad personalidad) {
		
		double valorZona = 0;
		
		valorZona += zona.bares * personalidad.diversion;
		valorZona += zona.bibliotecas * personalidad.cultura;
		valorZona += zona.cines * personalidad.diversion;
		valorZona += zona.comisarias * personalidad.seguridad;
		valorZona += zona.densidadPoblacional * personalidad.populoso;
		valorZona += zona.escuelas * personalidad.familiar;
		valorZona += zona.poderAdquisitivo * personalidad.barrioExclusivo;
		valorZona += zona.ruido * -personalidad.tranquilo;
		valorZona += zona.subtes * personalidad.transporte;
		valorZona += zona.plazas * personalidad.naturaleza;
		
		return new OpinionSimple(valorZona, zona);
	}

	@Override
	public Zona evaluarOpiniones(List<OpinionSimple> opiniones, Personalidad personalidad) {
		
		OpinionSimple mejorOpinion = Collections.max(opiniones, new Comparator<OpinionSimple>() {

			@Override
			public int compare(OpinionSimple o1, OpinionSimple o2) {
				return Double.compare(o1.getValorOpinion(), o2.getValorOpinion());
			}
		});
		
		if (mejorOpinion.getValorOpinion() < VALOR_MINIMO_OPINION) {
			return null;
		}
		
		return mejorOpinion.getZona();
	}

	@Override
	public void reconsiderarOpiniones(List<OpinionSimple> opiniones, Personalidad personalidad) {

		Field[] fieldArray = personalidad.getClass().getFields();
		
	    int numeroRandom = new Random( new Date().getTime() ).nextInt(fieldArray.length);
	    
		try {
			Field field = fieldArray[numeroRandom];
			field.setFloat(personalidad, 1.0f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

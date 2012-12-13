package ar.uba.fi.celdas7568.db;

import java.util.ArrayList;
import java.util.List;

import ar.uba.fi.celdas7568.ciudad.Agente;
import ar.uba.fi.celdas7568.ciudad.Personalidad;
import ar.uba.fi.celdas7568.ciudad.heuristicas.HeuristicaSimple;

public class AgenteDBMemoria implements AgenteDB {

	@Override
	public List<Agente> obtenerAgentes() {
		
		Personalidad personalidadAltaSociedad = new Personalidad();
		personalidadAltaSociedad.barrioExclusivo = 1;
		personalidadAltaSociedad.costo = -1;
		personalidadAltaSociedad.cultura = 1;
		personalidadAltaSociedad.diversion = 0;
		personalidadAltaSociedad.educacion = 0.5f;
		personalidadAltaSociedad.familiar = 0.5f;
		personalidadAltaSociedad.naturaleza = 0;
		personalidadAltaSociedad.populoso = 0;
		personalidadAltaSociedad.seguridad = 0.8f;
		personalidadAltaSociedad.tranquilo = 0;
		personalidadAltaSociedad.transporte = 0;
		
		Personalidad personalidadFiestera = new Personalidad();
		personalidadFiestera.barrioExclusivo = -1;
		personalidadFiestera.costo = 0;
		personalidadFiestera.cultura = 0;
		personalidadFiestera.diversion = 1;
		personalidadFiestera.educacion = 0;
		personalidadFiestera.familiar = -1;
		personalidadFiestera.naturaleza = 0;
		personalidadFiestera.populoso = 1;
		personalidadFiestera.seguridad = 0;
		personalidadFiestera.tranquilo = -1;
		personalidadFiestera.transporte = 0;
		
		Agente agenteAltaSociedad = new Agente("Alta Sociedad", personalidadAltaSociedad, new HeuristicaSimple());
		Agente agenteFiestero = new Agente("Fiestero", personalidadFiestera, new HeuristicaSimple());
		
		List<Agente> listaAgentes = new ArrayList<Agente>();
		listaAgentes.add(agenteAltaSociedad);
		listaAgentes.add(agenteFiestero);
		
		return listaAgentes;
	}

}

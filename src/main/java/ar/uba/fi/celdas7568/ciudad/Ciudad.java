package ar.uba.fi.celdas7568.ciudad;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public class Ciudad implements Iterable<Zona>{
	
	Multimap<Zona, Agente> agentes = LinkedListMultimap.create();
	Set<Zona> zonas = Sets.newHashSet();
	
	public void agregarAgente(Agente agente){
		Zona zonaElegida = agente.elegirZona(this);
		agentes.put(zonaElegida, agente);		
	}

	public Iterator<Zona> iterator() {
		return zonas.iterator();
	}
	
	public void addZona(Zona zona){
		this.zonas.add(zona);
	}
	
	public Collection<Agente> getAgentesEnZona(Zona zona){
		return agentes.get(zona);
	}

}

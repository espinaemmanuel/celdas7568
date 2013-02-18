package ar.uba.fi.celdas7568.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Properties;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import ar.uba.fi.celdas7568.ciudad.Agente;
import ar.uba.fi.celdas7568.ciudad.AtributoPersonalidad;
import ar.uba.fi.celdas7568.ciudad.Ciudad;
import ar.uba.fi.celdas7568.ciudad.ElementoCiudad;
import ar.uba.fi.celdas7568.ciudad.Personalidad;
import ar.uba.fi.celdas7568.ciudad.gui.ItemCiudad;

public class OrigenAgenteJson implements OrigenAgentes {
	
	private Agente agente;
	
	protected Agente load(File file) throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		JsonParser parser = new JsonParser();
		JsonObject agenteJson = parser.parse(new FileReader(file)).getAsJsonObject();
		
		Personalidad personalidad = new Personalidad();
		String nombre = null;
		
		
		for(Map.Entry<String, JsonElement> atributo : agenteJson.entrySet()){
			if(atributo.getKey().equals("nombre")){
				nombre = atributo.getValue().getAsString();
			} else {
				AtributoPersonalidad atPersonalidad = AtributoPersonalidad.valueOf(atributo.getKey());
				personalidad.put(atPersonalidad, atributo.getValue().getAsDouble());
			}
		}
		
		return new Agente(personalidad, nombre);
	}
	
	public OrigenAgenteJson(File origen) throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		this.agente = load(origen);
	}

	@Override
	public Agente obtenerAgente() {
		return agente;
	}
	
	@Override
	public String toString(){
		return this.agente.getNombre();
	}

}

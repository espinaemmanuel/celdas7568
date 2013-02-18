package ar.uba.fi.celdas7568.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import ar.uba.fi.celdas7568.ciudad.Ciudad;
import ar.uba.fi.celdas7568.ciudad.ElementoCiudad;
import ar.uba.fi.celdas7568.ciudad.gui.ItemCiudad;

public class CiudadLoaderJson {
	
	public static Ciudad load(File file) throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		JsonParser parser = new JsonParser();
		JsonObject ciudadJson = parser.parse(new FileReader(file)).getAsJsonObject();
		
		int ancho = ciudadJson.get("ancho").getAsInt();
		int alto = ciudadJson.get("alto").getAsInt();
		String nombre = ciudadJson.get("nombre").getAsString();
		
		Ciudad nuevaCiudad = new Ciudad(nombre, ancho, alto);

		for(JsonElement element : ciudadJson.get("edificios").getAsJsonArray()){
			JsonObject elemento = element.getAsJsonObject();
			
			int elementoX = elemento.get("x").getAsInt();
			int elementoY = elemento.get("y").getAsInt();
			ItemCiudad itemCiudad = ItemCiudad.valueOf(elemento.get("tipo").getAsString());
			
			ElementoCiudad elementoCiudad = new ElementoCiudad(elementoX, elementoY, itemCiudad);
			
			nuevaCiudad.addElementoCiudad(elementoCiudad);
			
		}
		
		return nuevaCiudad;
	}

}

package ar.uba.fi.celdas7568.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import ar.uba.fi.celdas7568.ciudad.Ciudad;
import ar.uba.fi.celdas7568.ciudad.Zona;

public class CiudadDbJson {
	
	public static Ciudad load(String fileName) throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		JsonParser parser = new JsonParser();
		JsonArray arrayZonas = parser.parse(new FileReader(new File(fileName))).getAsJsonArray();
		
		Gson gson = new Gson();
		
		Ciudad nuevaCiudad = new Ciudad();
		
		for(int i=0; i<arrayZonas.size(); i++){
			Zona zona = gson.fromJson(arrayZonas.get(i), Zona.class);
			nuevaCiudad.addZona(zona);
		}
		
		return nuevaCiudad;
	}

}

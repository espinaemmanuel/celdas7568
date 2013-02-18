package ar.uba.fi.celdas7568.ciudad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import ar.uba.fi.celdas7568.ciudad.gui.IconosCiudad;
import ar.uba.fi.celdas7568.ciudad.gui.ModeloSimulador;
import ar.uba.fi.celdas7568.ciudad.gui.VentanaPrincipal;
import ar.uba.fi.celdas7568.ciudad.heuristicas.Heuristica;
import ar.uba.fi.celdas7568.ciudad.heuristicas.HeuristicaSimple;
import ar.uba.fi.celdas7568.db.CiudadLoaderJson;
import ar.uba.fi.celdas7568.db.OrigenAgenteJson;
import ar.uba.fi.celdas7568.db.OrigenAgenteRandom;

public class SimularAgentesMain {

	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, IOException {

			Heuristica  heuristica = new HeuristicaSimple();
			VisualizacionZona visualizacion = new VisualizacionZona();
			IconosCiudad iconos = new IconosCiudad();
			ModeloSimulador modeloSimulador = new ModeloSimulador(heuristica, visualizacion, iconos);

			//Cargar ciudades
			File ciudades = new File("./ciudades");
			if(ciudades.listFiles().length == 0){
				modeloSimulador.setCiudad(new Ciudad("Arkham", 100, 100));
			} else {
				Ciudad ciudad = CiudadLoaderJson.load(ciudades.listFiles()[0]);
				modeloSimulador.setCiudad(ciudad);
			}
			
			//Cargar personalidades
			modeloSimulador.addAgenteOrigen(new OrigenAgenteRandom());
			
			File agentes = new File("./agentes");
			for(File agenteFile : agentes.listFiles()){
				OrigenAgenteJson origenJson = new OrigenAgenteJson(agenteFile);
				modeloSimulador.addAgenteOrigen(origenJson);
			}
			
            try {
                VentanaPrincipal.launch(modeloSimulador);
            }
            catch (Exception e){
                System.err.println("Error al cargar file!");
            }  
 	}
}

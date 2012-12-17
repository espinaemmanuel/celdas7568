package ar.uba.fi.celdas7568.ciudad;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import ar.uba.fi.celdas7568.db.AgenteDBMemoria;
import ar.uba.fi.celdas7568.db.CiudadDbJson;

public class SimularAgentesMain {

	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		
		Agente agenteElegido = pedirAgente();
		System.out.println("\n\n");
        System.out.println(agenteElegido);
		
        Ciudad ciudad = CiudadDbJson.load("newYork.json");
        
        ciudad.agregarAgente(agenteElegido);

	}

	private static Agente pedirAgente() {
		List<Agente> listaAgentes = new AgenteDBMemoria().obtenerAgentes();
		
		System.out.println("Elija uno de los siguientes agentes: \n");
		
		int i = 1;
		for (Agente agente : listaAgentes) {
			System.out.println("\t" + i + ". " + agente.getNombre());
			
			i++;
		}
		
		Scanner scanner = new Scanner(System.in);
        int numeroAgenteElegido= Integer.valueOf(scanner.nextLine());

		return listaAgentes.get(numeroAgenteElegido - 1);
	}

}

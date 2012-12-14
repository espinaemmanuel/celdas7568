package ar.uba.fi.celdas7568.ciudad;

import java.util.List;
import java.util.Scanner;

import ar.uba.fi.celdas7568.db.AgenteDBMemoria;

public class SimularAgentesMain {

	public static void main(String[] args) {
		
		Agente agenteElegido = pedirAgente();
		System.out.println("\n\n");
		
        System.out.println(agenteElegido);
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

package ar.uba.fi.celdas7568.ciudad;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import ar.uba.fi.celdas7568.ciudad.gui.VentanaPrincipal;
import ar.uba.fi.celdas7568.db.AgenteDBMemoria;
import ar.uba.fi.celdas7568.db.CiudadDbJson;
import javax.swing.JOptionPane;

public class SimularAgentesMain {

	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		
		//Agente agenteElegido = pedirAgente(); // LO VA A HACER LA GUI
		//System.out.println("\n\n");
                //System.out.println(agenteElegido);
            try {
                
                //ciudad.agregarAgente(agenteElegido);
               // VentanaOpciones vo = new VentanaOpciones();
                VentanaPrincipal.launch(null, null);

            }
            catch (Exception e){
                System.err.println("Error al cargar file!");
            }
        
        
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

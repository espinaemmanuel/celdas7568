package ar.uba.fi.celdas7568.ciudad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfiguracionSimulador {
	
	public final static String RADIO_INICIAL = "som.radioInicial";
	public final static String DECAIDA_DISTANCIA = "som.decaidaDistancia";
	public final static String DECAIDA_RADIO = "som.decaimientoRadio";
	public final static String LEARNING_RATE_INICIAL = "som.learningRateInicial";
	public final static String DECAIDA_LEARNING_RATE = "som.decaimientoLearningRate";
	public final static String INDESICION = "som.indesicion";
	public final static String TOLERANCIA_GENERADOR = "genetico.tolerancia";
	public final static String ORIGENES_GENERADOR = "genetico.origenes";


	public static Properties config = new Properties();
	private final static Logger logger = Logger
			.getLogger(ConfiguracionSimulador.class.getName());
	
	static{
		try {
			config.load(new FileReader(new File("config.properties")));
		} catch (FileNotFoundException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static double getDouble(String property){
		return Double.parseDouble(config.getProperty(property));
	}
	
	public static int getInteger(String property){
		return Integer.parseInt(config.getProperty(property));
	}
}

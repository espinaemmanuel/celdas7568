package ar.uba.fi.celdas7568.ciudad.gui;

import java.util.List;

import ar.uba.fi.celdas7568.ciudad.ObservadorModeloGrilla;

public interface ModeloGrilla {
	
	public int getAncho();
	
	public int getAlto();
	
	public double getIntensidad(int x, int y);
	
	public List<IconoGrilla> getIconos();
	
	public void agregarObservador(ObservadorModeloGrilla observador);

}

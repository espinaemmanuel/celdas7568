package ar.uba.fi.celdas7568.ciudad.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import ar.uba.fi.celdas7568.ciudad.ObservadorModeloGrilla;

import static com.google.common.base.Preconditions.*;

public class Grilla extends JPanel implements ObservadorModeloGrilla {
	
	private ModeloGrilla modelo;
	private int ancho;
	private int alto;
	private float tonoColor = 0;
	
	public Grilla(int ancho, int alto, ModeloGrilla modelo){
		checkArgument(ancho % modelo.getAncho() == 0);
		checkArgument(alto % modelo.getAlto() == 0);
		
		this.modelo = modelo;
		this.modelo.agregarObservador(this);
		this.ancho = ancho;
		this.alto = alto;		
	}
	
	@Override
    public Dimension getPreferredSize() {
		return new Dimension(this.ancho, this.alto);
	}

	@Override
	public void paintComponent(Graphics g) {
		checkArgument(this.ancho % modelo.getAncho() == 0);
		checkArgument(this.alto % modelo.getAlto() == 0);
		
		super.paintComponent(g);
		
		int anchoCelda = this.ancho / modelo.getAncho();
		int altoCelda = this.ancho / modelo.getAncho();
		
		for(int x = 0; x < modelo.getAncho(); x++){
			for(int y= 0; y < modelo.getAlto(); y++){
				
				int rectX = anchoCelda*x;
				int rectY = altoCelda*y;
				double intensidad = modelo.getIntensidad(x, y);
				
				g.setColor(buildColor(intensidad));
				g.fillRect(rectX, rectY, anchoCelda, altoCelda);
			}
		}
		
		for(IconoGrilla icono : modelo.getIconos()){
			int altoImagen = icono.getImage().getHeight();
			int anchoImagen = icono.getImage().getWidth();
			
			int centroX = this.ancho * icono.getX() / modelo.getAncho();
			int centroY = this.alto * icono.getY() / modelo.getAlto();
			
			int imagenX = centroX - anchoImagen / 2;
			int imagenY = centroY - altoImagen / 2;
			
		    g.drawImage(icono.getImage(), imagenX, imagenY, this);	
		}
		
	    g.finalize();
	}

	private Color buildColor(double intensidad) {
		return Color.getHSBColor(tonoColor, (float) intensidad, 1);
	}

	@Override
	public void notificarCambio() {
		repaint();
	}
}

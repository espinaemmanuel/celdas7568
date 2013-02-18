package ar.uba.fi.celdas7568.ciudad.gui;

import java.awt.image.BufferedImage;

public class IconoGrilla {
	
	private int x;
	private int y;
	private BufferedImage image;
	
	public IconoGrilla(int x, int y, BufferedImage image) {
		super();
		this.x = x;
		this.y = y;
		this.image = image;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}

}

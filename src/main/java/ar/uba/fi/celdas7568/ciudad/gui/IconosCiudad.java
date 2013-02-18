package ar.uba.fi.celdas7568.ciudad.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.common.collect.Maps;

public class IconosCiudad {
	
	private Map<ItemCiudad, BufferedImage> imagenes = Maps.newHashMap();
	
	public IconosCiudad() throws IOException{
		
		imagenes.put(ItemCiudad.COMISARIA, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/comisaria.png")));
		imagenes.put(ItemCiudad.BAR, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/bar.png")));
		imagenes.put(ItemCiudad.BOMBEROS, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/bomberos.png")));
		imagenes.put(ItemCiudad.CARA, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/cara.png")));
		imagenes.put(ItemCiudad.COLECTIVO, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/colectivo.png")));
		imagenes.put(ItemCiudad.EXCLUSIVA, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/exclusiva.png")));
		imagenes.put(ItemCiudad.HOSPITAL, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/hospital.png")));
		imagenes.put(ItemCiudad.IGLESIA, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/iglesia.png")));
		imagenes.put(ItemCiudad.PARQUE, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/parque.png")));
		imagenes.put(ItemCiudad.PELIGRO, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/peligro.png")));
		imagenes.put(ItemCiudad.RESTORAN, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/restoran.png")));
		imagenes.put(ItemCiudad.SUBTE, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/subte.png")));
		imagenes.put(ItemCiudad.SUCIO, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/sucio.png")));
		imagenes.put(ItemCiudad.TEATRO, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/teatro.png")));
		imagenes.put(ItemCiudad.TRANSITO, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/transito.png")));
		imagenes.put(ItemCiudad.UNIVERSIDAD, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/universidad.png")));
		imagenes.put(ItemCiudad.AGENTE, ImageIO.read(IconosCiudad.class.getResourceAsStream("/ar/uba/fi/celdas/iconos/agente.png")));

	}
	
	public BufferedImage getIcono(ItemCiudad item){
		return imagenes.get(item);
	}

}

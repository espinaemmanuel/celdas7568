package ar.uba.fi.celdas7568.ciudad;

import java.awt.Color;
import java.util.Map;

import com.google.common.collect.Maps;

public class VisualizacionZona {

	private AtributoPersonalidad vistaActual = AtributoPersonalidad.DIVERSION;
	private static Map<AtributoPersonalidad, Color> colorAtributo = Maps
			.newHashMap();

	static {
		colorAtributo.put(AtributoPersonalidad.CULTURA, Color.BLUE);
		colorAtributo.put(AtributoPersonalidad.DIVERSION, Color.RED);
		colorAtributo.put(AtributoPersonalidad.SEGURIDAD, Color.BLACK);
		colorAtributo.put(AtributoPersonalidad.EDUCACION, Color.YELLOW);
		colorAtributo.put(AtributoPersonalidad.NATURALEZA, Color.GREEN);
		colorAtributo.put(AtributoPersonalidad.POPULOSO, Color.CYAN);
		colorAtributo.put(AtributoPersonalidad.FAMILIAR, Color.MAGENTA);
		colorAtributo.put(AtributoPersonalidad.TRANQUILO, Color.ORANGE);
		colorAtributo.put(AtributoPersonalidad.TRANSPORTE, Color.PINK);
		colorAtributo.put(AtributoPersonalidad.EXCLUSIVIDAD,
				Color.getHSBColor(0.5f, 1f, 0.5f));
		colorAtributo.put(AtributoPersonalidad.COSTO,
				Color.getHSBColor(0.7f, 1f, 0.5f));

	}

	public double getIntensidadColor(Personalidad personalidad) {
		return personalidad.containsKey(vistaActual) ? personalidad
				.get(vistaActual) : 0;
	}
	
	public Color getColorBase(){
		return colorAtributo.get(vistaActual);
	}

	public AtributoPersonalidad getVistaActual() {
		return vistaActual;
	}

	public void setVistaActual(AtributoPersonalidad vistaActual) {
		this.vistaActual = vistaActual;
	}

}

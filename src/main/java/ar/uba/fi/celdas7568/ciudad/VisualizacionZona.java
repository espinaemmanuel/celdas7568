package ar.uba.fi.celdas7568.ciudad;

public class VisualizacionZona {
	
	//private AtributoPersonalidad vistaActual = AtributoPersonalidad.values()[0];
	
	private AtributoPersonalidad vistaActual = AtributoPersonalidad.DIVERSION;
	
	public double getIntensidadColor(Personalidad personalidad){
		return personalidad.containsKey(vistaActual) ? personalidad.get(vistaActual) : 0 ;
	}

	public AtributoPersonalidad getVistaActual() {
		return vistaActual;
	}

	public void setVistaActual(AtributoPersonalidad vistaActual) {
		this.vistaActual = vistaActual;
	}

}

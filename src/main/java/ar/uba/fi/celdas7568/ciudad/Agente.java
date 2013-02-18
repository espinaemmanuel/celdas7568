package ar.uba.fi.celdas7568.ciudad;


public class Agente {
	
	private Personalidad personalidad;
	private String nombre;
	
	public Agente(Personalidad personalidad, String nombre) {
		super();
		this.personalidad = personalidad;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		
		Personalidad personalidad = getPersonalidad();
		
		return "Personalidad del agente: " + getNombre() + "\n\n" + personalidad.toString();
	}
	
	public Personalidad getPersonalidad() {
		return personalidad;
	}

	public void setPersonalidad(Personalidad personalidad) {
		this.personalidad = personalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

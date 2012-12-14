package ar.uba.fi.celdas7568.ciudad;

/**
 * Cada valor tiene un rango entre -1 y 1 que indica la importancia que la persona le da a la caracteristica.
 * Valores negativos indican que la caracteristica es indeseable
 * Valores positivos indican que la caracteristica es deseable
 * Valores cero indican que la caracteristica es indistinta
 * @author emmanuelespina
 *
 */
public class Personalidad {
	public float cultura;
	public float diversion;
	public float seguridad;
	public float educacion;
	public float naturaleza;
	public float populoso;
	public float familiar;
	public float tranquilo;
	public float transporte;
	public float barrioExclusivo;
	public float costo;
	
	@Override
	public String toString() {
		
		String descripcion = "cultura: " + cultura + "\n" +
		"diversion: " + diversion + "\n" +
		"seguridad: " + seguridad + "\n" +
		"educacion: "  + educacion + "\n" +
		"naturaleza: " + naturaleza  + "\n" +
		"seguridad: " + seguridad + "\n" +
        "populoso: " + populoso + "\n" +
        "familiar: " + familiar + "\n" +
        "tranquilo: " + tranquilo + "\n" +
        "transporte: " + transporte + "\n" +
        "barrioExclusivo: " + barrioExclusivo + "\n" +
        "costo: " + costo + "\n";
		
		return descripcion;
	}
}

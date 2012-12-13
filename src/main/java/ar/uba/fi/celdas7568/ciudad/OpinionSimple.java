package ar.uba.fi.celdas7568.ciudad;

public class OpinionSimple extends Opinion {

	private double valorOpinion;
	
	public OpinionSimple(double valorOpinion, Zona zona) {
		this.valorOpinion = valorOpinion;
		setZona(zona);
	}

	public double getValorOpinion() {
		return valorOpinion;
	}

	public void setValorOpinion(double valorOpinion) {
		this.valorOpinion = valorOpinion;
	}
}

package cartes;

public class Borne extends Carte {
	private Integer km;

	public Borne(Integer km) {
		super();
		this.km = km;
	}

	public Integer getKm() {
		return km;
	}
}

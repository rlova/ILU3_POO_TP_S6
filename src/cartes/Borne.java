package cartes;

import java.util.Objects;

public class Borne extends Carte {
	private int km;

	public Borne(int km) {
		this.km = km;
	}

	public int getKm() {
		return km;
	}
	
	@Override
	public String toString() {
		return km +"KM";
	}

	@Override
	public int hashCode() {
		return Objects.hash(km);
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && km == ((Borne) obj).km;
	}
	
	
}

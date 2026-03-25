package jeu;

import java.util.Objects;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	
	private Joueur(String nom) {
		this.nom = nom;
		this.zoneDeJeu = new ZoneDeJeu();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		return Objects.equals(nom, other.nom);
	}

	@Override
	public String toString() {
		return "Joueur "+ nom;
	}

	public ZoneDeJeu getZoneDeJeu() {
		return zoneDeJeu;
	}
	
	
}

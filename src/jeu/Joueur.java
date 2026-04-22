package jeu;

import java.util.Objects;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur main;

	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}
	
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) return null;
		Carte carte = sabot.piocher();
		main.prendre(carte);
		return carte;
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}

	@Override
	public boolean equals(Object obj) {
		Joueur other = (Joueur) obj;
		return obj != null && Objects.equals(nom, other.nom);
	}

	@Override
	public String toString() {
		return "Joueur "+ nom;
	}

	public ZoneDeJeu getZoneDeJeu() {
		return zoneDeJeu;
	}
	
	
}

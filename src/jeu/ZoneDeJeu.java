package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Probleme;
import cartes.Type;

public class ZoneDeJeu {
	private List<Limite> pileLimites;
	private List<Probleme> pileBatailles;
	private List<Borne> collectionBornes;
	private Set<Botte> bottes;

	public ZoneDeJeu() {
		this.pileLimites = new ArrayList<>();
		this.pileBatailles = new ArrayList<>();
		this.collectionBornes = new ArrayList<>();
		this.bottes = new HashSet<>();
	}

	public int donnerLimitationVitesse() {
		if (bottes.contains(Cartes.PRIORITAIRE)) {
			return 200;
		}
		if (pileLimites.isEmpty()) {
			return 200;
		}
		Limite sommet = pileLimites.get(pileLimites.size() - 1);
		if (sommet instanceof FinLimite)
			return 200;
		else
			return 50;
	}

	public int donnerKmParcourus() {
		int total = 0;
		for (Borne borne : collectionBornes) {
			total += borne.getKm();
		}
		return total;
	}

	public void deposer(Carte c) {
		if (c instanceof Borne borne) {
			collectionBornes.add(borne);
		}
		if (c instanceof Limite limite) {
			pileLimites.add(limite);
		}
		if (c instanceof Bataille bataille) {
			pileBatailles.add(bataille);
		}
	}

	public boolean peutAvancer() {
		if (pileBatailles.isEmpty())
			return false;
		Probleme sommet = pileBatailles.get(pileBatailles.size() - 1);
//		return !(pileBatailles.isEmpty()) && sommet.equals(Cartes.FEU_VERT);
		return (sommet instanceof Parade) && sommet.getType() == Type.FEU;

	}

	public boolean estDepotFeuVertAutorise() {
		Probleme sommet = pileBatailles.get(pileBatailles.size() - 1);
		return pileBatailles.isEmpty() || (sommet instanceof Attaque && sommet.getType() == Type.FEU)
				|| (sommet instanceof Parade && sommet.getType() != Type.FEU);
	}

	public boolean estDepotBorneAutorise(Borne borne) {
		if (!peutAvancer()) {
			return false;
		}
		if (borne.getKm() > donnerLimitationVitesse()) {
			return false;
		}
		int sommeBornes = donnerKmParcourus() + borne.getKm();
		return sommeBornes <= 1000;
	}

	public boolean estDepotLimiteAutorise(Limite limite) {
		if (pileLimites.isEmpty())
			return (limite instanceof DebutLimite); // true si c'est un début
		Limite sommet = pileLimites.get(pileLimites.size() - 1);
		if (limite instanceof DebutLimite) {
			return pileLimites.isEmpty() || sommet instanceof FinLimite;
		}
		if (limite instanceof FinLimite) {
			return (sommet instanceof DebutLimite);
		}
		return false;
	}

	public boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque) {
			return peutAvancer();
		}
		if (bataille instanceof Parade) {
			Probleme sommet = pileBatailles.isEmpty() ? null : pileBatailles.get(pileBatailles.size() - 1);
			if (bataille.equals(Cartes.FEU_VERT)) {
				return pileBatailles.isEmpty() || (sommet instanceof Attaque && sommet.getType() == Type.FEU)
						|| (sommet instanceof Parade && sommet.getType() != Type.FEU);
			} else {
				if (pileBatailles.isEmpty()) {
					return false;
				}
				return (sommet instanceof Attaque) && (sommet.getType() == bataille.getType());
			}
		}
		return false;
	}

	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne borne)
			return estDepotBorneAutorise(borne);
		if (carte instanceof Limite limite)
			return estDepotLimiteAutorise(limite);
		if (carte instanceof Bataille bataille)
			return estDepotBatailleAutorise(bataille);
		return false;
	}
}

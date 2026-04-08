package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.Cartes;
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

	public boolean estPrioritaire() {
		Botte bottePrioritaire = new Botte(Type.FEU);
		return bottes.contains(Cartes.PRIORITAIRE);
	}

	public int donnerLimitationVitesse() {
		if (estPrioritaire()) {
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
			collectionBornes.add((Borne) c);
		}
		if (c instanceof Limite limite) {
			pileLimites.add((Limite) c);
		}
		if (c instanceof Bataille bataille) {
			pileBatailles.add((Bataille) c);
		}
	}

	public boolean peutAvancer() {
		Probleme sommet = pileBatailles.get(pileBatailles.size() - 1);
		return !(pileBatailles.isEmpty()) && sommet.equals(Cartes.FEU_VERT);
	}

	public boolean estDepotFeuVertAutorise() {
		Probleme sommet = pileBatailles.get(pileBatailles.size() - 1);
		return pileBatailles.isEmpty() || sommet.equals(Cartes.FEU_ROUGE)
				|| (sommet instanceof Parade && !sommet.equals(Cartes.FEU_VERT));
	}

	public boolean estDepotLimiteAutorise(Borne borne) {
		if (!peutAvancer()) {
			return false;
		}
		if (borne.getKm() > donnerLimitationVitesse()) {
			return false;
		}
		int sommeBornes = donnerKmParcourus() + borne.getKm();
		return sommeBornes <= 1000;
	}

}

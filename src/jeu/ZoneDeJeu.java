package jeu;

import java.util.HashSet;
import java.util.LinkedList;
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
	private List<Limite> pileLimites = new LinkedList<>();
	private List<Probleme> pileBatailles = new LinkedList<>();
	private List<Borne> collectionBornes = new LinkedList<>();
	private Set<Botte> bottes = new HashSet<>();

	public boolean estPrioritaire() {
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

	public void deposer(Carte carte) {
		if (carte instanceof Borne borne) {
			collectionBornes.add(borne);
		}
		if (carte instanceof Limite limite) {
			pileLimites.add(limite);
		}
		if (carte instanceof Bataille bataille) {
			pileBatailles.add(bataille);
		}
		if (carte instanceof Botte botte) {
			bottes.add(botte);
		}
	}

	public boolean peutAvancer() {
		if (pileBatailles.isEmpty() && estPrioritaire()) {
			return true;	
		}
		Probleme sommet = pileBatailles.get(pileBatailles.size() - 1);
		if ((sommet instanceof Parade) && sommet.getType() == Type.FEU) {
			return  true;
		}
		if (sommet instanceof Parade && estPrioritaire()) {
			return true;
		}
		if ((sommet instanceof Attaque) && sommet.getType() == Type.FEU && estPrioritaire()) {
			return true;
		}
		return (sommet instanceof Attaque) && sommet.getType() != Type.FEU && estPrioritaire();
	}

	public boolean estDepotFeuVertAutorise() {
		if (estPrioritaire()) {
			return false;
		}
		Probleme sommet = pileBatailles.get(pileBatailles.size() - 1);
		return pileBatailles.isEmpty() || (sommet instanceof Attaque && sommet.getType() == Type.FEU)
				|| (sommet instanceof Parade && sommet.getType() != Type.FEU) || (sommet instanceof Attaque && 
						bottes.contains(sommet));
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
		if (estPrioritaire()) {
			return false;
		}
		if (pileLimites.isEmpty()) {
			return (limite instanceof DebutLimite); // true si c'est un début
		}
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
		if (bottes.equals(bataille.getType())) {
			return false;
		}
//		if (bataille.equals(bottes)) {
//			return false;
//		}
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
		if (carte instanceof Botte) {
			return true;
		}
		if (carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		}
		if (carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		}
		if (carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		}
		return false;
	}
}

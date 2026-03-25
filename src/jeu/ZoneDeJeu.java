package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Borne;
import cartes.FinLimite;
import cartes.Limite;

public class ZoneDeJeu {
	private List<Limite> pileLimites;
	private List<FinLimite> finLimite;
	private List<Borne> collectionBornes;
	
	public ZoneDeJeu() {
		this.pileLimites = new ArrayList<>();
		this.finLimite = new ArrayList<>();
		this.collectionBornes = new ArrayList<>();
	}
}

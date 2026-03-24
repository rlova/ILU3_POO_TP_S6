package jeu;

import cartes.JeuDeCartes;
import utils.GestionCartes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.Carte;

public class Jeu {
	private Sabot sabot;
	public Jeu() {
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		Carte[] tableauCartes = jeuDeCartes.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, tableauCartes);
		listeCartes = GestionCartes.melanger(listeCartes);
//		créer sabot avec le tableau des cartes mélangé
		Carte[] cartesMelangees = listeCartes.toArray(new Carte[0]);
		sabot = new Sabot(cartesMelangees);
	}
	
	public Sabot getSabot() {
		return sabot;
	}
}

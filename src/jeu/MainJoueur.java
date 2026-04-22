package jeu;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte>{
	private List<Carte> cartes = new LinkedList<>();
	
	public void prendre(Carte c) {
		cartes.add(c);
	}
	
	public void jouer(Carte c) {
		assert cartes.contains(c): "La carte n'est pas présente";
		cartes.remove(c);
	}
	
	@Override
	public String toString() {
		StringBuilder affichage = new StringBuilder();
		affichage.append("Main du jouer : ");
		for (Carte carte : cartes) {
			affichage.append(carte).append(" ");
		}
		return affichage.toString();
	}
	
	@Override
	public Iterator<Carte> iterator() {
		return cartes.iterator();
	}
}

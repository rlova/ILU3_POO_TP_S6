package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte> {
	private int nbCartes;
	private Carte[] cartes;
	private int nombreOperations = 0;
	
	public Sabot(Carte[] carteInitiale) {
		super();
		this.nbCartes = carteInitiale.length;
		this.cartes = carteInitiale;
	}

	public boolean estVide() {
		return nbCartes == 0;
	}

	public void ajouterCarte(Carte carteAjoutee) {
		if (nbCartes >= cartes.length) {
			throw new IllegalArgumentException("Dépassement capacité");
		} 
		cartes[nbCartes++] = carteAjoutee;
		nombreOperations++;
	}

	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	public Carte piocher() {
		Iterator<Carte> it = iterator();
		if (estVide()) {
			throw new NoSuchElementException("Sabot vide");
		}
		Carte carteASupp = it.next();
		it.remove();
		return carteASupp;
	}

	private class Iterateur implements Iterator<Carte> {
		int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nombreOperationReference = nombreOperations;
	
		@Override
		public boolean hasNext() {
			return indiceIterateur<nbCartes;
		}

		@Override
		public Carte next() {
			verificationConcurrence();
			if (hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carte;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			verificationConcurrence();
			if (nbCartes<1 || !nextEffectue) {
				throw new IllegalStateException();
			}
			for (int i=indiceIterateur-1; i<nbCartes-1; i++) {
				cartes[i] = cartes[i+1];
			}
			nbCartes--;
			indiceIterateur--;
			nextEffectue = false;
		}
		
		private void verificationConcurrence() {
			if (nombreOperations != nombreOperationReference) {
				throw new ConcurrentModificationException();
			}
		}
	}
}

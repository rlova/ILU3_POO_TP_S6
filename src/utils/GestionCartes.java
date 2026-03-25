package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	private GestionCartes() {
		throw new IllegalStateException("Gestion Cartes");
	}

	private static Random random = new Random();
	
	public  static <T> T extraire1(List<T> liste) {
		int index = random.nextInt(liste.size());
		return liste.remove(index);
	}
	
	public static <T> T extraireAvecIterateur(List<T> liste) {
		int index = random.nextInt(liste.size());
		ListIterator<T> it = liste.listIterator(index);
		T element = it.previous();
		it.remove();
		return element;
	}
	
	public static <T> List<T> melanger(List<T> liste) {
		List<T> listeMelangee = new ArrayList<>();
		while (!liste.isEmpty()) {
			T elem = extraire1(liste);
			listeMelangee.add(elem);
		}
		return listeMelangee;
	}
	
	public static <T> boolean verifierMelange(List<T> liste1, List<T> liste2) {
		if (liste1.size() != liste2.size()) return false;
		for (T element : liste1) {
			int freq1 = Collections.frequency(liste1, element);
			int freq2 = Collections.frequency(liste2, element);
			if (freq1!=freq2) return false;
		}
		return true;
	}
	
	public static <T> List<T> rassembler(List<T> liste) {
		ArrayList<T> listeRassemblee = new ArrayList<>();
		ArrayList<T> listeCopie = new ArrayList<>(liste);	// pour parcourir
		while (!listeCopie.isEmpty()) {
			T element = listeCopie.get(0);	// premier element de la liste
			for (Iterator<T> it = listeCopie.iterator(); it.hasNext();) {
				T elem = it.next();
				if (elem.equals(element)) {
					listeRassemblee.add(elem);
					it.remove();
				}
			}
		}
		return listeRassemblee;
	}
	
	public static <T> boolean verifierElementSurResteListe(T elementAVerifier, ListIterator<T> resteListe) {
		boolean elementEstPresent = false;
		while (resteListe.hasNext()) {
			if (elementAVerifier!=null && resteListe.next().equals(elementAVerifier)) {
				elementEstPresent = true;
			}
		}
		return elementEstPresent;
	}
	
	public static <T> boolean verifierRassemblement(List<T> liste) {
		if (liste == null || liste.isEmpty() || liste.size() <=1) return true;
		for (ListIterator<T> it = liste.listIterator(); it.hasNext();) {
			T previous = it.next();	// balayer la liste 
			T current = it.next();	// balayer le reste de la liste
			if (current!=null && previous!=null && !current.equals(previous)) {
				ListIterator<T> resteListe = liste.listIterator(it.nextIndex());
//				pour vérifier si l'ancien réapparait dans le reste de la liste
				if (resteListe.hasNext() && verifierElementSurResteListe(previous, resteListe)) {
					return false;
				}
			}
		}
		return true;
	}
}

package cartes;

public class JeuDeCartes {
	private Configuration[] typesDeCartes = { new Configuration(new Borne(25), 10),
			new Configuration(new Borne(50), 10), new Configuration(new Borne(75), 10),
			new Configuration(new Borne(100), 12), new Configuration(new Borne(200), 4),
			new Configuration(new Parade(Type.FEU), 14), new Configuration(new FinLimite(), 6),
			new Configuration(new Parade(Type.ESSENCE), 6), new Configuration(new Parade(Type.CREVAISON), 6),
			new Configuration(new Parade(Type.ACCIDENT), 6), new Configuration(new Attaque(Type.FEU), 5),
			new Configuration(new DebutLimite(), 4), new Configuration(new Attaque(Type.ESSENCE), 3),
			new Configuration(new Attaque(Type.CREVAISON), 3), new Configuration(new Attaque(Type.ACCIDENT), 3),
			new Configuration(new Botte(Type.FEU), 1), new Configuration(new Botte(Type.ESSENCE), 1),
			new Configuration(new Botte(Type.CREVAISON), 1), new Configuration(new Botte(Type.ACCIDENT), 1), };

	public String affichageJeuDeCartes() {
		StringBuilder affichage = new StringBuilder();
		for (Configuration conf : typesDeCartes) {
			affichage.append(conf.getNbExemplaires());
			affichage.append(' ');
			affichage.append(conf.getCarte());
			affichage.append('\n');
		}
		return affichage.toString();
	}

	public Carte[] donnerCartes() {
		int totalCartes = 0;
		for (Configuration configuration : typesDeCartes) {
			totalCartes += configuration.getNbExemplaires();
		}
		Carte[] cartes = new Carte[totalCartes];
		int nombreCarte = 0;
		for (Configuration configuration : typesDeCartes) {
			Carte carte = configuration.getCarte();
			for (int i = 0; i < configuration.getNbExemplaires(); i++) {
				cartes[nombreCarte] = carte;
				nombreCarte++;
			}
		}
		return cartes;
	}
	
	public boolean checkCount() {
		Carte[] cartes = donnerCartes();
		for (Configuration configuration : typesDeCartes) {
			int nombreCartes = 0;
			int nbrCarteAttendu = configuration.nbExemplaires;
			Carte carteAttendue = configuration.carte;
			for (Carte carte : cartes) {
				if (carte.equals(carteAttendue)) {
					nombreCartes++;
				}
			}
			if (nombreCartes!=nbrCarteAttendu) {
				return false;
			}
		}
		return true;
	}

//	classe interne
	private static class Configuration {
		private int nbExemplaires;
		private Carte carte;

		private Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}
	}
}
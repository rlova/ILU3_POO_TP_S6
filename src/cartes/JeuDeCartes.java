package cartes;

public class JeuDeCartes {
	private Configuration[] typesDeCartes = { 
			new Configuration(new Borne(25),10),
			new Configuration(new Borne(50),10), 
			new Configuration(new Borne(75),10), 
			new Configuration(new Borne(100),12), 
			new Configuration(new Borne(200),4),
			new Configuration(new Parade(Type.FEU), 14),
			new Configuration(new FinLimite(),6),
			new Configuration(new Parade(Type.ESSENCE),6),
			new Configuration(new Parade(Type.CREVAISON),6),
			new Configuration(new Parade(Type.ACCIDENT),6),
			new Configuration(new Attaque(Type.FEU),5),
			new Configuration(new DebuLimite(),4),
			new Configuration(new Attaque(Type.ESSENCE),3),
			new Configuration(new Attaque(Type.CREVAISON),3),
			new Configuration(new Attaque(Type.ACCIDENT),3),
			new Configuration(new Botte(Type.FEU),1),
			new Configuration(new Botte(Type.ESSENCE),1),
			new Configuration(new Botte(Type.CREVAISON),1),
			new Configuration(new Botte(Type.ACCIDENT),1),
	};
	
	public String affichageJeuDeCartes() {
		StringBuilder affichage = new StringBuilder();
		for (Configuration conf : typesDeCartes) {
			affichage.append(conf.getNbExemplaires()).append(' ').append(conf.getCarte()).append('\n');
		}
		return affichage.toString();
	}


	public Carte[] donnerCartes() {
		int totalCartes = 0;
		for (Configuration c : typesDeCartes) {
			totalCartes += c.getNbExemplaires();
		}
		Carte[] cartes = new Carte[totalCartes];
		int numCarte = 0;
		for (Configuration c : typesDeCartes) {
			Carte carte = c.getCarte();
			for (int i=0; i<c.getNbExemplaires();i++) {
				cartes[numCarte] = carte;
				numCarte++;
			}
		}
		return cartes;
	}
	
//	classe interne
	private static class Configuration {
		private int nbExemplaires;
		private Carte carte;

		public Configuration(Carte carte, int nbExemplaires) {
			super();
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}

		public Integer getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}
	}
}

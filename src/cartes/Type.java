package cartes;

public enum Type {
	FEU("Feu rouge","Feu vert","Véhicule prioritaire"),
	ESSENCE("Panne d'essence","Essence","Citerne d'essence"),
	CREVAISON("Crevaison","Roue de secours","Increvable"),
	ACCIDENT("Accident","Réparations","As du volant");

	private String attaques;
	private String parades;
	private String bottes;
	
	Type(String attaques, String parades, String bottes) {
		this.attaques = attaques;
		this.parades = parades;
		this.bottes = bottes;
	}

	public String getAttaques() {
		return attaques;
	}

	public String getParades() {
		return parades;
	}

	public String getBottes() {
		return bottes;
	}
	
	
}

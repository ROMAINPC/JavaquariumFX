package fr.romainpc;

public enum Genre {
	
	MALE ("mâle"),
	FEMELLE ("femelle");
	
	private String chaine;
	
	private Genre(String chaine) {
		this.chaine = chaine;
	}
	
	public String toString() {
		
		return chaine;
		
	}
}

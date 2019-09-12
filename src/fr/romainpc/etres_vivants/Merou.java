package fr.romainpc.etres_vivants;

import fr.romainpc.Genre;
import javafx.scene.image.Image;

public class Merou extends PoissonCarnivore{

	public Merou(String nom, Genre sexe) {
		this(nom, sexe, 0);
	}
	
	public Merou(String nom, Genre sexe, int age) {
		super(nom, sexe, age);
		this.setImage( new Image("merou.png"));
	}

	@Override
	String race() {
		return "Merou";
	}
	
	

}

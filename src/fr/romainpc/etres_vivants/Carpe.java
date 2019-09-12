package fr.romainpc.etres_vivants;

import fr.romainpc.Genre;
import javafx.scene.image.Image;

public class Carpe extends PoissonHerbivore{
	
	public Carpe(String nom, Genre sexe) {
		this(nom, sexe, 0);
	}
	
	public Carpe(String nom, Genre sexe, int age) {
		super(nom, sexe, age);
		this.setImage( new Image("carpe.png"));
	}
	
	@Override
	String race() {
		return "Carpe";
	}
}

package fr.romainpc.etres_vivants;

import fr.romainpc.Genre;
import javafx.scene.image.Image;

public class Thon extends PoissonCarnivore{
	
	public Thon(String nom, Genre sexe) {
		this(nom, sexe, 0);
	}
	
	public Thon(String nom, Genre sexe, int age) {
		super(nom, sexe, age);
		this.setImage( new Image("thon.png"));
	}

	@Override
	String race() {
		return "Thon";
	}
}

package fr.romainpc.etres_vivants;

import fr.romainpc.Genre;
import javafx.scene.image.Image;

public class Bar extends PoissonHerbivore{
	
	public Bar(String nom, Genre sexe) {
		this(nom, sexe, 0);
	}
	
	public Bar(String nom, Genre sexe, int age) {
		super(nom, sexe, age);
		this.setImage( new Image("bar.png"));
	}

	@Override
	String race() {
		return "Bar";
	}
}

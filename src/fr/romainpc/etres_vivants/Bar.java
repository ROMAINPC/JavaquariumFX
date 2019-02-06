package fr.romainpc.etres_vivants;

import fr.romainpc.Genre;
import javafx.scene.image.Image;

public class Bar extends PoissonHerbivore{
	
	public Bar(String nom, Genre sexe) {
		super(nom, sexe);
		this.setImage( new Image("bar.png"));
	}

	@Override
	String race() {
		return "Bar";
	}
}

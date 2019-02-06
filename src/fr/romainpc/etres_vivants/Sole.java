package fr.romainpc.etres_vivants;

import fr.romainpc.Genre;
import javafx.scene.image.Image;

public class Sole extends PoissonHerbivore{

	public Sole(String nom, Genre sexe) {
		super(nom, sexe);
		this.setImage( new Image("sole.png"));
	}

	@Override
	String race() {
		return "Sole";
	}

}

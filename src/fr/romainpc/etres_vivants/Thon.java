package fr.romainpc.etres_vivants;

import fr.romainpc.Genre;
import javafx.scene.image.Image;

public class Thon extends PoissonCarnivore{
	
	public Thon(String nom, Genre sexe) {
		super(nom, sexe);
		this.setImage( new Image("thon.png"));
	}

	@Override
	String race() {
		return "Thon";
	}
}

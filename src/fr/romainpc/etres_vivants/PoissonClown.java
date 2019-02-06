package fr.romainpc.etres_vivants;

import fr.romainpc.Genre;
import javafx.scene.image.Image;

public class PoissonClown extends PoissonCarnivore{
	
	public PoissonClown(String nom, Genre sexe) {
		super(nom, sexe);
		this.setImage( new Image("poissonClown.png"));
	}

	@Override
	String race() {
		return "Poisson-clown";
	}
}

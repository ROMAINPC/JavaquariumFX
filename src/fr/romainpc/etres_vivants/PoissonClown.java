package fr.romainpc.etres_vivants;

import fr.romainpc.Genre;
import javafx.scene.image.Image;

public class PoissonClown extends PoissonCarnivore{
	
	public PoissonClown(String nom, Genre sexe) {
		this(nom, sexe, 0);
	}
	
	public PoissonClown(String nom, Genre sexe, int age) {
		super(nom, sexe, age);
		this.setImage( new Image("poissonClown.png"));
	}

	@Override
	String race() {
		return "Poisson-clown";
	}
}

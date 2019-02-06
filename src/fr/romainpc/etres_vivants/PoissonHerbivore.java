package fr.romainpc.etres_vivants;

import fr.romainpc.Genre;
import fr.romainpc.Main;
import javafx.scene.paint.Color;

public abstract class PoissonHerbivore extends Poisson implements Mangeur{

	public PoissonHerbivore(String nom, Genre sexe) {
		super(nom, sexe);
	}

	@Override
	public void manger() {
		
		Main.getConsole().afficher("Je suis " + this.getNom() + " et je mange des algues", Color.DARKRED);
		
	}

}

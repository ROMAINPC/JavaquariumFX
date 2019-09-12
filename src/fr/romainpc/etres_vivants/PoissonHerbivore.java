package fr.romainpc.etres_vivants;

import java.util.Random;

import fr.romainpc.Aquarium;
import fr.romainpc.Genre;
import fr.romainpc.Main;
import javafx.scene.paint.Color;

public abstract class PoissonHerbivore extends Poisson implements Mangeur{

	public PoissonHerbivore(String nom, Genre sexe, int age) {
		super(nom, sexe, age);
	}

	@Override
	public void manger() {
		
		
		Aquarium aqua = Main.getAqua();
		
		if(aqua.algueList.size() > 0) {
			Random r = new Random();
			int i = r.nextInt(aqua.algueList.size());
			
			this.getPoissonView().goTo(aqua.algueViewList.get(i).getLayoutX(), aqua.algueViewList.get(i).getLayoutY() + 150);
			aqua.threadPause();
			
			aqua.algueList.get(i).addVie(-2);
			this.addVie(3);
			
			
			
			Main.getConsole().afficher(this.getNom() + " a mangé une algue.", Color.DARKRED);
			return;
		}
		
		
		Main.getConsole().afficher(this.getNom() + " n'a plus rien à manger.", Color.DARKRED);
		
	}

}

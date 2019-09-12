package fr.romainpc.etres_vivants;

import java.util.Collections;
import java.util.Random;

import fr.romainpc.Aquarium;
import fr.romainpc.Genre;
import fr.romainpc.Main;
import javafx.scene.paint.Color;

public abstract class PoissonCarnivore  extends Poisson implements Mangeur{

	public PoissonCarnivore(String nom, Genre sexe, int age) {
		super(nom, sexe, age);
	}

	@Override
	public void manger() {
		
		Aquarium aqua = Main.getAqua();
		
		
		
		if(aqua.poissonList.size() - Collections.frequency(aqua.poissonList, null) > 1) {
			
			Random r = new Random();
			int i;
			
			do {
				
				i = r.nextInt(aqua.poissonList.size());
				
			}while(aqua.poissonList.get(i) == null);
			
			
			if(aqua.poissonList.get(i) == this || aqua.poissonList.get(i).getClass().getName().equals(this.getClass().getName())) {
				Main.getConsole().afficher(this.getNom() + " n'a rien trouvé à manger.", Color.DARKRED);
				return;
			}
			
			String nom = aqua.poissonList.get(i).getNom();
			
			aqua.poissonList.get(i).getPoissonView().arreter();
			this.getPoissonView().goTo(aqua.poissonViewList.get(i).getLayoutX(), aqua.poissonViewList.get(i).getLayoutY());
			aqua.threadPause();
			aqua.poissonList.get(i).getPoissonView().relancer();
			
			aqua.poissonList.get(i).addVie(-4);
			this.addVie(5);
			
			
			Main.getConsole().afficher(this.getNom() + " a mangé " + nom + ".", Color.DARKRED);
			return;
		}
		
		
		Main.getConsole().afficher(this.getNom() + " n'a plus rien à manger.", Color.DARKRED);
		
		

	}

}

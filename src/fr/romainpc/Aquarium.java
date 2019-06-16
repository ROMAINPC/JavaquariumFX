/*******************************************************************************
 * Copyright (C) 2019 ROMAINPC LECHAT
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Idée originale : SpaceFox
 ******************************************************************************/
package fr.romainpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import fr.romainpc.etres_vivants.Algue;
import fr.romainpc.etres_vivants.AlgueView;
import fr.romainpc.etres_vivants.Bar;
import fr.romainpc.etres_vivants.Carpe;
import fr.romainpc.etres_vivants.Merou;
import fr.romainpc.etres_vivants.Poisson;
import fr.romainpc.etres_vivants.PoissonClown;
import fr.romainpc.etres_vivants.PoissonView;
import fr.romainpc.etres_vivants.Sole;
import fr.romainpc.etres_vivants.Thon;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Aquarium extends Group{
	
	private final Random random = new Random();
	
	private Console console;
	private long tour;
	public boolean execution;
	
	public Thread boucle;
	
	
	public ArrayList<PoissonView> poissonViewList = new ArrayList<PoissonView>();
	public ArrayList<Poisson> poissonList = new ArrayList<Poisson>();
	public ArrayList<AlgueView> algueViewList = new ArrayList<AlgueView>();
	public ArrayList<Algue> algueList = new ArrayList<Algue>();
	
	
	public Aquarium() {
		//initialisation graphique:
		
		
		Image img = new Image("fond.jpg", 680, 550, false, false);
		ImageView fond = new ImageView(img);
		this.getChildren().add(fond);
		
		
		
		
		console = Main.getConsole();
		
		tour = 1;
		execution = true;
		
		
	}
	
	
	
	public  void launch() {
		
		
		//ajout des poissons:
		
		ajouterPoisson("Bernard", Genre.MALE, Espece.BAR);
		ajouterPoisson("Jean", Genre.MALE, Espece.CARPE);
		ajouterPoisson("Yvone", Genre.FEMELLE, Espece.MEROU);
		ajouterPoisson("Mélodie", Genre.FEMELLE, Espece.POISSON_CLOWN);
		ajouterPoisson("Baptiste", Genre.MALE, Espece.SOLE);
		ajouterPoisson("Johnson", Genre.MALE, Espece.THON);
		for(int i =0 ; i < 10;i++)
			ajouterAlgue();
		
		console.afficher("Au début il y a :", Color.CYAN);
		console.afficher(algueList.size() + " Algues,", Color.GREEN);
		console.afficher("et " + poissonList.size() + " Poissons :", Color.GREEN);
		for(int i = 0 ; i < poissonList.size() ; i++)
			console.afficher(poissonList.get(i).toString(), Color.GREEN);
		
		
		Aquarium aqua = this;
		
		boucle = new Thread(new Runnable() {
			@Override
			public void run() {
				
				while(execution && !(poissonList.isEmpty() && algueList.isEmpty())) {
					
					
					
					
					
					
					aqua.passer();
					
					
					//affichage fin de tour:
					
					//console.afficher("A la fin du tour " + (tour-1), Color.CYAN);
					console.afficher("Il reste :", Color.CYAN);
					console.afficher(algueList.size() + " Algues,", Color.GREEN);
					console.afficher("et " + poissonList.size() + " Poissons :", Color.GREEN);
					for(int i = 0 ; i < poissonList.size() ; i++)
						console.afficher(poissonList.get(i).toString(), Color.GREEN);
				}
				
				console.afficher("====< Fin de la simulation >====", Color.ORANGERED);
				
			}
		});
		boucle.start();
	}
	
	
	
	
	private void passer() {
		//TOUR DE SIMULATION : 
		
		
		
		console.afficher("--< Tour " + tour + " >--", Color.DARKCYAN);
		
		
		for(int i = 0  ; i < poissonList.size() ; i++) {
			if(poissonList.get(i) != null)
				poissonList.get(i).manger();
		}
		nettoyerMorts();

		
		
		
		//console.afficher("Début déplacement", Color.YELLOW);
		poissonViewList.get((int) tour%poissonList.size()).goTo(0,0); threadPause();
		//console.afficher("retour à la normale", Color.YELLOW);
		
		
		
		
		tour++;
		
		
		
		
	}
	
	
	
	
	
	private void nettoyerMorts() {
		poissonList.removeAll(Collections.singleton(null));
		poissonViewList.removeAll(Collections.singleton(null));
	}



	public void threadPause() {
		try {
			synchronized (boucle) {
				boucle.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;// nécessaire ! mais cette fonction doit rester private.
		}
	}
	
	
	
	
	
	private void ajouterPoisson(String nom, Genre sexe, Espece race) {
		Poisson p;
		
		switch(race) {
			case MEROU : p = new Merou(nom, sexe); break;
			case THON : p = new Thon(nom, sexe); break;
			case POISSON_CLOWN : p = new PoissonClown(nom, sexe); break;
			case BAR : p = new Bar(nom, sexe); break;
			case CARPE : p = new Carpe(nom, sexe); break;
			case SOLE : p = new Sole(nom, sexe); break;
			default : p = new PoissonClown("DEFAUT", sexe);
		}
		
		PoissonView pV = new PoissonView(p);
		//random.nextInt((max - min) + 1) + min;
		int n = random.nextInt((this.getChildren().size() - 1) + 1) + 1;
		this.getChildren().add(n, pV);
		poissonList.add(p);
		poissonViewList.add(pV);
	}
	
	private void ajouterAlgue() {
		Algue a = new Algue();
		AlgueView aV = new AlgueView(a);
		int n = random.nextInt((this.getChildren().size() - 1) + 1) + 1;
		this.getChildren().add(n, aV);
		algueList.add(a);
		algueViewList.add(aV);
	}



	public void supprimerAlgue(int i) {
		
		
		AlgueView cibleV = algueViewList.get(i);
		
		algueList.remove(i);
		algueViewList.remove(i);
		
		Aquarium aqua = this;
		Platform.runLater(new Runnable() {
			public void run() {
				aqua.getChildren().remove(cibleV);
			}
		});
		
		
		
	}


	public void supprimerPoisson(int i) {
		PoissonView cibleV = poissonViewList.get(i);
		cibleV.arreter();
		
		poissonList.set(i, null);
		poissonViewList.set(i, null);
		
		Aquarium aqua = this;
		Platform.runLater(new Runnable() {
			public void run() {
				aqua.getChildren().remove(cibleV);
			}
		});
	}

	

	
}

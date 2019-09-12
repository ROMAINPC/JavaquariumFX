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
	private long lastTime;
	public boolean execution;
	public boolean pause;
	
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
		pause = false;
		
	}
	
	
	
	public  void launch() {
		
		
		//ajout des poissons:
		
		ajouterPoisson(new Bar("Bernard", Genre.MALE, 5));
		ajouterPoisson(new Carpe("Jean", Genre.MALE, 5));
		ajouterPoisson(new Merou("Yvone", Genre.FEMELLE,15));
		ajouterPoisson(new PoissonClown("Mélodie", Genre.FEMELLE, 10));
		ajouterPoisson(new Sole("Baptiste", Genre.MALE));
		ajouterPoisson(new Thon("Johnson", Genre.MALE));
		for(int i = 0 ; i < 4; i++)
			ajouterAlgue(new Algue());
		
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
				
					if(pause) {
						threadPause();
					}
					aqua.passer();
					
					
					//affichage fin de tour:
					
					//console.afficher("A la fin du tour " + (tour-1), Color.CYAN);
					console.afficher("Il reste :", Color.CYAN);
					console.afficher(algueList.size() + " Algues,", Color.GREEN);
					console.afficher("et " + poissonList.size() + " Poissons :", Color.GREEN);
					for(int i = 0 ; i < poissonList.size() ; i++)
						console.afficher(poissonList.get(i).toString(), Color.GREEN);
					
					
					try {
						long duration = System.currentTimeMillis() - lastTime;
						Thread.sleep(duration < 500 ? 500-duration : 0);
						lastTime = System.currentTimeMillis();	
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
				}
				
				console.afficher("====< Fin de la simulation >====", Color.ORANGERED);
				
			}
		});
		boucle.start();
	}
	
	
	
	
	private void passer() {
		//TOUR DE SIMULATION : 
		
		
		
		console.afficher("--< Tour " + tour + " >--", Color.DARKCYAN);
		
		
		//faim des poissons:
		for(Poisson poisson : poissonList) {
			poisson.addVie(-1);
		}
		//manger:
		for(int i = 0  ; i < poissonList.size() ; i++) {
			if(poissonList.get(i) != null)
				if(poissonList.get(i).vie() <= 5)
					poissonList.get(i).manger();
		}
		nettoyerMorts();
		
		//pousse des algues:
		for(Algue algue : algueList) {
				algue.addVie(1);
		}
		
		
		
		
		
		//vieillissement:
		for(int i = 0  ; i < poissonList.size() ; i++) {
			poissonList.get(i).addAge(1);
		}
		nettoyerMorts();
		
		for(int i = 0  ; i < algueList.size() ; i++) {
			if(algueList.get(i).addAge(1)) {
				i--;
			}
		}
		
		
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
	
	
	
	
	
	private void ajouterPoisson(Poisson p) {

		PoissonView pV = new PoissonView(p);
		//random.nextInt((max - min) + 1) + min;
		int n = random.nextInt((this.getChildren().size() - 1) + 1) + 1;
		this.getChildren().add(n, pV);
		poissonList.add(p);
		poissonViewList.add(pV);
		
		
	}
	
	private void ajouterAlgue(Algue a) {
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

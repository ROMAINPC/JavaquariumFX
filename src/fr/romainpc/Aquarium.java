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
import java.util.Random;

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
	
	
	private ArrayList<PoissonView> poissonViewList = new ArrayList<PoissonView>();
	private ArrayList<Poisson> poissonList = new ArrayList<Poisson>();
	private ArrayList<AlgueView> algueViewList = new ArrayList<AlgueView>();
	private ArrayList<Algue> algueList = new ArrayList<Algue>();
	
	
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
		for(int i =0 ; i < 10;i++)
			ajouterPoisson();
		for(int i =0 ; i < 10;i++)
			ajouterAlgue();
		
		
		
		
		Aquarium aqua = this;
		
		boucle = new Thread(new Runnable() {
			@Override
			public void run() {
				
				while(execution && !(poissonList.isEmpty() && algueList.isEmpty())) {
					
					
					aqua.passer();
					
				}
				
				console.afficher("====< Fin de la simulation >====", Color.ORANGERED);
				
			}
		});
		boucle.start();
	}
	
	
	
	
	private void passer() {
		//TOUR DE SIMULATION : 
		
		
		
		console.afficher("--< Tour " + tour + " >--", Color.DARKCYAN);
		
		
		
		console.afficher("Début déplacement", Color.YELLOW);
		
		
		poissonViewList.get((int) tour%10).goTo(0,0); threadPause();
		
		
		console.afficher("retour à la normale", Color.YELLOW);
		
		
		
		
		tour++;
		
		
		
		
	}
	
	
	
	
	
	private void threadPause() {
		try {
			synchronized (boucle) {
				boucle.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;// nécessaire ! mais cette fonction doit rester private.
		}
	}
	
	
	
	
	
	private void ajouterPoisson() {
		Poisson p = new Poisson();
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
	
	
	
}

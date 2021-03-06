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
 * Id�e originale : SpaceFox
 ******************************************************************************/
package fr.romainpc.etres_vivants;

import fr.romainpc.Genre;
import fr.romainpc.Main;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Poisson implements Mangeur{
	
	private Image img;
	private String nom;
	private Genre sexe;
	private PoissonView poissonView;
	private int vie;
	private int age;
	
	public Poisson(String nom, Genre sexe, int age) {
		this.nom = nom;
		this.sexe = sexe;
		this.poissonView = null;
		this.vie = 10; 
		if(age < 0 || age > 20)//s�curit�
			age = 0;
		this.age = age;
	}

	public Image getImage() {
		return img;
	}
	
	public int vie() {
		return vie;
	}
	
	public void addVie(int n) {
		vie += n;
		if(vie <= 0) {//cas de d�c�s
			Main.getConsole().afficher(nom + " n'a plus de vie", Color.ORANGE);
			Main.getAqua().supprimerPoisson(Main.getAqua().poissonList.indexOf(this));
		}
		Platform.runLater(new Runnable() {
			public void run() {
				poissonView.updateViewInfo();
			}
		});
		
	}
	
	public int age() {
		return age;
	}
	
	public void addAge(int n) {
		age += n;
		if(age > 20) {//cas de d�c�s
			Main.getConsole().afficher(nom + " est mort de vieillesse", Color.ORANGE);
			Main.getAqua().supprimerPoisson(Main.getAqua().poissonList.indexOf(this));
		}
		Platform.runLater(new Runnable() {
			public void run() {
				poissonView.updateViewInfo();
			}
		});
		
	}
	
	
	public void setImage(Image img) {
		this.img = img;
	}
	
	
	public String getNom() {
		return nom;
	}


	public Genre getGenre() {
		return sexe;
	}
	
	public PoissonView getPoissonView() {
		return poissonView;
	}
	
	public void setPoissonView(PoissonView poissonView) {
		this.poissonView = poissonView;
	}

	
	public String toString() {
		
		return nom + " : " + this.race() + " " + sexe + "(" + vie +")("+ age + ")";
		
	}
	
	
	abstract String race();
	
	
}

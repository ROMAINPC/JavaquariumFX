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

package fr.romainpc.etres_vivants;

import java.util.Random;

import fr.romainpc.Main;
import javafx.application.Platform;
import javafx.scene.image.Image;

public class Algue {
	
	private Image img;
	private int vie;
	private int age;
	private AlgueView algueView;

	
	public Algue() {
		this(0);
	}
	
	public Algue(int age) {
		img = generateImage();
		vie = 10;
		algueView = null;
		if(age < 0 || age > 20)//sécurité
			age = 0;
		this.age = age;
	}
	
	

	private Image generateImage() {
		Random r = new Random();
		int n = r.nextInt(3);
		Image image = null;
		
		
		if(n == 0)
			image = new Image("algue1.png");
		if(n == 1)
			image = new Image("algue2.png");
		if(n == 2)
			image = new Image("algue3.png");
		
		return image;
	}

	public Image getImage() {
		return img;
	}
	
	public int vie() {
		return vie;
	}



	public void addVie(int n) {
		vie += n;
		if(vie <= 0) {//cas de décès
			Main.getAqua().supprimerAlgue(Main.getAqua().algueList.indexOf(this));
		}
		Platform.runLater(new Runnable() {
			public void run() {
				algueView.updateViewInfo();
			}
		});
		
	}
	
	public int age() {
		return age;
	}



	public boolean addAge(int n) {
		age += n;
		if(age > 20) {//cas de décès
			Main.getAqua().supprimerAlgue(Main.getAqua().algueList.indexOf(this));
			return true;
		}
		Platform.runLater(new Runnable() {
			public void run() {
				algueView.updateViewInfo();
			}
		});
		return false;
	}
	
	
	
	public AlgueView getAlgueView() {
		return algueView;
	}
	
	public void setAlgueView(AlgueView algueView) {
		this.algueView = algueView;
	}
	
}

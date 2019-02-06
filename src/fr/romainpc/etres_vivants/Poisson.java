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
import javafx.scene.image.Image;

public abstract class Poisson implements Mangeur{
	
	private Image img;
	private String nom;
	private Genre sexe;
	
	public Poisson(String nom, Genre sexe) {
		this.nom = nom;
		this.sexe = sexe;
	}

	public Image getImage() {
		return img;
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

	
	public String toString() {
		
		return nom + " : " + this.race() + " " + sexe;
		
	}
	
	abstract String race();
	
	
}
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

import java.util.Random;

import javafx.scene.image.Image;

public class Algue {
	
private Image img;
	
	public Algue() {
		img = generateImage();
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
	
}

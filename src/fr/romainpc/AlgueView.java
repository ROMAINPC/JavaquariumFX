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

import javafx.scene.image.ImageView;

public class AlgueView extends ImageView{
	
	public AlgueView(Algue algue) {
		
		this.setImage(algue.getImage());
		
		this.setLayoutY(200); //550-350
		
		Random r = new Random();
		this.setLayoutX(r.nextInt(480)); //680-200
		
		
	}
	
}

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

import fr.romainpc.LabelInfo;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AlgueView extends StackPane{
	
	
	private LabelInfo vie;
	private LabelInfo age;
	
	private Algue algue;
	
	private ImageView vue;
	
	public AlgueView(Algue algue) {
		
		this.algue = algue;
		algue.setAlgueView(this);
		
		this.setLayoutY(200); //550-350
		Random r = new Random();
		this.setLayoutX(r.nextInt(480)); //680-200

		vue = new ImageView(algue.getImage());
		
		
		VBox infos = new VBox();
		infos.setAlignment(Pos.CENTER);
		infos.setTranslateY(150);
		
		vie  = new LabelInfo();
		age  = new LabelInfo();
		
		infos.getChildren().addAll(vie, age);
		

		this.getChildren().addAll(vue,infos);
		
		updateViewInfo();
		
		
		//bouton d'affichage des infos:
		infos.setVisible(false);
		this.setOnMouseEntered(e -> infos.setVisible(true));
		this.setOnMouseExited(e -> infos.setVisible(false));
	}
	
	
	public void updateViewInfo() {
		vie.setText(Integer.toString(algue.vie()) + "PV");
		age.setText(Integer.toString(algue.age()) + "tr");
		double scale = 0.5 + algue.vie() * 0.05;
		if(scale > 1.5) {
			scale = 1.5;
		}
		vue.setScaleY(scale);
		vue.setTranslateY((350 - 350*scale)/2);
	}
	
	
	
}

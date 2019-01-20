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

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Console  extends ScrollPane{
private VBox group;
	
	public Console(){
		
		group = new VBox();
		group.setPadding(new Insets(10,10,10,10));
		this.setContent(group);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		
		
		//messages initiaux:
		
		this.afficher("====< Début de la simulation >====", Color.CYAN);
		
	}

	public void afficher(String txt, Color paint) {
		
		ScrollPane sp = this;
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Label label = new Label(txt);
				label.setFont(Font.font("Comic Sans MS", 14));
				label.setTextFill(paint);
				
				
				
				group.getChildren().add(label);
				sp.setVvalue(sp.getVmax());
				
			}
			
		});
		
				
	
	}
}

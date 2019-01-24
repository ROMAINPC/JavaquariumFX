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

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PoissonView extends StackPane{
	
	private final Random random = new Random();
	private Timeline timeline;
	private Position positionCourante;
	private double VITESSE; // px/ms
	
	private LabelInfo nom;
	private LabelInfo genre;
	
	private Poisson poisson;
	
	
	public PoissonView(Poisson poisson) {
		
		
		
		this.poisson = poisson;
		
		//affichage:
		ImageView vue = new ImageView(poisson.getImage());
		
		VBox infos = new VBox();
		infos.setAlignment(Pos.CENTER);
		
		nom  = new LabelInfo();
		genre  = new LabelInfo();
		
		infos.getChildren().addAll(nom, genre);
		
		
		this.getChildren().addAll(vue,infos);
		
		updateViewInfo();
		
		
		//bouton d'affichage des infos:
		infos.setVisible(false);
		this.setOnMouseEntered(e -> infos.setVisible(true));
		this.setOnMouseExited(e -> infos.setVisible(false));
		
		
		
		
		//animation:
		positionCourante = getRandomPos();
		this.setLayoutX(positionCourante.x);
		this.setLayoutY(positionCourante.y);
		
		timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
		
        VITESSE = 0.10;
        
        //début du déplacement
        goProchainLieu();
	}
	
	
	private void goProchainLieu(Position cible)
    {
		
		Position prochain = null;
		if(cible == null) {
			prochain = getRandomPos();
		}
		else {
			prochain = cible;
		}
		
			
		
        
        positionCourante.x = this.getLayoutX();
		positionCourante.y = this.getLayoutY();
		
		if(prochain.x > positionCourante.x) {
			this.getChildren().get(0).setScaleX(-1);
		}else {
			this.getChildren().get(0).setScaleX(1);
		}
		
		

        KeyValue kx = new KeyValue( this.layoutXProperty(), prochain.x );
        KeyValue ky = new KeyValue( this.layoutYProperty(), prochain.y );
        
        
        double distance = Math.sqrt((prochain.x - positionCourante.x) * (prochain.x - positionCourante.x) + (prochain.y - positionCourante.y) * (prochain.y - positionCourante.y));
        
        double t = distance / VITESSE;
        VITESSE = 0.10;
        
        timeline.stop();
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add( new KeyFrame( Duration.millis( t ),
                e -> {
                	goProchainLieu();
                	
                	if(cible != null) {
                		synchronized(Main.getAqua().boucle) {Main.getAqua().boucle.notify();}
                	}
                	
                }, kx, ky));
        timeline.play();
    }
	
	
	private void goProchainLieu() {
		goProchainLieu(null);
	}
	
	
	
	
	private Position getRandomPos()
    {	
		
        int x = random.nextInt( 530);//680 -150
        int y = random.nextInt( 400); //550 -100 -50
        Position p = new Position();
        p.x = x;
        p.y = y;
        return p;
    }


    private class Position
    {
        double x;
        double y;
    }

    
    //fonction pour lancer une animation de "déplacement vers" du poisson
	public void goTo(double x, double y) {
		
		
		
		timeline.stop();
		VITESSE = 0.6;
		Position p = new Position();
		p.x = x;
		p.y = y;
		goProchainLieu(p);
		
		
	}
	
	
	public void updateViewInfo() {
		this.nom.setText(poisson.getNom());
		this.genre.setText(poisson.getGenre().toString());
	}
	
	
}

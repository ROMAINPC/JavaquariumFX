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
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class PoissonView extends ImageView{
	
	private final Random random = new Random();
	private Timeline timeline;
	private Pos positionCourante;
	private double VITESSE; // px/ms
	
	
	
	public PoissonView(Poisson poisson) {
		
		this.setImage(poisson.getImage());
		
		
		positionCourante = getRandomPos();
		this.setLayoutX(positionCourante.x);
		this.setLayoutY(positionCourante.y);
		
		timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
		
        VITESSE = 0.15;
        
        goProchainLieu();
	}
	
	
	private void goProchainLieu(Pos cible)
    {
		
		Pos prochain = null;
		if(cible == null) {
			prochain = getRandomPos();
		}
		else {
			prochain = cible;
		}
		
			
		
        
        positionCourante.x = this.getLayoutX();
		positionCourante.y = this.getLayoutY();
		
		if(prochain.x > positionCourante.x) {
			this.setScaleX(-1);
		}else {
			this.setScaleX(1);
		}
		
		

        KeyValue kx = new KeyValue( this.layoutXProperty(), prochain.x );
        KeyValue ky = new KeyValue( this.layoutYProperty(), prochain.y );
        
        
        double distance = Math.sqrt((prochain.x - positionCourante.x) * (prochain.x - positionCourante.x) + (prochain.y - positionCourante.y) * (prochain.y - positionCourante.y));
        
        double t = distance / VITESSE;
        VITESSE = 0.15;
        
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
	
	
	
	
	private Pos getRandomPos()
    {	
		
        int x = random.nextInt( 530);//680 -150
        int y = random.nextInt( 400); //550 -100 -50
        Pos p = new Pos();
        p.x = x;
        p.y = y;
        return p;
    }


    private class Pos
    {
        double x;
        double y;
    }


	public void goTo(double x, double y) {
		
		
		
		timeline.stop();
		VITESSE = 0.6;
		Pos p = new Pos();
		p.x = x;
		p.y = y;
		goProchainLieu(p);
		
		
	}
	
	
	
}

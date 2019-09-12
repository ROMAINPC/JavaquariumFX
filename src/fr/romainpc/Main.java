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


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Console console;
	private static Aquarium aquarium;
	private static boolean pause;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,960,540);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			
			//l'interface sera composée de 2 parties : la console et l'aquarium
			
			//console:
			console = new Console();
			console.setPrefSize(300, 540);
			root.setRight(console);
			
			
			
			//aquarium
			
			
			aquarium = new Aquarium();
			LabelInfo pauseLabel = new LabelInfo(Color.RED, 42);
			pauseLabel.setText("PAUSE");
			pauseLabel.setLayoutX(20);
			pauseLabel.setLayoutY(20);
			pauseLabel.setVisible(false);
			
			scene.setOnKeyPressed(ke->{
				
				if(ke.getCode().equals(KeyCode.P)) {
						pause = pause ? false : true;
						pauseLabel.setVisible(pause);
						aquarium.pause = pause;
						if(!pause) {
							synchronized(aquarium.boucle) {
								aquarium.boucle.notify();
							}
								
						}
						
				}
			});
			
			pause = false;
			aquarium.launch();
			
			Group aquariumPart = new Group();

			aquariumPart.getChildren().addAll(aquarium, pauseLabel);
			
			root.setLeft(aquariumPart);
			
			
			
			primaryStage.getIcons().add(new Image(("icone.png")));
			
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			primaryStage.setOnCloseRequest(e -> {
				aquarium.execution = false;
				aquarium.pause =  false;
				aquarium.boucle.interrupt();
			});
			
			
			
			
			
			
			
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Console getConsole() {
		return console;
	}
	public static Aquarium getAqua() {
		return aquarium;
	}
	
	
	
}

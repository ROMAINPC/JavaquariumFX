package fr.romainpc;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LabelInfo extends Label{
	
	
	public LabelInfo() {
		this.setBackground(new Background(new BackgroundFill(Color.SLATEBLUE, null, null)));
		this.setFont(Font.font(12));
		this.setTextFill(Color.WHITE);
	}

	
	
}

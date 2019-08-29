package fr.romainpc;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LabelInfo extends Label{
	
	
	public LabelInfo() {
		this(Color.SLATEBLUE, 12);
	}

	public LabelInfo(Color background, int fontSize) {
		this.setBackground(new Background(new BackgroundFill(background, null, null)));
		this.setFont(Font.font(fontSize));
		this.setTextFill(Color.WHITE);
	}
	
}

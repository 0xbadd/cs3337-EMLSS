package frontend;

import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class GUIController {
	private int x;
	private int labelSize = 40;
	private Label[][] values = new Label[100][100];
	public GUIController() {
	for (int count = 0; count < values.length; count++) {
		for (int index = 0; index < values.length; index++) {
			values[count][index] = new Label();

		}
	}
}
	//sets up buildings and streets in grid
	
	public void refreshMapLabels(GridPane gp,Map map) {
		gp.getChildren().clear();
		for (int count = 0; count < map.getMap().length; count++) {
			for (int index = 0; index < map.getMap().length; index++) {
				if (map.getMap()[count][index].getValue() == 'B') {
					values[count][index] = new Label("");
					values[count][index].getStyleClass().add("building");

					values[count][index].setMinWidth(labelSize);
					values[count][index].setMinHeight(labelSize);

					gp.add(values[count][index], count, index);

				} else if (map.getMap()[count][index].getValue() == 'S') {
					values[count][index] = new Label("");
					values[count][index].getStyleClass().add("street");
					values[count][index].setMinWidth(labelSize);
					values[count][index].setMinHeight(labelSize);

					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'P') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("patient");
					values[count][index].setMinWidth(labelSize);
					values[count][index].setMinHeight(labelSize);

					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'O') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("homebase");
					values[count][index].setMinWidth(labelSize);
					values[count][index].setMinHeight(labelSize);

					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'H') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("hospital");
					values[count][index].setMinWidth(labelSize);
					values[count][index].setMinHeight(labelSize);

					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'A') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("ambulance");
					values[count][index].setMinWidth(labelSize);
					values[count][index].setMinHeight(labelSize);

					gp.add(values[count][index], count, index);
				}
			}
			
		
	}
		
	}
	public void setButtons(HBox hb) {
		//placeholder events to be implemented in future
		Button r = new Button();
		r.setText("Start");
		TextField tx1 = new TextField();
		TextField tx2 = new TextField();
		Label ambulanceNum= new Label();
		Label patientNum= new Label();
		r.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {


			}
	
});
		Button ambu = new Button();
		ambu.setText("Ambulances");
		
		ambu.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				String num = tx1.getText();
				int ambulances= Integer.parseInt(num);
				//code to change number of ambulances  in map

		
				ambulanceNum.setText("   "+Integer.toString(ambulances)+" Ambulances");
	
				tx1.clear();

			}
		
	
});
		Button pat = new Button();
		pat.setText("Patients");
		pat.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				String num = tx2.getText();
				int patients= Integer.parseInt(num);
				//code to change number of patients, or frequency
				patientNum.setText("    "+Integer.toString(patients)+" Patients");
				tx2.clear();
			}
		
	
});
		
		
		hb.getChildren().add(0,r);
		hb.getChildren().add(1,ambu);
	hb.getChildren().add(ambulanceNum);
		hb.getChildren().add(2,tx1);
		
		hb.getChildren().add(1,pat);
		hb.getChildren().add(patientNum);
		hb.getChildren().add(2,tx2);
}

}


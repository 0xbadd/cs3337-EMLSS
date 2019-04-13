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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class GUIController {
	private char x;
	private boolean simStatus = false;
	private int labelSize = 40;
	private int patientMax=0;
	Map map=null;
	GridPane gp =null;
	private Label[][] values = new Label[100][100];
	public GUIController(Map map, GridPane gp) {
		this.map = map;
		this.gp = gp;
	for (int count = 0; count < values.length; count++) {
		for (int index = 0; index < values.length; index++) {
			values[count][index] = new Label();
		}
	}
}

	public void run() {
		
	}
	public boolean getSimStatus() {
		return simStatus;
	}
	public void changeLabel(int x1,int y) {
		//using x1 cause private char is using x
		values[x1][y] = new Label("");
		values[x1][y].setMinWidth(labelSize);
		values[x1][y].setMinHeight(labelSize);
		int n = x1;
		int m = y;
		if (map.getMap()[x1][y].getValue() == 'B') {
			values[x1][y].getStyleClass().add("building");
			values[x1][y].setTooltip(new Tooltip("Tooltip Building"));
		}
		if (map.getMap()[x1][y].getValue() == 'S') {
			values[x1][y].getStyleClass().add("street");
			values[x1][y].setTooltip(new Tooltip("Tooltip Street"));
		}
		if (map.getMap()[x1][y].getValue() == 'H') {
			values[x1][y].getStyleClass().add("hospital");
			values[x1][y].setTooltip(new Tooltip("Tooltip Hosptial"));
		}
		if (map.getMap()[x1][y].getValue() == 'O') {
			values[x1][y].getStyleClass().add("homebase");
			values[x1][y].setTooltip(new Tooltip("Tooltip Home Base"));
		}
		if (map.getMap()[x1][y].getValue() == 'P') {
			values[x1][y].getStyleClass().add("patient");
			values[x1][y].setTooltip(new Tooltip("Tooltip EMERGENCY CALL details not implemented yet"));
		}
		if (map.getMap()[x1][y].getValue() == 'A') {
			values[x1][y].getStyleClass().add("activeAmbulance");
			values[x1][y].setTooltip(new Tooltip("Tooltip Ambulance active"));
		}
		if (map.getMap()[x1][y].getValue() == 'U') {
			values[x1][y].getStyleClass().add("inActiveAmbulance");
			values[x1][y].setTooltip(new Tooltip("Tooltip Ambulance standby"));
		}
		values[x1][y].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				
				map.getMap()[n][m].setValue(x);
				changeLabel(n,m);
				

			}
		
	
});
		gp.add(values[x1][y], x1, y);

		
		
	}
	//sets up buildings and streets in grid
	
	public void refreshMapLabels(GridPane gp,Map map) {
		gp.getChildren().clear();
		for (int count = 0; count < map.getMap().length; count++) {
			for (int index = 0; index < map.getMap().length; index++) {
				if (map.getMap()[count][index].getValue() == 'B') {
					values[count][index] = new Label("");
					values[count][index].getStyleClass().add("building");
					values[count][index].setTooltip(new Tooltip("Tooltip Building"));
					values[count][index].setMinWidth(labelSize);
					values[count][index].setMinHeight(labelSize);
					int n = count;
					int m = index;
					values[count][index].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
						@Override
						public void handle(Event event) {
							
							map.getMap()[n][m].setValue(x);
							changeLabel(n,m);

						}
					
				
			});
					gp.add(values[count][index], count, index);

				} else if (map.getMap()[count][index].getValue() == 'S') {
					values[count][index] = new Label("");
					values[count][index].getStyleClass().add("street");
					values[count][index].setTooltip(new Tooltip("Tooltip Street"));
					values[count][index].setMinWidth(labelSize);
					values[count][index].setMinHeight(labelSize);
					int n = count;
					int m = index;
					values[count][index].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
						@Override
						public void handle(Event event) {
							
							map.getMap()[n][m].setValue(x);
							changeLabel(n,m);

						}
					
				
			});
					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'P') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("patient");
					values[count][index].setTooltip(new Tooltip("Tooltip EMERGENCY CALL details not implemented yet"));
					values[count][index].setMinWidth(labelSize);
					values[count][index].setMinHeight(labelSize);
					int n = count;
					int m = index;
					
					values[count][index].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
						@Override
						public void handle(Event event) {
							
							map.getMap()[n][m].setValue(x);
							changeLabel(n,m);

						}
					
				
			});
					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'O') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("homebase");
					values[count][index].setTooltip(new Tooltip("Tooltip Home Base"));
					values[count][index].setMinWidth(labelSize);
					values[count][index].setMinHeight(labelSize);
					int n = count;
					int m = index;
					values[count][index].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
						@Override
						public void handle(Event event) {
							
							map.getMap()[n][m].setValue(x);
							changeLabel(n,m);

						}
					
				
			});
					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'H') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("hospital");
					values[count][index].setTooltip(new Tooltip("Tooltip Hospital stats not implemented yet"));
					values[count][index].setMinWidth(labelSize);
					values[count][index].setMinHeight(labelSize);
					int n = count;
					int m = index;
					values[count][index].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
						@Override
						public void handle(Event event) {
							
							map.getMap()[n][m].setValue(x);
							changeLabel(n,m);

						}
					
				
			});
					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'A') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("activeAmbulance");
					values[count][index].setTooltip(new Tooltip("Tooltip Ambulance status not implemented"));
					values[count][index].setMinWidth(labelSize);
					values[count][index].setMinHeight(labelSize);

					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'U') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("inActiveAmbulance");
					values[count][index].setTooltip(new Tooltip("Tooltip Ambulance status not implemented yet"));
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
		Label ambulanceNum= new Label();
		Label patientNum= new Label();
		r.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				//begins the sim
				simStatus = true;
				if(simStatus) {
					//code to continue sim until stop button is hit or sim runs its course
					//test code
					System.out.println("the sim is on");
					while(patientMax>0) {
						System.out.println("getting patients to hospitals");
						System.out.println("one patient got to hospital. "+patientMax+" patients to go");
						patientMax--;
					}
					
				}
				//to show sim loop stopped
				System.out.println("the sim is off");

			}
	
});
		Button s = new Button();
		s.setText("Stop");
		s.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				//stops the sim
				simStatus = false;
				//not implemented yet

			}
	
});
		Button placeHospital = new Button();
		placeHospital.setText("Hospital Placement");
		placeHospital.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				x = 'H';

			}
	
});
		Button placeHomebase = new Button();
		placeHomebase.setText("HomeBase Placement");
		placeHomebase.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				x = 'O';

			}
	
});
		Button placeStreet = new Button();
		placeStreet.setText("Street Placement");
		placeStreet.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				x = 'S';

			}
	
});
		Button placeBuilding = new Button();
		placeBuilding.setText("Building Placement");
		placeBuilding.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				x = 'B';

			}
	
});

		Button pat = new Button();
		pat.setText("Patients");
		pat.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				x = 'P';
			}
		
	
});

		
		hb.getChildren().add(0,r);
		hb.getChildren().add(0,s);
		
		hb.getChildren().add(pat);
		hb.getChildren().add(placeHospital);
		hb.getChildren().add(placeHomebase);
		hb.getChildren().add(placeBuilding);
		hb.getChildren().add(placeStreet);
}

}


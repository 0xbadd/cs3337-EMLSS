package frontend;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GUIController {
	private Label[][] values = new Label[100][100];
	public GUIController() {
	for (int count = 0; count < values.length; count++) {
		for (int index = 0; index < values.length; index++) {
			values[count][index] = new Label();

		}
	}
}
	//sets up buildings and streets in grid
	public void setMapLabels(GridPane gp, Map map) {
		gp.getChildren().clear();
		for (int count = 0; count < map.getMap().length; count++) {
			for (int index = 0; index < map.getMap().length; index++) {
				if (map.getMap()[count][index].getValue() == 'B') {
					values[count][index] = new Label("");
					values[count][index].getStyleClass().add("building");
					values[count][index].setMinWidth(10);
					values[count][index].setMinHeight(10);

					gp.add(values[count][index], count, index);

				} else if (map.getMap()[count][index].getValue() == 'S') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("street");
					values[count][index].setMinWidth(10);
					values[count][index].setMinHeight(10);

					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'P') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("patient");
					values[count][index].setMinWidth(10);
					values[count][index].setMinHeight(10);

					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'O') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("homebase");
					values[count][index].setMinWidth(10);
					values[count][index].setMinHeight(10);

					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'H') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("hospital");
					values[count][index].setMinWidth(10);
					values[count][index].setMinHeight(10);

					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'A') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("ambulance");
					values[count][index].setMinWidth(10);
					values[count][index].setMinHeight(10);

					gp.add(values[count][index], count, index);
				}
			}
		
	}
	
}
	public void refreshMapLabels(GridPane gp,Map map) {
		gp.getChildren().clear();
		for (int count = 0; count < map.getMap().length; count++) {
			for (int index = 0; index < map.getMap().length; index++) {
				if (map.getMap()[count][index].getValue() == 'B') {
					values[count][index] = new Label("");
					values[count][index].getStyleClass().add("building");
					values[count][index].setMinWidth(10);
					values[count][index].setMinHeight(10);

					gp.add(values[count][index], count, index);

				} else if (map.getMap()[count][index].getValue() == 'S') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("street");
					values[count][index].setMinWidth(10);
					values[count][index].setMinHeight(10);

					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'P') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("patient");
					values[count][index].setMinWidth(10);
					values[count][index].setMinHeight(10);

					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'O') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("homebase");
					values[count][index].setMinWidth(10);
					values[count][index].setMinHeight(10);

					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'H') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("hospital");
					values[count][index].setMinWidth(10);
					values[count][index].setMinHeight(10);

					gp.add(values[count][index], count, index);
				}
				else if (map.getMap()[count][index].getValue() == 'A') {
					values[count][index] = new Label(" ");
					values[count][index].getStyleClass().add("ambulance");
					values[count][index].setMinWidth(10);
					values[count][index].setMinHeight(10);

					gp.add(values[count][index], count, index);
				}
			}
		
	}
		
	}
}


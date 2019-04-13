package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

    		GridPane gp = new GridPane();
    		BorderPane bp = new BorderPane();
    		Map newMap = new Map();
    		newMap.setMap();
    		GUIController gui = new GUIController(newMap, gp);
    		gui.refreshMapLabels(gp, newMap);
    		bp.setCenter(gp);
    		HBox top = new HBox();
    		gui.setButtons(top);
    		bp.setTop(top);
    		ScrollPane scrollPane = new ScrollPane();
    		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    		 scrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
    		 scrollPane.setContent(bp);
    		Scene sc = new Scene(scrollPane);
    		
    		sc.getStylesheets().add("styles.css");
    		primaryStage.setScene(sc);
    		primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
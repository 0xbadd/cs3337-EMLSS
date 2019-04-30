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
    public void start(Stage primaryStage) {

    		GridPane gp = new GridPane();

    		Scene sc = new Scene(gp);
    		
    		sc.getStylesheets().add("styles.css");
    		primaryStage.setScene(sc);
    		primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

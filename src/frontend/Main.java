package frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("Emergency Medical Logistical Support System");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    		GridPane gp = new GridPane();
    		Map newMap = new Map();
    		newMap.setMap();
    		GUIController gui = new GUIController();
    		gui.setMapLabels(gp, newMap);
    		Scene sc = new Scene(gp);
    		sc.getStylesheets().add("styles.css");
    		primaryStage.setScene(sc);
    		primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

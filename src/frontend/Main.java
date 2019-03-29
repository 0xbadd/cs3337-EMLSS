package frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("Emergency Medical Logistical System");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    		GridPane gp = new GridPane();
    		BorderPane bp = new BorderPane();
    		Map newMap = new Map();
    		newMap.setMap();
    		GUIController gui = new GUIController();
    		gui.refreshMapLabels(gp, newMap);
    		bp.setCenter(gp);
    		HBox top = new HBox();
    		gui.setButtons(top);
    		bp.setTop(top);
    		Scene sc = new Scene(bp);
    		sc.getStylesheets().add("styles.css");
    		primaryStage.setScene(sc);
    		primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

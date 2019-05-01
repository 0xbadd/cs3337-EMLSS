package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Emergency Medical Logistical System - EMLS");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        GUIController controller = loader.getController();
        primaryStage.setOnCloseRequest(e -> controller.shutdown());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package frontend;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    TableView<TableItem> table;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
            window.setTitle("Emergency Medical Logistics System - EMLS");

        //Ambulances column
        TableColumn<TableItem, String> ambulanceColumn = new TableColumn<>("Ambulances");
        ambulanceColumn.setMinWidth(200);
        ambulanceColumn.setCellValueFactory(new PropertyValueFactory<>("ambulanceId"));
            // My plan was to put a Standby Item instewad of a string , like this a standby item can contain many attribbtues that can be
            // displayed in the cell , like name, type , id , etc. Im just not sure how to connect it and display it in the right way.
        //Emergency Calls column
        TableColumn<TableItem, String> emergencyCallsColumn = new TableColumn<>("Emergency Calls");
        emergencyCallsColumn.setMinWidth(200);
        emergencyCallsColumn.setCellValueFactory(new PropertyValueFactory<>("callId"));
        // My plan was to put a Emergency Item instead of a string , like this a Emergency item can contain many attribbtues that can be
        // displayed in the cell , like name, type , id ,status etc. Im just not sure how to connect it and display it in the right way.
        //Progress column
        TableColumn<TableItem, String> progressColumn = new TableColumn<>("Progress");
        TableColumn<TableItem, String> patientCondition = new TableColumn<>("Condition");
        patientCondition.setMinWidth(100);
        patientCondition.setCellValueFactory(new PropertyValueFactory<>("condition"));
        progressColumn.setMinWidth(100);
        progressColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        table = new TableView<>();
        table.setItems(getTableItem());
        table.getColumns().addAll(ambulanceColumn, emergencyCallsColumn, patientCondition,progressColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    //Get all of the TableItem - should connect with the back end to interact
    public ObservableList<TableItem> getTableItem(){
        ObservableList<TableItem> tableItem = FXCollections.observableArrayList();
        tableItem.add(new TableItem("ambulance #01 ", "call #22 ","Stable","incomplete"));
        tableItem.add(new TableItem("ambulance #09 ", "call #24 ","Critical","incomplete"));
        tableItem.add(new TableItem("ambulance #10 ", "call #2 ","Stable","incomplete"));
        tableItem.add(new TableItem("ambulance #02 ", "call #1 ","n/a","complete"));
        return tableItem;
    }


}
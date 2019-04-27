package frontend;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainController.MainController;

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
        MainController mc = new MainController();
        mc.startAcceptingEmergencyCalls();
        table = new TableView<>();
        table.setItems(getTableItem(mc));
        table.getColumns().addAll(ambulanceColumn, emergencyCallsColumn, patientCondition,progressColumn);
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);
        Button button1 = new Button("Load new Data");

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	vBox.getChildren().clear();
            	vBox.getChildren().addAll(table);
            	table.setItems(getTableItem(mc));
                vBox.getChildren().add(button1);
            }
        });
        vBox.getChildren().add(button1);
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    //Get all of the TableItem - should connect with the back end to interact
    public ObservableList<TableItem> getTableItem(MainController mc){
 
        ObservableList<TableItem> tableItem = FXCollections.observableArrayList();
        Iterator it = mc.getEmergencyCallDirectory().entrySet().iterator();
        tableItem.add(new TableItem("ambulance #01 ", "call #22 ","Stable","incomplete"));
        tableItem.add(new TableItem("ambulance #09 ", "call #24 ","Critical","incomplete"));
        tableItem.add(new TableItem("ambulance #10 ", "call #2 ","Stable","incomplete"));
        while (it.hasNext()) { 
            Map.Entry pair = (Map.Entry)it.next();
        
        tableItem.add(new TableItem("ambulance #22", "call#"+pair.getKey(),"n/a","complete"));
        }
        for (int i = 0; i < mc.getAssignments().size(); i++) {
            System.out.println(mc.getAssignments().get(i));
        }
        System.out.println(mc.getEmergencyCallDirectory().keySet().toString()+" emergency call id's");
        System.out.println(mc.getEmergencyCallDirectory().size()+" calls");
        System.out.println(mc.getAssignments().size()+" assignments");
        System.out.println(mc.getAmbulanceDirectory().size()+" ambulance");
        System.out.println(mc.getHomeBaseDirectory().size()+" homebases");
        System.out.println(mc.getHospitalDirectory().size()+" hospitals");
        System.out.println(mc.getPatientDirectory().size()+" patient");
        System.out.println(mc.getPatientQueue().size()+" patient Que size");

        return tableItem;
    }


}
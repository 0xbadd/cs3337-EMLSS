package frontend;
import javafx.animation.KeyFrame;  
import javafx.animation.Timeline; 
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.animation.Timeline;
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
import javafx.util.Duration;
import mainController.MainController;

public class Main extends Application {
	static int i=0;
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
        TableColumn<TableItem, String> resultColumn = new TableColumn<>("Result");
        patientCondition.setMinWidth(100);
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        TableColumn<TableItem, String> patientColumn = new TableColumn<>("Patient");
        patientColumn.setMinWidth(100);
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patient"));
        TableColumn<TableItem, String> hospitalColumn = new TableColumn<>("Hospital");
        hospitalColumn.setMinWidth(100);
        hospitalColumn.setCellValueFactory(new PropertyValueFactory<>("hospital"));
        //
        MainController mc = new MainController();
        mc.startAcceptingEmergencyCalls();
        table = new TableView<>();
        table.setItems(getTableItem(mc));
        table.getColumns().addAll( emergencyCallsColumn,patientColumn,patientCondition,ambulanceColumn,hospitalColumn,progressColumn,resultColumn);
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);
        //timeline test
        Timeline Updater = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {  
            @Override  
            public void handle(ActionEvent event) {  
            	vBox.getChildren().clear();
            	vBox.getChildren().addAll(table);
            	table.setItems(getTableItem(mc));
            }  
          }));  
          Updater.setCycleCount(Timeline.INDEFINITE);  
          Updater.play();  

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    //Get all of the TableItem - should connect with the back end to interact
    public ObservableList<TableItem> getTableItem(MainController mc){
 
        ObservableList<TableItem> tableItem = FXCollections.observableArrayList();
        Iterator it = mc.getEmergencyCallDirectory().entrySet().iterator();
        tableItem.add(new TableItem("call #22 ","ambulance #01 ", "Stable","incomplete"));
        tableItem.add(new TableItem( "call #24 ","ambulance #09 ","Critical","incomplete"));
        tableItem.add(new TableItem("call #2 ","ambulance #10 ", "Stable","incomplete"));
        while (it.hasNext()) { 
            Map.Entry pair = (Map.Entry)it.next();
        
        tableItem.add(new TableItem("ambulance #22", "call#"+pair.getKey(),"n/a","complete"));
        }
       
        i++;
        if(i==5)
            tableItem.get(0).setResult("DEAD");
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
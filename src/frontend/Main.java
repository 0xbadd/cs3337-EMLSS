package frontend;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    Stage window;
    TableView<TableItems> table;

    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        window= primaryStage;
        window.setTitle("Emergency Medical Logistic System EMLS");

        //Columns

        //Col 1
        TableColumn<TableItems,String> standbyColumn= new TableColumn<>("Standby");
        standbyColumn.setMinWidth(200);
        standbyColumn.setCellValueFactory(new PropertyValueFactory<>("standbyItem"));

        //Col 2
        TableColumn<TableItems,String> emergencyCallsColumn= new TableColumn<>("Emergency Calls");
        emergencyCallsColumn.setMinWidth(200);
        emergencyCallsColumn.setCellValueFactory(new PropertyValueFactory<>("call"));

        //Col 3
        TableColumn<TableItems,String> progressColumn= new TableColumn<>("Progress (Uncomplete/Complete)");
        progressColumn.setMinWidth(300);
        progressColumn.setCellValueFactory(new PropertyValueFactory<>("progress"));

        table = new TableView<>();
        table.getColumns().addAll(standbyColumn,emergencyCallsColumn,progressColumn);

        VBox vBox= new VBox();
        vBox.getChildren().addAll(table);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();




    }
}

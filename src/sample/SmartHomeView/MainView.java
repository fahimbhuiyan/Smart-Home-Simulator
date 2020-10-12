package sample.SmartHomeView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import sample.SmartHomeModel.ReadHouseLayoutModel;
import sample.SmartHomeView.SmartHomeMenu;


public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

     //   Parent root = FXMLLoader.load(getClass().getResource("TestView.fxml"));
        primaryStage.setTitle("Smart Home Simulator");
        BorderPane bp = new BorderPane();

        SmartHomeMenu shm = new SmartHomeMenu();
        // create a VBox (Content of the scene)

        bp.setTop(shm.createSmartHomeMenu());

        Rectangle r1 = new Rectangle();
        r1.setStroke(Color.BLUE);
        r1.setFill(Color.TRANSPARENT);
        r1.setWidth(200);
        r1.setHeight(100);
        r1.setX(100);
        r1.setY(200);

        bp.getChildren().add(r1);

        // create and set the scene
        primaryStage.setScene(new Scene(bp, 1000, 500));
        primaryStage.show();

    }


    public static void main(String[] args) {

        ReadHouseLayoutModel readHouseLayoutModel = new ReadHouseLayoutModel();
        readHouseLayoutModel.ReadJSON("HouseInfo.json");
        launch(args);
    }
}

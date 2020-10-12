package sample.SmartHomeView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import sample.SmartHomeController.HouseViewController;
import sample.SmartHomeModel.ReadHouseLayoutModel;
import sample.SmartHomeView.SmartHomeMenu;


public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

     //   Parent root = FXMLLoader.load(getClass().getResource("TestView.fxml"));
        primaryStage.setTitle("Smart Home Simulator");

        HouseViewController hvc = new HouseViewController();


        // create and set the scene
        primaryStage.setScene(new Scene(hvc.getHouseLayout(), 1000, 500));
        primaryStage.show();

    }


    public static void main(String[] args) {

        ReadHouseLayoutModel readHouseLayoutModel = new ReadHouseLayoutModel();
        readHouseLayoutModel.ReadJSON("HouseInfo.json");
        launch(args);
    }
}

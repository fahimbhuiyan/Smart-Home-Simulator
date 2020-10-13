package sample.SmartHomeView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import sample.SmartHomeController.SimulationDataController;
import sample.SmartHomeModel.SimulationData;


public class MainView extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{

        SimulationDataController sdc = new SimulationDataController();
        sdc.loadData();

       // Parent root = FXMLLoader.load(getClass().getResource("TestView.fxml"));
        primaryStage.setTitle("Smart Home Simulator");



        BorderPane bp = new BorderPane();


        SmartHomeMenu shm = new SmartHomeMenu();
        bp.setTop(shm.createSmartHomeMenu());

        for(int i = 0; i < sdc.getRoomArray().length; i++){

            Rectangle r1 = new Rectangle();
            r1.setStroke(Color.BLUE);
            r1.setFill(Color.TRANSPARENT);

            Text text = new Text(sdc.getRoomArray()[i].getName());
            if(!sdc.getRoomArray()[i].getName().equals("House")) {

                text.setX(sdc.getRoomArray()[i].getxAxis() + 5);
                text.setY(sdc.getRoomArray()[i].getyAxis() + 15);
            }

            r1.setWidth(sdc.getRoomArray()[i].getWidth());
            r1.setHeight(sdc.getRoomArray()[i].getHeight());
            r1.setX(sdc.getRoomArray()[i].getxAxis());
            r1.setY(sdc.getRoomArray()[i].getyAxis());

            bp.getChildren().addAll(text,r1);
        }

        // create and set the scene
        primaryStage.setScene(new Scene(bp, 1000, 1000));
        primaryStage.show();

    }


    public static void main(String[] args) {


        launch(args);
    }
}

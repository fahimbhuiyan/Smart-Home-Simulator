package sample.SmartHomeController;

import javafx.fxml.FXML;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import sample.SmartHomeModel.RoomModel;

public class MainViewController {

    SimulationDataController simulationDataController;
    HouseViewController houseViewController;
    RoomModel[] roomArray;

    @FXML
    BorderPane bp;

    public MainViewController(){
        simulationDataController = new SimulationDataController();
        houseViewController = new HouseViewController();
        simulationDataController.loadData();
        roomArray = simulationDataController.getRoomArray();
    }

    public void drawLayout(){
        System.out.println(bp.getChildren().isEmpty());
        houseViewController.drawLayout(roomArray, bp);
    }
}

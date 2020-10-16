package sample.SmartHomeController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.UserModel;

import javax.swing.*;
import java.util.ArrayList;


public class MainViewController {

    SimulationDataController simulationDataController;
    HouseViewController houseViewController;
    SHSController shsController;
    RoomModel[] roomArray;
    HouseModel houseModel;
    ArrayList<UserModel> userModelArrayList;



    @FXML
    BorderPane bp;

    public MainViewController(){
        simulationDataController = new SimulationDataController();
        houseViewController = new HouseViewController();
        shsController = new SHSController();
        simulationDataController.loadData();
        roomArray = simulationDataController.getRoomArray();
        userModelArrayList = simulationDataController.getUserArrayList();
    }

    public void drawLayout(){
        System.out.println(bp.getChildren().isEmpty());
        houseViewController.drawLayout(roomArray, bp);
    }




}
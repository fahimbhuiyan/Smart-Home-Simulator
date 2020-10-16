package sample.SmartHomeController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
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
    ArrayList<String> roomNameArrayList;

    @FXML
    BorderPane bp;

    @FXML
    GridPane gridSHS;

    @FXML
    GridPane gridSHC;

    @FXML
    GridPane gridSHP;

    @FXML
    GridPane gridSHH;

    @FXML
    Button buttonSelectSHS;

    @FXML
    Button buttonSelectSHC;

    @FXML
    Button buttonSelectSHP;

    @FXML
    Button buttonSelectSHH;

    @FXML
    ComboBox<String> locationComboBoxSHS;

    @FXML
    TableView<UserModel> userTable;

    private final ObservableList<UserModel> data =
            FXCollections.observableArrayList(
                    new UserModel("Jacob", "0", "Parent", "TBD"),
                    new UserModel("Daniel", "1", "Child", "TBD"),
                    new UserModel("Ana", "2", "Guest", "TBD"),
                    new UserModel("Rachel", "69", "Stranger", "TBD")
            );

    public MainViewController(){
        simulationDataController = new SimulationDataController();
        houseViewController = new HouseViewController();
        shsController = new SHSController();
        simulationDataController.loadData();
        roomArray = simulationDataController.getRoomArray();
        roomNameArrayList = simulationDataController.getRoomNameList();
        userModelArrayList = simulationDataController.getUserArrayList();
    }

    @FXML
    public void initialize () {
        houseViewController.drawLayout(roomArray, bp);

        roomNameArrayList.forEach((roomName) -> {
            locationComboBoxSHS.getItems().add(roomName);
        });

        userTable.setEditable(true);
        userTable.setItems(data);
    }

    public void drawLayout(){
        System.out.println(bp.getChildren().isEmpty());
        houseViewController.drawLayout(roomArray, bp);
    }

    public void selectModule(ActionEvent actionEvent){

        if (actionEvent.getSource().equals(buttonSelectSHS)) {
            gridSHS.setVisible(true);
            gridSHC.setVisible(false);
            gridSHP.setVisible(false);
            gridSHH.setVisible(false);
        }
        else if (actionEvent.getSource().equals(buttonSelectSHC)) {
            gridSHS.setVisible(false);
            gridSHC.setVisible(true);
            gridSHP.setVisible(false);
            gridSHH.setVisible(false);
        }
        else if (actionEvent.getSource().equals(buttonSelectSHP)) {
            gridSHS.setVisible(false);
            gridSHC.setVisible(false);
            gridSHP.setVisible(true);
            gridSHH.setVisible(false);
        }
        else if (actionEvent.getSource().equals(buttonSelectSHH)) {
            gridSHS.setVisible(false);
            gridSHC.setVisible(false);
            gridSHP.setVisible(false);
            gridSHH.setVisible(true);
        }
    }
}

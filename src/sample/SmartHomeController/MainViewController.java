package sample.SmartHomeController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.UserModel;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.ResourceBundle;


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

    private final ObservableList<UserModel> data;
//            FXCollections.observableArrayList(
////                    new UserModel("Jacob", "0", "Parent", "TBD"),
////                    new UserModel("Daniel", "1", "Child", "TBD"),
////                    new UserModel("Ana", "2", "Guest", "TBD"),
////                    new UserModel("Rachel", "69", "Stranger", "TBD")
 //           );

    public MainViewController(){
        simulationDataController = new SimulationDataController();
        houseViewController = new HouseViewController();
        shsController = new SHSController();
        simulationDataController.loadData();
        roomArray = simulationDataController.getRoomArray();
        roomNameArrayList = simulationDataController.getRoomNameList();
        userModelArrayList = simulationDataController.getUserArrayList();

        data =
                FXCollections.observableArrayList(
                        userModelArrayList.get(0),userModelArrayList.get(1),userModelArrayList.get(2),userModelArrayList.get(3)
                );
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

    @FXML
    public VBox locationsVBox;

    @FXML
    public JFXDatePicker dateSHS;

    @FXML
    public JFXTimePicker timeSHS;

    @FXML
    public TextField OutsidetemperatureSHS;

    @FXML
    public TextField InsidetemperatureSHS;


    @FXML
    public void saveDate(ActionEvent actionEvent) {
        if (dateSHS.getValue() != null)
            locationsVBox.getChildren().add(new Label("Date: " + dateSHS.getValue().toString()));
    }

    @FXML
    public void saveTime(ActionEvent actionEvent) {
        if (timeSHS.getValue() != null)
            locationsVBox.getChildren().add(new Label("Time: " + timeSHS.getValue().toString()));
    }

    @FXML
    public void outsideTemp(ActionEvent actionEvent) {
        if (OutsidetemperatureSHS.getText() != null)
            locationsVBox.getChildren().add(new Label("Outside Temperature: " + OutsidetemperatureSHS.getText().toString()));
    }

    @FXML
    public void insideTemp(ActionEvent actionEvent) {
        if (InsidetemperatureSHS.getText() != null)
            locationsVBox.getChildren().add(new Label("Inside Temperature: " + InsidetemperatureSHS.getText().toString()));
    }

//    @FXML
//    public void saveLocation(ActionEvent actionEvent) {
//        if (saveLocation().getItems != null)
//            locationsVBox.getChildren().add(new Label(saveLocation().getItems().toString()));
//    }






}

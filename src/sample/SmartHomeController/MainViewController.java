package sample.SmartHomeController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.UserModel;

import java.time.LocalDate;
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
    GridPane gridAddModule;

    @FXML
    Button turnOnOffSimulation;

    @FXML
    ComboBox<String> addModifyRoleComboBoxSHS;

    @FXML
    ComboBox<String> addModifyLocComboBoxSHS;

    @FXML
    ComboBox<String> blockWinLocComboBoxSHS;

    @FXML
    TableView<UserModel> userTable;

    @FXML
    DatePicker dateSHS;

    @FXML
    TextField addModifyUserName;

    @FXML
    TabPane moduleTabs;

    @FXML
    TextArea consoleTextField;

    @FXML
    Spinner<Integer> addModifyUserID;

    @FXML
    Spinner<Integer> userIdToRemove;

    @FXML
    Spinner<Integer> userIdToLogin;

    @FXML
    Spinner<Integer> hourSHS;

    @FXML
    Spinner<Integer> minSHS;

    @FXML
    Spinner<Integer> outTempSHS;

    @FXML
    Spinner<Integer> inTempSHS;

    @FXML
    Button saveDate;

    @FXML
    Button saveTime;

    @FXML
    Button saveOutsideTemp;

    @FXML
    Button saveInsideTemp;

    @FXML
    Button saveWindowBlock;

    private final ObservableList<UserModel> data;

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
            addModifyLocComboBoxSHS.getItems().add(roomName);
            blockWinLocComboBoxSHS.getItems().add(roomName);
        });

        addModifyLocComboBoxSHS.getItems().add("Outside");
        addModifyRoleComboBoxSHS.getItems().addAll("Parent", "Child", "Guest", "Stranger");

        addModifyLocComboBoxSHS.getSelectionModel().selectFirst();
        addModifyRoleComboBoxSHS.getSelectionModel().selectFirst();
        blockWinLocComboBoxSHS.getSelectionModel().selectFirst();

        userTable.setEditable(true);
        userTable.setItems(data);

        dateSHS.setValue(LocalDate.now());
    }

    @FXML
    public void drawLayout () {
        System.out.println(bp.getChildren().isEmpty());
        houseViewController.drawLayout(roomArray, bp);
    }

    @FXML
    public void startOrStopSimulation () {

        if (turnOnOffSimulation.getText().equals("Turn on the simulation")) {
            turnOnOffSimulation.setText("Turn off the simulation");
            consoleTextField.setText(consoleTextField.getText() + "Simulation has been started!\n");
            System.out.println("Simulation has been started!");
            moduleTabs.getTabs().get(0).setDisable(true);
        }
        else if (turnOnOffSimulation.getText().equals("Turn off the simulation")) {
            turnOnOffSimulation.setText("Turn on the simulation");
            consoleTextField.setText(consoleTextField.getText() + "Simulation has been stopped!\n");
            System.out.println("Simulation has been stopped!");
            moduleTabs.getTabs().get(0).setDisable(false);
        }
    }

    @FXML
    public void addOrModifyUser () {
        consoleTextField.setText(consoleTextField.getText() + "addOrModifyUser method called!\n");
    }

    @FXML
    public void removeUser () {
        consoleTextField.setText(consoleTextField.getText() + "removeUser method called!\n");
    }

    @FXML
    public void login () {
        consoleTextField.setText(consoleTextField.getText() + "login method called!\n");
    }

    @FXML
    public void saveSimulationConditions(ActionEvent actionEvent){
        if (actionEvent.getSource().equals(saveDate)) {
            consoleTextField.setText(consoleTextField.getText() + "saveSimulationConditions method called for Date!\n");
        }
        else if (actionEvent.getSource().equals(saveTime)) {
            consoleTextField.setText(consoleTextField.getText() + "saveSimulationConditions method called for Time!\n");
        }
        else if (actionEvent.getSource().equals(saveOutsideTemp)) {
            consoleTextField.setText(consoleTextField.getText() + "saveSimulationConditions method called for Outside Temp!\n");
        }
        else if (actionEvent.getSource().equals(saveInsideTemp)) {
            consoleTextField.setText(consoleTextField.getText() + "saveSimulationConditions method called for Inside Temp!\n");
        }
        else if (actionEvent.getSource().equals(saveWindowBlock)) {
            consoleTextField.setText(consoleTextField.getText() + "saveSimulationConditions method called for Window Block!\n");
        }
    }
}

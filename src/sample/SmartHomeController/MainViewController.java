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

    //Check Box for Block Window
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
    Spinner<Double> outTempSHS;

    @FXML
    Spinner<Double> inTempSHS;

    @FXML
    Button saveDate;

    @FXML
    Button saveTime;

    @FXML
    Button loginButton;

    @FXML
    Button saveOutsideTemp;

    @FXML
    Button saveInsideTemp;

    @FXML
    Button saveWindowBlock;



    private ObservableList<UserModel> data;

    public MainViewController(){
        simulationDataController = new SimulationDataController();
        houseViewController = new HouseViewController();
        shsController = new SHSController();
        simulationDataController.loadData();
        roomArray = simulationDataController.getRoomArray();
        roomNameArrayList = simulationDataController.getRoomNameList();
        userModelArrayList = simulationDataController.getUserArrayList();
        houseModel = simulationDataController.getHouseModel();
        loadUsersInShsTable();


    }

    @FXML
    public void initialize () {
        houseViewController.drawLayout(roomArray, bp, houseModel);

        addModifyLocComboBoxSHS.getItems().add(roomNameArrayList.get(0));

        for (int i = 1; i < roomNameArrayList.size(); i++) {
            addModifyLocComboBoxSHS.getItems().add(roomNameArrayList.get(i));
            blockWinLocComboBoxSHS.getItems().add(roomNameArrayList.get(i));
        }

        addModifyLocComboBoxSHS.getItems().add("Outside");
        addModifyRoleComboBoxSHS.getItems().addAll("Parent", "Child", "Guest", "Stranger");

        addModifyLocComboBoxSHS.getSelectionModel().selectFirst();
        addModifyRoleComboBoxSHS.getSelectionModel().selectFirst();
        blockWinLocComboBoxSHS.getSelectionModel().selectFirst();

        userTable.setEditable(true);
        userTable.setItems(data);

        dateSHS.setValue(LocalDate.now());
    }

    public void loadUsersInShsTable(){
        data = FXCollections.observableArrayList();
        for(int i = 0; i < userModelArrayList.size(); i++){
            data.add(userModelArrayList.get(i));

        }
    }

    @FXML
    public void drawLayout () {
        System.out.println(bp.getChildren().isEmpty());
        houseViewController.drawLayout(roomArray, bp, houseModel);
    }

    @FXML
    public void setOutsideTemperature(){

        double value = outTempSHS.getValue();
        shsController.setOutsideTemperature(houseModel, value);
        drawLayout();
    }

    @FXML
    public void setInsideTemperature(){
        double value = inTempSHS.getValue();
        shsController.setInsideTemperature(roomArray, value);
        drawLayout();
    }

    @FXML
    public void addObjectToWindow(){

        String value = blockWinLocComboBoxSHS.getValue();
        shsController.addObjectToWindow(roomArray, value, consoleTextField);
        drawLayout();


    }
    @FXML
    public void startOrStopSimulation () {
        if (turnOnOffSimulation.getText().equals("Turn on the simulation")) {
            turnOnOffSimulation.setText("Turn off the simulation");
            consoleTextField.setText("Simulation has been started!\n" + consoleTextField.getText());
            System.out.println("Simulation has been started!");
            saveDate.setDisable(true);
            saveTime.setDisable(true);
            saveOutsideTemp.setDisable(true);
            saveInsideTemp.setDisable(true);
            loginButton.setDisable(true);
        }
        else if (turnOnOffSimulation.getText().equals("Turn off the simulation")) {
            turnOnOffSimulation.setText("Turn on the simulation");
            consoleTextField.setText("Simulation has been stopped!\n" + consoleTextField.getText());
            System.out.println("Simulation has been stopped!");
            saveDate.setDisable(false);
            saveTime.setDisable(false);
            saveOutsideTemp.setDisable(false);
            saveInsideTemp.setDisable(false);
            loginButton.setDisable(false);
        }
    }

    @FXML
    public void addOrModifyUser () {
        consoleTextField.setText("addOrModifyUser method called!\n" + consoleTextField.getText());
    }

    @FXML
    public void login () {
        int id = userIdToLogin.getValue();
        shsController.login(houseModel,id,userModelArrayList,consoleTextField);
    }

    @FXML
    public void deleteUserProfile(){
        int id = userIdToRemove.getValue();
        shsController.deleteUserProfile(userModelArrayList,id,consoleTextField, houseModel);

        data.clear();
        loadUsersInShsTable();
        userTable.setItems(data);
    }

    @FXML
    public void addModifyUser(){

        int id = addModifyUserID.getValue();
        String name = addModifyUserName.getText();
        String userType = addModifyRoleComboBoxSHS.getValue();
        String location = addModifyLocComboBoxSHS.getValue();

        shsController.addModifyUser(userModelArrayList,id,name,userType,location,consoleTextField);
        data.clear();
        loadUsersInShsTable();
        userTable.setItems(data);
    }

    @FXML
    public void saveSimulationConditions(ActionEvent actionEvent){
        if (actionEvent.getSource().equals(saveDate)) {
            consoleTextField.setText("saveSimulationConditions method called for Date!\n" + consoleTextField.getText());
        }
        else if (actionEvent.getSource().equals(saveTime)) {
            consoleTextField.setText("saveSimulationConditions method called for Time!\n" + consoleTextField.getText());
        }
        else if (actionEvent.getSource().equals(saveOutsideTemp)) {
            consoleTextField.setText("saveSimulationConditions method called for Outside Temp!\n" + consoleTextField.getText());
        }
        else if (actionEvent.getSource().equals(saveInsideTemp)) {
            consoleTextField.setText("saveSimulationConditions method called for Inside Temp!\n" + consoleTextField.getText());
        }
        else if (actionEvent.getSource().equals(saveWindowBlock)) {
            consoleTextField.setText("saveSimulationConditions method called for Window Block!\n" + consoleTextField.getText() );
        }
    }
}

package sample.SmartHomeController;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.UserModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MainViewController {

    private SimulationDataController simulationDataController;
    private HouseViewController houseViewController;
    private SHSController shsController;
    private RoomModel[] roomArray;
    private HouseModel houseModel;
    private ArrayList<UserModel> userModelArrayList;
    private ArrayList<String> roomNameArrayList;

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
    JFXDatePicker dateSHS;

    @FXML
    JFXTimePicker timeSHS;

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

    @FXML
    VBox locationsVBox;

    @FXML
    Label leftPanelDate;

    @FXML
    Label leftPanelTime;

    @FXML
    Label leftPanelInTemp;

    @FXML
    Label leftPanelOutTemp;

    @FXML
    Label warningLabelSimulation;

    @FXML
    Label userNameLabel;

    @FXML
    Label userIDLabel;

    @FXML
    Label userLocationLabel;

    @FXML
    Label userPermissionLabel;

    private boolean isLoggedIn = false;

    private ObservableList<UserModel> data;

    private Object[] userInfo;

    public MainViewController() {
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
    public void initialize() {
        houseViewController.drawLayout(roomArray, bp, houseModel, userModelArrayList);

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

        turnOnOffSimulation.setDisable(true);

        timeSHS.setValue(LocalTime.of(12, 0));
    }

    private void loadUsersInShsTable() {
        data = FXCollections.observableArrayList();
        data.addAll(userModelArrayList);
    }

    @FXML
    private void drawLayout() {
        System.out.println(bp.getChildren().isEmpty());
        houseViewController.drawLayout(roomArray, bp, houseModel, userModelArrayList);
    }

    @FXML
    public void setOutsideTemperature(ActionEvent event) {
        double value = outTempSHS.getValue();

        shsController.setOutsideTemperature(houseModel, value);
        drawLayout();
        leftPanelOutTemp.setText("Outside Temperature: " + outTempSHS.getValue().toString() + " C");
        saveSimulationConditions(event);
    }

    @FXML
    public void setInsideTemperature(ActionEvent event) {
        double value = inTempSHS.getValue();

        shsController.setInsideTemperature(roomArray, value);
        drawLayout();
        leftPanelInTemp.setText("Inside Temperature: " + inTempSHS.getValue().toString() + " C");
        saveSimulationConditions(event);
    }

    @FXML
    public void addObjectToWindow(ActionEvent event) {
        String value = blockWinLocComboBoxSHS.getValue();
        shsController.addObjectToWindow(roomArray, value, consoleTextField);
        drawLayout();
        saveSimulationConditions(event);
    }

    @FXML
    public void startOrStopSimulation() {
        if (turnOnOffSimulation.getText().equals("Start the simulation")) {
            turnOnOffSimulation.setText("Stop the simulation");
            consoleTextField.setText("The simulation has been started!\n" + consoleTextField.getText());
            System.out.println("The simulation has been started!");
            saveDate.setDisable(true);
            saveTime.setDisable(true);
            saveOutsideTemp.setDisable(true);
            saveInsideTemp.setDisable(true);
            loginButton.setDisable(true);
        } else if (turnOnOffSimulation.getText().equals("Stop the simulation")) {
            turnOnOffSimulation.setText("Start the simulation");
            consoleTextField.setText("The simulation has been stopped!\n" + consoleTextField.getText());
            System.out.println("The simulation has been stopped!");
            saveDate.setDisable(false);
            saveTime.setDisable(false);
            saveOutsideTemp.setDisable(false);
            saveInsideTemp.setDisable(false);
            loginButton.setDisable(false);
        }
    }

    @FXML
    public void login() {
        int id = userIdToLogin.getValue();

        userInfo = shsController.login(houseModel, id, userModelArrayList, consoleTextField);

        turnOffSimulationWarning();
        processUserInfo();
    }

    @FXML
    public void deleteUserProfile() {
        int id = userIdToRemove.getValue();

        shsController.deleteUserProfile(userModelArrayList, id, consoleTextField, houseModel);

        data.clear();
        loadUsersInShsTable();
        userTable.setItems(data);
        drawLayout();
    }

    @FXML
    public void addModifyUser() {
        int id = addModifyUserID.getValue();

        String name = addModifyUserName.getText();
        String userType = addModifyRoleComboBoxSHS.getValue();
        String location = addModifyLocComboBoxSHS.getValue();

        userInfo = shsController.addModifyUser(userModelArrayList, id, name, userType, location, consoleTextField);

        processUserInfo();

        data.clear();
        loadUsersInShsTable();
        userTable.setItems(data);
        drawLayout();
    }

    @FXML
    public void saveSimulationConditions(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(saveDate)) {
            consoleTextField.setText("The date has been changed to " + dateSHS.getValue().toString() + ".\n" + consoleTextField.getText());
            leftPanelDate.setText("Date: " + dateSHS.getValue().toString());
        } else if (actionEvent.getSource().equals(saveTime)) {
            consoleTextField.setText("The time has been changed to " + timeSHS.getValue().toString() + ".\n" + consoleTextField.getText());
            leftPanelTime.setText("Time: " + timeSHS.getValue().toString());
        } else if (actionEvent.getSource().equals(saveOutsideTemp)) {
            consoleTextField.setText("The outside temperature has been changed to " + outTempSHS.getValue().toString() + " Celsius.\n" + consoleTextField.getText());
        } else if (actionEvent.getSource().equals(saveInsideTemp)) {
            consoleTextField.setText("The inside temperature has been changed to " + inTempSHS.getValue().toString() + " Celsius.\n" + consoleTextField.getText());
        } else if (actionEvent.getSource().equals(saveWindowBlock)) {
            consoleTextField.setText("The window in " + blockWinLocComboBoxSHS.getValue() + " has been blocked.\n" + consoleTextField.getText());
        }

        turnOffSimulationWarning();
    }

    private void turnOffSimulationWarning() {
        if (!leftPanelDate.getText().isEmpty() && !leftPanelTime.getText().isEmpty() && !leftPanelInTemp.getText().isEmpty() && !leftPanelOutTemp.getText().isEmpty() && isLoggedIn) {
            turnOnOffSimulation.setDisable(false);
            warningLabelSimulation.setText("");
        }
    }

    private void processUserInfo() {

        boolean actionSuccessful = (boolean) (userInfo[0]);
        UserModel loggedInUser = (UserModel) (userInfo[1]);

        if (!actionSuccessful) {
            return;
        }

        userNameLabel.setText("Name: " + loggedInUser.getName());
        userIDLabel.setText("ID: " + loggedInUser.getId());
        userPermissionLabel.setText("Permission: " + loggedInUser.getUser_type());
        userLocationLabel.setText("Location: " + loggedInUser.getLocation());
        isLoggedIn = true;
        turnOffSimulationWarning();
    }
}

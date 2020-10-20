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

/**
 * Class for the Main view controller.
 */
public class MainViewController {

    private SimulationDataController simulationDataController;
    private HouseViewController houseViewController;
    private SHSController shsController;
    private RoomModel[] roomArray;
    private HouseModel houseModel;
    private ArrayList<UserModel> userModelArrayList;
    private ArrayList<String> roomNameArrayList;

    /**
     * The BorderPane
     */
    @FXML
    BorderPane bp;

    /**
     * The SHS GridPane.
     */
    @FXML
    GridPane gridSHS;

    /**
     * The SHC GridPane.
     */
    @FXML
    GridPane gridSHC;

    /**
     * The SHP GridPane.
     */
    @FXML
    GridPane gridSHP;

    /**
     * The SHH GridPane.
     */
    @FXML
    GridPane gridSHH;

    /**
     * The Add Module GridPane.
     */
    @FXML
    GridPane gridAddModule;

    /**
     * The Turn on/off simulation button.
     */
    @FXML
    Button turnOnOffSimulation;

    /**
     * Add/modify role of user.
     */
    @FXML
    ComboBox<String> addModifyRoleComboBoxSHS;

    /**
     * Add/modify location of user.
     */
    @FXML
    ComboBox<String> addModifyLocComboBoxSHS;

    /**
     * Block Window.
     */
    @FXML
    ComboBox<String> blockWinLocComboBoxSHS;

    /**
     * The User table.
     */
    @FXML
    TableView<UserModel> userTable;

    /**
     * The Date.
     */
    @FXML
    JFXDatePicker dateSHS;

    /**
     * The Time.
     */
    @FXML
    JFXTimePicker timeSHS;

    /**
     * Add/modify user name.
     */
    @FXML
    TextField addModifyUserName;

    /**
     * The Module tabs.
     */
    @FXML
    TabPane moduleTabs;

    /**
     * The Console text field.
     */
    @FXML
    TextArea consoleTextField;

    /**
     * The Add/modify user id.
     */
    @FXML
    Spinner<Integer> addModifyUserID;

    /**
     * The User id to remove.
     */
    @FXML
    Spinner<Integer> userIdToRemove;

    /**
     * The User id to login.
     */
    @FXML
    Spinner<Integer> userIdToLogin;

    /**
     * The Outside temperature of the Simulation.
     */
    @FXML
    Spinner<Double> outTempSHS;

    /**
     * The Inside temperature of the Simulation.
     */
    @FXML
    Spinner<Double> inTempSHS;

    /**
     * The Save date button.
     */
    @FXML
    Button saveDate;

    /**
     * The Save time button.
     */
    @FXML
    Button saveTime;

    /**
     * The Login button.
     */
    @FXML
    Button loginButton;

    /**
     * The Save outside temp button.
     */
    @FXML
    Button saveOutsideTemp;

    /**
     * The Save inside temp button.
     */
    @FXML
    Button saveInsideTemp;

    /**
     * The Save window block button.
     */
    @FXML
    Button saveWindowBlock;

    /**
     * The Locations v box.
     */
    @FXML
    VBox locationsVBox;

    /**
     * The date contained in the Left panel.
     */
    @FXML
    Label leftPanelDate;

    /**
     * The time contained in the Left panel.
     */
    @FXML
    Label leftPanelTime;

    /**
     * The inside temperature contained in the Left panel.
     */
    @FXML
    Label leftPanelInTemp;

    /**
     * The outside temperature contained in the Left panel.
     */
    @FXML
    Label leftPanelOutTemp;

    /**
     * The Warning label simulation.
     */
    @FXML
    Label warningLabelSimulation;

    /**
     * The User name label.
     */
    @FXML
    Label userNameLabel;

    /**
     * The User id label.
     */
    @FXML
    Label userIDLabel;

    /**
     * The User location label.
     */
    @FXML
    Label userLocationLabel;

    /**
     * The User permission label.
     */
    @FXML
    Label userPermissionLabel;

    private boolean isLoggedIn = false;

    private ObservableList<UserModel> data;

    private Object[] userInfo;

    /**
     * Instantiates a new Main view controller.
     */
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

    /**
     * Initialize the default parameters of the simulation.
     */
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

    /**
     * Add users to the SHS Table
     */
    private void loadUsersInShsTable() {
        data = FXCollections.observableArrayList();
        data.addAll(userModelArrayList);
    }

    /**
     * Draw layout of the House
     */
    @FXML
    private void drawLayout() {
        System.out.println(bp.getChildren().isEmpty());
        houseViewController.drawLayout(roomArray, bp, houseModel, userModelArrayList);
    }

    /**
     * Sets outside temperature.
     *
     * @param event the event
     */
    @FXML
    public void setOutsideTemperature(ActionEvent event) {
        double value = outTempSHS.getValue();

        shsController.setOutsideTemperature(houseModel, value);
        drawLayout();
        leftPanelOutTemp.setText("Outside Temperature: " + outTempSHS.getValue().toString() + " C");
        saveSimulationConditions(event);
    }

    /**
     * Sets inside temperature.
     *
     * @param event the event
     */
    @FXML
    public void setInsideTemperature(ActionEvent event) {
        double value = inTempSHS.getValue();

        shsController.setInsideTemperature(roomArray, value);
        drawLayout();
        leftPanelInTemp.setText("Inside Temperature: " + inTempSHS.getValue().toString() + " C");
        saveSimulationConditions(event);
    }

    /**
     * Add object to window.
     *
     * @param event the event
     */
    @FXML
    public void addObjectToWindow(ActionEvent event) {
        String value = blockWinLocComboBoxSHS.getValue();
        shsController.addObjectToWindow(roomArray, value, consoleTextField);
        drawLayout();
        saveSimulationConditions(event);
    }

    /**
     * Start or stop simulation.
     */
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

    /**
     * Login.
     */
    @FXML
    public void login() {
        int id = userIdToLogin.getValue();

        userInfo = shsController.login(houseModel, id, userModelArrayList, consoleTextField);

        turnOffSimulationWarning();
        processUserInfo();
    }

    /**
     * Delete user profile.
     */
    @FXML
    public void deleteUserProfile() {
        int id = userIdToRemove.getValue();

        shsController.deleteUserProfile(userModelArrayList, id, consoleTextField, houseModel);

        data.clear();
        loadUsersInShsTable();
        userTable.setItems(data);
        drawLayout();
    }

    /**
     * Add/modify user.
     */
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

    /**
     * Save simulation conditions.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void saveSimulationConditions(ActionEvent actionEvent) {
        //saves to console and displays to left panel and on the house layout (for all)
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

    /**
     * Disable the simulation warning
     */
    private void turnOffSimulationWarning() {
        if (!leftPanelDate.getText().isEmpty() && !leftPanelTime.getText().isEmpty() && !leftPanelInTemp.getText().isEmpty() && !leftPanelOutTemp.getText().isEmpty() && isLoggedIn) {
            turnOnOffSimulation.setDisable(false);
            warningLabelSimulation.setText("");
        }
    }

    /**
     * Display all information of logged in User.
     */
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

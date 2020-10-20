package sample.SmartHomeController;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
     * The BorderPane holding the house layout.
     */
    @FXML
    BorderPane bp;

    /**
     * The SHS GridPane containing the SHS module.
     */
    @FXML
    GridPane gridSHS;

    /**
     * The SHC GridPane containing the SHC module.
     */
    @FXML
    GridPane gridSHC;

    /**
     * The SHP GridPane containing the SHP module.
     */
    @FXML
    GridPane gridSHP;

    /**
     * The SHH GridPane containing the SHH module.
     */
    @FXML
    GridPane gridSHH;

    /**
     * The GridPane where the user can add a new module.
     */
    @FXML
    GridPane gridAddModule;

    /**
     * The button used to start/stop the simulation.
     */
    @FXML
    Button turnOnOffSimulation;

    /**
     * A combobox containing the different permission levels (roles) that a user can have.
     */
    @FXML
    ComboBox<String> addModifyRoleComboBoxSHS;

    /**
     * A combobox containing the different locations that a user can be in.
     */
    @FXML
    ComboBox<String> addModifyLocComboBoxSHS;

    /**
     * A combobox containing the different locations that a user can block a window in.
     */
    @FXML
    ComboBox<String> blockWinLocComboBoxSHS;

    /**
     * The SHS table which displays all the users that are present in the simulation.
     */
    @FXML
    TableView<UserModel> userTable;

    /**
     * The date of the simulation.
     */
    @FXML
    JFXDatePicker dateSHS;

    /**
     * The time of the simulation.
     */
    @FXML
    JFXTimePicker timeSHS;

    /**
     * Text field used to specify the name of a new user or to modify the name of an existing user.
     */
    @FXML
    TextField addModifyUserName;

    /**
     * A container which holds the different modules tabs (one tab per module).
     */
    @FXML
    TabPane moduleTabs;

    /**
     * The text area used where the console messages will print.
     */
    @FXML
    TextArea consoleTextField;

    /**
     * Spinner used to specify the ID of a new or existing user.
     */
    @FXML
    Spinner<Integer> addModifyUserID;

    /**
     * Spinner used to specify the ID of an existing user that the user wants to removed.
     */
    @FXML
    Spinner<Integer> userIdToRemove;

    /**
     * Spinner used to specify the ID of an existing user that the user wants to log in as.
     */
    @FXML
    Spinner<Integer> userIdToLogin;

    /**
     * Spinner used to specify the outside temperature of the simulation.
     */
    @FXML
    Spinner<Double> outTempSHS;

    /**
     * Spinner used to specify the inside temperature of the simulation.
     */
    @FXML
    Spinner<Double> inTempSHS;

    /**
     * Button used to save the date of the simulation.
     */
    @FXML
    Button saveDate;

    /**
     * Button used to save the time of the simulation.
     */
    @FXML
    Button saveTime;

    /**
     * Button used to log in as a user with the specified ID.
     */
    @FXML
    Button loginButton;

    /**
     * Button used to save the outside temperature of the simulation.
     */
    @FXML
    Button saveOutsideTemp;

    /**
     * Button used to save the inside temperature of the simulation.
     */
    @FXML
    Button saveInsideTemp;

    /**
     * Button used to save the location in which the window must be blocked by an object.
     */
    @FXML
    Button saveWindowBlock;

    /**
     * The container (left-side panel) which contains the start/stop simulation button as well as
     * general information about the simulation and the logged-in user.
     */
    @FXML
    VBox locationsVBox;

    /**
     * The label displaying the simulation date in the left-side panel.
     */
    @FXML
    Label leftPanelDate;

    /**
     * The label displaying the simulation time in the left-side panel.
     */
    @FXML
    Label leftPanelTime;

    /**
     * The label displaying the inside temperature of the simulation in the left-side panel.
     */
    @FXML
    Label leftPanelInTemp;

    /**
     * The label displaying the outside temperature of the simulation in the left-side panel.
     */
    @FXML
    Label leftPanelOutTemp;

    /**
     * The label which warns the user of the conditions that must be met before being able to start the simulation.
     */
    @FXML
    Label warningLabelSimulation;

    /**
     * The label displaying the name of the user that is currently logged into the simulation.
     */
    @FXML
    Label userNameLabel;

    /**
     * The label displaying the ID of the user that is currently logged into the simulation.
     */
    @FXML
    Label userIDLabel;

    /**
     * The label displaying the location of the user that is currently logged into the simulation.
     */
    @FXML
    Label userLocationLabel;

    /**
     * The label displaying the permission level of the user that is currently logged into the simulation.
     */
    @FXML
    Label userPermissionLabel;

    /**
     * A boolean indicating whether a user is logged into the simulation or not.
     */
    private boolean isLoggedIn = false;

    /**
     * The list of users that are currently in the simulation.
     */
    private ObservableList<UserModel> data;

    /**
     * Information about the user that has either been added, modified, or logged-in as.
     */
    private Object[] userInfo;

    /**
     * All the information on the user that is currently logged in.
     */
    private UserModel loggedInUser = null;

    /**
     * Instantiate a new Main view controller.
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
        loadUsersInSHSTable();
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
     * Add users to the SHS table.
     */
    private void loadUsersInSHSTable() {
        data = FXCollections.observableArrayList();
        data.addAll(userModelArrayList);
    }

    /**
     * Draw the layout of the house.
     */
    @FXML
    private void drawLayout() {
        System.out.println(bp.getChildren().isEmpty());
        houseViewController.drawLayout(roomArray, bp, houseModel, userModelArrayList);
    }

    /**
     * Set outside temperature.
     *
     * @param event the event which indicates that the user interacted with the button that saves the outside temperature.
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
     * Set inside temperature.
     *
     * @param event the event which indicates that the user interacted with the button that saves the inside temperature.
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
     * @param event the event which indicates that the user interacted with the button that saves the location in
     *              which the window must be blocked by an object.
     */
    @FXML
    public void addObjectToWindow(ActionEvent event) {
        String value = blockWinLocComboBoxSHS.getValue();
        shsController.addObjectToWindow(roomArray, value, consoleTextField);
        drawLayout();
        saveSimulationConditions(event);
    }

    /**
     * Start or stop the simulation and enable or disable the needed UI controls.
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
     * Log in the user based on the user ID provided.
     */
    @FXML
    public void login() {
        int id = userIdToLogin.getValue();

        userInfo = shsController.login(houseModel, id, userModelArrayList, consoleTextField);

        turnOffSimulationWarning();
        processUserInfo("login");
    }

    /**
     * Delete user profile based on the user ID provided.
     */
    @FXML
    public void deleteUserProfile() {
        int id = userIdToRemove.getValue();

        shsController.deleteUserProfile(userModelArrayList, id, consoleTextField, houseModel);

        data.clear();
        loadUsersInSHSTable();
        userTable.setItems(data);
        drawLayout();
    }

    /**
     * Add/modify user based on the information provided.
     */
    @FXML
    public void addModifyUser() {
        int id = addModifyUserID.getValue();

        String name = addModifyUserName.getText();
        String userType = addModifyRoleComboBoxSHS.getValue();
        String location = addModifyLocComboBoxSHS.getValue();

        userInfo = shsController.addModifyUser(userModelArrayList, id, name, userType, location, consoleTextField);

        processUserInfo("add/modify");

        data.clear();
        loadUsersInSHSTable();
        userTable.setItems(data);
        drawLayout();
    }

    /**
     * Save the simulation conditions.
     *
     * @param event the event which specifies the button which the user interacted with in order to save a certain
     *              simulation condition.
     */
    @FXML
    public void saveSimulationConditions(ActionEvent event) {
        if (event.getSource().equals(saveDate)) {
            consoleTextField.setText("The date has been changed to " + dateSHS.getValue().toString() + ".\n" + consoleTextField.getText());
            leftPanelDate.setText("Date: " + dateSHS.getValue().toString());
        } else if (event.getSource().equals(saveTime)) {
            consoleTextField.setText("The time has been changed to " + timeSHS.getValue().toString() + ".\n" + consoleTextField.getText());
            leftPanelTime.setText("Time: " + timeSHS.getValue().toString());
        } else if (event.getSource().equals(saveOutsideTemp)) {
            consoleTextField.setText("The outside temperature has been changed to " + outTempSHS.getValue().toString() + " Celsius.\n" + consoleTextField.getText());
        } else if (event.getSource().equals(saveInsideTemp)) {
            consoleTextField.setText("The inside temperature has been changed to " + inTempSHS.getValue().toString() + " Celsius.\n" + consoleTextField.getText());
        } else if (event.getSource().equals(saveWindowBlock)) {
            consoleTextField.setText("The window in " + blockWinLocComboBoxSHS.getValue() + " has been blocked.\n" + consoleTextField.getText());
        }

        turnOffSimulationWarning();
    }

    /**
     * Disable the simulation warning and allow the user to start the simulation if all the necessary conditions have
     * been met.
     */
    private void turnOffSimulationWarning() {
        if (!leftPanelDate.getText().isEmpty() && !leftPanelTime.getText().isEmpty() && !leftPanelInTemp.getText().isEmpty() && !leftPanelOutTemp.getText().isEmpty() && isLoggedIn) {
            turnOnOffSimulation.setDisable(false);
            warningLabelSimulation.setText("");
        }
    }

    /**
     * Display all the information of the logged in user.
     *
     * @param action a String which specifies if the user is currently attempting to log in or to add/modify a user profile.
     */
    private void processUserInfo(String action) {

        boolean actionSuccessful = (boolean) (userInfo[0]);
        UserModel processedUser = (UserModel) (userInfo[1]);

        if (!actionSuccessful) {
            return;
        }

        if (action.equals("login") || (isLoggedIn && action.equals("add/modify") && loggedInUser.getId() == processedUser.getId())) {
            loggedInUser = new UserModel(processedUser.getName(), processedUser.getId(), processedUser.getUser_type(), processedUser.getLocation());

            userNameLabel.setText("Name: " + loggedInUser.getName());
            userIDLabel.setText("ID: " + loggedInUser.getId());
            userPermissionLabel.setText("Permission: " + loggedInUser.getUser_type());
            userLocationLabel.setText("Location: " + loggedInUser.getLocation());
            isLoggedIn = true;
            turnOffSimulationWarning();
        }
    }
}

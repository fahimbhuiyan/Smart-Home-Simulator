package sample.SmartHomeController;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.UserModel;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class for the Main view controller.
 */
public class MainViewController {

    /**
     * A controller which handles the house and user information (i.e. the simulation data).
     */
    private SimulationDataController simulationDataController;

    /**
     * A controller which handles the drawing of the house layout.
     */
    private HouseViewController houseViewController;

    /**
     * A controller which handles the modification of the simulation data.
     */
    private SHSController shsController;

    /**
     * A controller which handles the modification of the simulation data.
     */
    private SHCController shcController;

    /**
     * A controller which handles the security of the smart home (lights, intruders, away mode).
     */
    private SHPController shpController;

    /**
     * An array with all the rooms of the house.
     */
    private Map<String, RoomModel> rooms;

    /**
     * A instance of a HouseModel object which represents the house itself.
     */
    private HouseModel houseModel;

    /**
     * A list which contains all the user profiles present in the simulation.
     */
    private ArrayList<UserModel> userModelArrayList;

    /**
     * A list which contains all the names of the rooms of the house.
     */
    private Set<String> roomNamesSet;

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
     * The Button used to start/stop the simulation.
     */
    @FXML
    Button turnOnOffSimulation;

    /**
     * A ComboBox containing the different permission levels (roles) that a user can have.
     */
    @FXML
    ComboBox<String> addModifyRoleComboBoxSHS;

    /**
     * A ComboBox containing the different locations that a user can be in.
     */
    @FXML
    ComboBox<String> addModifyLocComboBoxSHS;

    /**
     * A ComboBox containing the different possible speeds for the simulation time.
     */
    @FXML
    ComboBox<String> timeSpeedComboBoxSHS;

    /**
     * A ComboBox containing the different locations that a user can block a window in.
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
     * TextField used to specify the name of a new user or to modify the name of an existing user.
     */
    @FXML
    TextField addModifyUserName;

    /**
     * TabPane used as a container which holds the different modules tabs (one tab per module).
     */
    @FXML
    TabPane moduleTabs;

    /**
     * The TextArea used where the console messages will print.
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
     * VBox as the container (left-side panel) which contains the start/stop simulation button as well as
     * general information about the simulation and the logged-in user.
     */
    @FXML
    VBox locationsVBox;

    /**
     * The Label displaying the simulation date in the left-side panel.
     */
    @FXML
    Label leftPanelDate;

    /**
     * The Label displaying the simulation time in the left-side panel.
     */
    @FXML
    Label leftPanelTime;

    /**
     * The Label displaying the simulation time speed in the left-side panel.
     */
    @FXML
    Label leftPanelTimeSpeed;

    /**
     * The Label displaying the inside temperature of the simulation in the left-side panel.
     */
    @FXML
    Label leftPanelInTemp;

    /**
     * The Label displaying the outside temperature of the simulation in the left-side panel.
     */
    @FXML
    Label leftPanelOutTemp;

    /**
     * The Label which warns the user of the conditions that must be met before being able to start the simulation.
     */
    @FXML
    Label warningLabelSimulation;

    /**
     * The Label displaying the name of the user that is currently logged into the simulation.
     */
    @FXML
    Label userNameLabel;

    /**
     * The Label displaying the ID of the user that is currently logged into the simulation.
     */
    @FXML
    Label userIDLabel;

    /**
     * The Label displaying the location of the user that is currently logged into the simulation.
     */
    @FXML
    Label userLocationLabel;

    /**
     * The Label displaying the permission level of the user that is currently logged into the simulation.
     */
    @FXML
    Label userPermissionLabel;

    /**
     * A TextField where the user enters the path of the house layout JSON file that they wish to upload.
     */
    @FXML
    TextField houseLayoutFilePath;

    /**
     * A Button which saves the path of the house layout JSON file that the user wishes to upload.
     */
    @FXML
    Button saveHouseLayoutFilePath;

    /**
     * A Label which describes the section where the user must upload a path to the house layout JSON file.
     */
    @FXML
    Label labelHouseLayoutFile;

    /**
     * A Label which informs the user of any issues that many arise during the processing of the path to the house layout JSON file.
     */
    @FXML
    Label errorLabelHouseLayoutFile;

    /**
     * A container which holds the default avatar profile picture of the logged-in user.
     */
    @FXML
    ImageView avatarImageView;

    /**
     * A Label which describes the section that holds general user information (avatar, name, ID, permission level, and
     * location).
     */
    @FXML
    Label labelUserInfoLeftPanel;

    /**
     * A label which describes the section that holds general simulation parameters (date, time, inside temperature, and
     * outside temperature).
     */
    @FXML
    Label labelLeftPanelSimParam;

    @FXML
    ComboBox<String> doorComboBoxSHC;

    @FXML
    Button openDoor;

    @FXML
    Button closeDoor;

    @FXML
    ComboBox<String> winComboBoxSHC;

    @FXML
    Button openWindow;

    @FXML
    Button closeWindow;

    @FXML
    ComboBox<String> lightComboBoxSHC;

    @FXML
    Button turnOnLight;

    @FXML
    Button turnOffLight;

    @FXML
    Button turnOnOffAutomode;

    @FXML
    ComboBox<String> lockDoorComboBoxSHC;

    @FXML
    Button lockDoor;

    @FXML
    Button unlockDoor;

    @FXML
    ComboBox<String> lightComboBoxSHP;

    @FXML
    JFXTimePicker timerFrom;

    @FXML
    JFXTimePicker timerTo;

    @FXML
    Button saveDuration;

    @FXML
    Button saveTimeSpeed;

    @FXML
    Spinner<Integer> timerMinuteAuthority;

    @FXML
    Spinner<Integer> timerSecondAuthority;

    @FXML
    Button saveDurationAuth;

    @FXML
    Button awayButton;

    @FXML
    Button cancelAlertButton;

    @FXML
    Label countDownAuthorities;

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

    private AtomicBoolean running = new AtomicBoolean(true);
    private AtomicBoolean countdown = new AtomicBoolean(true);

    private LocalTime chosenTime;

    private PrintConsole printConsole;

    private static volatile int speed = 1000; //Waiting time in ms

    private boolean awayModeOn = false;

    private boolean alertTriggered = false;

    private int countdownMinutesLeft = 0;
    private int countdownSecondsLeft = 0;

    private class Countdown extends Thread{

        @Override
        public void run() {
            AtomicInteger seconds = new AtomicInteger(countdownSecondsLeft);
            AtomicInteger minutes = new AtomicInteger(countdownMinutesLeft);
            AtomicReference<PrintConsole> printCons = new AtomicReference<>(printConsole);

            while (countdown.get()) {
                Platform.runLater(() -> {

                    if (seconds.get() != 0) {
                        seconds.getAndDecrement();
                    }
                    else if (minutes.get() != 0) {
                        minutes.getAndDecrement();
                        seconds.getAndSet(60);
                    }
                    else if (seconds.get() == 0 && minutes.get() == 0) {
                        countdown.getAndSet(false);
                        printCons.get().setText("The authorities have been called!!!");
                    }

                    countDownAuthorities.setText(
                            String.format("Time: %s:%s",minutes.get()<10?"0"+minutes.get():""+minutes.get(), seconds.get()<10?"0"+seconds.get():seconds.get()+"")
                    );
                });

                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }

            // Store the remaining sec and min?
            countdownSecondsLeft = seconds.get();
            countdownMinutesLeft = minutes.get();
        }
    }

    private class Clock extends Thread{

        @Override
        public void run() {
            AtomicReference<LocalTime> local = new AtomicReference<>(chosenTime);

            while (running.get()) {
                Platform.runLater(() -> {
                    local.getAndSet(local.get().plusSeconds(1));
                    int h = local.get().getHour();
                    int m = local.get().getMinute();
                    int s = local.get().getSecond();
                    leftPanelTime.setText(
                            String.format("Time: %s:%s:%s", h<10?"0"+h:""+h, m<10?"0"+m:""+m, s<10?"0"+s:s+"")
                    );
                    if (h == 0 && m == 0 && s == 0)
                        leftPanelDate.setText("Date: "+ dateSHS.getValue().plusDays(1));
                });

                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
            chosenTime = local.get();
        }
    }

    class PrintConsole {
        void setText (String message) {

            int h = chosenTime.getHour();
            int m = chosenTime.getMinute();
            int s = chosenTime.getSecond();

            String time = String.format("%s:%s:%s", h<10?"0"+h:""+h, m<10?"0"+m:""+m, s<10?"0"+s:s+"");

            consoleTextField.setText("[" + time + "] " + message + "\n" + consoleTextField.getText());
        }
    }

    /**
     * Instantiate a new Main view controller.
     */
    public MainViewController() {
        simulationDataController = new SimulationDataController();
        houseViewController = new HouseViewController();
        shsController = new SHSController();
        shcController = new SHCController();
        shpController = new SHPController();
    }

    /**
     * Initialize the simulator by restricting the access of the UI until the user uploads
     * a path of the house layout JSON file that they wishes to upload.
     */
    @FXML
    public void initialize() {
        turnOnOffSimulation.setDisable(true);
        moduleTabs.setDisable(true);

        showUIElement(avatarImageView, false);
        showUIElement(userNameLabel, false);
        showUIElement(userIDLabel, false);
        showUIElement(userPermissionLabel, false);
        showUIElement(userLocationLabel, false);
        showUIElement(leftPanelDate, false);
        showUIElement(leftPanelTime, false);
        showUIElement(leftPanelTimeSpeed, false);
        showUIElement(leftPanelOutTemp, false);
        showUIElement(leftPanelInTemp, false);
        showUIElement(labelLeftPanelSimParam, false);
        showUIElement(labelUserInfoLeftPanel, false);

        gridSHC.setDisable(true);
        gridSHH.setDisable(true);
        gridSHP.setDisable(true);

        shsController.register(shcController);
        shsController.register(shpController);
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
        houseViewController.drawLayout(bp, houseModel, userModelArrayList);
    }

    /**
     * Set outside temperature (prints on left panel and also on House Layout).
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
     * Set inside temperature (prints on left panel and also on House Layout).
     *
     * @param event the event which indicates that the user interacted with the button that saves the inside temperature.
     */
    @FXML
    public void setInsideTemperature(ActionEvent event) {
        double value = inTempSHS.getValue();

        shsController.setInsideTemperature(rooms, value);
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
        shsController.addObjectToWindow(rooms, value, printConsole);
        drawLayout();
        saveSimulationConditions(event);
    }

    /**
     * Start or stop the simulation and enable or disable the needed UI controls (also prints on output console).
     */
    @FXML
    public void startOrStopSimulation() {
        if (turnOnOffSimulation.getText().equals("Start the simulation")) {
            turnOnOffSimulation.setText("Stop the simulation");

            System.out.println("The simulation has been started!");
            saveDate.setDisable(true);
            saveTime.setDisable(true);
            saveOutsideTemp.setDisable(true);
            saveInsideTemp.setDisable(true);
            loginButton.setDisable(true);
            saveTimeSpeed.setDisable(true);

            running.getAndSet(true);

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            printConsole.setText("The simulation has been started!");
            (new Clock()).start();
        }
        else if (turnOnOffSimulation.getText().equals("Stop the simulation")) {
            turnOnOffSimulation.setText("Start the simulation");

            System.out.println("The simulation has been stopped!");
            saveDate.setDisable(false);
            saveTime.setDisable(false);
            saveOutsideTemp.setDisable(false);
            saveInsideTemp.setDisable(false);
            loginButton.setDisable(false);
            saveTimeSpeed.setDisable(false);

            running.getAndSet(false);

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            printConsole.setText("The simulation has been stopped!");
        }
    }

    /**
     * Log in the user based on the user ID provided.
     */
    @FXML
    public void login() {
        int id = userIdToLogin.getValue();

        userInfo = shsController.login(houseModel, id, userModelArrayList, printConsole);

        turnOffSimulationWarning();
        processUserInfo("login");
        processPermission((UserModel) userInfo[1]);

        checkIfIntrusion();
    }

    /**
     * Delete user profile based on the user ID provided.
     */
    @FXML
    public void deleteUserProfile() {
        int id = userIdToRemove.getValue();

        shsController.deleteUserProfile(userModelArrayList, id, printConsole);

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

        userInfo = shsController.addModifyUser(userModelArrayList, rooms, id, name, userType, location, printConsole);

        //Catching exception, this method is only called when the autoMode is turned on
        try {
            openOrCloseLights(null);
        } catch (Exception e) {
            System.out.println("Creating new user");
        }

        processUserInfo("add/modify");
        try {
            processPermission((UserModel) userInfo[1]);
        } catch (Exception e) {
            System.out.println("Creating new user");
        }

        checkIfIntrusion();


        data.clear();
        loadUsersInSHSTable();
        userTable.setItems(data);
        drawLayout();
    }

    private void checkIfIntrusion() {
        // Check if you can enable away mode.
        awayButton.setDisable(false);

        for (String roomName : rooms.keySet()) {

            System.out.println("Room name: " + roomName);

            if (rooms.get(roomName).getNbPeople() > 0) {
                awayButton.setDisable(true);

                if (awayModeOn) {
                    printConsole.setText("There is an intruder in the " + roomName + " area!!!");
                    alertTriggered = true;
                }
            }
        }

        if (alertTriggered) {
            saveDurationAuth.setDisable(true);
            countdown.getAndSet(true);
            (new Countdown()).start();
        }
    }

    @FXML
    public void saveUserProfiles() {
        shsController.saveUserProfiles(userModelArrayList, printConsole);
    }

    /**
     * Save the simulation conditions (also prints on left panel and on House Layout).
     *
     * @param event the event which specifies the button which the user interacted with in order to save a certain
     *              simulation condition.
     */
    @FXML
    public void saveSimulationConditions(ActionEvent event) {
        if (event.getSource().equals(saveDate)) {
            printConsole.setText("The date has been changed to " + dateSHS.getValue().toString() + ".");
            leftPanelDate.setText("Date: " + dateSHS.getValue().toString());
        } else if (event.getSource().equals(saveTime)) {
            printConsole.setText("The time has been changed to " + timeSHS.getValue().toString() + ".");
            leftPanelTime.setText("Time: " + timeSHS.getValue().toString());
            chosenTime = timeSHS.getValue();

            int h = timeSHS.getValue().getHour();
            int m = timeSHS.getValue().getMinute();
            int s = timeSHS.getValue().getSecond();

            leftPanelTime.setText(
                    String.format("Time: %s:%s:%s", h<10?"0"+h:""+h, m<10?"0"+m:""+m, s<10?"0"+s:s+"")
            );

        } else if (event.getSource().equals(saveTimeSpeed)) {
            printConsole.setText("The simulation time speed has been changed to " + timeSpeedComboBoxSHS.getValue() + "x.");
        } else if (event.getSource().equals(saveOutsideTemp)) {
            printConsole.setText("The outside temperature has been changed to " + outTempSHS.getValue().toString() + " Celsius.");
        } else if (event.getSource().equals(saveInsideTemp)) {
            printConsole.setText("The inside temperature has been changed to " + inTempSHS.getValue().toString() + " Celsius.");
        }

        turnOffSimulationWarning();
    }

    /**
     * Hide the simulation warning message and allow the user to start the simulation if all the necessary conditions have
     * been met.
     */
    private void turnOffSimulationWarning() {
        if (!leftPanelDate.getText().isEmpty() && !leftPanelTime.getText().isEmpty() && !leftPanelTimeSpeed.getText().isEmpty() && !leftPanelInTemp.getText().isEmpty() && !leftPanelOutTemp.getText().isEmpty() && isLoggedIn) {
            turnOnOffSimulation.setDisable(false);
            warningLabelSimulation.setVisible(false);
            warningLabelSimulation.setManaged(false);
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
            loggedInUser = new UserModel(processedUser.getName(), processedUser.getId(), processedUser.getUser_type(), processedUser.getCurrentLocation());

            avatarImageView.setVisible(true);
            avatarImageView.setManaged(true);
            userNameLabel.setText("Name: " + loggedInUser.getName());
            userIDLabel.setText("ID: " + loggedInUser.getId());
            userPermissionLabel.setText("Permission: " + loggedInUser.getUser_type());
            userLocationLabel.setText("Location: " + loggedInUser.getCurrentLocation());
            isLoggedIn = true;
            turnOffSimulationWarning();
        }
    }

    /**
     * Display available commands for logged user.
     */
    private void processPermission(UserModel user) {

        if (user.getUser_type().equals("Parent")) {
            gridSHC.setDisable(false);
            gridSHH.setDisable(false);
            gridSHP.setDisable(false);
            turnOnOffAutomode.setDisable(false);
            fillDefaultComboBox(false);
        }
        if (user.getUser_type().equals("Stranger")) {
            gridSHC.setDisable(true);
            gridSHH.setDisable(true);
            gridSHP.setDisable(true);
        }
        //Permission revoke if child or guest are not in a room
        if ((user.getUser_type().equals("Child") || user.getUser_type().equals("Guest")) && (user.getCurrentLocation().equals("Backyard") ||
                user.getCurrentLocation().equals("Front yard") || user.getCurrentLocation().equals("House"))) {
            gridSHC.setDisable(true);
            gridSHH.setDisable(true);
            gridSHP.setDisable(true);
        }
        //Limited Permission if child or guest are in a room
        if ((user.getUser_type().equals("Child") || user.getUser_type().equals("Guest")) && (!user.getCurrentLocation().equals("Backyard") &&
                !user.getCurrentLocation().equals("Front yard") && !user.getCurrentLocation().equals("House"))) {
            gridSHC.setDisable(false);
            gridSHH.setDisable(true);
            gridSHP.setDisable(true);
            openDoor.setDisable(true);
            closeDoor.setDisable(true);
            unlockDoor.setDisable(true);
            lockDoor.setDisable(true);
            turnOnOffAutomode.setDisable(true);

            lightComboBoxSHC.getItems().clear();
            lightComboBoxSHP.getItems().clear();
            winComboBoxSHC.getItems().clear();
            lightComboBoxSHC.getItems().add(user.getCurrentLocation());
            lightComboBoxSHP.getItems().add(user.getCurrentLocation());
            winComboBoxSHC.getItems().add(user.getCurrentLocation());
            lightComboBoxSHC.getSelectionModel().selectFirst();
            lightComboBoxSHP.getSelectionModel().selectFirst();
            winComboBoxSHC.getSelectionModel().selectFirst();
        }
    }

    /**
     * Process the path of the house layout JSON file that the user wishes to upload and give access to the rest of
     * the simulator if the file exists.
     */
    @FXML
    private void readHouseLayoutFile() {

        //For the path, try something like C:\Soen 343\Project\HouseInfo.json
        JSONParser parser = new JSONParser();
        String path = houseLayoutFilePath.getText().replaceAll("\\\\+", "\\\\\\\\");
        Object obj = null;

        try {
            obj = parser.parse(new FileReader(path));
        } catch (IOException | ParseException e) {
            System.out.println("File not found or parse error occurred!");
            System.out.println("Textfield: " + houseLayoutFilePath.getText());
            System.out.println("Path: " + path);
            errorLabelHouseLayoutFile.setText("An error has occurred!\nEither the file was not found\nor a parsing error has\noccurred. Please double\ncheck your file path.");
            return;
        }

        warningLabelSimulation.setText("Please log in as a user as\nwell as set the date, time,\nsimulation time speed,\ninside temperature, and\noutside temperature before\nstarting the simulation.");
        System.out.println("File found!");
        System.out.println("Textfield: " + houseLayoutFilePath.getText());
        System.out.println("Path: " + path);

        moduleTabs.setDisable(false);
        showUIElement(labelHouseLayoutFile, false);
        showUIElement(houseLayoutFilePath, false);
        showUIElement(saveHouseLayoutFilePath, false);
        showUIElement(errorLabelHouseLayoutFile, false);

        showUIElement(avatarImageView, true);
        showUIElement(userNameLabel, true);
        showUIElement(userIDLabel, true);
        showUIElement(userPermissionLabel, true);
        showUIElement(userLocationLabel, true);
        showUIElement(leftPanelDate, true);
        showUIElement(leftPanelTime, true);
        showUIElement(leftPanelTimeSpeed, true);
        showUIElement(leftPanelOutTemp, true);
        showUIElement(leftPanelInTemp, true);
        showUIElement(labelLeftPanelSimParam, true);
        showUIElement(labelUserInfoLeftPanel, true);

        // SHS prep

        simulationDataController.loadData(path);
        houseModel = simulationDataController.getHouseModel();
        rooms = houseModel.getRooms();
        roomNamesSet = houseModel.getRooms().keySet();
        userModelArrayList = simulationDataController.getUserArrayList();

        loadUsersInSHSTable();

        houseViewController.drawLayout(bp, houseModel, userModelArrayList);

        fillDefaultComboBox(true);

        userTable.setEditable(true);
        userTable.setItems(data);

        dateSHS.setValue(LocalDate.now());

        turnOnOffSimulation.setDisable(true);

        timeSHS.setValue(LocalTime.of(12, 0,0));
        chosenTime = LocalTime.of(12, 0,0);

        timerFrom.setValue(LocalTime.of(12, 0,0));
        timerTo.setValue(LocalTime.of(12, 1,10));

        printConsole = new PrintConsole();
    }

    // Fill ComboBox of actions (open/close)
    private void fillDefaultComboBox(boolean setup) {

        if (!setup) { // We are just reload certain ComboBoxes because the user who just logged in has higher permissions
            lightComboBoxSHC.getItems().clear();
            lightComboBoxSHP.getItems().clear();
            winComboBoxSHC.getItems().clear();

            for (String roomName : roomNamesSet) {
                winComboBoxSHC.getItems().add(roomName);
                lightComboBoxSHC.getItems().add(roomName);
                lightComboBoxSHP.getItems().add(roomName);
            }

            lightComboBoxSHC.getItems().addAll("Backyard", "Front yard");
            lightComboBoxSHP.getItems().addAll("Backyard", "Front yard");

            winComboBoxSHC.getSelectionModel().selectFirst();
            lightComboBoxSHC.getSelectionModel().selectFirst();
            lightComboBoxSHP.getSelectionModel().selectFirst();
        } else { // Initial setup

            addModifyLocComboBoxSHS.getItems().add("House");

            for (String roomName : roomNamesSet) {
                addModifyLocComboBoxSHS.getItems().add(roomName);
                blockWinLocComboBoxSHS.getItems().add(roomName);
                doorComboBoxSHC.getItems().add(roomName);
                winComboBoxSHC.getItems().add(roomName);
                lightComboBoxSHC.getItems().add(roomName);
                lightComboBoxSHP.getItems().add(roomName);
                lockDoorComboBoxSHC.getItems().add(roomName);
            }

            lightComboBoxSHC.getItems().addAll("Backyard", "Front yard");
            lightComboBoxSHP.getItems().addAll("Backyard", "Front yard");
            doorComboBoxSHC.getItems().addAll("Backyard", "Front yard");
            lockDoorComboBoxSHC.getItems().addAll("Backyard", "\"Front yard\"");
            addModifyLocComboBoxSHS.getItems().addAll("Front yard", "Backyard");
            addModifyRoleComboBoxSHS.getItems().addAll("Parent", "Child", "Guest", "Stranger");
            timeSpeedComboBoxSHS.getItems().addAll("0.25", "0.5", "0.75", "1", "1.25", "1.5", "1.75", "2");


            addModifyLocComboBoxSHS.getSelectionModel().selectFirst();
            addModifyRoleComboBoxSHS.getSelectionModel().selectFirst();
            blockWinLocComboBoxSHS.getSelectionModel().selectFirst();
            doorComboBoxSHC.getSelectionModel().selectFirst();
            lockDoorComboBoxSHC.getSelectionModel().selectFirst();
            winComboBoxSHC.getSelectionModel().selectFirst();
            lightComboBoxSHC.getSelectionModel().selectFirst();
            lightComboBoxSHP.getSelectionModel().selectFirst();
            timeSpeedComboBoxSHS.getSelectionModel().select(3);
        }
    }

    /**
     * Helper method which shows or hides a given UI element.
     *
     * @param node the UI element which must either be shown or hidden.
     * @param bool a boolean where true means to show and false means to hide.
     */
    private void showUIElement(Node node, boolean bool) {
        node.setVisible(bool);
        node.setManaged(bool);
    }

    @FXML
    public void setAutoMode() {
        shcController.setAutoMode(!shcController.isAutoMode());

        if (shcController.isAutoMode()) {
            turnOnOffAutomode.setText("Turn Off AutoMode");
        } else {
            turnOnOffAutomode.setText("Turn On AutoMode");
        }
    }

    @FXML
    public void openDoor() {
        String value = doorComboBoxSHC.getValue();
        shcController.openDoor(value, houseModel, printConsole);
        drawLayout();
    }

    @FXML
    public void closeDoor() {
        String value = doorComboBoxSHC.getValue();
        shcController.closeDoor(value, houseModel, printConsole);
        drawLayout();
    }

    @FXML
    public void lockDoor() {
        String value = lockDoorComboBoxSHC.getValue();
        shcController.lockDoor(value, houseModel, printConsole);
        drawLayout();
    }

    @FXML
    public void unLock() {
        String value = lockDoorComboBoxSHC.getValue();
        shcController.unLock(value, houseModel, printConsole);
        drawLayout();
    }

    @FXML
    void openWindow() {
        String value = winComboBoxSHC.getValue();
        shcController.openWindow(value, houseModel, printConsole);
        drawLayout();
    }

    @FXML
    void closeWindow() {
        String value = winComboBoxSHC.getValue();
        shcController.closeWindow(value, houseModel, printConsole);
        drawLayout();
    }

    @FXML
    void openOrCloseLights(ActionEvent event) {
        String value = lightComboBoxSHC.getValue();
        if (event != null && event.getSource().equals(turnOnLight)) {
            shcController.openOrCloseLights(value, true, "open", houseModel, printConsole);
            turnOnOffAutomode.setText("Turn On AutoMode");
        } else if (event != null && event.getSource().equals(turnOffLight)) {
            shcController.openOrCloseLights(value, true, "close", houseModel, printConsole);
            turnOnOffAutomode.setText("Turn On AutoMode");
        } else {
            UserModel user = ((UserModel) userInfo[1]);
            if (rooms.containsKey(user.getCurrentLocation())) {
                shcController.openOrCloseLights(user.getCurrentLocation(), false, "open", houseModel, printConsole);
            }

            if (rooms.containsKey(user.getPreviousLocation()) && rooms.get(user.getPreviousLocation()).getNbPeople() == 0) {
                shcController.openOrCloseLights(user.getPreviousLocation(), false, "close", houseModel, printConsole);
            }

        }

        drawLayout();
    }

    public void saveTimeSpeed(ActionEvent event) {

        leftPanelTimeSpeed.setText("Simulation time speed: " + timeSpeedComboBoxSHS.getValue() + "x");

        AtomicInteger newSpeed = new AtomicInteger(((Double)(1000 / Double.parseDouble(timeSpeedComboBoxSHS.getValue()))).intValue());
        speed = newSpeed.get();

        saveSimulationConditions(event);
    }

    public void saveHourMinute(ActionEvent event) {
    }

    public void saveCountdownAuthority() {
        countdownMinutesLeft = timerMinuteAuthority.getValue();
        countdownSecondsLeft = timerSecondAuthority.getValue();
    }

    @FXML
    public void enterAwayMode () {

        awayModeOn = true;

        for (RoomModel room : rooms.values()) {
            shcController.closeWindow(room.getName(), houseModel, printConsole);
            shcController.lockDoor(room.getName(), houseModel, printConsole);
        }

        drawLayout();
    }

    public void cancelAlert () {
        countdown.getAndSet(false);
        awayModeOn = false;
        alertTriggered = false;
        saveDurationAuth.setDisable(false);
        printConsole.setText("The alert has been canceled.");
        countDownAuthorities.setText("Time: ----");
    }
}

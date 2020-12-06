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
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.SmartHomeModel.*;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
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
     * A controller which handles the security of the smart home (lights, intruders, away mode).
     */
    private SHHController shhController;

    /**
     * An array with all the rooms of the house.
     */
    private Map<String, RoomModel> rooms;

    private Map<String, Zone> zoneList;

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

    /**
     * The Door combo box shc.
     */
    @FXML
    ComboBox<String> doorComboBoxSHC;

    /**
     * The Open door.
     */
    @FXML
    Button openDoor;

    /**
     * The Close door.
     */
    @FXML
    Button closeDoor;

    /**
     * The Win combo box shc.
     */
    @FXML
    ComboBox<String> winComboBoxSHC;

    /**
     * The Open window.
     */
    @FXML
    Button openWindow;

    /**
     * The Close window.
     */
    @FXML
    Button closeWindow;

    /**
     * The Light combo box shc.
     */
    @FXML
    ComboBox<String> lightComboBoxSHC;

    /**
     * The Turn on light.
     */
    @FXML
    Button turnOnLight;

    /**
     * The Turn off light.
     */
    @FXML
    Button turnOffLight;

    /**
     * The Turn on off automode.
     */
    @FXML
    Button turnOnOffAutomode;

    /**
     * The Lock door combo box shc.
     */
    @FXML
    ComboBox<String> lockDoorComboBoxSHC;

    /**
     * The Lock door.
     */
    @FXML
    Button lockDoor;

    /**
     * The Unlock door.
     */
    @FXML
    Button unlockDoor;

    /**
     * The Light combo box shp.
     */
    @FXML
    ComboBox<String> lightComboBoxSHP;

    /**
     * The Timer from.
     */
    @FXML
    JFXTimePicker timerFrom;

    /**
     * The Timer to.
     */
    @FXML
    JFXTimePicker timerTo;

    /**
     * The Save duration.
     */
    @FXML
    Button saveDuration;

    /**
     * The Save time speed.
     */
    @FXML
    Button saveTimeSpeed;

    /**
     * The Timer minute authority.
     */
    @FXML
    Spinner<Integer> timerMinuteAuthority;

    /**
     * The Timer second authority.
     */
    @FXML
    Spinner<Integer> timerSecondAuthority;

    /**
     * The Save duration auth.
     */
    @FXML
    Button saveDurationAuth;

    /**
     * The Away button.
     */
    @FXML
    Button awayButton;

    /**
     * The Cancel alert button.
     */
    @FXML
    Button cancelAlertButton;

    /**
     * The Count down authorities.
     */
    @FXML
    Label countDownAuthorities;

    /**
     * The Authorities called message.
     */
    @FXML
    Label authoritiesCalledMessage;

    /**
     * The Calling authorities label.
     */
    @FXML
    Label callingAuthoritiesLabel;

    /**
     * The Select light message.
     */
    @FXML
    Label selectLightMessage;

    @FXML
    ComboBox<String> month;

    @FXML
    ComboBox<String> season;

    // In SHS
    @FXML
    Button saveSeason;

    @FXML
    ComboBox<String> locationComboBoxSHH;

    @FXML
    ComboBox<String> locationManComboBoxSHH;

    @FXML
    ComboBox<String> zoneComboBoxSHH;

    @FXML
    ComboBox<String> zoneTemperatureComboBox;

    @FXML
    ComboBox<String> periodComboBoxSHH;

    @FXML
    ComboBox<String> seasonComboBoxSHH;

    @FXML
    Spinner<Double> temperatureSeasonSpinnerSHH;


    @FXML
    Spinner<Double> temperaturePeriodSpinnerSHH;

    @FXML
    Spinner<Double> temperatureManSpinnerSHH;

    @FXML
    Button saveZone;

    @FXML
    Button savePeriodTemperature;

    @FXML
    Button saveSeasonTemperature;

    @FXML
    Button saveManSHH;

    @FXML
    Button saveHVAC;

    @FXML
    ComboBox<String> locationOverrideComboBoxSHH;

    @FXML
    Button saveOffOverride;

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
     * Time running.
     */
    private AtomicBoolean running = new AtomicBoolean(false);
    /**
     * Time countdown.
     */
    private AtomicBoolean countdown = new AtomicBoolean(false);
    /**
     * Time chosen.
     */
    private LocalTime chosenTime;

    /**
     * Printing on console.
     */
    private volatile PrintConsole printConsole;
    /**
     * Time speed.
     */
    private static volatile int speed = 1000; //Waiting time in ms
    /**
     * awayMood.
     */
    private boolean awayModeOn = false;
    /**
     * When intrusion occurs and alert is triggered.
     */
    private boolean alertTriggered = false;
    /**
     * Time countdown for minutes left.
     */
    private int countdownMinutesLeft = 0;
    /**
     * Time countdown for minutes left.
     */
    private int countdownSecondsLeft = 0;
    /**
     * Parameters for simualtion is set.
     */
    private boolean simulationParametersSet = false;
    /**
     * HashMap for time.
     */
    private Map<String, Pair<LocalTime, LocalTime>> keepLightsOn = new HashMap<>();

    private AtomicBoolean isHVACOn = new AtomicBoolean();

    private volatile Map<RoomModel, HVAC> roomHVACS = new HashMap<>();

    private volatile Map<RoomModel, Double> requestsForHVACS = new HashMap<>();

    private volatile Map<String, Boolean> isroomHVACManual = new HashMap<>();

    /**
     * The thread for clock for simulation.
     */
    private class Countdown extends Thread {

        @Override
        public void run() {
            AtomicInteger seconds = new AtomicInteger(countdownSecondsLeft);
            AtomicInteger minutes = new AtomicInteger(countdownMinutesLeft);
            AtomicReference<PrintConsole> console = new AtomicReference<>(printConsole);

            if (!running.get()) {
                Platform.runLater(() -> {
                    countDownAuthorities.setText(
                            String.format("%s:%s", minutes.get() < 10 ? "0" + minutes.get() : "" + minutes.get(), seconds.get() < 10 ? "0" + seconds.get() : seconds.get() + "")
                    );
                });
            }

            while (countdown.get() && running.get()) {

                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.getMessage();
                }

                Platform.runLater(() -> {
                    if (seconds.get() != 0) {
                        seconds.getAndDecrement();
                    } else if (minutes.get() != 0) {
                        minutes.getAndDecrement();
                        seconds.getAndSet(60);
                    } else if (seconds.get() == 0 && minutes.get() == 0) {
                        countdown.getAndSet(false);
                        authoritiesCalledMessage.setText("The authorities have been called!!!");
                        console.get().setText("The authorities have been called!!!");
                    }

                    countDownAuthorities.setText(
                            String.format("%s:%s", minutes.get() < 10 ? "0" + minutes.get() : "" + minutes.get(), seconds.get() < 10 ? "0" + seconds.get() : seconds.get() + "")
                    );
                });

                // Store the remaining sec and min
                countdownSecondsLeft = seconds.get();
                countdownMinutesLeft = minutes.get();
            }
        }
    }

    /**
     * Thread for clock
     */
    private class Clock extends Thread {

        AtomicReference<LocalTime> local = new AtomicReference<>(chosenTime);
        AtomicReference<Map<String, Pair<LocalTime, LocalTime>>> lightsToTurnOn = new AtomicReference<>(keepLightsOn);
        AtomicReference<JFXDatePicker> date = new AtomicReference<>(dateSHS);
        AtomicReference<PrintConsole> console = new AtomicReference<>(printConsole);
        AtomicReference<HouseModel> house = new AtomicReference<>(houseModel);
        Map<RoomModel, Boolean> pipeBurstMessageSent = new HashMap<>();
        Map<RoomModel, Boolean> continuousBlockingMessageSent = new HashMap<>();
        AtomicReference<Map<RoomModel, HVAC>> HVACS = new AtomicReference<>(roomHVACS);
        AtomicReference<Map<RoomModel, Double>> HVACSRequests = new AtomicReference<>(requestsForHVACS);
        AtomicReference<Map<String, Boolean>> isRoomManual = new AtomicReference<>(isroomHVACManual);
        AtomicReference<SHCController> shcControllerAtomicReference = new AtomicReference<>(shcController);

        @Override
        public void run() {

            // Start an HVAC thread for every room. Every HVAC starts with the default target temp as the outside.
            for (RoomModel room : house.get().getRooms().values()) {
                HVAC hvac = new HVAC(new AtomicReference<>(room), 0.1, room.getTemperature(), new DecimalFormat("#.#"));
                HVACS.get().put(room, hvac);
                pipeBurstMessageSent.put(room, false);
                continuousBlockingMessageSent.put(room, false);
                isRoomManual.get().put(room.getName(), false);
                hvac.start();
            }

            while (running.get()) {
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.getMessage();
                }

                Platform.runLater(() -> {

                    // Lights scheduling
                    for (String lightName : lightsToTurnOn.get().keySet()) {

                        if (local.get().compareTo(lightsToTurnOn.get().get(lightName).getKey()) == 0) {
                            System.out.println(lightsToTurnOn.get().get(lightName).getKey());
                            scheduleTurnOnOffLight(lightName, "open");
                        } else if (local.get().compareTo(lightsToTurnOn.get().get(lightName).getValue()) == 0) {
                            scheduleTurnOnOffLight(lightName, "close");
                        }
                    }

                    System.out.println(local.get());

                    // When entering these two for loops, a HVAC can be in four states:
                    // 1. Idle and not away mode
                    // 2. Idle and away mode
                    // 3. Manual and not away mode
                    // 4. Manual and away mode

                    for (RoomModel room : house.get().getRooms().values()) {
                        for (Zone zone : house.get().getZoneList().values()) {

                            String month = date.get().getValue().getMonth().toString();
                            month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase(); // Capitalize first letter

                            if (isHVACOn.get()) {

                                // Activating all the manual temperature changes.
                                if (HVACSRequests.get().containsKey(room) && !HVACS.get().get(room).getStateHVAC().equals("Manual")) {
                                    HVACS.get().get(room).setTargetTemperature(HVACSRequests.get().get(room));
                                    HVACS.get().get(room).setRate(0.1);
                                    HVACS.get().get(room).setRounding(new DecimalFormat("#.#"));
                                    HVACS.get().get(room).setStateHVAC("Manual");
                                    console.get().setText("Starting to cool/heat the " + room.getName() + " to a temperature of " + HVACSRequests.get().get(room) + " C.");
                                }

                                // Case #-2 If you had the HVAC off and now you turned it back on, your state will still be off and so we have to make it idle.
                                if (HVACS.get().get(room).getStateHVAC().equals("Off")) {
                                    HVACS.get().get(room).setStateHVAC("Idle");
                                    HVACS.get().get(room).setTargetTemperature(room.getTemperature());
                                }

                                // Case #-1 If you are in away mode and you are not in state Idle, Manual, Away Mode Summer or Away Mode Winter, then your state will change to Idle. /////////// add to this if? || HVACS.get().get(room).getStateHVAC().equals("Summer Cooling")
                                if (awayModeOn && !HVACS.get().get(room).getStateHVAC().equals("Idle") && !HVACS.get().get(room).getStateHVAC().equals("Manual") && !HVACS.get().get(room).getStateHVAC().equals("Away Mode Summer") && !HVACS.get().get(room).getStateHVAC().equals("Away Mode Winter")) {
                                    HVACS.get().get(room).setStateHVAC("Idle");
                                    HVACS.get().get(room).setTargetTemperature(room.getTemperature());
                                }

                                // Case #0 If you were in away mode and in either the state Away Mode Summer or the state Away Mode Winter AND you just turned off away mode, then your state is now set to idle.
                                if (!awayModeOn && (HVACS.get().get(room).getStateHVAC().equals("Away Mode Summer") || HVACS.get().get(room).getStateHVAC().equals("Away Mode Winter"))) {
                                    HVACS.get().get(room).setStateHVAC("Idle");
                                    HVACS.get().get(room).setTargetTemperature(room.getTemperature());
                                }

                                // Case #1
                                if (!awayModeOn && zone.getRooms().contains(room) && !HVACS.get().get(room).getStateHVAC().equals("Manual") && !HVACS.get().get(room).getStateHVAC().equals("Away Mode Summer") && !HVACS.get().get(room).getStateHVAC().equals("Away Mode Winter")) {
                                    // Check if it's 00:00, 08:00, 16:00 and change target temp according to the zone's corresponding period temp
                                    if (local.get().compareTo(LocalTime.of(0, 0)) >= 0 && local.get().compareTo(LocalTime.of(8, 0)) < 0 && !HVACS.get().get(room).getStateHVAC().equals("Night Schedule") && !zone.isUnsetNightTemp()) {
                                        HVACS.get().get(room).setStateHVAC("Night Schedule");
                                        HVACS.get().get(room).setTargetTemperature(zone.getNightTemp());
                                        HVACS.get().get(room).setRate(0.1);
                                        HVACS.get().get(room).setRounding(new DecimalFormat("#.#"));
                                        console.get().setText("Starting scheduling heating/cooling for the night in the " + room.getName() + " (" + zone.getZoneName() + "). Target temperature: " + zone.getNightTemp() + " C.");
                                    } else if (local.get().compareTo(LocalTime.of(8, 0)) >= 0 && local.get().compareTo(LocalTime.of(16, 0)) < 0 && !HVACS.get().get(room).getStateHVAC().equals("Day Schedule") && !zone.isUnsetDayTemp()) {
                                        HVACS.get().get(room).setStateHVAC("Day Schedule");
                                        HVACS.get().get(room).setTargetTemperature(zone.getDayTemp());
                                        HVACS.get().get(room).setRate(0.1);
                                        HVACS.get().get(room).setRounding(new DecimalFormat("#.#"));
                                        console.get().setText("Starting scheduling heating/cooling for the day in the " + room.getName() + " (" + zone.getZoneName() + "). Target temperature: " + zone.getDayTemp() + " C.");
                                    } else if (local.get().compareTo(LocalTime.of(16, 0)) >= 0 && local.get().compareTo(LocalTime.of(23, 59, 59)) < 0 && !HVACS.get().get(room).getStateHVAC().equals("Evening Schedule") && !zone.isUnsetEveningTemp()) {
                                        HVACS.get().get(room).setStateHVAC("Evening Schedule");
                                        HVACS.get().get(room).setTargetTemperature(zone.getEveningTemp());
                                        HVACS.get().get(room).setRate(0.1);
                                        HVACS.get().get(room).setRounding(new DecimalFormat("#.#"));
                                        console.get().setText("Starting scheduling heating/cooling for the evening in the " + room.getName() + " (" + zone.getZoneName() + "). Target temperature: " + zone.getEveningTemp() + " C.");
                                    }
                                }

                                // Case #2 when in summer month, away mode is off, it's not manual mode, window of a specific room is not blocked, HVAC is on, and room temp > outside temp: set target temp for the
                                // specific room to the outside temp and make the room temp go down by 0.05 C/S
                                // Check if any room temp is above outside temp if the we're in a summer month and the window of the room is not blocked.
                                if (!awayModeOn && house.get().getSummerMonthList().contains(month) && (house.get().getOutsideTemp() < room.getTemperature()) && !HVACS.get().get(room).getStateHVAC().equals("Summer Cooling") && !HVACS.get().get(room).getStateHVAC().equals("Manual") && !HVACS.get().get(room).getStateHVAC().equals("Night Schedule") && !HVACS.get().get(room).getStateHVAC().equals("Day Schedule") && !HVACS.get().get(room).getStateHVAC().equals("Evening Schedule")) {
                                    if (!room.getWindow().isOpen() && room.getWindow().hasObject().get() && !continuousBlockingMessageSent.get(room)) {
                                        continuousBlockingMessageSent.put(room, true);
                                        console.get().setText("Cannot cool " + room.getName() + " to outside temperature because the windows cannot be opened (there is an object blocking them)!");
                                    } else if (room.getWindow().isOpen() || (!room.getWindow().isOpen() && !room.getWindow().hasObject().get())) {
                                        continuousBlockingMessageSent.put(room, false);
                                        shcControllerAtomicReference.get().openWindow(room.getName(), house.get(), console.get());
                                        HVACS.get().get(room).setTargetTemperature(house.get().getOutsideTemp());
                                        HVACS.get().get(room).setRate(0.05);
                                        HVACS.get().get(room).setRounding(new DecimalFormat("#.##"));
                                        HVACS.get().get(room).setStateHVAC("Summer Cooling");
                                        console.get().setText("Canceling any current heating/cooling for the " + room.getName() + " in order to cool it to the outside temperature! [Continuous monitoring]");
                                    }
                                }

                                /**
                                 * TODO Make the thread check the summer cooling again and again (maybe the windows and now closed and blocked) (need to keep windows open) (careful not to send the message 100 times)
                                 */

                                // Case #3 If the house is in away mode and the HVAC is on and it's a summer month and the room is not in manual mode, change target temp of the room to season temp
                                if (awayModeOn && !HVACS.get().get(room).getStateHVAC().equals("Manual")) {
                                    //refactoring Extract Variable, make condition more readable
                                    final boolean monthIsASummerMonth = house.get().getSummerMonthList().contains(month);
                                    final boolean monthIsAWinterMonth = house.get().getWinterMonthList().contains(month);
                                    final boolean isRoomInZone = room.getZone() != null;
                                    final boolean isSummerTemperatureSet = !houseModel.isUnsetSummerTemp();
                                    final boolean isAwayModeSummer = !HVACS.get().get(room).getStateHVAC().equals("Away Mode Summer");
                                    final boolean isAwayModeWinter = !HVACS.get().get(room).getStateHVAC().equals("Away Mode Winter");
                                    final boolean isWinterTemperatureSet = !houseModel.isUnsetWinterTemp();

                                    if(monthIsASummerMonth && isRoomInZone && isAwayModeSummer && isSummerTemperatureSet){

                                        // Set state to away mode summer
                                        HVACS.get().get(room).setTargetTemperature(house.get().getSummerTemperature());
                                        HVACS.get().get(room).setRate(0.1);
                                        HVACS.get().get(room).setRounding(new DecimalFormat("#.#"));
                                        HVACS.get().get(room).setStateHVAC("Away Mode Summer");
                                        console.get().setText("Starting default heating/cooling for the summer in the " + room.getName() + ". Target temperature: " + house.get().getSummerTemperature() + " C. [Away Mode]");

                                    } else if(monthIsAWinterMonth && isRoomInZone && isAwayModeWinter && isWinterTemperatureSet) {

                                        // Set state to away mode winter

                                        HVACS.get().get(room).setTargetTemperature(house.get().getWinterTemperature());
                                        HVACS.get().get(room).setRate(0.1);
                                        HVACS.get().get(room).setRounding(new DecimalFormat("#.#"));
                                        HVACS.get().get(room).setStateHVAC("Away Mode Winter");
                                        console.get().setText("Starting default heating/cooling for the summer in the " + room.getName() + ". Target temperature: " + house.get().getWinterTemperature() + " C. [Away Mode]");
                                    }
                                }
                            }

                            // Case #4 when the HVAC is off: set target temp for all rooms to the outside temp and make the room temp go down by 0.05 C/S
                            if (!isHVACOn.get() && !HVACS.get().get(room).getStateHVAC().equals("Off")) {
                                HVACS.get().get(room).setTargetTemperature(house.get().getOutsideTemp());
                                HVACS.get().get(room).setRate(0.05);
                                HVACS.get().get(room).setRounding(new DecimalFormat("#.##"));
                                HVACS.get().get(room).setStateHVAC("Off");
                            }

                            if (room.getTemperature() > 0 && pipeBurstMessageSent.get(room)) {
                                pipeBurstMessageSent.put(room, false);
                            }

                            if (room.getTemperature() < 0 && !pipeBurstMessageSent.get(room)) {
                                pipeBurstMessageSent.put(room, true);
                                console.get().setText("The temperature in the " + room.getName() + " is below zero! There might be a burst pipe in there!");
                            }
                        }
                    }

                    local.getAndSet(local.get().plusSeconds(1));
                    int h = local.get().getHour();
                    int m = local.get().getMinute();
                    int s = local.get().getSecond();
                    leftPanelTime.setText(
                            String.format("Time: %s:%s:%s", h < 10 ? "0" + h : "" + h, m < 10 ? "0" + m : "" + m, s < 10 ? "0" + s : s + "")
                    );
                    if (h == 0 && m == 0 && s == 0)
                        leftPanelDate.setText("Date: " + dateSHS.getValue().plusDays(1));

                    chosenTime = local.get();
                });
            }
        }
    }

    private class HVAC extends Thread {

        AtomicReference<RoomModel> room;
        volatile double rate;
        volatile double targetTemperature;
        DecimalFormat df;
        String stateHVAC;
        boolean isManual = false;
        AtomicReference<Map<String, Boolean>> isRoomManual = new AtomicReference<>(isroomHVACManual);

        HVAC(AtomicReference<RoomModel> room, double rate, double targetTemperature, DecimalFormat df) {
            this.room = room;
            this.rate = rate;
            this.targetTemperature = targetTemperature;
            this.df = df;
            stateHVAC = "Idle";
        }

        @Override
        public void run() {
            while (running.get()) { //Runs on the same variable as the simulation's clock

                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.getMessage();
                }

                if (room.get().getZone() != null) {
                    if (stateHVAC.equals("Night Schedule")) {
                        targetTemperature = room.get().getZone().getNightTemp();
                    } else if (stateHVAC.equals("Day Schedule")) {
                        targetTemperature = room.get().getZone().getDayTemp();
                    } else if (stateHVAC.equals("Evening Schedule")) {
                        targetTemperature = room.get().getZone().getEveningTemp();
                    }
                }

                Platform.runLater(() -> {

                    // Logs
                    if (room.get().getName().equals("Bedroom")) {
                        System.out.println("Room: " + room.get().getName());
                        System.out.println("Rate: " + rate);
                        System.out.println("Target temp: " + targetTemperature);
                        System.out.println("Room temp live: " + room.get().getTemperature());
                        System.out.println("HVAC state: " + stateHVAC);
                        System.out.println("isManual: " + isManual);
                    }

                    if (targetTemperature < room.get().getTemperature()) {
                        changeTempPeriod(Double.parseDouble(df.format(room.get().getTemperature() - rate)), room.get().getName(), isManual);
                    } else if (targetTemperature > room.get().getTemperature()) {
                        changeTempPeriod(Double.parseDouble(df.format(room.get().getTemperature() + rate)), room.get().getName(), isManual);
                    }
                });
            }
        }

        void setRate(double rate) {
            this.rate = rate;
        }

        void setTargetTemperature(double targetTemperature) {
            this.targetTemperature = targetTemperature;
        }

        void setRounding(DecimalFormat df) {
            this.df = df;
        }

        String getStateHVAC() {
            return stateHVAC;
        }

        void setStateHVAC(String state) {
            this.stateHVAC = state;
            isManual = state.equals("Manual");

            if (isManual) {
                isRoomManual.get().put(room.get().getName(), true);
            } else {
                isRoomManual.get().put(room.get().getName(), false);
            }
        }
    }

    /**
     * The type Print console.
     */
    class PrintConsole {
        /**
         * Sets text.
         *
         * @param message the message
         */
        void setText(String message) {

            int h = chosenTime.getHour();
            int m = chosenTime.getMinute();
            int s = chosenTime.getSecond();

            String time = String.format("%s:%s:%s", h < 10 ? "0" + h : "" + h, m < 10 ? "0" + m : "" + m, s < 10 ? "0" + s : s + "");

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
        shhController = new SHHController();
    }

    /**
     * Initialize the simulator by restricting the access of the UI until the user uploads
     * a path of the house layout JSON file that they wishes to upload.
     */
    @FXML
    public void initialize() {
        turnOnOffSimulation.setDisable(true);
        moduleTabs.setDisable(true);
        callingAuthoritiesLabel.setVisible(false);
        selectLightMessage.setVisible(false);

        countDownAuthorities.setVisible(false);

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

        countdownSecondsLeft = timerSecondAuthority.getValue();
        countdownMinutesLeft = timerMinuteAuthority.getValue();

        // Check HVAC on or off.
        isHVACOn.set(saveHVAC.getText().equals("Turn Off HVAC"));
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
    private synchronized void drawLayout() {
        System.out.println(bp.getChildren().isEmpty());
        houseViewController.drawLayout(bp, houseModel, userModelArrayList, isroomHVACManual);
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

        // New lines
        shsController.setInsideTemperature(rooms, value);

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

        // shsController.setInsideTemperature(rooms, value);
        drawLayout();
        leftPanelInTemp.setText("Inside Temperature: " + inTempSHS.getValue().toString() + " C");
        saveSimulationConditions(event);
    }

    /**
     * Add object to window.
     *
     * @param event the event which indicates that the user interacted with the button that saves the location in              which the window must be blocked by an object.
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

            running.set(true);

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            printConsole.setText("The simulation has been started! Starting the simulation clock and the scheduled heating/cooling.");
            (new Clock()).start();

            if (alertTriggered) {

                callingAuthoritiesLabel.setVisible(true);
                countDownAuthorities.setVisible(true);

                (new Countdown()).start();
            }
        } else if (turnOnOffSimulation.getText().equals("Stop the simulation")) {
            turnOnOffSimulation.setText("Start the simulation");

            System.out.println("The simulation has been stopped!");
            saveDate.setDisable(false);
            saveTime.setDisable(false);
            saveOutsideTemp.setDisable(false);
            saveInsideTemp.setDisable(false);
            loginButton.setDisable(false);
            saveTimeSpeed.setDisable(false);

            running.set(false);

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            printConsole.setText("The simulation has been stopped! Stopping the simulation clock and the scheduled heating/cooling.");
        }
    }

    /**
     * Log in the user based on the user ID provided.
     */
    @FXML
    public void login() {
        int id = userIdToLogin.getValue();

        userInfo = shsController.login(houseModel, id, userModelArrayList, printConsole);

        processUserInfo("login");

        turnOffSimulationWarning();

        checkIfIntrusion();
    }

    /**
     * Delete user profile based on the user ID provided.
     */
    @FXML
    public void deleteUserProfile() {
        int id = userIdToRemove.getValue();

        shsController.deleteUserProfile(userModelArrayList, rooms, id, printConsole);

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

        data.clear();
        loadUsersInSHSTable();
        userTable.setItems(data);
        drawLayout();

        checkIfIntrusion();

    }

    /**
     * Check for Intrusion.
     */
    private void checkIfIntrusion() {
        // Check if you can enable away mode.
        awayButton.setDisable(false);

        System.out.println("\n\nCheck intrusion called");

        for (String roomName : rooms.keySet()) {

            System.out.println("Room name: " + roomName);
            System.out.println("rooms.get(roomName).getNbPeople(): " + rooms.get(roomName).getNbPeople());

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
            callingAuthoritiesLabel.setVisible(true);
            countDownAuthorities.setVisible(true);
            (new Countdown()).start();
        }
    }

    /**
     * Save user profiles.
     */
    @FXML
    public void saveUserProfiles() {
        shsController.saveUserProfiles(userModelArrayList, printConsole);
    }

    /**
     * Save the simulation conditions (also prints on left panel and on House Layout).
     *
     * @param event the event which specifies the button which the user interacted with in order to save a certain              simulation condition.
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
                    String.format("Time: %s:%s:%s", h < 10 ? "0" + h : "" + h, m < 10 ? "0" + m : "" + m, s < 10 ? "0" + s : s + "")
            );

        } else if (event.getSource().equals(saveTimeSpeed)) {
            printConsole.setText("The simulation time speed has been changed to " + timeSpeedComboBoxSHS.getValue() + "x.");
        } else if (event.getSource().equals(saveOutsideTemp)) {
            printConsole.setText("The outside temperature has been changed to " + outTempSHS.getValue().toString() + " Celsius.");
        } else if (event.getSource().equals(saveInsideTemp)) {
            printConsole.setText("The inside temperature has been changed to " + inTempSHS.getValue().toString() + " Celsius.");
        }

        turnOffSimulationWarning();
        System.out.println(dateSHS.getValue().toString());
    }

    /**
     * Hide the simulation warning message and allow the user to start the simulation if all the necessary conditions have
     * been met.
     */
    private void turnOffSimulationWarning() {
        if (!leftPanelDate.getText().isEmpty() && !leftPanelTime.getText().isEmpty() && !leftPanelTimeSpeed.getText().isEmpty() && !leftPanelTimeSpeed.getText().isEmpty() && !leftPanelInTemp.getText().isEmpty() && !leftPanelOutTemp.getText().isEmpty() && isLoggedIn) {
            turnOnOffSimulation.setDisable(false);

            warningLabelSimulation.setText("Welcome to the Best Smart\nHome Simulator Ever!\nYou are now Ready to\nStart the Simulation!");
            warningLabelSimulation.setTextFill(Color.GREEN);

            simulationParametersSet = true;

            processPermission(loggedInUser);
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

            if (simulationParametersSet) {
                processPermission(loggedInUser);
            }
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
            openDoor.setDisable(false);
            closeDoor.setDisable(false);
            unlockDoor.setDisable(false);
            lockDoor.setDisable(false);
            saveZone.setDisable(false);
            savePeriodTemperature.setDisable(false);
            saveSeasonTemperature.setDisable(false);
            saveHVAC.setDisable(false);
            fillDefaultComboBox(false);
        }
        if (user.getUser_type().equals("Stranger")) {
            gridSHC.setDisable(true);
            gridSHH.setDisable(true);
            gridSHP.setDisable(true);
        }
        //Permission revoke if child or guest are not in a room
        if (user.getUser_type().equals("Guest") && (user.getCurrentLocation().equals("Backyard") ||
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
            winComboBoxSHC.getItems().clear();

            lightComboBoxSHC.getItems().add(user.getCurrentLocation());
            winComboBoxSHC.getItems().add(user.getCurrentLocation());

            lightComboBoxSHC.getSelectionModel().selectFirst();
            winComboBoxSHC.getSelectionModel().selectFirst();

            if (user.getUser_type().equals("Child")) {
                gridSHP.setDisable(false);
            }
            else if (user.getUser_type().equals("Guest")) {
                gridSHH.setDisable(false);
                saveZone.setDisable(true);
                savePeriodTemperature.setDisable(true);
                saveSeasonTemperature.setDisable(true);
                saveHVAC.setDisable(true);

                locationManComboBoxSHH.getItems().clear();
                locationOverrideComboBoxSHH.getItems().clear();

                locationManComboBoxSHH.getItems().add(user.getCurrentLocation());
                locationOverrideComboBoxSHH.getItems().add(user.getCurrentLocation());

                locationManComboBoxSHH.getSelectionModel().selectFirst();
                locationOverrideComboBoxSHH.getSelectionModel().selectFirst();
            }
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

        warningLabelSimulation.setText("Please log in as a user as\nwell as set the date, time,\nsimulation time speed,\ninside temperature, and\noutside temperature before\nstarting the simulation\nand accessing the modules.");
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
        zoneList = houseModel.getZoneList();

        loadUsersInSHSTable();

        houseViewController.drawLayout(bp, houseModel, userModelArrayList, isroomHVACManual);

        fillDefaultComboBox(true);

        userTable.setEditable(true);
        userTable.setItems(data);

        dateSHS.setValue(LocalDate.now());

        turnOnOffSimulation.setDisable(true);

        timeSHS.setValue(LocalTime.of(12, 0, 0));
        chosenTime = LocalTime.of(12, 0, 0);

        timerFrom.setValue(LocalTime.of(12, 0, 0));
        timerTo.setValue(LocalTime.of(12, 1, 0));

        printConsole = new PrintConsole();
    }


    /**
     * Open and close lights, windows, time, block/unblock windows
     *
     * @param setup lights, windows, time, block/unlock windows.
     */
    // Fill ComboBox of actions (open/close)
    private void fillDefaultComboBox(boolean setup) {

        if (!setup) { // We are just reload certain ComboBoxes because the user who just logged in has higher permissions
            lightComboBoxSHC.getItems().clear();
            lightComboBoxSHP.getItems().clear();
            winComboBoxSHC.getItems().clear();
            locationManComboBoxSHH.getItems().clear();
            locationOverrideComboBoxSHH.getItems().clear();

            for (String roomName : roomNamesSet) {
                winComboBoxSHC.getItems().add(roomName);
                lightComboBoxSHC.getItems().add(roomName);
                lightComboBoxSHP.getItems().add(roomName);
                locationManComboBoxSHH.getItems().add(roomName);
                locationOverrideComboBoxSHH.getItems().add(roomName);
            }

            lightComboBoxSHC.getItems().addAll("Backyard", "Front yard");
            lightComboBoxSHP.getItems().addAll("Backyard", "Front yard");

            winComboBoxSHC.getSelectionModel().selectFirst();
            lightComboBoxSHC.getSelectionModel().selectFirst();
            lightComboBoxSHP.getSelectionModel().selectFirst();
            locationManComboBoxSHH.getSelectionModel().selectFirst();
            locationOverrideComboBoxSHH.getSelectionModel().selectFirst();

        } else { // Initial setup

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
            month.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
            season.getItems().addAll("Winter", "Summer");

            zoneComboBoxSHH.getItems().addAll("Zone 1", "Zone 2", "Zone 3", "Zone 4");
            zoneTemperatureComboBox.getItems().addAll("Zone 1", "Zone 2", "Zone 3", "Zone 4");
            locationComboBoxSHH.getItems().addAll("Garage", "Kitchen", "Bedroom", "Living Room", "Children's Room");
            locationManComboBoxSHH.getItems().addAll("Garage", "Kitchen", "Bedroom", "Living Room", "Children's Room");
            locationOverrideComboBoxSHH.getItems().addAll("Garage", "Kitchen", "Bedroom", "Living Room", "Children's Room");
            periodComboBoxSHH.getItems().addAll("00:00 - 08:00", "08:00 - 16:00", "16:00 - 24:00");
            seasonComboBoxSHH.getItems().addAll("Summer", "Winter");


            addModifyLocComboBoxSHS.getSelectionModel().selectFirst();
            addModifyRoleComboBoxSHS.getSelectionModel().selectFirst();
            blockWinLocComboBoxSHS.getSelectionModel().selectFirst();
            doorComboBoxSHC.getSelectionModel().selectFirst();
            lockDoorComboBoxSHC.getSelectionModel().selectFirst();
            winComboBoxSHC.getSelectionModel().selectFirst();
            lightComboBoxSHC.getSelectionModel().selectFirst();
            lightComboBoxSHP.getSelectionModel().selectFirst();
            timeSpeedComboBoxSHS.getSelectionModel().select(3);
            zoneComboBoxSHH.getSelectionModel().selectFirst();
            zoneTemperatureComboBox.getSelectionModel().selectFirst();
            locationComboBoxSHH.getSelectionModel().selectFirst();
            locationManComboBoxSHH.getSelectionModel().selectFirst();
            locationOverrideComboBoxSHH.getSelectionModel().selectFirst();
            periodComboBoxSHH.getSelectionModel().selectFirst();
            seasonComboBoxSHH.getSelectionModel().selectFirst();
            season.getSelectionModel().selectFirst();
            month.getSelectionModel().selectFirst();
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

    /**
     * Sets auto mode.
     */
    @FXML
    public void setAutoMode() {
        shcController.setAutoMode(!shcController.isAutoMode());

        if (shcController.isAutoMode()) {
            turnOnOffAutomode.setText("Turn Off AutoMode");
            printConsole.setText("Turning on AutoMode.");
        } else {
            turnOnOffAutomode.setText("Turn On AutoMode");
            printConsole.setText("Turning off AutoMode.");
        }
    }

    /**
     * Open door.
     */
    @FXML
    public void openDoor() {
        String value = doorComboBoxSHC.getValue();
        shcController.openDoor(value, houseModel, printConsole);
        drawLayout();
    }

    /**
     * Close door.
     */
    @FXML
    public void closeDoor() {
        String value = doorComboBoxSHC.getValue();
        shcController.closeDoor(value, houseModel, printConsole);
        drawLayout();
    }

    /**
     * Lock door.
     */
    @FXML
    public void lockDoor() {
        String value = lockDoorComboBoxSHC.getValue();
        shcController.lockDoor(value, houseModel, printConsole);
        drawLayout();
    }

    /**
     * Un lock.
     */
    @FXML
    public void unLock() {
        String value = lockDoorComboBoxSHC.getValue();
        shcController.unLock(value, houseModel, printConsole);
        drawLayout();
    }

    /**
     * Open window.
     */
    @FXML
    void openWindow() {
        String value = winComboBoxSHC.getValue();
        shcController.openWindow(value, houseModel, printConsole);
        drawLayout();
    }

    /**
     * Close window.
     */
    @FXML
    void closeWindow() {
        String value = winComboBoxSHC.getValue();
        shcController.closeWindow(value, houseModel, printConsole);
        drawLayout();
    }

    @FXML
    void changeRoomTemperature() {
        String location = locationManComboBoxSHH.getValue();
        double temperature = temperatureManSpinnerSHH.getValue().doubleValue();
        RoomModel room = houseModel.getRooms().get(location);

        // If the simulation is running, update the HVAC target temperature manually live.
        if (running.get()) {
            roomHVACS.get(room).setTargetTemperature(temperature);
            roomHVACS.get(room).setRate(0.1);
            roomHVACS.get(room).setRounding(new DecimalFormat("#.#"));
            roomHVACS.get(room).setStateHVAC("Manual");
            printConsole.setText("Starting to cool/heat the " + room.getName() + " to a temperature of " + temperature + " C.");
        } else {
            printConsole.setText("Will start to cool/heat the " + room.getName() + " to a temperature of " + temperature + " C once the simulation restarts.");
        }

        // Regardless of whether or not the simulation is running, store the manual update which will be processed each time the simulation goes live (until the override is removed).
        requestsForHVACS.put(room, temperature);
    }

    /**
     * Open or close lights.
     *
     * @param event the event
     */
    @FXML
    void openOrCloseLights(ActionEvent event) {
        String value = lightComboBoxSHC.getValue();
        if (event != null && event.getSource().equals(turnOnLight)) {
            shcController.openOrCloseLights(value, true, "open", houseModel, printConsole, false);
            turnOnOffAutomode.setText("Turn On AutoMode");
        } else if (event != null && event.getSource().equals(turnOffLight)) {
            shcController.openOrCloseLights(value, true, "close", houseModel, printConsole, false);
            turnOnOffAutomode.setText("Turn On AutoMode");
        } else {
            UserModel user = ((UserModel) userInfo[1]);
            if (rooms.containsKey(user.getCurrentLocation())) {
                shcController.openOrCloseLights(user.getCurrentLocation(), false, "open", houseModel, printConsole, false);
            }

            if (rooms.containsKey(user.getPreviousLocation()) && rooms.get(user.getPreviousLocation()).getNbPeople() == 0) {
                shcController.openOrCloseLights(user.getPreviousLocation(), false, "close", houseModel, printConsole, false);
            }

        }

        drawLayout();
    }

    /**
     * Save time speed.
     *
     * @param event the event
     */
    public void saveTimeSpeed(ActionEvent event) {

        leftPanelTimeSpeed.setText("Simulation time speed: " + timeSpeedComboBoxSHS.getValue() + "x");

        AtomicInteger newSpeed = new AtomicInteger(((Double) (1000 / Double.parseDouble(timeSpeedComboBoxSHS.getValue()))).intValue());
        speed = newSpeed.get();

        saveSimulationConditions(event);
    }

    /**
     * Save light open.
     */
    @FXML
    public void saveLightOpen() {
        LocalTime to = timerTo.getValue();
        LocalTime from = timerFrom.getValue();

        if (to.compareTo(from) > 0) {
            keepLightsOn.put(lightComboBoxSHP.getValue(), new Pair<>(from, to));
            printConsole.setText("Scheduling for light in room " + lightComboBoxSHP.getValue() + " to be on between " + from + " and " + to + ".");
        } else {
            selectLightMessage.setVisible(true);
            selectLightMessage.setText("Lights: (The second time must be after the first one)");
            selectLightMessage.setTextFill(Color.RED);
            printConsole.setText("In order to schedule a light to remain on, the second time must be after the first one.");
        }
    }

    /**
     * Save countdown authority.
     */
    @FXML
    public void saveCountdownAuthority() {
        countdownMinutesLeft = timerMinuteAuthority.getValue();
        countdownSecondsLeft = timerSecondAuthority.getValue();
        printConsole.setText("Setting the waiting time until the authorities are called at " + countdownMinutesLeft + " min and " + countdownSecondsLeft + " sec.");
    }

    /**
     * Enter away mode.
     */
    @FXML
    public void enterAwayMode() {

        if (awayModeOn) {
            awayModeOn = false;
            awayButton.setText("Turn On Away Mode");
            printConsole.setText("Turning off Away Mode.");
            return;
        }

        awayButton.setText("Turn Off Away Mode");
        awayModeOn = true;
        printConsole.setText("Turning on Away Mode.");

        for (String windowName : houseModel.getWindows().keySet()) {
            shcController.closeWindow(windowName, houseModel, printConsole);
        }

        for (String doorName : houseModel.getDoors().keySet()) {
            shcController.lockDoor(doorName, houseModel, printConsole);
        }

        //shhController.changeZoneTemperatureToSeasonTemperature(leftPanelDate, houseModel, printConsole);

        drawLayout();
    }

    /**
     * Cancel alert.
     */
    public void cancelAlert() {

        if (!alertTriggered) {
            printConsole.setText("There is no alert to cancel.");
            return;
        }

        countdown.getAndSet(false);
        awayModeOn = false;
        alertTriggered = false;
        saveDurationAuth.setDisable(false);
        printConsole.setText("The alert has been canceled.");
        countDownAuthorities.setVisible(false);
        authoritiesCalledMessage.setText("The alert has been canceled.");
        callingAuthoritiesLabel.setVisible(false);
    }

    private synchronized void scheduleTurnOnOffLight(String location, String action) {
        shcController.openOrCloseLights(location, true, action, houseModel, printConsole, true);
        drawLayout();
    }


    private synchronized void changeTempPeriod(double temperature, String location, boolean isManual) {
        shhController.changeRoomTemperature(houseModel, printConsole, temperature, location);
        drawLayout();
    }

    @FXML
    public void setMonthToSeason() {
        String monthSelected = month.getValue();
        String seasonSelected = season.getValue();
        shsController.setMonthToSeason(houseModel, monthSelected, seasonSelected, printConsole);
    }

    @FXML
    public void setSeasonTemperature() {
        String season = seasonComboBoxSHH.getValue();
        Double temperature = temperatureSeasonSpinnerSHH.getValue();
        shhController.setSeasonTemperature(houseModel, printConsole, temperature, season);

        for (HVAC hvac : roomHVACS.values()) {
            if (season.equals("Summer") && hvac.getStateHVAC().equals("Away Mode Summer")) {
                hvac.setTargetTemperature(temperature);
                printConsole.setText("Any current and future heating/cooling to the default summer temperature will now heat/cool to this temperature.");
            } else if (season.equals("Winter") && hvac.getStateHVAC().equals("Away Mode Winter")) {
                hvac.setTargetTemperature(temperature);
                printConsole.setText("Any current and future heating/cooling to the default winter temperature will now heat/cool to this temperature.");
            }
        }
    }

    @FXML
    public void setRoomInZone() {
        String location = locationComboBoxSHH.getValue();
        String zone = zoneComboBoxSHH.getValue();
        shhController.setRoomInZone(houseModel, printConsole, zone, location);
    }

    @FXML
    public void setTemperatureZonePeriod() {
        String zone = zoneTemperatureComboBox.getValue();
        String period = periodComboBoxSHH.getValue();
        double temperature = temperaturePeriodSpinnerSHH.getValue().doubleValue();
        shhController.setTemperatureZonePeriod(houseModel, zone, period, temperature, printConsole);
    }

    @FXML
    public void turnOnOffHVAC() {
        // Flip text to off (by default, HVAC is on)

        if (saveHVAC.getText().equals("Turn Off HVAC")) {
            saveHVAC.setText("Turn On HVAC");
            printConsole.setText("The HVAC has been turned OFF! All heating/cooling has been stopped!");
        } else {
            saveHVAC.setText("Turn Off HVAC");
            printConsole.setText("The HVAC has been turned ON!");
        }

        // Check HVAC on or off.
        isHVACOn.set(saveHVAC.getText().equals("Turn Off HVAC"));
    }

    @FXML
    public void turnOffOverride() {
        String location = locationOverrideComboBoxSHH.getValue();

        RoomModel room = houseModel.getRooms().get(location);

        // If the simulation is running, update the HVAC target temperature manually live.
        if (running.get() && roomHVACS.get(room).getStateHVAC().equals("Manual")) {
            roomHVACS.get(room).setStateHVAC("Idle");
            roomHVACS.get(room).setTargetTemperature(room.getTemperature());
        }

        if (requestsForHVACS.containsKey(room)) {
            requestsForHVACS.remove(room);
            printConsole.setText("Turning off manual override for the " + room.getName() + ". The HVAC in that room is now in idle mode.");
            drawLayout();
        } else {
            printConsole.setText("The " + room.getName() + " is not currently in Manual Override Mode.");
        }
    }
}

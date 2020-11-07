package sample.SmartHomeController;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.SimulationData;
import sample.SmartHomeModel.UserModel;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Map;

/**
 * Class for the SHS Controller.
 */
public class SHSController {

    /**
     * The Simulation data.
     */
    private SimulationData simulationData;

    /**
     * The User model.
     */
    UserModel userModel;

    /**
     * The Logged user id.
     */
    private int loggedUserID = -1;

    PrintWriter printWriter;


    /**
     * Instantiates a new SHS Controller.
     */
    public SHSController() {
        simulationData = new SimulationData();
    }

    /**
     * Sets outside temperature.
     *
     * @param houseModel the house model
     * @param value      the value
     */
    void setOutsideTemperature(HouseModel houseModel, double value) {
        houseModel.setOutsideTemp(value);
    }

    /**
     * Sets inside temperature.
     *
     * @param rooms the rooms map
     * @param value the value
     */
    void setInsideTemperature(Map<String, RoomModel> rooms, double value) {
        rooms.forEach((name, room) -> {
            room.setTemperature(value);
        });
    }

    /**
     * Login object [ ].
     *
     * @param houseModel       the house model
     * @param id               the id
     * @param userList         the user list
     * @param consoleTextField the console text field
     * @return the object [ ]
     */
    Object[] login(HouseModel houseModel, int id, ArrayList<UserModel> userList, TextArea consoleTextField, String currentTime) {
        boolean userExist = false;
        Object[] userInfo = new Object[2];

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == (id)) {
                houseModel.setLoggedUserName(userList.get(i).getName());
                consoleTextField.setText("[" + currentTime + "]" + "You're logged in as " + userList.get(i).getName() + ".\n" + consoleTextField.getText());
                userExist = true;
                userInfo[1] = userList.get(i);
                loggedUserID = userList.get(i).getId();
            }
        }

        if (!userExist) {
            consoleTextField.setText("Try again. No user with ID " + id + " exists in the database.\n" + consoleTextField.getText());
        }

        userInfo[0] = userExist;

        return userInfo;
    }


    /**
     * Delete user profile (if user can be removed).
     *
     * @param userList         the user list
     * @param id               the id
     * @param consoleTextField the console text field
     * @param houseModel       the house model
     */
    void deleteUserProfile(ArrayList<UserModel> userList, int id, TextArea consoleTextField, HouseModel houseModel) {
        boolean userExist = false;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == (id)) {
                if (userList.get(i).getId() != loggedUserID) {
                    consoleTextField.setText("You removed " + userList.get(i).getName() + ".\n" + consoleTextField.getText());
                    userList.remove(i);
                } else {
                    consoleTextField.setText("You cannot remove yourself. First, make sure the simulation is stopped and then log out if you want to remove the user you're currently logged in as.\n" + consoleTextField.getText());
                }
                userExist = true;
            }
        }

        if (!userExist) {
            consoleTextField.setText("Try again. You cannot remove someone that doesn't exist in the database.\n" + consoleTextField.getText());
        }
    }

    /**
     * Add object to window (and print object blocking window).
     *
     * @param rooms            the room map (mapping each name of a room to the corresponding room object)
     * @param roomName         the room name
     * @param consoleTextField the console text field
     */
    void addObjectToWindow(Map<String, RoomModel> rooms, String roomName, TextArea consoleTextField) {
        rooms.forEach((name, room) -> {
            if (name.equals(roomName)) {
                if (room.getWindow().isOpen()) {
                    if (!room.getWindow().HasObject()) {
                        room.getWindow().setHasObject(true);
                        consoleTextField.setText("Adding object to block the window of the " + room.getName() + ".\n" + consoleTextField.getText());
                    } else {
                        room.getWindow().setHasObject(false);
                        consoleTextField.setText("Removing blocking object from the window of the " + room.getName() + ".\n" + consoleTextField.getText());
                    }
                } else {
                    consoleTextField.setText("You cannot add an object to this window. The window is closed.\n" + consoleTextField.getText());
                }
            }
        });
    }

    /**
     * Add/modify user object [ ].
     *
     * @param userList         the user list
     * @param id               the id
     * @param name             the name
     * @param userType         the user type
     * @param location         the location
     * @param consoleTextField the console text field
     * @return the object [ ]
     */
    Object[] addModifyUser(ArrayList<UserModel> userList, Map<String, RoomModel> rooms, int id, String name, String userType, String location, TextArea consoleTextField) {
        boolean userExist = false;
        Object[] userInfo = new Object[2];
        String previousLocation = "";
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == (id)) {
                userExist = true;
                userList.get(i).setName(name);
                userList.get(i).setUser_type(userType);

                if(!userList.get(i).getCurrentLocation().equals(location)){

                    if(( (location.equals("House"))|| (location.equals("Backyard")) || (location.equals("Front yard")) || (rooms.get(location).getDoor().isOpen() == true) ||
                            (rooms.get(location).getDoor().isOpen() == false && rooms.get(location).getDoor().isLocked() == false))){

                        previousLocation = userList.get(i).getCurrentLocation();

                        if (rooms.containsKey(location)) {
                            rooms.get(location).incrementNbPeople();
                        }

                        if (rooms.containsKey(previousLocation)) {
                            rooms.get(previousLocation).decrementNbPeople();
                        }

                        userList.get(i).setCurrentLocation(location);
                        userList.get(i).setPreviousLocation(previousLocation);

                    }
                    else if((rooms.get(location).getDoor().isOpen() == false && rooms.get(location).getDoor().isLocked() == true)){
                        consoleTextField.setText("Cannot move this user in " + location+ ". The door is locked.\n" + consoleTextField.getText());
                    }
                }
                else if (userList.get(i).getCurrentLocation().equals(location)){
                    consoleTextField.setText("User is already in " + location+ ".\n" + consoleTextField.getText());
                }


                userInfo[1] = userList.get(i);

                consoleTextField.setText("Modifying information for user with ID " + userList.get(i).getId() + ".\n" + consoleTextField.getText());
            }
        }

        if (!userExist) {
            UserModel user = new UserModel(name, id, userType, location);
            userList.add(user);
            consoleTextField.setText("Creating new user " + name + ".\n" + consoleTextField.getText());
        }

        userInfo[0] = userExist;

        return userInfo;
    }

    void saveUserProfiles(ArrayList<UserModel> userList, TextArea consoleTextField){
        try{

            printWriter = new PrintWriter("Profiles.txt", "UTF-8");

            for(UserModel userModel : userList){
                printWriter.println(userModel.getName() + "," + userModel.getId() + "," + userModel.getUser_type() + "," + userModel.getCurrentLocation());
            }
            consoleTextField.setText("Saving user profiles.\n" + consoleTextField.getText());

            printWriter.flush();
            printWriter.close();

        } catch (FileNotFoundException e){
            System.out.println("File does not exists.");
        }
        catch (UnsupportedEncodingException e){
            System.out.println("UnsupportedEncoding Error");
        }

    }
}

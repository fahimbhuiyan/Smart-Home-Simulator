package sample.SmartHomeController;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.SimulationData;
import sample.SmartHomeModel.UserModel;

import java.awt.*;
import java.sql.Struct;
import java.util.ArrayList;

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
    public void setOutsideTemperature(HouseModel houseModel, double value) {
        houseModel.setOutsideTemp(value);
    }

    /**
     * Sets inside temperature.
     *
     * @param roomArray the room array
     * @param value     the value
     */
    public void setInsideTemperature(RoomModel[] roomArray, double value) {
        for (int i = 0; i < roomArray.length; i++) {
            roomArray[i].setTemperature(value);
        }
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
    public Object[] login(HouseModel houseModel, int id, ArrayList<UserModel> userList, TextArea consoleTextField) {
        boolean userExist = false;
        Object[] userInfo = new Object[2];

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == (id)) {
                houseModel.setLoggedUserName(userList.get(i).getName());
                consoleTextField.setText("You're logged in as " + userList.get(i).getName() + ".\n" + consoleTextField.getText());
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
     * Delete user profile.
     *
     * @param userList         the user list
     * @param id               the id
     * @param consoleTextField the console text field
     * @param houseModel       the house model
     * @return if user can be removed
     */
    public void deleteUserProfile(ArrayList<UserModel> userList, int id, TextArea consoleTextField, HouseModel houseModel) {
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
     * Add object to window.
     *
     * @param roomArray        the room array
     * @param roomName         the room name
     * @param consoleTextField the console text field
     * @return print object blocking window
     */
    public void addObjectToWindow(RoomModel[] roomArray, String roomName, TextArea consoleTextField) {
        for (int i = 0; i < roomArray.length; i++) {
            if (roomArray[i].getName().equals(roomName)) {

                if (roomArray[i].getWindow().isOpen()) {

                    if (!roomArray[i].getWindow().HasObject()) {
                        roomArray[i].getWindow().setHasObject(true);
                        consoleTextField.setText("Adding object to block the window of the " + roomArray[i].getName() + ".\n" + consoleTextField.getText());
                    }
                    else {
                        roomArray[i].getWindow().setHasObject(false);
                        consoleTextField.setText("Removing blocking object from the window of the " + roomArray[i].getName() + ".\n" + consoleTextField.getText());
                    }


                } else {
                    consoleTextField.setText("You cannot add an object to this window. The window is closed.\n" + consoleTextField.getText());
                }
            }
        }
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
    public Object[] addModifyUser(ArrayList<UserModel> userList, int id, String name, String userType, String location, TextArea consoleTextField) {
        boolean userExist = false;
        Object[] userInfo = new Object[2];
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == (id)) {
                userExist = true;
                userList.get(i).setName(name);
                userList.get(i).setUser_type(userType);
                userList.get(i).setLocation(location);

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
}

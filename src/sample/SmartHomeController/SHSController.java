package sample.SmartHomeController;

import sample.Interfaces.Observer;
import sample.Interfaces.Subject;
import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.SimulationData;
import sample.SmartHomeModel.UserModel;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class for the SHS Controller.
 */
public class SHSController implements Subject {

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

    private PrintWriter printWriter;

    private List<Observer> observerList;

    private Object state;


    /**
     * Instantiates a new SHS Controller.
     */
    public SHSController() {
        simulationData = new SimulationData();
        observerList = new ArrayList<>();
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
     * @param printConsole     the console used for printing
     * @return the object [ ]
     */
    Object[] login(HouseModel houseModel, int id, ArrayList<UserModel> userList, MainViewController.PrintConsole printConsole) {
        boolean userExist = false;
        Object[] userInfo = new Object[2];

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == (id)) {
                houseModel.setLoggedUserName(userList.get(i).getName());
                printConsole.setText("You're logged in as " + userList.get(i).getName() + ".");
                userExist = true;
                userInfo[1] = userList.get(i);
                loggedUserID = userList.get(i).getId();
            }
        }

        if (!userExist) {
            printConsole.setText("Try again. No user with ID " + id + " exists in the database.");
        }

        userInfo[0] = userExist;

        return userInfo;
    }


    /**
     * Delete user profile (if user can be removed).
     *
     * @param userList         the user list
     * @param id               the id
     * @param printConsole     the console object used for printing
     */
    void deleteUserProfile(ArrayList<UserModel> userList, int id, MainViewController.PrintConsole printConsole) {
        boolean userExist = false;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == (id)) {
                if (userList.get(i).getId() != loggedUserID) {
                    printConsole.setText("You removed " + userList.get(i).getName() + ".");
                    userList.remove(i);
                } else {
                    printConsole.setText("You cannot remove yourself. First, make sure the simulation is stopped and then log out if you want to remove the user you're currently logged in as.");
                }
                userExist = true;
            }
        }

        if (!userExist) {
            printConsole.setText("Try again. You cannot remove someone that doesn't exist in the database.");
        }
    }

    /**
     * Add object to window (and print object blocking window).
     *
     * @param rooms            the room map (mapping each name of a room to the corresponding room object)
     * @param roomName         the room name
     * @param consoleTextField the console text field
     */
    void addObjectToWindow(Map<String, RoomModel> rooms, String roomName, MainViewController.PrintConsole consoleTextField) {
        rooms.forEach((name, room) -> {
            if (name.equals(roomName)) {
                if (room.getWindow().isOpen()) {
                    if (!room.getWindow().HasObject()) {
                        room.getWindow().setHasObject(true);
                        consoleTextField.setText("Adding object to block the window of the " + room.getName() + ".");
                    } else {
                        room.getWindow().setHasObject(false);
                        consoleTextField.setText("Removing blocking object from the window of the " + room.getName() + ".");
                    }
                } else {
                    consoleTextField.setText("You cannot add an object to this window. The window is closed.\n" + ".");
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
     * @param printConsole     the console used to print
     * @return the object [ ]
     */
    Object[] addModifyUser(ArrayList<UserModel> userList, Map<String, RoomModel> rooms, int id, String name, String userType, String location, MainViewController.PrintConsole printConsole) {
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
                        printConsole.setText("Cannot move this user in " + location+ ". The door is locked.");
                    }
                }
                else if (userList.get(i).getCurrentLocation().equals(location)){
                    printConsole.setText("User is already in " + location+ ".");
                }


                userInfo[1] = userList.get(i);

                printConsole.setText("Modifying information for user with ID " + userList.get(i).getId() + ".");
            }
        }

        if (!userExist) {
            UserModel user = new UserModel(name, id, userType, location);
            userList.add(user);
            printConsole.setText("Creating new user " + name + ".");
        }

        userInfo[0] = userExist;

        return userInfo;
    }

    void saveUserProfiles(ArrayList<UserModel> userList, MainViewController.PrintConsole printConsole){
        try{

            printWriter = new PrintWriter("Profiles.txt", "UTF-8");

            for(UserModel userModel : userList){
                printWriter.println(userModel.getName() + "," + userModel.getId() + "," + userModel.getUser_type() + "," + userModel.getCurrentLocation());
            }
            printConsole.setText("Saving user profiles.");

            printWriter.flush();
            printWriter.close();

        } catch (FileNotFoundException e){
            System.out.println("File does not exists.");
        }
        catch (UnsupportedEncodingException e){
            System.out.println("UnsupportedEncoding Error");
        }

    }

    @Override
    public void register(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        List<Observer> observerListLocal = new ArrayList<>(observerList);

        for (Observer observer : observerListLocal) {
            observer.update(state);
        }
    }
}

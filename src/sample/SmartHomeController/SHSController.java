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

public class SHSController {

    SimulationData simulationData;
    UserModel userModel;


    public SHSController(){
        simulationData = new SimulationData();
    }

    public void setOutsideTemperature(HouseModel houseModel, double value){

            houseModel.setOutsideTemp(value);

    }

    public void setInsideTemperature(RoomModel[] roomArray, double value){
        for(int i = 0; i < roomArray.length; i ++){
            roomArray[i].setTemperature(value);
        }

    }

    public void login(HouseModel houseModel, int id, ArrayList<UserModel> userList, TextArea consoleTextField){

        boolean userExist = false;
        for (int i = 0; i < userList.size(); i++){
            if (userList.get(i).getId() == (id)){
                houseModel.setLoggedUserName(userList.get(i).getName());
                consoleTextField.setText("You're logged in as " + userList.get(i).getName() + ".\n" + consoleTextField.getText());
                userExist = true;
            }
        }

        if (!userExist){
            consoleTextField.setText("Try again. No user with id " + id + " exists in the database.\n" + consoleTextField.getText());
        }

    }


    public void deleteUserProfile(ArrayList<UserModel> userList, int id, TextArea consoleTextField, HouseModel houseModel) {
        boolean userExist = false;
        for (int i = 0; i < userList.size(); i++){
            if (userList.get(i).getId() == (id)){
                if(!userList.get(i).getName().equals(houseModel.getLoggedUserName())){
                    consoleTextField.setText("You removed " + userList.get(i).getName() + ".\n" + consoleTextField.getText());
                    userList.remove(i);

                }
                else {
                    consoleTextField.setText("You cannot removed yourself. Log out first.\n" + consoleTextField.getText());
                }
                userExist = true;
            }
        }

        if (!userExist){
            consoleTextField.setText("Try again. You cannot remove someone that doesn't exist in the database.\n" + consoleTextField.getText());
        }
    }

    public void addObjectToWindow(RoomModel[] roomArray, String roomName, TextArea consoleTextField){
        for (int i = 0; i < roomArray.length; i++){
            if (roomArray[i].getName().equals(roomName)){

                if(roomArray[i].getWindow().isOpen() == true){
                    roomArray[i].getWindow().setHasObject(true);
                    consoleTextField.setText("Adding object to block the window of the " + roomArray[i].getName() + ".\n" + consoleTextField.getText());
                }else{
                    consoleTextField.setText("You cannot add an object to this window. The window is closed.\n" + consoleTextField.getText());
                }
            }
        }
    }

    public void addPeopleInRoom(RoomModel[] roomArray, String roomName, String userNameToMove, ArrayList<UserModel> userModelArrayList) {
        for (int i = 0; i < roomArray.length; i++){
            if (roomArray[i].getName().equals(roomName)){
                for (int j = 0; j < userModelArrayList.size(); j++){
                    if (userModelArrayList.get(j).getName().equals(userNameToMove)){
                        roomArray[i].getListPeopleInRoom().add(userModelArrayList.get(j));
                        userModelArrayList.get(j).setLocation(roomName);
                    }
                }
            }
        }

    }

    public void addModifyUser(ArrayList<UserModel> userList,int id, String name, String userType, String location, TextArea consoleTextField){
        boolean userExist = false;

        for (int i = 0; i < userList.size(); i++){
            if (userList.get(i).getId() == (id)){
               userExist = true;
               userList.get(i).setName(name);
               userList.get(i).setUser_type(userType);
               userList.get(i).setLocation(location);

               consoleTextField.setText("Modifying information for user with id " + userList.get(i).getId() + ".\n" + consoleTextField.getText());
            }

        }

        if(!userExist){
            UserModel user = new UserModel(name, id, userType, location);
            userList.add(user);
            consoleTextField.setText("Creating new user " + name + ".\n" + consoleTextField.getText());
        }
    }
}

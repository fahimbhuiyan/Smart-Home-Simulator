package sample.SmartHomeController;

import javafx.fxml.FXML;
import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.SimulationData;
import sample.SmartHomeModel.UserModel;

import java.sql.Struct;
import java.util.ArrayList;

public class SHSController {

    SimulationData simulationData;
    public SHSController(){
        simulationData = new SimulationData();
    }

    public void setOutsideTemperature(HouseModel houseModel, String value){

            houseModel.setOutsideTemp(Integer.parseInt(value));

    }

    public void setInsideTemperature(RoomModel[] roomArray, String value){
        for(int i = 0; i < roomArray.length; i ++){
            roomArray[i].setTemperature(Integer.parseInt(value));
        }

    }

    public void createUserProfile(ArrayList<UserModel> userList, String name, String userType){
        UserModel user = new UserModel(name, simulationData.generateId(), userType);
        userList.add(user);
    }

    public void deleteUserProfile(ArrayList<UserModel> userList, String name){
        for (int i = 0; i < userList.size(); i++){
            if (userList.get(i).getName().equals(name)){
                userList.remove(i);
            }
        }

    }

    public void addObjectToWindow(RoomModel[] roomArray, String roomName){
        for (int i = 0; i < roomArray.length; i++){
            if (roomArray[i].getName().equals(roomName)){
                roomArray[i].getWindow().setHasObject(true);
            }
        }
    }

//    public void addPeopleInRoom(RoomModel[] roomArray, String roomName) {
//        for (int i = 0; i < roomArray.length; i++){
//            if (roomArray[i].getName().equals(roomName)){
//
//            }
//        }
//
//    }
}

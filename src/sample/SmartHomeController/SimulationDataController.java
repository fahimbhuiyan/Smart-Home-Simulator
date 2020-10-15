package sample.SmartHomeController;

import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.SimulationData;
import sample.SmartHomeModel.UserModel;

import java.util.ArrayList;

public class SimulationDataController {

    SimulationData smd = new SimulationData();

    public void loadData(){

        smd.createData("HouseInfo.json");
    }

    public RoomModel[] getRoomArray(){
        return smd.getRoomArray();
    }

    public ArrayList<UserModel> getUserArrayList() {
        return smd.getUserArrayList();
    }
}

package sample.SmartHomeController;

import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.SimulationData;
import sample.SmartHomeModel.UserModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SimulationDataController {

    SimulationData smd = new SimulationData();

    public void loadData(){

        try {
            smd.createData("HouseInfo.json");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public RoomModel[] getRoomArray(){
        return smd.getRoomArray();
    }

    public ArrayList<String> getRoomNameList() {
        return smd.getRoomNameList();
    }

    public ArrayList<UserModel> getUserArrayList() {
        return smd.getUserArrayList();
    }

    public HouseModel getHouseModel(){
        return  smd.getHouseModel();
    }
}

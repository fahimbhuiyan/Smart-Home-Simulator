package sample.SmartHomeController;

import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.SimulationData;

public class SimulationDataController {

    SimulationData smd = new SimulationData();

    public void loadData(){

        smd.createData("HouseInfo.json");
    }

    public RoomModel[] getRoomArray(){
        return smd.getRoomArray();
    }
}

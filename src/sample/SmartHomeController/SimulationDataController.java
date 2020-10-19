package sample.SmartHomeController;

import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.SimulationData;
import sample.SmartHomeModel.UserModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class for the Simulation Data Controller.
 */
public class SimulationDataController {

    /**
     * The Simulation Data.
     */
    SimulationData smd = new SimulationData();

    /**
     * Load data from json file
     */
    public void loadData(){

        try {
            smd.createData("HouseInfo.json");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

    /**
     * Get room array room model [ ].
     *
     * @return the room model [ ]
     */
    public RoomModel[] getRoomArray(){
        return smd.getRoomArray();
    }

    /**
     * Gets room name list.
     *
     * @return the room name list
     */
    public ArrayList<String> getRoomNameList() {
        return smd.getRoomNameList();
    }

    /**
     * Gets user array list.
     *
     * @return the user array list
     */
    public ArrayList<UserModel> getUserArrayList() {
        return smd.getUserArrayList();
    }

    /**
     * Get house model house model.
     *
     * @return the house model
     */
    public HouseModel getHouseModel(){
        return  smd.getHouseModel();
    }
}

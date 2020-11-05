package sample.SmartHomeController;

import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.SimulationData;
import sample.SmartHomeModel.UserModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Class for the Simulation Data Controller.
 */
public class SimulationDataController {

    /**
     * The Simulation Data.
     */
    private SimulationData smd = new SimulationData();

    /**
     * Load data from json file
     *
     * @param path a String which specifies the file path of the JSON house layout file.
     */
    void loadData(String path){
        try {
            smd.createData(path);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Gets user array list.
     *
     * @return the user array list
     */
    ArrayList<UserModel> getUserArrayList() {
        return smd.getUserArrayList();
    }

    /**
     * Get house model house model.
     *
     * @return the house model
     */
    HouseModel getHouseModel(){
        return  smd.getHouseModel();
    }
}

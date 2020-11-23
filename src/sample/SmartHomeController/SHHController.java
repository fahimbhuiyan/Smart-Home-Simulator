package sample.SmartHomeController;

import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.UserModel;

import java.util.ArrayList;

/**
 * Class for the SHH Controller.
 */
public class SHHController {

    public void changeRoomTemperature(HouseModel houseModel, MainViewController.PrintConsole printConsole, String temperature, String location){
        houseModel.getRooms().get(location).setTemperature(Double.parseDouble(temperature));
        printConsole.setText("Updating the temperature for the " + location + ".");
    }
}

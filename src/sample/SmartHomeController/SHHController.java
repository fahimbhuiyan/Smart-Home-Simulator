package sample.SmartHomeController;

import javafx.scene.control.Label;
import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.UserModel;
import sample.SmartHomeModel.Zone;

import java.util.ArrayList;
import java.util.Map;

/**
 * Class for the SHH Controller.
 */
public class SHHController {

    public void changeRoomTemperature(HouseModel houseModel, MainViewController.PrintConsole printConsole, double temperature, String location){
        houseModel.getRooms().get(location).setTemperature(temperature);
    }

    public void setSeasonTemperature(HouseModel houseModel, MainViewController.PrintConsole printConsole, Double temperature, String season){
        if(season.equals("Summer")){
            houseModel.setSummerTemperature(temperature);
            printConsole.setText("Default temperature for Summer is set to " + temperature + " °C.");
        }
        if(season.equals("Winter")){
            houseModel.setWinterTemperature(temperature);
            printConsole.setText("Default temperature for Winter is set to " + temperature + " °C.");
        }
    }

    public void setRoomInZone(HouseModel houseModel, MainViewController.PrintConsole printConsole, String zone, String location){
        houseModel.getRooms().get(location).setZone(zone);

        houseModel.getZoneList().get(zone).addRomeToList(houseModel.getRooms().get(location));

        printConsole.setText("Adding " + location + " to " +zone);
    }

    public void changeZoneTemperatureToSeasonTemperature(Label date, HouseModel houseModel, MainViewController.PrintConsole printConsole){
        String [] dateArray  = date.toString().split("-");
        String month = dateArray[1];
        boolean hasRoomInZone = false;

        if(houseModel.getSummerMonthList().contains(month) == true){
            for (Map.Entry<String, RoomModel> entry : houseModel.getRooms().entrySet()) {
                if(!entry.getValue().getZone().equals("None")){
                    hasRoomInZone = true;
                    entry.getValue().setTemperature(houseModel.getSummerTemperature());
                }
            }
            if(hasRoomInZone == true){
                printConsole.setText("Changing all rooms in zone to the default Summer Temperature of " + houseModel.getSummerTemperature());
            }
            if(hasRoomInZone == false){
                printConsole.setText("No changes in room temperature. All your zones are empty.");
            }
        }
        else if(houseModel.getWinterMonthList().contains(month) == true){
            for (Map.Entry<String, RoomModel> entry : houseModel.getRooms().entrySet()) {
                if(!entry.getValue().getZone().equals("None")){
                    hasRoomInZone = true;
                    entry.getValue().setTemperature(houseModel.getWinterTemperature());
                }
            }
            if(hasRoomInZone == true){
                printConsole.setText("Changing all rooms in zone to the default Winter Temperature of " + houseModel.getWinterTemperature());
            }
            if(hasRoomInZone == false){
                printConsole.setText("No changes in room temperature. All your zones are empty.");
            }
        }
        else{
            printConsole.setText("No changes in room temperature. This month is not linked to a season.");
        }
    }

    //to do
    public void setTemperatureZonePeriod(HouseModel houseModel, String zone, String period, double temperature, MainViewController.PrintConsole printConsole){

        if(period.equals("00:00 - 08:00")){
            houseModel.getZoneList().get(zone).setNightTemp(temperature);
            printConsole.setText(zone + " temperature is set to " + temperature + " °C for the 00:00 to 08:00 period.");
        }
        else if(period.equals("08:00 - 16:00")){
            houseModel.getZoneList().get(zone).setDayTemp(temperature);
            printConsole.setText(zone + " temperature is set to " + temperature + " °C for the 08:00 to 16:00 period.");
        }
        else if(period.equals("16:00 - 24:00")){
            houseModel.getZoneList().get(zone).setEveningTemp(temperature);
            printConsole.setText(zone + " temperature is set to " + temperature + " °C for the 16:00 to 24:00 period.");
        }
    }
}

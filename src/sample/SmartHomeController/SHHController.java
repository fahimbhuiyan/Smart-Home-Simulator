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

    /**
     * Change room temperature.
     *
     * @param houseModel   the house model
     * @param printConsole the print console
     * @param temperature  the temperature
     * @param location     the location
     */
    public void changeRoomTemperature(HouseModel houseModel, MainViewController.PrintConsole printConsole, double temperature, String location){
        houseModel.getRooms().get(location).setTemperature(temperature);
    }

    /**
     * Set season temperature.
     *
     * @param houseModel   the house model
     * @param printConsole the print console
     * @param temperature  the temperature
     * @param season       the season
     */
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

    /**
     * Set room in zone.
     *
     * @param houseModel   the house model
     * @param printConsole the print console
     * @param zone         the zone
     * @param location     the location
     */
    public void setRoomInZone(HouseModel houseModel, MainViewController.PrintConsole printConsole, String zone, String location){
        houseModel.getRooms().get(location).setZone(houseModel.getZoneList().get(zone));

        houseModel.getZoneList().forEach((zoneName, zoneItem) -> {

            if (zoneItem.getRooms().contains(houseModel.getRooms().get(location))) {
                zoneItem.getRooms().remove(houseModel.getRooms().get(location));
                printConsole.setText("Removing " + location + " from " + zoneName);
            }

            if (zone.equals(zoneName)) {
                zoneItem.addRomeToList(houseModel.getRooms().get(location));
            }
        });

        printConsole.setText("Adding " + location + " to " + zone);
    }


    /**
     * Set temperature zone period.
     *
     * @param houseModel   the house model
     * @param zone         the zone
     * @param period       the period
     * @param temperature  the temperature
     * @param printConsole the print console
     */
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

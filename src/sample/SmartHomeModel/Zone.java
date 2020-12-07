package sample.SmartHomeModel;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Zone {

    private String zoneName = "";
    private double nightTemp;
    private double dayTemp;
    private double eveningTemp;
    private List<RoomModel> rooms = new ArrayList<>();
    private volatile boolean unsetnightTemp = true;
    private volatile boolean unsetdayTemp = true;
    private volatile boolean unseteveningTemp = true;


    /**
     * Zone constructor
     *
     * @param zoneName name of the zone
     * @param outsideTemp outside temperature
     */
    public Zone(String zoneName, double outsideTemp){
        this.zoneName = zoneName;
        this.dayTemp = outsideTemp;
        this.eveningTemp = outsideTemp;
        this.nightTemp = outsideTemp;
    }

    /**
     * Add a room to list
     *
     * @param room the room
     */
    public void addRomeToList (RoomModel room) {
        rooms.add(room);
    }

    /**
     * Getter for the list of rooms
     *
     * @return the rooms
     */
    public List<RoomModel> getRooms () {
        return rooms;
    }

    /**
     * Getter for the zone name
     * @return the name of the zone
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * Getter for the day temperature
     * @return the day temperature
     */
    public double getDayTemp() {
        return dayTemp;
    }

    /**
     * Setter for the day temperature
     * @param dayTemp the day temperature
     */
    public void setDayTemp(double dayTemp) {
        this.dayTemp = dayTemp;
        unsetdayTemp = false;
    }

    /**
     * Getter for the evening temperature
     * @return the evening temperature
     */
    public double getEveningTemp() {
        return eveningTemp;
    }

    /**
     * Setter for the evening temperature
     * @param eveningTemp the evening temperature
     */
    public void setEveningTemp(double eveningTemp) {
        this.eveningTemp = eveningTemp;
        unseteveningTemp = false;
    }

    /**
     * Getter for the night temperature
     * @return the night temperature
     */
    public double getNightTemp() {
        return nightTemp;
    }

    /**
     * Setter for the night temperature
     * @param nightTemp the night temperature
     */
    public void setNightTemp(double nightTemp) {
        this.nightTemp = nightTemp;
        unsetnightTemp = false;
    }

    /**
     * Boolean for if the night temperature is set
     * @return unsetnightTemp
     */
    public boolean isUnsetNightTemp () {
        return unsetnightTemp;
    }

    /**
     * Boolean for if the day temperature is set
     * @return unsetdayTemp
     */
    public boolean isUnsetDayTemp () {
        return unsetdayTemp;
    }

    /**
     * Boolean for if the evening temperature is set
     * @return unseteveningTemp
     */
    public boolean isUnsetEveningTemp () {
        return unseteveningTemp;
    }
}

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


    public Zone(String zoneName, double outsideTemp){
        this.zoneName = zoneName;
        this.dayTemp = outsideTemp;
        this.eveningTemp = outsideTemp;
        this.nightTemp = outsideTemp;
    }

    public void addRomeToList (RoomModel room) {
        rooms.add(room);
    }

    public List<RoomModel> getRooms () {
        return rooms;
    }

    public String getZoneName() {
        return zoneName;
    }

    public double getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(double dayTemp) {
        this.dayTemp = dayTemp;
        unsetdayTemp = false;
    }

    public double getEveningTemp() {
        return eveningTemp;
    }

    public void setEveningTemp(double eveningTemp) {
        this.eveningTemp = eveningTemp;
        unseteveningTemp = false;
    }

    public double getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(double nightTemp) {
        this.nightTemp = nightTemp;
        unsetnightTemp = false;
    }

    public boolean isUnsetNightTemp () {
        return unsetnightTemp;
    }

    public boolean isUnsetDayTemp () {
        return unsetdayTemp;
    }

    public boolean isUnsetEveningTemp () {
        return unseteveningTemp;
    }
}

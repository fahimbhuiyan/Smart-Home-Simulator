package sample.SmartHomeModel;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Zone {

    private String zoneName = "";
    private String dayTemp = "";
    private String eveningTemp = "";
    private String nightTemp = "";

    public Zone(String zoneName){
        this.zoneName = zoneName;
        this.dayTemp = "empty";
        this.eveningTemp = "empty";
        this.nightTemp = "empty";
    }

    public String getZoneName() {
        return zoneName;
    }

    public String getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(String dayTemp) {
        this.dayTemp = dayTemp;
    }

    public String getEveningTemp() {
        return eveningTemp;
    }

    public void setEveningTemp(String eveningTemp) {
        this.eveningTemp = eveningTemp;
    }

    public String getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(String nightTemp) {
        this.nightTemp = nightTemp;
    }
}

package sample.SmartHomeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for the House model.
 */
public class HouseModel {

    private double outsideTemp;
    private String loggedUserName;
    private Map<String, RoomModel> rooms;
    private Map<String, LightModel> lights;
    private Map<String, DoorModel> doors;
    private Map<String, WindowModel> windows;

    private int width;
    private int height;
    private int xAxis;
    private int yAxis;

    private double winterTemperature = 0.0;
    private double summerTemperature = 0.0;

    ArrayList<String> summer;
    ArrayList<String> winter;
    Map<String, Zone> zoneList;

    private volatile boolean unsetSummerTemp = true;
    private volatile boolean unsetWinterTemp = true;

    /**
     * Instantiates a new House model.
     *
     * @param outsideTemp    the outside temp
     * @param loggedUserName the logged user name
     * @param rooms          the rooms
     * @param lights         the lights
     * @param doors          the doors
     * @param windows        the windows
     * @param width          the width
     * @param height         the height
     * @param xAxis          the x axis
     * @param yAxis          the y axis
     */
    public HouseModel(int outsideTemp, String loggedUserName, Map<String, RoomModel> rooms, Map<String, LightModel> lights, Map<String, DoorModel> doors, Map<String, WindowModel> windows, int width, int height, int xAxis, int yAxis) {
        this.outsideTemp = outsideTemp;
        this.loggedUserName = loggedUserName;
        this.rooms = rooms;
        this.lights = lights;
        this.doors = doors;
        this.windows = windows;
        this.width = width;
        this.height = height;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        summer = new ArrayList<>();
        winter = new ArrayList<>();
        zoneList = new HashMap<>();
        createZone();
    }

    private void createZone(){
        Zone zone1 = new Zone("Zone 1", outsideTemp);
        Zone zone2 = new Zone("Zone 2", outsideTemp);
        Zone zone3 = new Zone("Zone 3", outsideTemp);
        Zone zone4 = new Zone("Zone 4", outsideTemp);

        zoneList.put(zone1.getZoneName(), zone1);
        zoneList.put(zone2.getZoneName(), zone2);
        zoneList.put(zone3.getZoneName(), zone3);
        zoneList.put(zone4.getZoneName(), zone4);

    }

    public Map<String, Zone> getZoneList(){
        return zoneList;
    }

    public double getWinterTemperature() {
        return winterTemperature;
    }

    public void setWinterTemperature(double winterTemperature) {
        this.winterTemperature = winterTemperature;
        unsetWinterTemp = false;
    }

    public double getSummerTemperature() {
        return summerTemperature;
    }

    public void setSummerTemperature(double summerTemperature) {
        this.summerTemperature = summerTemperature;
        unsetSummerTemp = false;
    }

    public ArrayList<String> getSummerMonthList() {
        return summer;
    }

    public void setSummer(ArrayList<String> summer) {
        this.summer = summer;
    }

    public ArrayList<String> getWinterMonthList() {
        return winter;
    }

    public void setWinter(ArrayList<String> winter) {
        this.winter = winter;
    }

    /**
     * Gets outside temp.
     *
     * @return the outside temp
     */
    public double getOutsideTemp() {
        return outsideTemp;
    }

    /**
     * Sets outside temp.
     *
     * @param outsideTemp the outside temp
     */
    public void setOutsideTemp(double outsideTemp) {
        this.outsideTemp = outsideTemp;
    }

    /**
     * Gets logged user name.
     *
     * @return the logged user name
     */
    String getLoggedUserName() {
        return loggedUserName;
    }

    /**
     * Sets logged user name.
     *
     * @param loggedUserName the logged user name
     */
    public void setLoggedUserName(String loggedUserName) {
        this.loggedUserName = loggedUserName;
    }

    /**
     * Returns the lights of the house.
     *
     * @return a Map which maps the name of the light to the light object.
     */
    public Map<String, LightModel> getLights() {return lights;}

    /**
     * Returns the doors of the house.
     *
     * @return a Map which maps the name of the door to the door object.
     */
    public Map<String, DoorModel> getDoors() {return doors;}

    /**
     * Returns the windows of the house.
     *
     * @return a Map which maps the name of the window to the window object.
     */
    public Map<String, WindowModel> getWindows() {return windows;}

    /**
     * Returns the rooms of the house rooms.
     *
     * @return a Map which maps the name of the room to the room object.
     */
    public Map<String, RoomModel> getRooms() {return rooms;}

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {return height;}

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {return width;}

    /**
     * Gets axis.
     *
     * @return the axis
     */
    public int getxAxis() {return xAxis;}

    /**
     * Gets axis.
     *
     * @return the axis
     */
    public int getyAxis() {return yAxis;}

    public boolean isUnsetSummerTemp () {
        return unsetSummerTemp;
    }

    public boolean isUnsetWinterTemp () {
        return unsetWinterTemp;
    }
}

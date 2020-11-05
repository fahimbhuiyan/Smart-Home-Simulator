package sample.SmartHomeModel;

import java.util.Map;

/**
 * Class for the House model.
 */
public class HouseModel {

    private double outsideTemp = 0;
    private String loggedUserName = "";
    private Map<String, RoomModel> rooms;
    private Map<String, LightModel> lights;
    private Map<String, DoorModel> doors;
    private Map<String, WindowModel> windows;

    private int width = 0;
    private int height = 0;
    private int xAxis = 0;
    private int yAxis = 0;

    /**
     * Instantiates a new House model.
     *
     * @param outsideTemp      the outside temp
     * @param loggedUserName   the logged user name
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

    public int getHeight() {return height;}

    public int getWidth() {return width;}

    public int getxAxis() {return xAxis;}

    public int getyAxis() {return yAxis;}
}

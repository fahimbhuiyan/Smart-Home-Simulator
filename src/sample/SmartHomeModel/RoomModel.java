package sample.SmartHomeModel;

/**
 * Class for the Room Model.
 */
public class RoomModel {
	
	private String roomID;
	private String name;
	private double temperature = 0;
	private int nbPeople = 0;

	private DoorModel door;
	private LightModel light;
	private WindowModel window;

	private int width = 0;
	private int height = 0;
	private int xAxis = 0;
	private int yAxis = 0;

	private Zone zone;

	/**
	 * Getter for the width of the room.
	 *
	 * @return the int width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Setter for the width of the room.
	 *
	 * @param width the width to set the room to.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Getter for the height of the room.
	 *
	 * @return the int height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Setter for the height of the room.
	 *
	 * @param height the height to set the room to.
	 */
	public void setHeight(int height) {
		this.height = height;
	}


	/**
	 * Getter for the x-axis of the room.
	 *
	 * @return the int x-axis
	 */
	public int getxAxis() {
		return xAxis;
	}

	/**
	 * Setter for the x-axis of the room.
	 *
	 * @param xAxis the x-axis to set the room to.
	 */
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}

	/**
	 * Getter for the y-axis of the room.
	 *
	 * @return the int y-axis
	 */
	public int getyAxis() {
		return yAxis;
	}

	/**
	 * Setter for the y-axis of the room.
	 *
	 * @param yAxis the y-axis to set the room to.
	 */
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}

	/**
	 * Instantiates a new Room model.
	 *
	 * @param roomID the ID of the room.
	 * @param name   the name of the room.
	 * @param width  the width of the room.
	 * @param height the height of the room.
	 * @param xAxis  the x-axis of the room.
	 * @param yAxis  the y-axis of the room.
	 * @param door   the door of the room.
	 * @param light  the light of the room.
	 * @param window the window of the room.
	 */
	public RoomModel(String roomID, String name, int width, int height, int xAxis, int yAxis, DoorModel door, LightModel light, WindowModel window) {
		this.roomID = roomID;
		this.name = name;
		this.width = width;
		this.height = height;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.door = door;
		this.light = light;
		this.window = window;
		this.zone = null;
	}

	/**
	 * Get the zone
	 *
	 * @return the zone
	 */
	public Zone getZone() {
		return zone;
	}

	/**
	 * Set the zone
	 *
	 * @param zone the zone
	 */
	public void setZone(Zone zone) {
		this.zone = zone;
	}

	/**
	 * Getter for the ID of the room.
	 *
	 * @return the String roomID
	 */
	String getRoomID() {
		return roomID;
	}

	/**
	 * Setter for the ID of the room.
	 *
	 * @param roomID the ID of the room being set.
	 */
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	/**
	 * Getter for the name of the room.
	 *
	 * @return the String name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the name of the room.
	 *
	 * @param name the name of the room being set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the temperature of the room.
	 *
	 * @return the double temperature.
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * Setter for the temperature of the room.
	 *
	 * @param temperature the temperature of the room.
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/**
	 * Getter for the number of people of the room.
	 *
	 * @return the int nbPeople.
	 */
	public int getNbPeople() {
		return nbPeople;
	}

	/**
	 * Increment the number of people of the room.
	 */
	public void incrementNbPeople(){
		nbPeople++;
	}

	/**
	 * Decrement the number of people of the room.
	 */
	public void decrementNbPeople(){
		nbPeople--;
	}

	/**
	 * Getter for the door of the room.
	 *
	 * @return the DoorModel door.
	 */
	public DoorModel getDoor() {
		return door;
	}

	/**
	 * Getter for the light of the room.
	 *
	 * @return the LightModel light.
	 */
	public LightModel getLight() {
		return light;
	}

	/**
	 * Getter for the window of the room.
	 *
	 * @return the WindowModel window.
	 */
	public WindowModel getWindow() {
		return window;
	}

	/**
	 * Setter for the door of the room.
	 *
	 * @param door the door of the room.
	 */
	public void setDoor(DoorModel door) {
		this.door = door;
	}

	/**
	 * Setter for the light of the room.
	 *
	 * @param light the light of the room.
	 */
	public void setLight(LightModel light) {
		this.light = light;
	}

	/**
	 * Setter for the window of the room.
	 *
	 * @param window the window of the room.
	 */
	public void setWindow(WindowModel window) {
		this.window = window;
	}

}

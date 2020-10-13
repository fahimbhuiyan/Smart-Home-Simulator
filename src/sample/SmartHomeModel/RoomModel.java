package sample.SmartHomeModel;

import java.util.ArrayList;

public class RoomModel {
	
	private String roomID;
	private String name;
	private int temperature = 0;
	private int nbPeople = 0;

	private int width = 0;
	private int height = 0;
	private int xAxis = 0;
	private int yAxis = 0;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getxAxis() {
		return xAxis;
	}

	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}

	public int getyAxis() {
		return yAxis;
	}

	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}

	private DoorModel door;
	private LightModel light;
	private WindowModel window;


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
	}


	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getNbPeople() {
		return nbPeople;
	}

	public void setNbPeople(int nbPeople) {
		this.nbPeople = nbPeople;
	}


	public DoorModel getDoor() {
		return door;
	}

	public LightModel getLight() {
		return light;
	}

	public WindowModel getWindow() {
		return window;
	}

	public void setDoor(DoorModel door) {
		this.door = door;
	}

	public void setLight(LightModel light) {
		this.light = light;
	}

	public void setWindow(WindowModel window) {
		this.window = window;
	}
}

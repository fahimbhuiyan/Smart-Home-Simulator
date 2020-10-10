package sample.Model;

import java.util.ArrayList;

public class RoomModel {
	
	private int roomID;
	private String name;
	private int temperature = 0;
	private int nbPeople = 0;
	
	ArrayList<DoorModel> doorList = new ArrayList<DoorModel>();
	ArrayList<LightModel> lightList = new ArrayList<LightModel>();
	ArrayList<WindowModel> windowList = new ArrayList<WindowModel>();
	
	public RoomModel(int roomID, String name) {
		
		super();
		this.roomID = roomID;
		this.name = name;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
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

	public ArrayList<DoorModel> getDoorList() {
		return doorList;
	}


	public ArrayList<LightModel> getLightList() {
		return lightList;
	}


	public ArrayList<WindowModel> getWindowList() {
		return windowList;
	}

	
	
}

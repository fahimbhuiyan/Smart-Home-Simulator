package sample.SmartHomeModel;

public class HeaterModel {
	
	private int id;
	private int roomID;
	private int temperature = 0;
	
	public HeaterModel(int id, int roomID) {
		super();
		this.id = id;
		this.roomID = roomID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
	
	
	

}

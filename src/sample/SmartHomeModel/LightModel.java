package sample.SmartHomeModel;

public class LightModel {

	private int id;
	private int roomID;
	private boolean isOpen = false;
	
	public LightModel(int id, int roomID) {
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

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
}

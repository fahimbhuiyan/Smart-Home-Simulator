package sample.Model;


public class WindowModel {
	
	private int id;
	private int roomID;
	private boolean isOpen = false;
	private boolean hasObject = false;
	
	public WindowModel(int id, int roomID) {
		
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

	public boolean isHasObject() {
		return hasObject;
	}

	public void setHasObject(boolean hasObject) {
		this.hasObject = hasObject;
	}
	
}

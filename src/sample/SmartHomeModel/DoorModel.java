package sample.SmartHomeModel;

import org.w3c.dom.ranges.DocumentRange;

public class DoorModel {



	private String id;
	private boolean isOpen = false;
	private boolean isLocked = false;
	private String doorType;



	public DoorModel(String id, String doorType) {

		super();
		this.id = id;
		this.doorType = doorType;


	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public String getDoorType() {
		return doorType;
	}

	public void setDoorType(String doorType) {
		this.doorType = doorType;
	}
		
		
}

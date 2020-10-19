package sample.SmartHomeModel;

import org.w3c.dom.ranges.DocumentRange;

/**
 * The Door model.
 */
public class DoorModel {



	private String id;
	private boolean isOpen = false;
	private boolean isLocked = false;
	private String doorType;


	/**
	 * Instantiates a new Door model.
	 *
	 * @param id       the id
	 * @param doorType the door type
	 */
	public DoorModel(String id, String doorType) {

		super();
		this.id = id;
		this.doorType = doorType;


	}

	/**
	 * Gets id of the Door.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets id of the Door.
	 *
	 * @param id the id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Is open boolean.
	 *
	 * @return the boolean
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * Sets Door to open.
	 *
	 * @param isOpen the is open
	 */
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	/**
	 * Is locked boolean.
	 *
	 * @return the boolean
	 */
	public boolean isLocked() {
		return isLocked;
	}

	/**
	 * Sets Door to locked.
	 *
	 * @param isLocked the is locked
	 */
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	/**
	 * Gets door type.
	 *
	 * @return the door type
	 */
	public String getDoorType() {
		return doorType;
	}

	/**
	 * Sets door type.
	 *
	 * @param doorType the door type
	 */
	public void setDoorType(String doorType) {
		this.doorType = doorType;
	}
		
		
}

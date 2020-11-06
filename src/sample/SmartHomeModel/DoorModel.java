package sample.SmartHomeModel;

import javafx.scene.image.Image;

/**
 * Class for the Door model.
 */
public class DoorModel {
	private String id;
	private String name;
	private boolean isOpen = false;
	private boolean isLocked = false;
	private Image imageOpen;
	private Image imageClose;

	/**
	 * Instantiates a new Door model.
	 *
	 * @param name	   the name of the door
	 * @param id       the id of the door
	 */
	public DoorModel(String id, String name) {
		this.name = name;
		this.id = id;
		loadImage();
	}

	public void loadImage() {

		imageOpen = new Image(getClass().getResourceAsStream("/img/door_open.jpg"));
		imageClose = new Image(getClass().getResourceAsStream("/img/door_close.jpg"));

	}


	/**
	 * Gets id of the Door.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	public Image getImageOpen() {
		return imageOpen;
	}

	public Image getImageClose() {
		return imageClose;
	}

	/**
	 * Gets name of the Door.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
}

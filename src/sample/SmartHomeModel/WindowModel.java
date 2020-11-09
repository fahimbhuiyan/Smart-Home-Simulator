package sample.SmartHomeModel;

import javafx.scene.image.Image;
import java.io.FileNotFoundException;

/**
 * Class for the Window Model.
 */
public class WindowModel {
	
	private String id;
	private String name;
	private boolean isOpen = true;
	private boolean hasObject = false;
	private Image imageOpen;
	private Image imageClose;

	/**
	 * Instantiate a Window.
	 *
	 * @param id   the id of the window.
	 * @param name the name
	 */
	public WindowModel(String id, String name) {
		this.id = id;
		this.name = name;
		loadImage();
	}

	/**
	 * Load the images used for windows.
	 */
	private void loadImage() {

			imageOpen = new Image(getClass().getResourceAsStream("/img/window_open.png"));
			imageClose = new Image(getClass().getResourceAsStream("/img/window_close.png"));
	}

	/**
	 * Getter for the id of a window.
	 *
	 * @return the String id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter for the name of a window.
	 *
	 * @return the String name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method that returns if a window is open.
	 *
	 * @return the boolean isOpen.
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * Setter the window as open.
	 *
	 * @param isOpen boolean for whether the window is open or not.
	 */
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	/**
	 * Method that returns if a window has an arbitrary object in front of it.
	 *
	 * @return the boolean hasObject
	 */
	public boolean HasObject() {
		return hasObject;
	}

	/**
	 * Setter for the window having an object in front of it.
	 *
	 * @param hasObject boolean for whether a window has an object in front of it.
	 */
	public void setHasObject(boolean hasObject) {
		this.hasObject = hasObject;
	}

	/**
	 * Getter for window open image.
	 *
	 * @return the Image imageOpen.
	 */
	public Image getImageOpen() {
		return imageOpen;
	}

	/**
	 * Getter for window closed image.
	 *
	 * @return the Image imageClose.
	 */
	public Image getImageClose() {
		return imageClose;
	}
}

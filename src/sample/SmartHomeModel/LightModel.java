package sample.SmartHomeModel;

import javafx.scene.image.Image;

/**
 * Class for the Light model.
 */
public class LightModel {

	private String id;
	private String name;
	private boolean isOpen = false;
	private Image imageOpen;
	private Image imageClose;

	/**
	 * Instantiates a new Light model.
	 *
	 * @param id the id
	 */
	public LightModel(String id, String name) {
		this.id = id;
		this.name = name;
		loadImage();
	}

	/**
	 * Load the images used for windows.
	 */
	public void loadImage() {

		imageOpen = new Image(getClass().getResourceAsStream("/img/light_bulb_open.jpg"));
		imageClose = new Image(getClass().getResourceAsStream("/img/light_bulb_close.jpg"));
		System.out.println(imageOpen+"bla");
		System.out.println(imageClose);

	}

	/**
	 * Gets id of the light.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets name of the light.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Is light open.
	 *
	 * @return the boolean
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * Sets light to open.
	 *
	 * @param isOpen the is open
	 */
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	/**
	 * Getter for light open image.
	 *
	 * @return the Image imageOpen.
	 */
	public Image getImageOpen() {
		return imageOpen;
	}

	/**
	 * Getter for light closed image.
	 *
	 * @return the Image imageClose.
	 */
	public Image getImageClose() {
		return imageClose;
	}
}

package sample.SmartHomeModel;

import javafx.scene.image.Image;

/**
 * Class for the Light model.
 */
public class LightModel {

	private String id;
	private boolean isOpen = false;
	private Image imageOpen;
	private Image imageClose;

	/**
	 * Instantiates a new Light model.
	 *
	 * @param id the id
	 */
	public LightModel(String id) {
		super();
		this.id = id;
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
	 * Sets id of the light.
	 *
	 * @param id the id
	 */
	public void setId(String id) {
		this.id = id;
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

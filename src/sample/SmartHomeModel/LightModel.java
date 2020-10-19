package sample.SmartHomeModel;

/**
 * The Light model.
 */
public class LightModel {

	private String id;
	private boolean isOpen = false;

	/**
	 * Instantiates a new Light model.
	 *
	 * @param id the id
	 */
	public LightModel(String id) {
		super();
		this.id = id;
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
	
}

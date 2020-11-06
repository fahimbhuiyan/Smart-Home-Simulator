package sample.SmartHomeModel;

/**
 * Class for the User Model.
 */
public class UserModel {

	 private String name;
	 private int id;
	 private String user_type;
	 private String currentLocation;
	 private String previousLocation;

	/**
	 * Instantiate a user.
	 *
	 * @param name the name of the user.
	 * @param id the id of the user.
	 * @param user_type the type of the user.
	 * @param currentLocation the location of the user.
	 */
	public UserModel(String name, int id, String user_type, String currentLocation) {
		this.name = name;
		this.id = id;
		this.user_type = user_type;
		this.currentLocation = currentLocation;
		this.previousLocation = currentLocation;
	}


	/**
	 * Getter for the name of a user.
	 *
	 * @return the String name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name of a user.
	 *
	 * @param name the name of a user.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for id of a user.
	 *
	 * @return the int id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for id of a user.
	 *
	 * @param id the id of a user.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for the type of a user.
	 *
	 * @return the String user_type.
	 */
	public String getUser_type() {
		return user_type;
	}

	/**
	 * Setter for type of a user.
	 *
	 * @param user_type the type of a user.
	 */
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	/**
	 * Getter for the location of a user.
	 *
	 * @return the String location.
	 */
	public String getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * Setter for location of a user.
	 *
	 * @param location the location of a user.
	 */
	public void setCurrentLocation(String location) {
		this.currentLocation = location;
	}

	/**
	 * Getter for the location of a user.
	 *
	 * @return the String location.
	 */
	public String getPreviousLocation() {
		return previousLocation;
	}

	/**
	 * Setter for location of a user.
	 *
	 * @param location the location of a user.
	 */
	public void setPreviousLocation(String location) {
		this.previousLocation = location;
	}

}

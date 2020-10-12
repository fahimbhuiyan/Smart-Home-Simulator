package sample.SmartHomeModel;

public class UserModel {

	 private String name;
	 private int id;
	 private String user_type;
	 private String location;
	 
	public UserModel(String name, int id, String user_type, String location) {
		super();
		this.name = name;
		this.id = id;
		this.user_type = user_type;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	 
	 
}

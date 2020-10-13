package sample.SmartHomeModel;

public class LightModel {

	private String id;
	private boolean isOpen = false;
	
	public LightModel(String id) {
		super();
		this.id = id;
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
	
}

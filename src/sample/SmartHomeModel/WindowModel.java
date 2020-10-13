package sample.SmartHomeModel;


public class WindowModel {
	
	private String id;
	private boolean isOpen = false;
	private boolean hasObject = false;
	
	public WindowModel(String id) {
		
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

	public boolean HasObject() {
		return hasObject;
	}

	public void setHasObject(boolean hasObject) {
		this.hasObject = hasObject;
	}
	
}

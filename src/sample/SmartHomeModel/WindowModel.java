package sample.SmartHomeModel;


import javafx.scene.image.Image;
import java.io.FileNotFoundException;

public class WindowModel {
	
	private String id;
	private boolean isOpen = false;
	private boolean hasObject = false;
	private Image imageOpen;
	private Image imageClose;

	public WindowModel(String id) throws FileNotFoundException {
		
		super();
		this.id = id;
		loadImage();
	}

	public void loadImage() {

			imageOpen = new Image(getClass().getResourceAsStream("/img/window_open.png"));
			imageClose = new Image(getClass().getResourceAsStream("/img/window_close.png"));
			System.out.println(imageOpen+"bla");
			System.out.println(imageClose);

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

	public Image getImageOpen() {
		return imageOpen;
	}

	public Image getImageClose() {
		return imageClose;
	}
}

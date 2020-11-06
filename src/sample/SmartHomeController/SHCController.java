package sample.SmartHomeController;

import javafx.scene.control.TextArea;
import sample.SmartHomeModel.HouseModel;

/**
 * Class for the SHC Controller.
 */
public class SHCController {

    private boolean autoMode;

    public SHCController() {
        this.autoMode = false;
    }

    void openOrCloseLights(String areaName, boolean manualControl, String action, HouseModel houseModel, TextArea consoleTextField) {

        if(manualControl || autoMode){

            autoMode = !manualControl;

            if(action.equals("close")){
                System.out.println("Light to close: " + houseModel.getLights().get(areaName).getName());
                houseModel.getLights().get(areaName).setOpen(false);
                consoleTextField.setText("Closing the lights in " + areaName + ".\n" + consoleTextField.getText());
            }
            else if(action.equals("open")){
                System.out.println("Light to open: " + houseModel.getLights().get(areaName).getName());
                houseModel.getLights().get(areaName).setOpen(true);
                consoleTextField.setText("Opening the lights in " + areaName + ".\n" + consoleTextField.getText());
            }
        }
        else {
            System.out.println("Manual control: " + manualControl + ", and autoMode: " + autoMode);
        }
    }

    void openDoor(String roomName, HouseModel houseModel, TextArea consoleTextField) {
        houseModel.getDoors().get(roomName).setOpen(true);
        consoleTextField.setText("Opening the door in " + roomName + ".\n" + consoleTextField.getText());
    }
    void closeDoor(String roomName, HouseModel houseModel, TextArea consoleTextField) {
        houseModel.getDoors().get(roomName).setOpen(false);
        consoleTextField.setText("Closing the door in " + roomName + ".\n" + consoleTextField.getText());
    }
    void lockDoor(String roomName, HouseModel houseModel, TextArea consoleTextField) {
        houseModel.getDoors().get(roomName).setLocked(true);
        consoleTextField.setText("Locking the door in " + roomName + ".\n" + consoleTextField.getText());
    }
    void unLock(String roomName, HouseModel houseModel, TextArea consoleTextField) {
        houseModel.getDoors().get(roomName).setLocked(false);
        consoleTextField.setText("Unlocking the door in " + roomName + ".\n" + consoleTextField.getText());
    }
    void openWindow(String roomName, HouseModel houseModel, TextArea consoleTextField) {
        houseModel.getWindows().get(roomName).setOpen(true);
        consoleTextField.setText("Opening the window in " + roomName + ".\n" + consoleTextField.getText());
    }
    void closeWindow(String roomName, HouseModel houseModel, TextArea consoleTextField) {
        if(houseModel.getWindows().get(roomName).HasObject()){
            consoleTextField.setText("There is an object, cannot close the window in " + roomName + ".\n " + consoleTextField.getText());
        }
        if(!houseModel.getWindows().get(roomName).HasObject()){
            houseModel.getWindows().get(roomName).setOpen(false);
            consoleTextField.setText("Closing the window in " + roomName + ".\n" + consoleTextField.getText());
        }
    }

    public void setAutoMode(boolean setAutoMode){
        this.autoMode = setAutoMode;
    }

    public boolean isAutoMode(){
        return autoMode;
    }
}

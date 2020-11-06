package sample.SmartHomeController;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;
import sample.SmartHomeModel.UserModel;

import javax.swing.*;
import java.util.Map;

/**
 * Class for the SHC Controller.
 */
public class SHCController {

    private boolean autoMode;

    public SHCController() {
        this.autoMode = false;
    }

    void openOrCloseLights(String roomName, boolean manualControl, String action, HouseModel houseModel, TextArea consoleTextField) {

        if(manualControl || autoMode){
            if(action.equals("close")){
                houseModel.getLights().get(roomName).setOpen(false);
                consoleTextField.setText("Closing the lights in " + roomName + ".\n" + consoleTextField.getText());
            }
            else if(action.equals("open")){
                houseModel.getLights().get(roomName).setOpen(true);
                consoleTextField.setText("Opening the lights in " + roomName + ".\n" + consoleTextField.getText());
            }
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

package sample.SmartHomeController;

import sample.Interfaces.Observer;
import sample.SmartHomeModel.HouseModel;

/**
 * Class for the SHC Controller.
 */
public class SHCController implements Observer {

    private boolean autoMode;

    public SHCController() {
        this.autoMode = false;
    }

    void openOrCloseLights(String areaName, boolean manualControl, String action, HouseModel houseModel, MainViewController.PrintConsole printConsole) {

        if(manualControl || autoMode){

            autoMode = !manualControl;

            if(action.equals("close")){
                System.out.println("Light to close: " + houseModel.getLights().get(areaName).getName());
                houseModel.getLights().get(areaName).setOpen(false);
                printConsole.setText("Closing the lights in " + areaName + ".");
            }
            else if(action.equals("open")){
                System.out.println("Light to open: " + houseModel.getLights().get(areaName).getName());
                houseModel.getLights().get(areaName).setOpen(true);
                printConsole.setText("Opening the lights in " + areaName + ".");
            }
        }
        else {
            System.out.println("Manual control: " + manualControl + ", and autoMode: " + autoMode);
        }
    }

    void openDoor(String roomName, HouseModel houseModel, MainViewController.PrintConsole printConsole) {

        if(houseModel.getDoors().get(roomName).isLocked() && houseModel.getDoors().get(roomName).isOpen() == false){
            printConsole.setText("Cannot open the door in " + roomName + ".");
        }
        else{
            houseModel.getDoors().get(roomName).setOpen(true);
            printConsole.setText("Opening the door in " + roomName + ".");
        }
    }
    void closeDoor(String roomName, HouseModel houseModel, MainViewController.PrintConsole printConsole) {
        houseModel.getDoors().get(roomName).setOpen(false);
        printConsole.setText("Closing the door in " + roomName + ".");
    }
    void lockDoor(String roomName, HouseModel houseModel, MainViewController.PrintConsole printConsole) {
        houseModel.getDoors().get(roomName).setLocked(true);
        printConsole.setText("Locking the door in " + roomName + ".");
    }
    void unLock(String roomName, HouseModel houseModel, MainViewController.PrintConsole printConsole) {
        houseModel.getDoors().get(roomName).setLocked(false);
        printConsole.setText("Unlocking the door in " + roomName + ".");
    }
    void openWindow(String roomName, HouseModel houseModel, MainViewController.PrintConsole printConsole) {
        houseModel.getWindows().get(roomName).setOpen(true);
        printConsole.setText("Opening the window in " + roomName + ".");
    }
    void closeWindow(String roomName, HouseModel houseModel, MainViewController.PrintConsole printConsole) {
        if(houseModel.getWindows().get(roomName).HasObject()){
            printConsole.setText("There is an object, cannot close the window in " + roomName + ".");
        }
        if(!houseModel.getWindows().get(roomName).HasObject()){
            houseModel.getWindows().get(roomName).setOpen(false);
            printConsole.setText("Closing the window in " + roomName + ".");
        }
    }

    public void setAutoMode(boolean setAutoMode){
        this.autoMode = setAutoMode;
    }

    public boolean isAutoMode(){
        return autoMode;
    }

    @Override
    public void update(Object observable) {

    }
}

package sample.SmartHomeController;

import sample.Interfaces.Observer;
import sample.SmartHomeModel.HouseModel;

/**
 * Class for the SHC Controller.
 */
public class SHCController implements Observer {

    private boolean autoMode;

    /**
     * Instantiates a new Shc controller.
     */
    public SHCController() {
        this.autoMode = false;
    }

    /**
     * Open or close lights.
     *
     * @param areaName      the area name
     * @param manualControl the manual control
     * @param action        the action
     * @param houseModel    the house model
     * @param printConsole  the print console
     * @param isScheduled   the is scheduled
     */
    void openOrCloseLights(String areaName, boolean manualControl, String action, HouseModel houseModel, MainViewController.PrintConsole printConsole, boolean isScheduled) {

        if(manualControl || autoMode){

            autoMode = !manualControl;

            String extraInfo = isScheduled ? " as scheduled." : ".";

            if(action.equals("close")){
                System.out.println("Light to close: " + houseModel.getLights().get(areaName).getName());
                houseModel.getLights().get(areaName).setOpen(false);
                printConsole.setText("Closing the lights in " + areaName + extraInfo);
            }
            else if(action.equals("open")){
                System.out.println("Light to open: " + houseModel.getLights().get(areaName).getName());
                houseModel.getLights().get(areaName).setOpen(true);
                printConsole.setText("Opening the lights in " + areaName + extraInfo);
            }
        }
        else {
            System.out.println("Manual control: " + manualControl + ", and autoMode: " + autoMode);
        }
    }

    /**
     * Open door.
     *
     * @param roomName     the room name
     * @param houseModel   the house model
     * @param printConsole the print console
     */
    void openDoor(String roomName, HouseModel houseModel, MainViewController.PrintConsole printConsole) {

        if(houseModel.getDoors().get(roomName).isLocked() && houseModel.getDoors().get(roomName).isOpen() == false){
            printConsole.setText("Cannot open the door in " + roomName + ".");
        }
        else{
            houseModel.getDoors().get(roomName).setOpen(true);
            printConsole.setText("Opening the door in " + roomName + ".");
        }
    }

    /**
     * Close door.
     *
     * @param roomName     the room name
     * @param houseModel   the house model
     * @param printConsole the print console
     */
    void closeDoor(String roomName, HouseModel houseModel, MainViewController.PrintConsole printConsole) {
        houseModel.getDoors().get(roomName).setOpen(false);
        printConsole.setText("Closing the door in " + roomName + ".");
    }

    /**
     * Lock door.
     *
     * @param roomName     the room name
     * @param houseModel   the house model
     * @param printConsole the print console
     */
    void lockDoor(String roomName, HouseModel houseModel, MainViewController.PrintConsole printConsole) {
        houseModel.getDoors().get(roomName).setLocked(true);
        printConsole.setText("Locking the door in " + roomName + ".");
    }

    /**
     * Un lock.
     *
     * @param roomName     the room name
     * @param houseModel   the house model
     * @param printConsole the print console
     */
    void unLock(String roomName, HouseModel houseModel, MainViewController.PrintConsole printConsole) {
        houseModel.getDoors().get(roomName).setLocked(false);
        printConsole.setText("Unlocking the door in " + roomName + ".");
    }

    /**
     * Open window.
     *
     * @param roomName     the room name
     * @param houseModel   the house model
     * @param printConsole the print console
     */
    void openWindow(String roomName, HouseModel houseModel, MainViewController.PrintConsole printConsole) {
        if(houseModel.getWindows().get(roomName).hasObject().get()){
            printConsole.setText("Cannot open the windows in " + roomName + " since there is an object blocking them.");
        }
        if(!houseModel.getWindows().get(roomName).hasObject().get()){
            houseModel.getWindows().get(roomName).setOpen(true);
            printConsole.setText("Opening the window in " + roomName + ".");
        }
    }

    /**
     * Close window.
     *
     * @param roomName     the room name
     * @param houseModel   the house model
     * @param printConsole the print console
     */
    void closeWindow(String roomName, HouseModel houseModel, MainViewController.PrintConsole printConsole) {
        if(houseModel.getWindows().get(roomName).hasObject().get()){
            printConsole.setText("Cannot close the windows in " + roomName + " since there is an object blocking them.");
        }
        if(!houseModel.getWindows().get(roomName).hasObject().get()){
            houseModel.getWindows().get(roomName).setOpen(false);
            printConsole.setText("Closing the window in " + roomName + ".");
        }
    }

    /**
     * Set auto mode.
     *
     * @param setAutoMode the set auto mode
     */
    public void setAutoMode(boolean setAutoMode){
        this.autoMode = setAutoMode;
    }

    /**
     * Is auto mode boolean.
     *
     * @return the boolean
     */
    public boolean isAutoMode(){
        return autoMode;
    }

    /**
     * Update
     *
     * @param observable Object
     */
    @Override
    public void update(Object observable) {

    }
}

package sample.SmartHomeModel;

public class HouseModel {

    private int outsideTemp = 0;
    private DoorModel frontDoor;
    private DoorModel backDoor;
    private String userName = "";

    public HouseModel(DoorModel frontDoor, DoorModel backDoor, String userName) {

        this.frontDoor = frontDoor;
        this.backDoor = backDoor;
        this.userName = userName;
    }

    public int getOutsideTemp() {
        return outsideTemp;
    }

    public void setOutsideTemp(int outsideTemp) {
        this.outsideTemp = outsideTemp;
    }

    public DoorModel getFrontDoor() {
        return frontDoor;
    }

    public void setFrontDoor(DoorModel frontDoor) {
        this.frontDoor = frontDoor;
    }

    public DoorModel getBackDoor() {
        return backDoor;
    }

    public void setBackDoor(DoorModel backDoor) {
        this.backDoor = backDoor;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

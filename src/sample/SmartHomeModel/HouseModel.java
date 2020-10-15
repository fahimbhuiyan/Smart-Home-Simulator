package sample.SmartHomeModel;

public class HouseModel {

    private int outsideTemp = 0;
    private String loggedUserName = "";
    private boolean simulationActive = false;

    public HouseModel(int outsideTemp, String loggedUserName, boolean simulationActive) {

        this.loggedUserName = loggedUserName;
    }

    public int getOutsideTemp() {
        return outsideTemp;
    }

    public void setOutsideTemp(int outsideTemp) {
        this.outsideTemp = outsideTemp;
    }

    public String getLoggedUserName() {
        return loggedUserName;
    }

    public void setLoggedUserName(String loggedUserName) {
        this.loggedUserName = loggedUserName;
    }

    public boolean isSimulationActive() {
        return simulationActive;
    }

    public void setSimulationActive(boolean simulationActive) {
        this.simulationActive = simulationActive;
    }
}

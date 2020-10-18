package sample.SmartHomeModel;

public class HouseModel {

    private double outsideTemp = 0;
    private String loggedUserName = "";
    private int id;
    private boolean simulationActive = false;

    public HouseModel(int outsideTemp, String loggedUserName, boolean simulationActive) {

        this.loggedUserName = loggedUserName;
    }

    public double getOutsideTemp() {
        return outsideTemp;
    }

    public void setOutsideTemp(double outsideTemp) {
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

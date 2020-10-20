package sample.SmartHomeModel;

/**
 * Class for the House model.
 */
public class HouseModel {
    private double outsideTemp = 0;
    private String loggedUserName = "";
    private int id;
    private boolean simulationActive = false;

    /**
     * Instantiates a new House model.
     *
     * @param outsideTemp      the outside temp
     * @param loggedUserName   the logged user name
     * @param simulationActive the simulation active
     */
    public HouseModel(int outsideTemp, String loggedUserName, boolean simulationActive) {
        this.loggedUserName = loggedUserName;
    }

    /**
     * Gets outside temp.
     *
     * @return the outside temp
     */
    public double getOutsideTemp() {
        return outsideTemp;
    }

    /**
     * Sets outside temp.
     *
     * @param outsideTemp the outside temp
     */
    public void setOutsideTemp(double outsideTemp) {
        this.outsideTemp = outsideTemp;
    }

    /**
     * Gets logged user name.
     *
     * @return the logged user name
     */
    public String getLoggedUserName() {
        return loggedUserName;
    }

    /**
     * Sets logged user name.
     *
     * @param loggedUserName the logged user name
     */
    public void setLoggedUserName(String loggedUserName) {
        this.loggedUserName = loggedUserName;
    }

    /**
     * Is simulation active boolean.
     *
     * @return the boolean
     */
    public boolean isSimulationActive() {
        return simulationActive;
    }

    /**
     * Sets simulation active.
     *
     * @param simulationActive the simulation active
     */
    public void setSimulationActive(boolean simulationActive) {
        this.simulationActive = simulationActive;
    }
}

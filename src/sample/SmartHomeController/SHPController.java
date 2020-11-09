package sample.SmartHomeController;

import sample.Interfaces.Observer;

/**
 * Class for the SHP Controller.
 */
public class SHPController implements Observer {

    @Override
    public void update(Object observable) {
        System.out.println("Alerting cops!");
    }
}

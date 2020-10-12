package sample.SmartHomeController;

import javafx.scene.layout.BorderPane;
import sample.SmartHomeView.HouseView;

public class HouseViewController {

    HouseView hv = new HouseView();
    public BorderPane getHouseLayout(){
        return hv.HouseLayout();
    }
}

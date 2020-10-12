package sample.SmartHomeView;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HouseView {

    public BorderPane HouseLayout(){
        BorderPane bp = new BorderPane();

        SmartHomeMenu shm = new SmartHomeMenu();
        // create a VBox (Content of the scene)

        bp.setTop(shm.createSmartHomeMenu());

        Rectangle r1 = new Rectangle();
        r1.setStroke(Color.BLUE);
        r1.setFill(Color.TRANSPARENT);
        r1.setWidth(200);
        r1.setHeight(100);
        r1.setX(100);
        r1.setY(200);

        bp.getChildren().add(r1);

        return bp;
    }
}

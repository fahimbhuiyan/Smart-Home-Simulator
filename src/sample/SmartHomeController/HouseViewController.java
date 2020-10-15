package sample.SmartHomeController;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import sample.SmartHomeModel.RoomModel;

public class HouseViewController {

    public void drawLayout(RoomModel[] roomArray, BorderPane bp){
        for(int i = 0; i < roomArray.length; i++){

            Rectangle r1 = new Rectangle();
            r1.setStroke(Color.BLUE);
            r1.setFill(Color.TRANSPARENT);

            Text text = new Text(roomArray[i].getName());
            if(!roomArray[i].getName().equals("House")) {

                text.setX(roomArray[i].getxAxis() + 5);
                text.setY(roomArray[i].getyAxis() + 15);
            }

            r1.setWidth(roomArray[i].getWidth());
            r1.setHeight(roomArray[i].getHeight());
            r1.setX(roomArray[i].getxAxis());
            r1.setY(roomArray[i].getyAxis());

            bp.getChildren().addAll(text,r1);
        }
    }
}

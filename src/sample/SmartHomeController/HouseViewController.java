package sample.SmartHomeController;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sample.SmartHomeModel.HouseModel;
import sample.SmartHomeModel.RoomModel;

public class HouseViewController {

    public void drawLayout(RoomModel[] roomArray, BorderPane bp, HouseModel houseModel){

        bp.getChildren().clear();

        for(int i = 0; i < roomArray.length; i++){

            Rectangle r1 = new Rectangle();
            r1.setStroke(Color.BLUE);
            r1.setFill(Color.TRANSPARENT);

            Text text = new Text(roomArray[i].getName());

            if(!roomArray[i].getName().equals("House")) {

                text.setX(roomArray[i].getxAxis() + 5);
                text.setY(roomArray[i].getyAxis() + 15);
                text.setFont(Font.font("Sans serif", FontWeight.BOLD, FontPosture.REGULAR, 14));
            }

            if(roomArray[i].getName().equals("House")){
                Text outsideTemperature = new Text("Outside Temperature: "+Double.toString(houseModel.getOutsideTemp())+"°C");
                outsideTemperature.setX(roomArray[i].getxAxis()-25);
                outsideTemperature.setY(roomArray[i].getyAxis()-25);

                bp.getChildren().add(outsideTemperature);
            }

            r1.setWidth(roomArray[i].getWidth());
            r1.setHeight(roomArray[i].getHeight());
            r1.setX(roomArray[i].getxAxis());
            r1.setY(roomArray[i].getyAxis());

            if(!roomArray[i].getName().equals("House")){

                Text temperature = new Text(Double.toString(roomArray[i].getTemperature())+"°C");
                temperature.setX(roomArray[i].getxAxis() + 5);
                temperature.setY(roomArray[i].getyAxis() + 30);
                temperature.setFont(Font.font("Sans serif", FontWeight.BOLD, FontPosture.REGULAR, 10));

                ImageView imageView = new ImageView();

                if(roomArray[i].getWindow().isOpen() == false){
                    imageView.setImage(roomArray[i].getWindow().getImageClose());
                }else{
                    imageView.setImage(roomArray[i].getWindow().getImageOpen());
                }

                Text hasObjectText = new Text();
                if(roomArray[i].getWindow().HasObject() == false){
                     hasObjectText = new Text("No Object");
                }
                else {
                     hasObjectText = new Text("Has Object");
                }

                hasObjectText.setX(roomArray[i].getxAxis() + roomArray[i].getWidth()-50);
                hasObjectText.setY(roomArray[i].getyAxis()+60);


                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                imageView.setX(roomArray[i].getxAxis()+roomArray[i].getWidth()-50);
                imageView.setY(roomArray[i].getyAxis());

                bp.getChildren().add(temperature);
                bp.getChildren().add(imageView);
                bp.getChildren().add(hasObjectText);
            }

            bp.getChildren().addAll(text,r1);
        }


    }
}

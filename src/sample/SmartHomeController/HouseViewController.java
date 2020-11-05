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
import sample.SmartHomeModel.UserModel;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for the House view controller.
 */
public class HouseViewController {

    /**
     * Draw layout of the house.
     *
     * @param bp         the bp
     * @param houseModel the house model
     * @param userList   the user list
     */
    void drawLayout(BorderPane bp, HouseModel houseModel, ArrayList<UserModel> userList) {

        bp.getChildren().clear();

        // Drawing the empty house layout
        Rectangle r1 = new Rectangle();
        r1.setStroke(Color.BLUE);
        r1.setFill(Color.TRANSPARENT);

        //Add outside temperature
        Text outsideTemperature = new Text("Outside Temperature: " + Double.toString(houseModel.getOutsideTemp()) + "°C");
        outsideTemperature.setX(houseModel.getxAxis() - 25);
        outsideTemperature.setY(houseModel.getyAxis() - 25);

        r1.setWidth(houseModel.getWidth());
        r1.setHeight(houseModel.getHeight());
        r1.setX(houseModel.getxAxis());
        r1.setY(houseModel.getyAxis());

        bp.getChildren().addAll(outsideTemperature, r1);

        for (String roomName : houseModel.getRooms().keySet()) {

            //Draw Room
            r1 = new Rectangle();
            r1.setStroke(Color.BLUE);
            r1.setFill(Color.TRANSPARENT);

            //Add Room Name
            Text text = new Text(roomName);

            text.setX(houseModel.getRooms().get(roomName).getxAxis() + 5);
            text.setY(houseModel.getRooms().get(roomName).getyAxis() + 15);
            text.setFont(Font.font("Sans serif", FontWeight.BOLD, FontPosture.REGULAR, 14));

            r1.setWidth(houseModel.getRooms().get(roomName).getWidth());
            r1.setHeight(houseModel.getRooms().get(roomName).getHeight());
            r1.setX(houseModel.getRooms().get(roomName).getxAxis());
            r1.setY(houseModel.getRooms().get(roomName).getyAxis());

            Text temperature = new Text(Double.toString(houseModel.getRooms().get(roomName).getTemperature()) + "°C");
            temperature.setX(houseModel.getRooms().get(roomName).getxAxis() + 5);
            temperature.setY(houseModel.getRooms().get(roomName).getyAxis() + 30);
            temperature.setFont(Font.font("Sans serif", FontWeight.BOLD, FontPosture.REGULAR, 10));

            ImageView windowImageView = new ImageView();

            if (!houseModel.getRooms().get(roomName).getWindow().isOpen()) {
                windowImageView.setImage(houseModel.getRooms().get(roomName).getWindow().getImageClose());
            } else {
                windowImageView.setImage(houseModel.getRooms().get(roomName).getWindow().getImageOpen());
            }

            Text hasObjectText;
            if (!houseModel.getRooms().get(roomName).getWindow().HasObject()) {
                hasObjectText = new Text("No Object");
            } else {
                hasObjectText = new Text("Has Object");
            }

            hasObjectText.setX(houseModel.getRooms().get(roomName).getxAxis() + houseModel.getRooms().get(roomName).getWidth() - 50);
            hasObjectText.setY(houseModel.getRooms().get(roomName).getyAxis() + 60);

            ImageView lightImageView = new ImageView();

            if (houseModel.getRooms().get(roomName).getLight().isOpen()) {
                lightImageView.setImage(houseModel.getRooms().get(roomName).getLight().getImageOpen());
            } else {
                lightImageView.setImage(houseModel.getRooms().get(roomName).getLight().getImageClose());
            }

            lightImageView.setFitWidth(50);
            lightImageView.setFitHeight(50);
            lightImageView.setX(houseModel.getRooms().get(roomName).getxAxis() + houseModel.getRooms().get(roomName).getWidth() - 50);
            lightImageView.setY(houseModel.getRooms().get(roomName).getyAxis() + houseModel.getRooms().get(roomName).getHeight() - 53);

            windowImageView.setFitWidth(50);
            windowImageView.setFitHeight(50);
            windowImageView.setX(houseModel.getRooms().get(roomName).getxAxis() + houseModel.getRooms().get(roomName).getWidth() - 50);
            windowImageView.setY(houseModel.getRooms().get(roomName).getyAxis());

            bp.getChildren().add(temperature);
            bp.getChildren().add(windowImageView);
            bp.getChildren().add(lightImageView);
            bp.getChildren().add(hasObjectText);

            //adding people in room
            for (UserModel userModel : userList) {

                Random r = new Random();
                Text userName = new Text("• " + userModel.getName() + " (ID: " + userModel.getId() + ")");

                if (userModel.getLocation().equals(houseModel.getRooms().get(roomName).getName())) {
                    int xLow = (int) (0.2 * houseModel.getRooms().get(roomName).getWidth());
                    int xHigh = (int) (0.8 * houseModel.getRooms().get(roomName).getWidth());
                    int xResult = r.nextInt(xHigh - xLow) + xLow;

                    int yLow = (int) (0.2 * houseModel.getRooms().get(roomName).getHeight());
                    int yHigh = (int) (0.8 * houseModel.getRooms().get(roomName).getHeight());
                    int yResult = r.nextInt(yHigh - yLow) + yLow;

                    userName.setX(houseModel.getRooms().get(roomName).getxAxis() + xResult);
                    userName.setY(houseModel.getRooms().get(roomName).getyAxis() + yResult);
                    bp.getChildren().add(userName);
                }
            }

            bp.getChildren().addAll(text, r1);
        }

        // Adding people outside of rooms (such as in the general area of the house and outside).
        for (UserModel userModel : userList) {

            Random r = new Random();
            Text userName = new Text("• " + userModel.getName() + " (ID: " + userModel.getId() + ")");

            if (userModel.getLocation().equals("Outside")) {
                int xLow = 0;
                int xHigh = (int) (0.8 * houseModel.getxAxis());
                int xResult = r.nextInt(xHigh - xLow) + xLow;

                int yLow = 0;
                int yHigh = (int) (0.8 * houseModel.getyAxis() + houseModel.getHeight());
                int yResult = r.nextInt(yHigh - yLow) + yLow;

                userName.setX(xResult);
                userName.setY(yResult);
            } else if (userModel.getLocation().equals("House")) {

                int xLow = (int) (0.1 * houseModel.getWidth());
                int xHigh = (int) (0.3 * houseModel.getWidth());
                int xResult = r.nextInt(xHigh - xLow) + xLow;

                int yLow = (int) (0.4 * houseModel.getHeight());
                int yHigh = (int) (0.6 * houseModel.getHeight());
                int yResult = r.nextInt(yHigh - yLow) + yLow;

                userName.setX(houseModel.getxAxis() + xResult);
                userName.setY(houseModel.getyAxis() + yResult);
            }

            bp.getChildren().add(userName);
        }
    }
}

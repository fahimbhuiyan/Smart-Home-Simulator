package sample.SmartHomeController;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sample.SmartHomeModel.HouseModel;
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
    public void drawLayout(BorderPane bp, HouseModel houseModel, ArrayList<UserModel> userList) {

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

            ImageView lightImageView = new ImageView();

            if (houseModel.getRooms().get(roomName).getLight().isOpen()) {
                lightImageView.setImage(houseModel.getRooms().get(roomName).getLight().getImageOpen());
            } else {
                lightImageView.setImage(houseModel.getRooms().get(roomName).getLight().getImageClose());
            }

            ImageView doorImageView = new ImageView();

            if (houseModel.getRooms().get(roomName).getDoor().isOpen()) {
                doorImageView.setImage(houseModel.getRooms().get(roomName).getDoor().getImageOpen());
            } else {
                doorImageView.setImage(houseModel.getRooms().get(roomName).getDoor().getImageClose());
            }

            Text doorIsLock;
            if (houseModel.getRooms().get(roomName).getDoor().isLocked()) {
                doorIsLock = new Text("Locked");
            } else {
                doorIsLock = new Text("Unlocked");
            }

            hasObjectText.setX(houseModel.getRooms().get(roomName).getxAxis() + houseModel.getRooms().get(roomName).getWidth() - 50);
            hasObjectText.setY(houseModel.getRooms().get(roomName).getyAxis() + 60);

            doorIsLock.setX(houseModel.getRooms().get(roomName).getxAxis());
            doorIsLock.setY(houseModel.getRooms().get(roomName).getyAxis() + houseModel.getRooms().get(roomName).getHeight() - 55);

            doorImageView.setFitWidth(50);
            doorImageView.setFitHeight(50);
            doorImageView.setX(houseModel.getRooms().get(roomName).getxAxis());
            doorImageView.setY(houseModel.getRooms().get(roomName).getyAxis() + houseModel.getRooms().get(roomName).getHeight() - 52);

            lightImageView.setFitWidth(50);
            lightImageView.setFitHeight(50);
            lightImageView.setX(houseModel.getRooms().get(roomName).getxAxis() + houseModel.getRooms().get(roomName).getWidth() - 50);
            lightImageView.setY(houseModel.getRooms().get(roomName).getyAxis() + houseModel.getRooms().get(roomName).getHeight() - 52);

            windowImageView.setFitWidth(50);
            windowImageView.setFitHeight(50);
            windowImageView.setX(houseModel.getRooms().get(roomName).getxAxis() + houseModel.getRooms().get(roomName).getWidth() - 50);
            windowImageView.setY(houseModel.getRooms().get(roomName).getyAxis());

            bp.getChildren().addAll(doorIsLock,hasObjectText,temperature,doorImageView,windowImageView,lightImageView);

            //adding people in room
            for (UserModel userModel : userList) {

                Random r = new Random();
                Text userName = new Text("• " + userModel.getName() + " (ID: " + userModel.getId() + ")");

                if (userModel.getCurrentLocation().equals(houseModel.getRooms().get(roomName).getName())) {
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

        //Adding lights and doors (front,back)
        ImageView frontDoorImage = new ImageView();
        ImageView backDoorImage = new ImageView();
        ImageView frontLightImage = new ImageView();
        ImageView backLightImage = new ImageView();

        Text frontDoorIsLock;
        if (houseModel.getDoors().get("Front yard").isLocked()) {
            frontDoorIsLock = new Text("Locked");
        } else {
            frontDoorIsLock = new Text("Unlocked");
        }

        Text backDoorIsLock;
        if (houseModel.getDoors().get("Backyard").isLocked()) {
            backDoorIsLock = new Text("Locked");
        } else {
            backDoorIsLock = new Text("Unlocked");
        }

        if(houseModel.getDoors().get("Front yard").isOpen()){
            frontDoorImage.setImage(houseModel.getDoors().get("Front yard").getImageOpen());
        } else{
            frontDoorImage.setImage(houseModel.getDoors().get("Front yard").getImageClose());
        }

        if(houseModel.getDoors().get("Backyard").isOpen()){
            backDoorImage.setImage(houseModel.getDoors().get("Backyard").getImageOpen());
        } else{
            backDoorImage.setImage(houseModel.getDoors().get("Backyard").getImageClose());
        }

        if(houseModel.getLights().get("Front yard").isOpen()){
            frontLightImage.setImage(houseModel.getLights().get("Front yard").getImageOpen());
        } else{
            frontLightImage.setImage(houseModel.getLights().get("Front yard").getImageClose());
        }

        if(houseModel.getLights().get("Backyard").isOpen()){
            backLightImage.setImage(houseModel.getLights().get("Backyard").getImageOpen());
        } else{
            backLightImage.setImage(houseModel.getLights().get("Backyard").getImageClose());
        }

        frontDoorIsLock.setX(houseModel.getxAxis() - 55);
        frontDoorIsLock.setY(houseModel.getyAxis() + (houseModel.getHeight()/2) - 35);

        backDoorIsLock.setX(houseModel.getxAxis() + houseModel.getWidth() + 10);
        backDoorIsLock.setY(houseModel.getyAxis() + (houseModel.getHeight()/2) - 35);


        frontDoorImage.setFitWidth(50);
        frontDoorImage.setFitHeight(50);
        frontDoorImage.setX(houseModel.getxAxis() - 55);
        frontDoorImage.setY(houseModel.getyAxis() + (houseModel.getHeight()/2) - 30);

        backDoorImage.setFitWidth(50);
        backDoorImage.setFitHeight(50);
        backDoorImage.setX(houseModel.getxAxis() + houseModel.getWidth() + 10);
        backDoorImage.setY(houseModel.getyAxis() + (houseModel.getHeight()/2) - 30);

        frontLightImage.setFitWidth(50);
        frontLightImage.setFitHeight(50);
        frontLightImage.setX(houseModel.getxAxis()  - 55);
        frontLightImage.setY(houseModel.getyAxis()  + (houseModel.getHeight()/2) + 30);

        backLightImage.setFitWidth(50);
        backLightImage.setFitHeight(50);
        backLightImage.setX(houseModel.getxAxis() + houseModel.getWidth() + 10);
        backLightImage.setY(houseModel.getyAxis() + (houseModel.getHeight()/2) + 30);

        bp.getChildren().addAll(frontDoorImage,frontLightImage,backDoorImage,backLightImage,frontDoorIsLock, backDoorIsLock);

        // Adding people outside of rooms (such as in the general area of the house and outside).
        for (UserModel userModel : userList) {

            Random r = new Random();
            Text userName = new Text("• " + userModel.getName() + " (ID: " + userModel.getId() + ")");

            /**
             * Complete if for Backyard
             */
            if (userModel.getCurrentLocation().equals("Front yard")) {
                int xLow = 0;
                int xHigh = (int) (0.8 * houseModel.getxAxis());
                int xResult = r.nextInt(xHigh - xLow) + xLow;

                int yLow = 0;
                int yHigh = (int) (0.8 * houseModel.getyAxis() + houseModel.getHeight());
                int yResult = r.nextInt(yHigh - yLow) + yLow;

                userName.setX(xResult);
                userName.setY(yResult);

                bp.getChildren().add(userName);
            }
             else if (userModel.getCurrentLocation().equals("Backyard")) {
                int xLow = houseModel.getxAxis() + houseModel.getWidth() + 10;
                int xHigh = houseModel.getxAxis() + houseModel.getWidth() + 70;
                int xResult = r.nextInt(xHigh - xLow) + xLow;

                int yLow = houseModel.getxAxis();
                int yHigh = houseModel.getyAxis() + houseModel.getHeight();
                int yResult = r.nextInt(yHigh - yLow) + yLow;

                userName.setX(xResult);
                userName.setY(yResult);

                bp.getChildren().add(userName);
            } else if (userModel.getCurrentLocation().equals("House")) {

                int xLow = (int) (0.1 * houseModel.getWidth());
                int xHigh = (int) (0.3 * houseModel.getWidth());
                int xResult = r.nextInt(xHigh - xLow) + xLow;

                int yLow = (int) (0.4 * houseModel.getHeight());
                int yHigh = (int) (0.6 * houseModel.getHeight());
                int yResult = r.nextInt(yHigh - yLow) + yLow;

                userName.setX(houseModel.getxAxis() + xResult);
                userName.setY(houseModel.getyAxis() + yResult);

                bp.getChildren().add(userName);
            }
        }
    }
}

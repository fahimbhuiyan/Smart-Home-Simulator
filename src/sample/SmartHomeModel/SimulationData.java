package sample.SmartHomeModel;

import org.json.simple.JSONObject;
import sample.SmartHomeModel.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SimulationData {

    RoomModel []roomArray;
    ArrayList<String> roomNameList = new ArrayList<>();
    ArrayList<UserModel> userList = new ArrayList<UserModel>();
    HouseModel houseModel;


    public void createData(String fileName) throws FileNotFoundException {

        System.out.println("Creating Data");
        createDefaultUser();
        createHouse();
        ReadHouseLayout rhm = new ReadHouseLayout();
        rhm.ReadJSON(fileName);

        roomArray = new RoomModel[rhm.getJsonArraySize()];

        JSONObject []jsonObjectArray = rhm.getHouseLayout();

        System.out.println("Creating Rooms");
        for(int i = 0; i < rhm.getJsonArraySize(); i++){

            String name = jsonObjectArray[i].get("name").toString();
            int width = Integer.parseInt(jsonObjectArray[i].get("width").toString());
            int height = Integer.parseInt(jsonObjectArray[i].get("height").toString());
            int xAxis = Integer.parseInt(jsonObjectArray[i].get("x-axis").toString());
            int yAxis = Integer.parseInt(jsonObjectArray[i].get("y-axis").toString());


            DoorModel door;
            if(!name.equals("House")) {
                 door = new DoorModel(generateId(), "RoomDoor");
            } else{
                 door = new DoorModel(generateId(), "HouseDoor");
            }
            LightModel light = new LightModel(generateId());
            WindowModel window = new WindowModel(generateId());
            RoomModel room = new RoomModel(generateId(),name,width,height,xAxis,yAxis,door,light,window);

            roomArray[i] = room;
            roomNameList.add(name);

            System.out.println("Room Id: "+room.getRoomID());
            System.out.println("Room Name: "+room.getName());
            System.out.println("Room Width: "+room.getWidth());
            System.out.println("Room Height: "+room.getHeight());
            System.out.println("Room x-axis: "+room.getxAxis());
            System.out.println("Room y-axis "+room.getyAxis());

            System.out.println("Room Light ID: "+room.getLight().getId());
            System.out.println("Room Light Status: "+room.getLight().isOpen());

            System.out.println("Room Door ID: "+room.getDoor().getId());
            System.out.println("Room Door is Open: "+room.getDoor().isOpen());
            System.out.println("Room Door is Lock: "+room.getDoor().isLocked());
            System.out.println("Room Door type: "+room.getDoor().getDoorType());

            System.out.println("Room Window ID: "+room.getWindow().getId());
            System.out.println("Room Window is open: "+room.getWindow().isOpen());
            System.out.println("Room Window has object: "+room.getWindow().HasObject());
            System.out.println();
        }
    }


    public void createDefaultUser(){

        UserModel defaultParent = new UserModel("Bob", 0,"Parent", "Kitchen");
        UserModel defaultChild = new UserModel("Daniel", 1, "Child", "Toilet");
        UserModel defaultGuest = new UserModel("Boris", 2,"Guest", "House");
        UserModel defaultStranger = new UserModel("Tony", 3, "Stranger", "Outside");
        userList.add(defaultParent);
        userList.add(defaultChild);
        userList.add(defaultGuest);
        userList.add(defaultStranger);

        System.out.println("Default Users are created");
        for(int i = 0; i < userList.size(); i++){
            System.out.println(userList.get(i).getId());
            System.out.println(userList.get(i).getName());
            System.out.println(userList.get(i).getUser_type());
            System.out.println();


        }



    }

    public void createHouse(){
        houseModel = new HouseModel(0,"",false);
        System.out.println("House model is created");
        System.out.println(houseModel.getLoggedUserName());
        System.out.println(houseModel.getOutsideTemp());
        System.out.println(houseModel.isSimulationActive());
        System.out.println();
    }



    public String generateId(){

        String uniqueID = java.util.UUID.randomUUID().toString();
        return uniqueID;

    }

    public RoomModel[] getRoomArray() {
        return roomArray;
    }

    public ArrayList<String> getRoomNameList() {
        return roomNameList;
    }

    public HouseModel getHouseModel(){
        return houseModel;
    }

    public ArrayList<UserModel> getUserArrayList() {
        return userList;
    }

}

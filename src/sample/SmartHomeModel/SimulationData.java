package sample.SmartHomeModel;

import org.json.simple.JSONObject;
import sample.SmartHomeModel.*;

public class SimulationData {

    RoomModel []roomArray;


    public void createData(String fileName) {

        ReadHouseLayout rhm = new ReadHouseLayout();
        rhm.ReadJSON(fileName);

        roomArray = new RoomModel[rhm.getJsonArraySize()];

        JSONObject []jsonObjectArray = rhm.getHouseLayout();

        for(int i = 0; i < rhm.getJsonArraySize(); i++){

            String name = jsonObjectArray[i].get("name").toString();
            int width = Integer.parseInt(jsonObjectArray[i].get("width").toString());
            int height = Integer.parseInt(jsonObjectArray[i].get("height").toString());
            int xAxis = Integer.parseInt(jsonObjectArray[i].get("x-axis").toString());
            int yAxis = Integer.parseInt(jsonObjectArray[i].get("y-axis").toString());


            DoorModel door = new DoorModel(generateId(), "roomDoor");
            LightModel light = new LightModel(generateId());
            WindowModel window = new WindowModel(generateId());
            RoomModel room = new RoomModel(generateId(),name,width,height,xAxis,yAxis,door,light,window);

            roomArray[i] = room;

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

    public String generateId(){

        String uniqueID = java.util.UUID.randomUUID().toString();
        return uniqueID;

    }

    public RoomModel[] getRoomArray() {
        return roomArray;
    }


}

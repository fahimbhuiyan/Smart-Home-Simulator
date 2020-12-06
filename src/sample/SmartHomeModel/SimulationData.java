package sample.SmartHomeModel;

import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for the data of the simulation.
 */
public class SimulationData {

    private Map<String, RoomModel> rooms;
    private ArrayList<UserModel> userList = new ArrayList<>();
    private HouseModel houseModel;
    private BufferedReader bufferedReader;


    /**
     * Create the data for the rooms, doors, windows, and lights.
     *
     * @param fileName the name of the house layout file
     * @throws IOException the io exception
     */
    public void createData(String fileName) throws IOException {

        System.out.println("Creating Data");

        ReadHouseLayout rhm = new ReadHouseLayout();
        rhm.ReadJSON(fileName);

        rooms = new HashMap<>();
        ArrayList<JSONObject> jsonObjectArray = rhm.getHouseLayout();

        System.out.println("Creating Rooms");
        //start at 1 so we don't include the house room inside the json file
        for(int i = 1; i < jsonObjectArray.size(); i++){

            String name = jsonObjectArray.get(i).get("name").toString();
            int width = Integer.parseInt(jsonObjectArray.get(i).get("width").toString());
            int height = Integer.parseInt(jsonObjectArray.get(i).get("height").toString());
            int xAxis = Integer.parseInt(jsonObjectArray.get(i).get("x-axis").toString());
            int yAxis = Integer.parseInt(jsonObjectArray.get(i).get("y-axis").toString());

            DoorModel door = new DoorModel(generateId(), name);
            LightModel light = new LightModel(generateId(), name);
            System.out.println(name+" light");
            WindowModel window = new WindowModel(generateId(), name);
            RoomModel room = new RoomModel(generateId(), name, width, height, xAxis, yAxis, door, light, window);

            rooms.put(name, room);

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

            System.out.println("Room Window ID: "+room.getWindow().getId());
            System.out.println("Room Window is open: "+room.getWindow().isOpen());
            System.out.println("Room Window has object: "+room.getWindow().hasObject());
            System.out.println();
        }

        createDefaultUsers();


        createHouse(Integer.parseInt(jsonObjectArray.get(0).get("width").toString()), Integer.parseInt(jsonObjectArray.get(0).get("height").toString()), Integer.parseInt(jsonObjectArray.get(0).get("x-axis").toString()), Integer.parseInt(jsonObjectArray.get(0).get("y-axis").toString()));
    }

    /**
     * Instantiate default users.
     */
    private void createDefaultUsers() throws IOException {

        loadExistingUser();

        if(userList.size() == 0){
            UserModel defaultParent = new UserModel("Bob", 0,"Parent", "Kitchen");
            UserModel defaultChild = new UserModel("Daniel", 1, "Child", "Garage");
            UserModel defaultGuest = new UserModel("Boris", 2,"Guest", "Bedroom");
            UserModel defaultStranger = new UserModel("Tony", 3, "Stranger", "Backyard");

            rooms.get("Kitchen").incrementNbPeople();
            rooms.get("Garage").incrementNbPeople();


            userList.add(defaultParent);
            userList.add(defaultChild);
            userList.add(defaultGuest);
            userList.add(defaultStranger);
        }


        System.out.println("Default Users are created");
        for (UserModel userModel : userList) {
            System.out.println(userModel.getId());
            System.out.println(userModel.getName());
            System.out.println(userModel.getUser_type());
            System.out.println(userModel.getCurrentLocation());
            System.out.println(userModel.getPreviousLocation());
            System.out.println();
        }
    }

    private void loadExistingUser() throws IOException{
        try {
            bufferedReader = new BufferedReader(new FileReader("Profiles.txt"));
            String currentLine;
            String [] profileArray = new String[4];
            currentLine = bufferedReader.readLine();

            while (currentLine.length() > 0) {
                profileArray = currentLine.split(",");
                System.out.println("Length"+profileArray.length);
                UserModel user = new UserModel(profileArray[0],Integer.parseInt(profileArray[1]),profileArray[2],profileArray[3]);

                try{
                    rooms.get(profileArray[3]).incrementNbPeople();
                }catch (NullPointerException e){
                    System.out.println("User does not start in a room.");
                }

                userList.add(user);
                currentLine = bufferedReader.readLine();

            }
        }
        catch (FileNotFoundException e){
            System.out.println("File was not found");
        }
        catch (NullPointerException e){
            System.out.println("Reached end of file");
        }
    }
    /**
     * Create the House Model.
     */
    private void createHouse(int width, int height, int xAxis, int yAxis){

        Map<String, LightModel> lights = new HashMap<>();
        Map<String, DoorModel> doors = new HashMap<>();
        Map<String, WindowModel> windows = new HashMap<>();

        lights.put("Front yard", new LightModel(generateId(), "Front yard"));
        lights.put("Backyard", new LightModel(generateId(), "Backyard"));

        doors.put("Front yard", new DoorModel(generateId(), "Front yard"));
        doors.put("Backyard", new DoorModel(generateId(), "Backyard"));

        for (String roomName : rooms.keySet()) {
            lights.put(roomName, rooms.get(roomName).getLight());
            System.out.println(roomName+"key");
            doors.put(roomName, rooms.get(roomName).getDoor());
            windows.put(roomName, rooms.get(roomName).getWindow());
        }

        houseModel = new HouseModel(0,"", rooms, lights, doors, windows, width, height, xAxis, yAxis);

        System.out.println("House model is created");
        System.out.println(houseModel.getLoggedUserName());
        System.out.println(houseModel.getOutsideTemp());
        System.out.println();
    }



    /**
     * Generate a randomized ID.
     *
     * @return the String uniqueID.
     */
    private String generateId(){
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * Getter for the house model.
     *
     * @return the HouseModel houseModel.
     */
    public HouseModel getHouseModel(){
        return houseModel;
    }

    /**
     * Getter for the array list of users.
     *
     * @return the userList.
     */
    public ArrayList<UserModel> getUserArrayList() {
        return userList;
    }

}

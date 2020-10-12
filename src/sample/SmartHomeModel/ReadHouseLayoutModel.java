package sample.SmartHomeModel;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadHouseLayoutModel {



    public void ReadJSON(String fileName){

        JSONParser parser = new JSONParser();



        try{
            Object obj = parser.parse(new FileReader(fileName));
            JSONObject jsonObj = (JSONObject) obj;

            JSONObject listInfo = (JSONObject) jsonObj.get("listOfRoom");
            //    System.out.println(listInfo.size());

            for(int i = 1; i <= listInfo.size(); i++){

                JSONObject roomInfo = (JSONObject) listInfo.get("room"+i);

                String name = (String) roomInfo.get("name").toString();
                String nbDoor = roomInfo.get("nbDoor").toString();
                String nbLight = roomInfo.get("nbLight").toString();
                String nbWindow = roomInfo.get("nbWindow").toString();

                System.out.println("Room name is: " +name);
                System.out.println("Number of Doors: " +nbDoor);
                System.out.println("Number of Lights: " +nbLight);
                System.out.println("Number of Windows: " +nbWindow);
                System.out.println("");

                // Instead, create the specific objects
            }




        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

package sample.SmartHomeModel;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadHouseLayout {


    JSONObject[] jsonObjectArray;


    public void ReadJSON(String fileName){

        JSONParser parser = new JSONParser();


        try{
            Object obj = parser.parse(new FileReader(fileName));
            JSONObject jsonObj = (JSONObject) obj;

            JSONObject listInfo = (JSONObject) jsonObj.get("listOfRoom");
            //    System.out.println(listInfo.size());

            jsonObjectArray = new JSONObject[listInfo.size()];

            for(int i = 1; i <= listInfo.size(); i++){

                JSONObject roomInfo = (JSONObject) listInfo.get("room"+i);

                String name = (String) roomInfo.get("name").toString();
                String nbDoor = roomInfo.get("nbDoor").toString();
                String nbLight = roomInfo.get("nbLight").toString();
                String nbWindow = roomInfo.get("nbWindow").toString();
                String width = roomInfo.get("width").toString();
                String height = roomInfo.get("height").toString();
                String xAxis = roomInfo.get("x-axis").toString();
                String yAxis = roomInfo.get("y-axis").toString();


                jsonObjectArray[i-1] = roomInfo;

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

    public JSONObject [] getHouseLayout(){

        return jsonObjectArray;
    }
    public int getJsonArraySize(){
        return jsonObjectArray.length;
    }

}

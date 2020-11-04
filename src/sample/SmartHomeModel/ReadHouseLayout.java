package sample.SmartHomeModel;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to read the house layout file.
 */
public class ReadHouseLayout {


    JSONObject[] jsonObjectArray;

    /**
     * Reads the JSON file.
     *
     * @param fileName the name of the file.
     */
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
    /**
     * Gets the house layout.
     *
     * @return the JSONObject []
     */
    public JSONObject [] getHouseLayout(){

        return jsonObjectArray;
    }

    /**
     * Gets the length of the JSON Object array.
     *
     * @return the int size of jsonObjectArray
     */
    public int getJsonArraySize(){

        return jsonObjectArray.length;
    }

}

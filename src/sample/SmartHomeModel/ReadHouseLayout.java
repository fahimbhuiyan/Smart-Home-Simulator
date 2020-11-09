package sample.SmartHomeModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to read the house layout file.
 */
public class ReadHouseLayout {

    private ArrayList<JSONObject> jsonObjectArray;

    /**
     * Reads the JSON file.
     *
     * @param fileName the name of the file.
     */
    void ReadJSON(String fileName){

        JSONParser parser = new JSONParser();

        try{
            Object obj = parser.parse(new FileReader(fileName));
            JSONObject jsonObj = (JSONObject) obj;

            JSONObject listInfo = (JSONObject) jsonObj.get("listOfRoom");
            //    System.out.println(listInfo.size());

            jsonObjectArray = new ArrayList<>();

            for(int i = 1; i <= listInfo.size(); i++){

                JSONObject roomInfo = (JSONObject) listInfo.get("room"+i);

                jsonObjectArray.add(roomInfo);

                // Instead, create the specific objects
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Gets the house layout.
     *
     * @return an ArrayList containing JSONObjects
     */
    ArrayList<JSONObject> getHouseLayout(){
        return jsonObjectArray;
    }
}

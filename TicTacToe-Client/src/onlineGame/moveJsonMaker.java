/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineGame;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author 20106
 */
public class moveJsonMaker {
    public static String createMoveJson(int position){
    
        return new String("{\"position\":"+position+"}");
    }
     public static int getPosition(String jsonString) throws ParseException{
        JSONObject position= (JSONObject) new JSONParser().parse(jsonString);
        position.get("position");
    
        return (int) position.get("position");
    }
}

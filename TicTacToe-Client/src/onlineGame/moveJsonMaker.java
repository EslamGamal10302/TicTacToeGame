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
        JSONObject positionJson= new JSONObject();
        positionJson.put("position", position);
        positionJson.put("type", 4);
        return positionJson.toString();
    }
     public static PlayerMove getMove(String jsonString) throws ParseException{
      
        JSONObject moveJson= (JSONObject) new JSONParser().parse(jsonString);
        
        long tempPosition = ((long)moveJson.get("position"));
        long winPosition1 = ((long)moveJson.get("winPosition1"));
        long winPosition2 = ((long)moveJson.get("winPosition2"));
        long playerTurn = ((long)moveJson.get("playerTurn"));
        
        
    
        return new PlayerMove(tempPosition, winPosition1,
               winPosition2, playerTurn,( (Long)moveJson.get("gameStat")).intValue());
    }
}

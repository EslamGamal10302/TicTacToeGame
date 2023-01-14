/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameHandler;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author 20106
 */
public class JsonDecoder {
    public static int getPosition(String jsonString) throws ParseException{
        JSONObject positionJson= (JSONObject) new JSONParser().parse(jsonString);
        Long temp =(long) positionJson.get("position");
        return temp.intValue();
    }
}

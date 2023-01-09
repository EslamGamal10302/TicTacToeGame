/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameHandler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author 20106
 */
public class RequestHandler extends Thread {
   //DataInputStream serverDataInput;
   //PrintStream serverDataOutput;
   static boolean playerExist = false;
   GameHandler gamelogic;
   static Vector<RequestHandler> clientsVector =
   new Vector<RequestHandler>();
   
   
   public RequestHandler(Socket clientSocket){
      
      
       //try {
          // serverDataInput = new DataInputStream(clientSocket.getInputStream());
           //serverDataOutput = new PrintStream(clientSocket.getOutputStream());
           //RequestHandler.clientsVector.add(this);
          // start();
       //} catch (IOException ex) {
        //   Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
       //}
       //if (!playerExist){
          // gamelogic =new GameHandler();
         //  gamelogic.setPalyer1(this);
        //   playerExist = true;
      // }else{
         //  gamelogic= clientsVector.get(0).gamelogic;
        //   gamelogic.setPalyer2(this);
      // }
   }
   @Override
   public void run(){
               
    while(true){
   
      //  try {
            
          //  BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(serverDataInput));
           // JSONObject positionJson= (JSONObject) new JSONParser().parse(clientBufferedReader.readLine());
            // String name =(String) positionJson.get("userName");
             //String password =(String) positionJson.get("password");
             //System.out.println(name);
             //System.out.println(password);
             //serverDataOutput.println("recevied");
            //int move = JsonDecoder.getPosition(clientBufferedReader.readLine());
            //String josnString = gamelogic.getJosnMassige(move);
            //sendMessageToAll(josnString);
    //    } catch (IOException ex) {
        //    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
      //  } catch (ParseException ex) {
          //  Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
    //    }
    }
   }
    // void sendMessageToAll(String msg){


       //  for(int i=0 ; i<clientsVector.size() ; i++){

         //clientsVector.get(i).serverDataOutput.println(msg);
        // }
     }

    
//}

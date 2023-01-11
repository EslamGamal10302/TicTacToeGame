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
public class PlayerHandler extends Thread {
   private DataInputStream serverDataInput;
   private PrintStream serverDataOutput;
   private String userName;
   private boolean closeThread;
   private GameHandler gamelogic;
   private static Vector<PlayerHandler> clientsVector = new Vector<PlayerHandler>();
   
   
   public PlayerHandler(Socket clientSocket,String userName){
      this.userName=userName;
      try {
         serverDataInput = new DataInputStream(clientSocket.getInputStream());
           serverDataOutput = new PrintStream(clientSocket.getOutputStream());
           PlayerHandler.clientsVector.add(this);
           start();
       } catch (IOException ex) {
           Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }
   @Override
   public void run(){
               
        while(true){

            try {

                BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(serverDataInput));
                JSONObject playerJson= (JSONObject) new JSONParser().parse(clientBufferedReader.readLine());
                int type = (int)playerJson.get("type");
                switch(type){
                    case 1:
                        challengePlayer(playerJson);
                        break;
                    case 2:
                        acceptResponse(playerJson);
                       
                        break; 
                    case 3:
                        rejectResponse(playerJson);
                       
                        break; 
                    case 4:
                        String turnMassige = gamelogic.getJosnMassige((int) playerJson.get("position"));
                        gamelogic.sendMassigeToPlayer(turnMassige);
                        break;
                    case 5:
                        break;
                }
                
            } catch (IOException ex) {
                Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
              Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   private PlayerHandler getPlayerHandler(String userName){
       for (PlayerHandler playerHandler : clientsVector) {
           if(playerHandler.userName.equals(userName)){
               return playerHandler;
           }
       }
       return null;
       
   }
    

    private void challengePlayer(JSONObject playerJson) {
        PlayerHandler challengedplayerHandler =   getPlayerHandler((String)playerJson.get("userName"));
        challengedplayerHandler.sendChallenge(userName);
    }

    private void sendChallenge(String userName) {
        JSONObject challengeJson =new JSONObject();
        challengeJson.put("type", 1);
        challengeJson.put("userName", userName); 
        serverDataOutput.println(challengeJson.toString());
    }

    private void acceptResponse(JSONObject playerJson) {
        PlayerHandler challengingplayerHandler = getPlayerHandler((String) playerJson.get("userName"));
        challengingplayerHandler.sendAccept();
        startGame(challengingplayerHandler, this);
    }

    private void sendAccept() {
        JSONObject acceptJson =new JSONObject();
        acceptJson.put("type", 2);
        serverDataOutput.println(acceptJson.toString());
        
    }
    private void startGame(PlayerHandler player1,PlayerHandler player2 ){
        gamelogic.setPlayer1(player1);
        gamelogic.setPlayer2(player2);
    }

    void sendTurn(String turnMassige) {
        serverDataOutput.print(turnMassige);
    }

    private void rejectResponse(JSONObject playerJson) {
      PlayerHandler challengingplayerHandler = getPlayerHandler((String) playerJson.get("userName"));
      challengingplayerHandler.sendReject();
    }

    private void sendReject() {
       JSONObject acceptJson =new JSONObject();
        acceptJson.put("type", 3);
        serverDataOutput.println(acceptJson.toString());
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameHandler;

import dataAccesslayer.Game;
import dataAccesslayer.GamesDAL;
import dataAccesslayer.Player;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tictactoe.server.DataAccessmethods;

/**
 *
 * @author 20106
 */
public class PlayerHandler extends Thread {

    private DataInputStream serverDataInput;
    private PrintStream serverDataOutput;
    public String userName;
    private boolean closeThread;
    private GameHandler gamelogic;
    private static Vector<PlayerHandler> clientsVector = new Vector<PlayerHandler>();
    private  ArrayList<Player> playersList = new ArrayList<Player>();
    private boolean isConected;

    public PlayerHandler(Socket clientSocket, String userName) {
        this.userName = userName;
        try {
            serverDataInput = new DataInputStream(clientSocket.getInputStream());
            serverDataOutput = new PrintStream(clientSocket.getOutputStream());
            PlayerHandler.clientsVector.add(this);
            sendPlayersListToAll();
            start();
            isConected=true;
        } catch (IOException ex) {
            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    @Override
    public void run() {
                        BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(serverDataInput));

        while (isConected) {
            System.out.println("New loop");

            try {

                String jsonString =clientBufferedReader.readLine();
                System.out.println(jsonString);
                JSONObject playerJson = (JSONObject) new JSONParser().parse(jsonString);
                int type = ((Long) playerJson.get("type")).intValue();
                System.out.println(type);
                System.out.println("//////////////////////////////////////////////////)");
                switch (type) {
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
                        String turnMassige = gamelogic.getJosnMassige(((Long) playerJson.get("position")).intValue());
                        System.out.println(turnMassige);
                        gamelogic.sendMassigeToPlayer(turnMassige);
                        break;
                    case 5:
                        clientsVector.remove(this);
                        isConected=false;
                        if(gamelogic!=null){
                            if (gamelogic.isGameDidStarted()){
                                gamelogic.didSurrender(this);
                             }
                        }
                        break;
                    case 6:
                        gamelogic.didSurrender(this);
                        break;
                    case 7:
                        sendRecordToUser();
                        break;
                    case 8:
                        gamelogic.setPlayerStates();
                        break;
                }

            } catch (IOException ex) {
                  clientsVector.remove(this);
                isConected=false;
                if(gamelogic!=null){
                if (gamelogic.isGameDidStarted()){
                    gamelogic.didSurrender(this);
                }
                }
                Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex.getCause());
            } catch (ParseException ex) {
                Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private PlayerHandler getPlayerHandler(String userName) {
        for (PlayerHandler playerHandler : clientsVector) {
            if (playerHandler.userName.equals(userName)) {
                return playerHandler;
            }
        }
        return null;

    }

    private void challengePlayer(JSONObject playerJson) {
        PlayerHandler challengedplayerHandler = getPlayerHandler((String) playerJson.get("userName"));
        challengedplayerHandler.sendChallenge(userName);
    }

    private void sendChallenge(String userName) {
        JSONObject challengeJson = new JSONObject();
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
        JSONObject acceptJson = new JSONObject();
        acceptJson.put("type", 2);
        serverDataOutput.println(acceptJson.toString());

    }

    private void startGame(PlayerHandler player1, PlayerHandler player2) {
        gamelogic =new GameHandler();
        gamelogic.setGameDidStarted(true);
        player1.gamelogic =gamelogic;
        gamelogic.setPlayer1(player1);
        gamelogic.setPlayer2(player2);
        int status=2;
        try {
            DataAccessmethods.inGame(status,player1.userName, player2.userName);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       // sendPlayersListToAll();
    }
    

    void sendTurn(String turnMassige) {
        serverDataOutput.println(turnMassige);
    }

    private void rejectResponse(JSONObject playerJson) {
        PlayerHandler challengingplayerHandler = getPlayerHandler((String) playerJson.get("userName"));
        challengingplayerHandler.sendReject();
    }

    private void sendReject() {
        JSONObject acceptJson = new JSONObject();
        acceptJson.put("type", 3);
        serverDataOutput.println(acceptJson.toString());
    }

    public void sendPlayersListToAll() {

        try {

            playersList = DataAccessmethods.getPlayersFromDatabase();
            for (int i = 0; i < clientsVector.size(); i++) {
                JSONArray array=new JSONArray();

                for (int j = 0; j < playersList.size(); j++) {
                    if (!clientsVector.get(i).userName.equals(playersList.get(j).getPlayerName())) {
                        JSONObject obj = new JSONObject();
                        obj.put("playerName", playersList.get(j).getPlayerName());
                        obj.put("status", playersList.get(j).getPlayerStat());
                        obj.put("no_game", playersList.get(j).getNo_games());
                        obj.put("score", playersList.get(j).getPlayerScore());
                        
                        array.add(obj);


                    }
                    JSONObject obj = new JSONObject();
                    obj.put("player", array);
                    obj.put("type", 4);
                    serverDataOutput.println(obj);


                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendRecordToUser() {
        try {
            ArrayList<Game> Games =GamesDAL.getGameFromDatabase(userName);
            JSONArray array=new JSONArray();
            for (Game Game : Games) {
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
}

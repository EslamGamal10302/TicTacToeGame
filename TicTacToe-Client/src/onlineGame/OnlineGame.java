/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineGame;

import gameBoard.OnlineGameBoardController;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javax.swing.text.Position;
import login.SocketClient;
import org.json.simple.parser.ParseException;

/**
 *
 * @author 20106
 */
public class OnlineGame {
    private Socket serverSocket;
    private DataInputStream clientInputStream ;
    private PrintStream clientOutputStream;
    private OnlineGameBoardController gameController;
    private String positionJson;
    private PlayerMove move;

    public OnlineGame(OnlineGameBoardController gameController) throws IOException {
        this.gameController = gameController;
        serverSocket = SocketClient.getInstant().getSocket();
        clientInputStream = new DataInputStream(serverSocket.getInputStream ());
        clientOutputStream = new PrintStream(serverSocket.getOutputStream ());   
        getMoveFromServer();
    }
    public void sendMoveToServer(int position){
       clientOutputStream.println(moveJsonMaker.createMoveJson(position));
       
    }

    private void getMoveFromServer() {
        new Thread(() -> {
            while (true) {   
                System.out.println("new loop");
                BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(clientInputStream));
                try {
                    String str = clientBufferedReader.readLine();
                    System.out.println(str);
                    move= moveJsonMaker.getMove(str);
                    
                     System.out.println(move);
                } catch (IOException ex) {
                    
                    Logger.getLogger(OnlineGame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(OnlineGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(move.getGameStat()==gameController.getPlayerTurn()){
                    Platform.runLater(() -> {
                        
                        gameController.drawLine(move.getWinPosition1(),move.getWinPosition2());
                        gameController.didWin();
                    });
                }else if(move.getGameStat()==3){
                    Platform.runLater(() -> {
                        gameController.didTie();
                    });
                }else if(move.getGameStat()==0){
                   gameController.changeTurn(move.getPlayerTurn()); 
                }else{
                    Platform.runLater(() -> {
                        
                        gameController.drawLine(move.getWinPosition1(),move.getWinPosition2());
                        gameController.didLose();
                    });
                }
                Platform.runLater(() -> {
                        gameController.setImage(move.getPosition());
                    });
            }
        }).start();

    }
    
    
}

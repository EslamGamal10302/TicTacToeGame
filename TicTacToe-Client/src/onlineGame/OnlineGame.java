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
    private int position;

    public OnlineGame(OnlineGameBoardController gameController) throws IOException {
        this.gameController = gameController;
        serverSocket = new Socket("127.0.0.1", 5005);
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
                BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(clientInputStream));
                try {
                    position= moveJsonMaker.getPosition(clientBufferedReader.readLine());
                } catch (IOException ex) {
                    Logger.getLogger(OnlineGame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(OnlineGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(() -> {
                    gameController.setImage(position);
                });
            }
        }).start();

    }
    
    
}

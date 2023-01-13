/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.client;

import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.SocketClient;
import org.json.simple.JSONObject;

/**
 *
 * @author Dell
 */
public class TicTacToeClient extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/welcome/home.fxml"));

        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        /*stage.setMinHeight(400);
        stage.setMinWidth(605);
        stage.setMaxHeight(400);
        stage.setMaxWidth(605);*/
        stage.setOnCloseRequest((event) -> {
            if(SocketClient.getInstant().isIsInitialized()){
                try {
                    PrintStream serverDataOutput = new PrintStream(SocketClient.getInstant().getSocket().getOutputStream());
                    JSONObject challengeJson= new JSONObject();
                    challengeJson.put("type",5);  
                    serverDataOutput.println(challengeJson.toString());
                    SocketClient.getInstant().CloseSocket();
                } catch (IOException ex) {
                    Logger.getLogger(TicTacToeClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        stage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

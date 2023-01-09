/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.server;

import gameHandler.RequestHandler;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Dell
 */
public class TicTacToeServer extends Application {
    ServerSocket serverSocket;
    DataInputStream serverDataInput;
   PrintStream serverDataOutput;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(5005);
                Socket clientSocket = serverSocket.accept();
                serverDataInput = new DataInputStream(clientSocket.getInputStream());
                serverDataOutput = new PrintStream(clientSocket.getOutputStream());
                BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(serverDataInput));
               JSONObject positionJson= (JSONObject) new JSONParser().parse(clientBufferedReader.readLine());
               String name =(String) positionJson.get("userName");
               String password =(String) positionJson.get("password");
               System.out.println(name);
               System.out.println(password);
             // int x=DataAccessmethods.login(positionJson);
             // to send the data to DB for login 
             //  DataAccessmethods.signUp(positionJson);
             //to send the data to DB to sign up 
                serverDataOutput.println("recevied");
                //new RequestHandler(clientSocket);
            }   catch (IOException | ParseException ex) {
             Logger.getLogger(TicTacToeServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
         
            
              
        }).start();

        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

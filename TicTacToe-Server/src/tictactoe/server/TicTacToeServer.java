/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.server;

import gameHandler.PlayerHandler;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
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
         while (true){
               Socket clientSocket = serverSocket.accept();
               serverDataInput = new DataInputStream(clientSocket.getInputStream());
               serverDataOutput = new PrintStream(clientSocket.getOutputStream());
               BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(serverDataInput));
               String client = clientBufferedReader.readLine();
               System.out.println(client);
               JSONObject positionJson= (JSONObject) new JSONParser().parse(client); 
                
                long type =(long) positionJson.get("type");
               if (type ==1){
                    /*String name_singUp =(String) positionJson.get("userName");
                    String email_signUp =(String) positionJson.get("email");
                    String password_signUp =(String) positionJson.get("password");
                   
                    System.out.println(name_singUp);
                    System.out.println(email_signUp);
                    System.out.println(password_signUp);*/
                    int status=1;
                    int checkUnique=DataAccessmethods.checkUnique(positionJson);
                    if (checkUnique==0){
                          int x =DataAccessmethods.signUp(positionJson,status);
                          System.out.println(x);
                          serverDataOutput.println("success_signup"); 
                          System.out.println("success_signup");
                    }
                    else{
                        serverDataOutput.println("username_notAvailable"); 
                        System.out.println("username_notAvailable");
                    }
               }
               else if (type==2){
                   String name_login =(String) positionJson.get("userName");
                   String password_login =(String) positionJson.get("password");
                   System.out.println(name_login);
                   System.out.println(password_login);
                   int x =DataAccessmethods.login(positionJson);
                   System.out.println(x);
                   if (x==1){
                       
                       
                      System.out.println("success"); 
                      serverDataOutput.println("success_login"); 
                      new PlayerHandler(clientSocket, name_login);
                   }
                   else{
                       System.out.println("fail login"); 
                      serverDataOutput.println("fail_login"); 
                   }
               }
               
             //  int x=DataAccessmethods.login(positionJson);
             // to send the data to DB for login 
             //  DataAccessmethods.signUp(positionJson);
             //to send the data to DB to sign up 
                //serverDataOutput.println("recevied"); 
            }
                //new RequestHandler(clientSocket);
            }   catch (IOException | ParseException ex) {
             Logger.getLogger(TicTacToeServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
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

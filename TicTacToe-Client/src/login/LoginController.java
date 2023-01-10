/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import jdk.nashorn.api.scripting.JSObject;
//import static login.SocketClient.dis;
//import static login.SocketClient.mySocket;
//import static login.SocketClient.ps;
import org.json.simple.JSONObject;
import tictactoe.client.Utility;
import welcome.HomeController;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
   
    @FXML
    private TextField userName;

   
    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Button signUp;
     
    @FXML
    private Text text_repeat;
    Socket mySocket;
    DataInputStream dis ;
    PrintStream ps;
      @FXML
    void loginButtonAction(ActionEvent event) {
        String name = userName.getText();
        String pass = password.getText();
        long type = 2;
        //Socket mySocket = SocketClient.getInstance() ;
        //SocketClient login = new SocketClient();
         try {
            // TODO
            mySocket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(mySocket.getInputStream ());
            ps = new PrintStream(mySocket.getOutputStream ());
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject obj = new JSONObject();
        obj.put("userName", name);
        obj.put("password", pass);
        obj.put("type", type);
       // String json=("{\"userName\":"+name+"},{\"password\":"+pass+"}");
        //System.out.println(json);
        ps.println(obj);
        System.out.println("1");
        new Thread(() -> {  
        try {
        String replyMsg = dis.readLine();
        System.out.println(replyMsg);
        if (replyMsg.equals("success_login")){
         
            Platform.runLater(() ->{
            try {
               text_repeat.setText(""); 
              Utility.changeTOScene(getClass(), event, "/playersList/PlayerListFXML.fxml");
             } 
            catch (Exception ex) {
              Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
               }
            });
        }
        else{
           Platform.runLater(() ->{
               text_repeat.setText("Repeat again"); 
            });
            
           
        }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         finally
    {
     try
    {
    ps.close();
    dis.close();
    mySocket.close();
    }
    catch(Exception ex)
    {
    ex.printStackTrace();
    }
    } }).start();
        //try {
         //   Utility.changeTOScene(getClass(), event, "/playersList/PlayerListFXML.fxml");
       // } catch (Exception ex) {
      //      Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
    //    } 
    }

    @FXML
    void signUpButtonAction(ActionEvent event) {
       try {
            Utility.changeTOScene(getClass(), event, "/login/signup.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        /*try {
            // TODO
            mySocket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(mySocket.getInputStream ());
            ps = new PrintStream(mySocket.getOutputStream ());
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }    
    
}

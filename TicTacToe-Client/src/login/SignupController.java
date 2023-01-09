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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import tictactoe.client.Utility;
import welcome.HomeController;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class SignupController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Button signUp;

    @FXML
    private TextField signUpUserName;

    @FXML
    private TextField email;

    @FXML
    private TextField signUpPassword;

    @FXML
    private TextField signUpConfirmPassword;
    Socket mySocket;
    DataInputStream dis ;
    PrintStream ps;                       
    @FXML
    void signUpButtonAction(ActionEvent event) {
        String name = signUpUserName.getText();
        String Email = email.getText();
        String pass = signUpPassword.getText();
        JSONObject obj = new JSONObject();
        obj.put("userName", name);
        obj.put("email", Email);
        obj.put("password", pass);
       // String json=("{\"userName\":"+name+"},{\"password\":"+pass+"}");
        //System.out.println(json);
        ps.println(obj);
        System.out.println("1");
        new Thread(() -> {  
        try {
        String replyMsg = dis.readLine();
        System.out.println(replyMsg);
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
       
// try {
       //     Utility.changeTOScene(getClass(), event, "/playersList/PlayerListFXML.fxml");
        //} catch (Exception ex) {
          //  Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        //} 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           try {
            // TODO
            mySocket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(mySocket.getInputStream ());
            ps = new PrintStream(mySocket.getOutputStream ());
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

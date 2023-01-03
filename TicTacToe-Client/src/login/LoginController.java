/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private TextField password;

    @FXML
    private Button login;

    @FXML
    private Button signUp;
      @FXML
    void loginButtonAction(ActionEvent event) {
        try {
            Utility.changeTOScene(getClass(), event, "/playersList/PlayerListFXML.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
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
        // TODO
    }    
    
}

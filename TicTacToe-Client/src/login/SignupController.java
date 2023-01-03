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
    void signUpButtonAction(ActionEvent event) {
      try {
            Utility.changeTOScene(getClass(), event, "/playersList/PlayerListFXML.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

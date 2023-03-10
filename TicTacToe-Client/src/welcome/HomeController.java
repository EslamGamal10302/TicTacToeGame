/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package welcome;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tictactoe.client.Utility;

/**
 * FXML Controller class
 *
 * @author Nada
 */

public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button ButtonSinglePlayer;

    @FXML
    private Button ButtonMultiPlayer;

    @FXML
    private Button ButtonOnlineMode;

    @FXML
    private ImageView ButtonHome;
    
  
     @FXML
    void exitGame(MouseEvent event) {
        Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    

    @FXML
    void startMultiPlayerGame(ActionEvent event) {
       try {
            Utility.changeTOScene(getClass(), event, "/gameBoard/TwoPlayersGameBoard.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void startOnlineGame(ActionEvent event) {
        System.out.println("active");
        try {
            Utility.changeTOScene(getClass(), event, "/login/login.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }

    @FXML
    void startSinglePlayreGame(ActionEvent event) {
        try {
            Utility.changeTOScene(getClass(), event, "/gameBoard/board.fxml");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      
    }    
    
    
    
    
    
    
    
}
